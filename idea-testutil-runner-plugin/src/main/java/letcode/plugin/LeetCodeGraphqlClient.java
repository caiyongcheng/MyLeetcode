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
    JsonObject fetchQuestionDetail(@NotNull String titleSlug) throws IOException {
        Map<String, String> variables = new LinkedHashMap<>();
        variables.put("titleSlug", titleSlug);
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

    private JsonObject postGraphql(String query, @Nullable Map<String, String> variables) throws IOException {
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

    private static String buildRequestBody(String query, @Nullable Map<String, String> variables) {
        StringBuilder json = new StringBuilder(128);
        json.append("{\"query\":").append(escapeJsonString(query));
        if (variables != null && !variables.isEmpty()) {
            json.append(",\"variables\":{");
            boolean first = true;
            for (Map.Entry<String, String> entry : variables.entrySet()) {
                if (!first) {
                    json.append(',');
                }
                first = false;
                json.append(escapeJsonString(entry.getKey())).append(':')
                        .append(escapeJsonString(entry.getValue()));
            }
            json.append('}');
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
