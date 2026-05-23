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
            throw new IOException("Daily question not found (activeDailyCodingChallengeQuestion is empty)");
        }
        JsonObject question = active.getAsJsonObject("question");
        if (question == null || question.isJsonNull()) {
            throw new IOException("Daily question object is empty");
        }
        String slug = textOrNull(question.get("titleSlug"));
        if (slug == null || slug.isEmpty()) {
            throw new IOException("Daily question titleSlug is empty");
        }
        return slug;
    }

    @NotNull
    private String fetchDailyTitleSlugFromTodayRecord(IOException activeDailyError) throws IOException {
        try {
            JsonObject data = postGraphql(CN_DAILY_SLUG_QUERY, null);
            JsonElement todayRecordEl = data.get("todayRecord");
            if (todayRecordEl == null || !todayRecordEl.isJsonArray() || todayRecordEl.getAsJsonArray().size() == 0) {
                throw new IOException("todayRecord is empty");
            }
            JsonObject record = todayRecordEl.getAsJsonArray().get(0).getAsJsonObject();
            JsonObject question = record.getAsJsonObject("question");
            if (question == null || question.isJsonNull()) {
                throw new IOException("todayRecord.question is empty");
            }
            String slug = firstNonEmpty(
                    textOrNull(question.get("titleSlug")),
                    textOrNull(question.get("questionTitleSlug"))
            );
            if (slug == null || slug.isEmpty()) {
                throw new IOException("todayRecord question titleSlug is empty");
            }
            return slug;
        } catch (IOException todayRecordError) {
            throw new IOException(
                    "fetch daily question failed. activeDailyCodingChallengeQuestion: "
                            + activeDailyError.getMessage()
                            + "; todayRecord: "
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
            throw new IOException("Question detail is empty: " + titleSlug);
        }
        return question;
    }

    /** 提交题解所需的内部 questionId（非 questionFrontendId）。 */
    @NotNull
    String fetchQuestionId(@NotNull String titleSlug) throws IOException {
        JsonObject question = fetchQuestionDetail(titleSlug);
        String questionId = jsonValueAsString(question.get("questionId"));
        if (questionId == null || questionId.isEmpty()) {
            throw new IOException("Question questionId is empty: " + titleSlug);
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
            connection.setRequestProperty("Content-Type", "application/json");
            applyHeaders(connection);
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

    private void applyHeaders(HttpURLConnection connection) {
        if (!settings.bearerToken.isEmpty()) {
            connection.setRequestProperty("Authorization", "Bearer " + settings.bearerToken.trim());
        }
        if (!settings.cookie.isEmpty()) {
            connection.setRequestProperty("Cookie", settings.cookie.trim());
        }
        if (!settings.csrfToken.isEmpty()) {
            connection.setRequestProperty("x-csrftoken", settings.csrfToken.trim());
        }
        for (Map.Entry<String, String> header : parseExtraHeaders(settings.extraHeaders).entrySet()) {
            connection.setRequestProperty(header.getKey(), header.getValue());
        }
    }

    static Map<String, String> parseExtraHeaders(String raw) {
        Map<String, String> headers = new LinkedHashMap<>();
        if (raw == null || raw.isEmpty()) {
            return headers;
        }
        String[] lines = raw.split("\\R");
        for (String line : lines) {
            if (line == null) {
                continue;
            }
            String trimmed = line.trim();
            if (trimmed.isEmpty()) {
                continue;
            }
            int colon = trimmed.indexOf(':');
            if (colon <= 0 || colon >= trimmed.length() - 1) {
                continue;
            }
            String name = trimmed.substring(0, colon).trim();
            String value = trimmed.substring(colon + 1).trim();
            if (!name.isEmpty()) {
                headers.put(name, value);
            }
        }
        return headers;
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

    private static String escapeJsonString(String value) {
        if (value == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder(value.length() + 16);
        sb.append('"');
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            switch (c) {
                case '"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    if (c < 0x20) {
                        sb.append(String.format("\\u%04x", (int) c));
                    } else {
                        sb.append(c);
                    }
            }
        }
        sb.append('"');
        return sb.toString();
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
