package letcode.plugin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

import static letcode.plugin.LeetCodeHttpHeaders.applyBrowserLikeHeaders;

/**
 * LeetCode GraphQL 客户端：leetcode.cn 公开读取优先走原生 HttpURLConnection，提交相关走认证路径。
 */
final class LeetCodeGraphqlClient {

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

    private final LeetCodeSettings settings;
    private final String baseUrl;

    LeetCodeGraphqlClient(@NotNull LeetCodeSettings settings) {
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
        String body = LeetCodeGraphqlBodies.buildGraphqlBody(query, variables);
        String graphqlUrl = settings.endpoint.trim();
        String pageUrl = resolvePageUrl(titleSlug);

        if (requireAuth && LeetCodeBrowserSession.canUseBrowserSession(settings)) {
            try {
                return parseData(postViaBrowser(pageUrl, graphqlUrl, body));
            } catch (IOException browserError) {
                if (LeetCodeLoginCookieRefresher.isAuthExpired(browserError)) {
                    LeetCodeBrowserSession.clearBrowserSession(settings);
                    throw browserError;
                }
                if (LeetCodeHttpHeaders.hasAuth(settings)) {
                    return parseData(postViaHttp(body, titleSlug, true));
                }
                throw browserError;
            }
        }

        if (!requireAuth && isCnEndpoint()) {
            try {
                return parseData(postViaHttp(body, titleSlug, false));
            } catch (IOException httpError) {
                if (LeetCodeBrowserHttpClient.isSupported()) {
                    try {
                        return parseData(postViaBrowser(pageUrl, graphqlUrl, body));
                    } catch (IOException browserError) {
                        httpError.addSuppressed(browserError);
                    }
                }
                throw httpError;
            }
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
        LeetCodeBrowserHttpClient.HttpResult result =
                LeetCodeBrowserHttpClient.postJson(pageUrl, graphqlUrl, body);
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
}
