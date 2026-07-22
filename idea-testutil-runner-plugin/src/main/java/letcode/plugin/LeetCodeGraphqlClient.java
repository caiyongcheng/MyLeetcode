package letcode.plugin;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.intellij.openapi.project.Project;
import com.google.gson.JsonParser;
import com.intellij.ui.jcef.JBCefBrowser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static letcode.plugin.LeetCodeHttpHeaders.applyBrowserLikeHeaders;

/**
 * LeetCode GraphQL 客户端：leetcode.cn 公开读取优先走原生 HttpURLConnection，提交相关走认证路径。
 */
final class LeetCodeGraphqlClient {

    private static final int QUESTION_LOOKUP_PAGE_SIZE = 100;
    private static final int QUESTION_LOOKUP_MAX_PAGES = 100;

    private static final String DAILY_SLUG_QUERY =
            "query { activeDailyCodingChallengeQuestion { question { titleSlug } } }";

    private static final String CN_DAILY_SLUG_QUERY =
            "query questionOfToday { todayRecord { question { questionTitleSlug titleSlug } } }";

    private static final String QUESTION_DETAIL_QUERY =
            "query questionData($titleSlug: String!) {"
                    + " question(titleSlug: $titleSlug) {"
                    + " questionId questionFrontendId title translatedTitle difficulty"
                    + " content translatedContent sampleTestCase exampleTestcases"
                    + " codeSnippets { lang langSlug code }"
                    + " } }";

    private static final String PROBLEMSET_LIST_QUERY =
            "query problemsetQuestionList($categorySlug: String!, $limit: Int!, $skip: Int!, $filters: QuestionListFilterInput) {"
                    + " problemsetQuestionList(categorySlug: $categorySlug, limit: $limit, skip: $skip, filters: $filters) {"
                    // LeetCode CN 已将 QuestionListNode 的 totalNum/data 改为 total/questions。
                    + " total"
                    + " questions { questionFrontendId: frontendQuestionId titleSlug difficulty paidOnly }"
                    + " } }";

    private final LeetCodeSettings settings;
    private final Project project;
    private final String baseUrl;

    LeetCodeGraphqlClient(@NotNull Project project, @NotNull LeetCodeSettings settings) {
        this.project = project;
        this.settings = settings;
        this.baseUrl = LeetCodeSubmitClient.resolveBaseUrl(settings.endpoint);
    }

    @NotNull
    String fetchDailyTitleSlug() throws IOException {
        if (isCnEndpoint()) {
            return fetchDailyTitleSlugFromTodayRecord();
        }
        return fetchDailyTitleSlugFromActiveDaily();
    }

    @NotNull
    private String fetchDailyTitleSlugFromActiveDaily() throws IOException {
        JsonObject data = postGraphqlPublic(DAILY_SLUG_QUERY, null, null);
        JsonObject active = data.getAsJsonObject("activeDailyCodingChallengeQuestion");
        if (active == null || active.isJsonNull()) {
            throw new IOException("未找到每日一题（activeDailyCodingChallengeQuestion 为空）");
        }
        JsonObject question = active.getAsJsonObject("question");
        if (question == null || question.isJsonNull()) {
            throw new IOException("每日一题对象为空");
        }
        String slug = textOrNull(question.get("titleSlug"));
        if (slug == null || slug.isEmpty()) {
            throw new IOException("每日一题 titleSlug 为空");
        }
        return slug;
    }

    @NotNull
    private String fetchDailyTitleSlugFromTodayRecord() throws IOException {
        JsonObject data = postGraphqlPublic(CN_DAILY_SLUG_QUERY, null, null);
        JsonElement todayRecordEl = data.get("todayRecord");
        if (todayRecordEl == null || !todayRecordEl.isJsonArray() || todayRecordEl.getAsJsonArray().size() == 0) {
            throw new IOException("todayRecord 为空");
        }
        JsonObject record = todayRecordEl.getAsJsonArray().get(0).getAsJsonObject();
        JsonObject question = record.getAsJsonObject("question");
        if (question == null || question.isJsonNull()) {
            throw new IOException("todayRecord.question 为空");
        }
        String slug = firstNonEmpty(
                textOrNull(question.get("titleSlug")),
                textOrNull(question.get("questionTitleSlug"))
        );
        if (slug == null || slug.isEmpty()) {
            throw new IOException("todayRecord 中 titleSlug 为空");
        }
        return slug;
    }

    private boolean isCnEndpoint() {
        return settings.endpoint != null && settings.endpoint.toLowerCase().contains("leetcode.cn");
    }

    @NotNull
    List<QuestionListItem> fetchProblemsetByDifficulty(@NotNull String difficulty,
                                                       int limit,
                                                       int skip) throws IOException {
        Map<String, String> variables = new LinkedHashMap<>();
        variables.put("categorySlug", "");
        Map<String, String> rawJsonVariables = new LinkedHashMap<>();
        rawJsonVariables.put("limit", String.valueOf(limit));
        rawJsonVariables.put("skip", String.valueOf(skip));
        rawJsonVariables.put("filters", "{\"difficulty\":\"" + difficulty + "\"}");

        return parseProblemsetList(postGraphqlPublic(PROBLEMSET_LIST_QUERY, variables, rawJsonVariables, null));
    }

    /**
     * 按前端题号检索题库列表项。
     *
     * LeetCode CN 的 searchKeywords 不支持题号检索，必须遍历题库页后精确匹配。
     */
    @NotNull
    QuestionListItem fetchQuestionListItemByFrontendId(@NotNull String frontendId) throws IOException {
        Map<String, String> variables = new LinkedHashMap<>();
        variables.put("categorySlug", "");
        for (int page = 0; page < QUESTION_LOOKUP_MAX_PAGES; page++) {
            Map<String, String> rawJsonVariables = new LinkedHashMap<>();
            rawJsonVariables.put("limit", String.valueOf(QUESTION_LOOKUP_PAGE_SIZE));
            rawJsonVariables.put("skip", String.valueOf(page * QUESTION_LOOKUP_PAGE_SIZE));
            rawJsonVariables.put("filters", "{}");

            List<QuestionListItem> items =
                    parseProblemsetList(postGraphqlPublic(PROBLEMSET_LIST_QUERY, variables, rawJsonVariables, null));
            for (QuestionListItem item : items) {
                if (frontendId.equals(item.questionFrontendId)) {
                    return item;
                }
            }
            if (items.size() < QUESTION_LOOKUP_PAGE_SIZE) {
                break;
            }
        }
        throw new IOException("未找到题号 " + frontendId + " 对应的题目");
    }

    @NotNull
    private static List<QuestionListItem> parseProblemsetList(@NotNull JsonObject data) throws IOException {
        JsonObject list = data.getAsJsonObject("problemsetQuestionList");
        if (list == null || list.isJsonNull()) {
            throw new IOException("problemsetQuestionList 为空");
        }
        JsonElement questionsEl = list.get("questions");
        if (questionsEl == null || questionsEl.isJsonNull()) {
            throw new IOException("problemsetQuestionList.questions 为空");
        }
        if (!questionsEl.isJsonArray()) {
            throw new IOException("problemsetQuestionList.questions 不是数组");
        }
        JsonArray questions = questionsEl.getAsJsonArray();
        List<QuestionListItem> items = new ArrayList<>();
        for (JsonElement element : questions) {
            if (!element.isJsonObject()) {
                continue;
            }
            JsonObject question = element.getAsJsonObject();
            // 中国站有时忽略 GraphQL 别名，直接返回原字段 frontendQuestionId。
            String frontendId = firstNonEmpty(
                    textOrNull(question.get("questionFrontendId")),
                    textOrNull(question.get("frontendQuestionId"))
            );
            String titleSlug = textOrNull(question.get("titleSlug"));
            if (titleSlug == null || titleSlug.isEmpty()) {
                continue;
            }
            boolean paidOnly = question.has("paidOnly")
                    && !question.get("paidOnly").isJsonNull()
                    && question.get("paidOnly").getAsBoolean();
            items.add(new QuestionListItem(frontendId, titleSlug, textOrNull(question.get("difficulty")), paidOnly));
        }
        return items;
    }

    @NotNull
    JsonObject fetchQuestionDetail(@NotNull String titleSlug) throws IOException {
        Map<String, String> variables = new LinkedHashMap<>();
        variables.put("titleSlug", titleSlug);
        JsonObject data = postGraphqlPublic(QUESTION_DETAIL_QUERY, variables, titleSlug);
        JsonObject question = data.getAsJsonObject("question");
        if (question == null || question.isJsonNull()) {
            throw new IOException("题目详情为空: " + titleSlug);
        }
        return question;
    }

    /** 提交题解所需的内部 questionId（非 questionFrontendId）。 */
    @NotNull
    String fetchQuestionId(@NotNull String titleSlug) throws IOException {
        Map<String, String> variables = new LinkedHashMap<>();
        variables.put("titleSlug", titleSlug);
        JsonObject data = postGraphqlAuthenticated(QUESTION_DETAIL_QUERY, variables, titleSlug);
        JsonObject question = data.getAsJsonObject("question");
        if (question == null || question.isJsonNull()) {
            throw new IOException("题目详情为空: " + titleSlug);
        }
        String questionId = jsonValueAsString(question.get("questionId"));
        if (questionId == null || questionId.isEmpty()) {
            throw new IOException("题目 questionId 为空: " + titleSlug);
        }
        return questionId;
    }

    @Nullable
    private static String jsonValueAsString(@Nullable JsonElement element) {
        if (element == null || element.isJsonNull()) {
            return null;
        }
        if (element.isJsonPrimitive()) {
            return element.getAsJsonPrimitive().getAsString();
        }
        return element.toString();
    }

    @NotNull
    private JsonObject postGraphqlPublic(String query,
                                         @Nullable Map<String, String> variables,
                                         @Nullable String titleSlug) throws IOException {
        return postGraphql(query, variables, titleSlug, false);
    }

    @NotNull
    private JsonObject postGraphqlPublic(String query,
                                         @Nullable Map<String, String> variables,
                                         @Nullable Map<String, String> rawJsonVariables,
                                         @Nullable String titleSlug) throws IOException {
        return postGraphql(query, variables, rawJsonVariables, titleSlug, false);
    }

    @NotNull
    private JsonObject postGraphqlAuthenticated(String query,
                                                @Nullable Map<String, String> variables,
                                                @Nullable String titleSlug) throws IOException {
        return postGraphql(query, variables, titleSlug, true);
    }

    @NotNull
    private JsonObject postGraphql(String query,
                                   @Nullable Map<String, String> variables,
                                   @Nullable String titleSlug,
                                   boolean requireAuth) throws IOException {
        return postGraphql(query, variables, null, titleSlug, requireAuth);
    }

    @NotNull
    private JsonObject postGraphql(String query,
                                   @Nullable Map<String, String> variables,
                                   @Nullable Map<String, String> rawJsonVariables,
                                   @Nullable String titleSlug,
                                   boolean requireAuth) throws IOException {
        String body = LeetCodeGraphqlBodies.buildGraphqlBody(query, variables, rawJsonVariables);
        String graphqlUrl = settings.endpoint.trim();
        String pageUrl = resolvePageUrl(titleSlug);

        if (requireAuth && LeetCodeBrowserSession.canUseBrowserSession(project, settings)) {
            try {
                return parseData(postViaBrowser(pageUrl, graphqlUrl, body));
            } catch (IOException browserError) {
                if (LeetCodeLoginCookieRefresher.isAuthExpired(browserError)) {
                    LeetCodeBrowserSession.clearBrowserSession(project, settings);
                    throw browserError;
                }
                if (LeetCodeHttpHeaders.hasAuth(settings)) {
                    return parseData(postViaHttp(body, titleSlug, true));
                }
                throw browserError;
            }
        }

        if (!requireAuth && isCnEndpoint()) {
            // 中国站直连可能拿到风控页或被 CDN 改写的响应，优先复用 IDE 的同源浏览器上下文。
            if (LeetCodeBrowserHttpClient.isSupported()) {
                try {
                    return parseData(postViaBrowser(pageUrl, graphqlUrl, body));
                } catch (IOException browserError) {
                    try {
                        return parseData(postViaHttp(body, titleSlug, false));
                    } catch (IOException httpError) {
                        browserError.addSuppressed(httpError);
                        throw browserError;
                    }
                }
            }
            return parseData(postViaHttp(body, titleSlug, false));
        }

        if (!requireAuth && LeetCodeBrowserHttpClient.isSupported()) {
            try {
                return parseData(postViaBrowser(pageUrl, graphqlUrl, body));
            } catch (IOException browserError) {
                return parseData(postViaHttp(body, titleSlug, false));
            }
        }

        if (requireAuth) {
            LeetCodeHttpHeaders.validateAuth(settings);
        }
        return parseData(postViaHttp(body, titleSlug, requireAuth));
    }

    @NotNull
    private String postViaBrowser(@NotNull String pageUrl,
                                  @NotNull String graphqlUrl,
                                  @NotNull String body) throws IOException {
        JBCefBrowser browser = LeetCodeBrowserSession.getActiveBrowser(project, settings);
        LeetCodeBrowserHttpClient.HttpResult result =
                browser == null
                        ? LeetCodeBrowserHttpClient.postJson(pageUrl, graphqlUrl, body)
                        : LeetCodeBrowserHttpClient.postJson(browser, graphqlUrl, body);
        if (result.status < 200 || result.status >= 300) {
            throw new IOException("HTTP " + result.status + ": " + truncate(result.body, 500));
        }
        return result.body;
    }

    @NotNull
    private String postViaHttp(@NotNull String body,
                               @Nullable String titleSlug,
                               boolean requireAuth) throws IOException {
        if (requireAuth) {
            LeetCodeHttpHeaders.validateAuth(settings);
        }
        HttpURLConnection connection = openConnection(settings.endpoint);
        try {
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            applyBrowserLikeHeaders(
                    connection,
                    settings,
                    baseUrl,
                    titleSlug,
                    "application/json"
            );
            byte[] payload = body.getBytes(StandardCharsets.UTF_8);
            try (OutputStream out = connection.getOutputStream()) {
                out.write(payload);
            }
            int status = connection.getResponseCode();
            String responseText = readResponse(connection, status);
            if (status < 200 || status >= 300) {
                throw new IOException("HTTP " + status + ": " + truncate(responseText, 500));
            }
            return responseText;
        } finally {
            connection.disconnect();
        }
    }

    @NotNull
    private String resolvePageUrl(@Nullable String titleSlug) {
        if (titleSlug != null && !titleSlug.isEmpty()) {
            return baseUrl + "/problems/" + titleSlug + "/";
        }
        return baseUrl + "/problemset/";
    }

    private static HttpURLConnection openConnection(String endpoint) throws IOException {
        URL url = new URL(endpoint.trim());
        return (HttpURLConnection) url.openConnection();
    }

    private static JsonObject parseData(String responseText) throws IOException {
        JsonElement root = new JsonParser().parse(responseText);
        if (!root.isJsonObject()) {
            throw new IOException("GraphQL response is not a JSON object");
        }
        JsonObject obj = root.getAsJsonObject();
        if (obj.has("errors") && obj.get("errors").isJsonArray() && obj.getAsJsonArray("errors").size() > 0) {
            throw new IOException("GraphQL errors: " + obj.getAsJsonArray("errors"));
        }
        JsonElement dataEl = obj.get("data");
        if (dataEl == null || !dataEl.isJsonObject()) {
            throw new IOException("GraphQL response missing data");
        }
        return dataEl.getAsJsonObject();
    }

    private static String readResponse(HttpURLConnection connection, int status) throws IOException {
        InputStream stream = status >= 400 ? connection.getErrorStream() : connection.getInputStream();
        if (stream == null) {
            return "";
        }
        byte[] buf = stream.readAllBytes();
        return new String(buf, StandardCharsets.UTF_8);
    }

    @Nullable
    static String textOrNull(JsonElement element) {
        if (element == null || element.isJsonNull()) {
            return null;
        }
        if (element.isJsonPrimitive()) {
            return element.getAsString();
        }
        return element.toString();
    }

    @Nullable
    private static String firstNonEmpty(@Nullable String first, @Nullable String second) {
        if (first != null && !first.trim().isEmpty()) {
            return first.trim();
        }
        if (second != null && !second.trim().isEmpty()) {
            return second.trim();
        }
        return null;
    }

    private static String truncate(String text, int max) {
        if (text == null) {
            return "";
        }
        if (text.length() <= max) {
            return text;
        }
        return text.substring(0, max) + "...";
    }

    static final class QuestionListItem {
        final String questionFrontendId;
        final String titleSlug;
        final String difficulty;
        final boolean paidOnly;

        QuestionListItem(@Nullable String questionFrontendId,
                         @NotNull String titleSlug,
                         @Nullable String difficulty,
                         boolean paidOnly) {
            this.questionFrontendId = questionFrontendId;
            this.titleSlug = titleSlug;
            this.difficulty = difficulty;
            this.paidOnly = paidOnly;
        }
    }
}
