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

/**
 * LeetCode 题解提交与判题结果轮询。
 */
final class LeetCodeSubmitClient {

    private static final long POLL_INTERVAL_MS = 2_000L;
    private static final long POLL_TIMEOUT_MS = 120_000L;

    private final LeetCodeSettings settings;
    private final String baseUrl;

    LeetCodeSubmitClient(@NotNull LeetCodeSettings settings) {
        this.settings = settings;
        this.baseUrl = resolveBaseUrl(settings.endpoint);
    }

    @NotNull
    String submit(@NotNull String titleSlug, @NotNull String questionId, @NotNull String typedCode) throws IOException {
        LeetCodeHttpHeaders.validateAuth(settings);
        String url = baseUrl + "/problems/" + titleSlug + "/submit/";
        String body = LeetCodeHttpHeaders.buildSubmitJsonBody(questionId, typedCode, "java");
        HttpURLConnection connection = openPost(url, titleSlug);
        try {
            byte[] payload = body.getBytes(StandardCharsets.UTF_8);
            try (OutputStream out = connection.getOutputStream()) {
                out.write(payload);
            }
            int status = connection.getResponseCode();
            String responseText = readResponse(connection, status);
            if (status < 200 || status >= 300) {
                throw new IOException("提交失败 HTTP " + status + ": " + truncate(responseText, 500));
            }
            return parseSubmissionId(responseText, status);
        } finally {
            connection.disconnect();
        }
    }

    @NotNull
    LeetCodeSubmissionResult pollUntilDone(@NotNull String submissionId) throws IOException {
        long deadline = System.currentTimeMillis() + POLL_TIMEOUT_MS;
        IOException lastError = null;
        while (System.currentTimeMillis() < deadline) {
            try {
                JsonObject check = fetchCheck(submissionId);
                String state = LeetCodeGraphqlClient.textOrNull(check.get("state"));
                if (state == null || state.isEmpty()) {
                    state = LeetCodeGraphqlClient.textOrNull(check.get("status"));
                }
                if (isPending(state)) {
                    sleepQuietly(POLL_INTERVAL_MS);
                    continue;
                }
                return LeetCodeSubmissionResult.fromCheckResponse(check, submissionId);
            } catch (IOException ex) {
                lastError = ex;
                sleepQuietly(POLL_INTERVAL_MS);
            }
        }
        if (lastError != null) {
            throw new IOException("判题超时（120s）: " + lastError.getMessage(), lastError);
        }
        throw new IOException("判题超时（120s），请稍后在 LeetCode 网站查看提交记录");
    }

    private JsonObject fetchCheck(@NotNull String submissionId) throws IOException {
        String url = baseUrl + "/submissions/detail/" + submissionId + "/check/";
        HttpURLConnection connection = openGet(url);
        try {
            int status = connection.getResponseCode();
            String responseText = readResponse(connection, status);
            if (status < 200 || status >= 300) {
                throw new IOException("轮询判题 HTTP " + status + ": " + truncate(responseText, 500));
            }
            JsonElement root = new JsonParser().parse(responseText);
            if (!root.isJsonObject()) {
                throw new IOException("判题响应不是 JSON 对象");
            }
            return root.getAsJsonObject();
        } finally {
            connection.disconnect();
        }
    }

    private HttpURLConnection openPost(String url, String titleSlug) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setInstanceFollowRedirects(true);
        LeetCodeHttpHeaders.applyBrowserLikeHeaders(
                connection,
                settings,
                baseUrl,
                titleSlug,
                "application/json; charset=UTF-8"
        );
        return connection;
    }

    private HttpURLConnection openGet(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setInstanceFollowRedirects(true);
        LeetCodeHttpHeaders.applyBrowserLikeHeaders(connection, settings, baseUrl, null, null);
        return connection;
    }

    private static boolean isPending(@Nullable String state) {
        if (state == null || state.isEmpty()) {
            return true;
        }
        String upper = state.toUpperCase();
        return "PENDING".equals(upper)
                || "STARTED".equals(upper)
                || "RUNNING".equals(upper)
                || "JUDGING".equals(upper)
                || "QUEUED".equals(upper);
    }

    private static String parseSubmissionId(String responseText, int httpStatus) throws IOException {
        JsonElement root;
        try {
            root = new JsonParser().parse(responseText);
        } catch (Exception ex) {
            throw new IOException(
                    "提交响应不是有效 JSON（HTTP " + httpStatus + "）: " + truncate(responseText, 300),
                    ex
            );
        }
        if (!root.isJsonObject()) {
            throw new IOException(
                    "提交响应不是 JSON 对象（HTTP " + httpStatus + "）: " + truncate(responseText, 300)
            );
        }
        JsonObject obj = root.getAsJsonObject();
        String id = LeetCodeGraphqlClient.textOrNull(obj.get("submission_id"));
        if (id == null || id.isEmpty()) {
            id = LeetCodeGraphqlClient.textOrNull(obj.get("submissionId"));
        }
        if (id != null && !id.isEmpty()) {
            return id;
        }
        if (obj.has("error") && !obj.get("error").isJsonNull()) {
            throw new IOException("提交失败: " + obj.get("error"));
        }
        throw new IOException(
                "提交响应缺少 submission_id（HTTP " + httpStatus + "）: " + truncate(responseText, 300)
        );
    }

    @NotNull
    static String resolveBaseUrl(@NotNull String endpoint) {
        String trimmed = endpoint.trim();
        if (trimmed.isEmpty()) {
            return "https://leetcode.cn";
        }
        int schemeEnd = trimmed.indexOf("://");
        if (schemeEnd < 0) {
            return "https://leetcode.cn";
        }
        int hostStart = schemeEnd + 3;
        int pathStart = trimmed.indexOf('/', hostStart);
        if (pathStart < 0) {
            return trimmed;
        }
        return trimmed.substring(0, pathStart);
    }

    private static String readResponse(HttpURLConnection connection, int status) throws IOException {
        InputStream stream = status >= 400 ? connection.getErrorStream() : connection.getInputStream();
        if (stream == null) {
            return "";
        }
        return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
    }

    private static void sleepQuietly(long millis) throws IOException {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new IOException("判题轮询被中断", ex);
        }
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
