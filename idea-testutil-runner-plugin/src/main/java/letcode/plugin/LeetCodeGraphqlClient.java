package letcode.plugin;

import com.google.gson.JsonArray;
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
import java.util.concurrent.ThreadLocalRandom;

import static letcode.plugin.LeetCodeHttpHeaders.applyBrowserLikeHeaders;
import static letcode.plugin.LeetCodeHttpHeaders.escapeJsonString;

/**
 * LeetCode GraphQL 客户端：按项目配置组装请求头并解析响应。
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

    private static final String CN_PROBLEM_LIST_QUERY =
            "query problemsetQuestionList($limit: Int, $skip: Int, $filters: QuestionListFilterInput) {"
                    + " problemsetQuestionList(categorySlug: \"\", limit: $limit, skip: $skip, filters: $filters) {"
                    + " total questions { titleSlug paidOnly } } }";

    private static final String GLOBAL_PROBLEM_LIST_QUERY =
            "query problemsetQuestionList($limit: Int, $skip: Int, $filters: QuestionListFilterInput) {"
                    + " problemsetQuestionList: questionList(categorySlug: \"\", limit: $limit, skip: $skip, filters: $filters) {"
                    + " total: totalNum questions: data { titleSlug paidOnly: isPaidOnly } } } }";

    private static final int RANDOM_PICK_ATTEMPTS = 12;

    private final LeetCodeSettings settings;

    LeetCodeGraphqlClient(@NotNull LeetCodeSettings settings) {
        this.settings = settings;
    }

    @NotNull
    String fetchDailyTitleSlug() throws IOException {
        try {
            return fetchDailyTitleSlugFromActiveDaily();
        } catch (IOException activeDailyError) {
            if (!isCnEndpoint()) {
                throw activeDailyError;
            }
            return fetchDailyTitleSlugFromTodayRecord(activeDailyError);
        }
    }

    @NotNull
    private String fetchDailyTitleSlugFromActiveDaily() throws IOException {
        JsonObject data = postGraphql(DAILY_SLUG_QUERY, null);
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
    private String fetchDailyTitleSlugFromTodayRecord(IOException activeDailyError) throws IOException {
        try {
            JsonObject data = postGraphql(CN_DAILY_SLUG_QUERY, null);
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
        } catch (IOException todayRecordError) {
            throw new IOException(
                    "获取每日一题失败。activeDailyCodingChallengeQuestion: "
                            + activeDailyError.getMessage()
                            + "；todayRecord: "
                            + todayRecordError.getMessage(),
                    todayRecordError
            );
        }
    }

    private boolean isCnEndpoint() {
        return settings.endpoint != null && settings.endpoint.toLowerCase().contains("leetcode.cn");
    }

    @NotNull
    String fetchRandomTitleSlug(@NotNull LeetCodeDifficulty difficulty) throws IOException {
        String filter = difficulty.filterValue(isCnEndpoint());
        int total = fetchProblemListTotal(filter);
        if (total <= 0) {
            throw new IOException("未找到符合条件的题目");
        }
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int attempt = 0; attempt < RANDOM_PICK_ATTEMPTS; attempt++) {
            int skip = random.nextInt(total);
            String slug = fetchProblemSlugAt(filter, skip);
            if (slug != null) {
                return slug;
            }
        }
        throw new IOException("随机题目均为会员题或获取失败，请重试");
    }

    @NotNull
    JsonObject fetchQuestionDetail(@NotNull String titleSlug) throws IOException {
        JsonObject variables = new JsonObject();
        variables.addProperty("titleSlug", titleSlug);
        JsonObject data = postGraphql(QUESTION_DETAIL_QUERY, variables);
        JsonObject question = data.getAsJsonObject("question");
        if (question == null || question.isJsonNull()) {
            throw new IOException("题目详情为空: " + titleSlug);
        }
        return question;
    }

    /** 提交题解所需的内部 questionId（非 questionFrontendId）。 */
    @NotNull
    String fetchQuestionId(@NotNull String titleSlug) throws IOException {
        JsonObject question = fetchQuestionDetail(titleSlug);
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

    private int fetchProblemListTotal(@Nullable String difficultyFilter) throws IOException {
        JsonObject list = fetchProblemListNode(difficultyFilter, 1, 0);
        JsonElement totalEl = list.get("total");
        if (totalEl == null || totalEl.isJsonNull()) {
            totalEl = list.get("total");
        }
        if (totalEl != null && totalEl.isJsonPrimitive()) {
            return totalEl.getAsInt();
        }
        throw new IOException("无法获取题目总数");
    }

    @Nullable
    private String fetchProblemSlugAt(@Nullable String difficultyFilter, int skip) throws IOException {
        JsonObject list = fetchProblemListNode(difficultyFilter, 1, skip);
        JsonArray questions = extractQuestions(list);
        if (questions == null || questions.size() == 0) {
            return null;
        }
        JsonObject question = questions.get(0).getAsJsonObject();
        if (isPaidOnly(question)) {
            return null;
        }
        return textOrNull(question.get("titleSlug"));
    }

    @NotNull
    private JsonObject fetchProblemListNode(@Nullable String difficultyFilter, int limit, int skip) throws IOException {
        JsonObject variables = new JsonObject();
        variables.addProperty("limit", limit);
        variables.addProperty("skip", skip);
        JsonObject filters = new JsonObject();
        if (difficultyFilter != null && !difficultyFilter.isEmpty()) {
            filters.addProperty("difficulty", difficultyFilter);
        }
        variables.add("filters", filters);
        String query = isCnEndpoint() ? CN_PROBLEM_LIST_QUERY : GLOBAL_PROBLEM_LIST_QUERY;
        JsonObject data = postGraphql(query, variables);
        JsonObject list = data.getAsJsonObject("problemsetQuestionList");
        if (list == null || list.isJsonNull()) {
            throw new IOException("题目列表为空");
        }
        return list;
    }

    @Nullable
    private static JsonArray extractQuestions(@NotNull JsonObject list) {
        JsonElement questionsEl = list.get("questions");
        if (questionsEl == null || questionsEl.isJsonNull()) {
            return null;
        }
        if (questionsEl.isJsonArray()) {
            return questionsEl.getAsJsonArray();
        }
        if (questionsEl.isJsonObject()) {
            JsonElement dataEl = questionsEl.getAsJsonObject().get("data");
            if (dataEl != null && dataEl.isJsonArray()) {
                return dataEl.getAsJsonArray();
            }
        }
        return null;
    }

    private static boolean isPaidOnly(@NotNull JsonObject question) {
        JsonElement paidOnly = question.get("paidOnly");
        return paidOnly != null && paidOnly.isJsonPrimitive() && paidOnly.getAsBoolean();
    }

    private JsonObject postGraphql(String query, @Nullable JsonObject variables) throws IOException {
        String body = buildRequestBody(query, variables);
        HttpURLConnection connection = openConnection(settings.endpoint);
        try {
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            applyBrowserLikeHeaders(
                    connection,
                    settings,
                    LeetCodeSubmitClient.resolveBaseUrl(settings.endpoint),
                    null,
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
            return parseData(responseText);
        } finally {
            connection.disconnect();
        }
    }

    private static HttpURLConnection openConnection(String endpoint) throws IOException {
        URL url = new URL(endpoint.trim());
        return (HttpURLConnection) url.openConnection();
    }

    private static String buildRequestBody(String query, @Nullable JsonObject variables) {
        StringBuilder json = new StringBuilder(128);
        json.append("{\"query\":").append(escapeJsonString(query));
        if (variables != null) {
            json.append(",\"variables\":").append(variables);
        }
        json.append('}');
        return json.toString();
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
