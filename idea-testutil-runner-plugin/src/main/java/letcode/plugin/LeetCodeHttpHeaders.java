package letcode.plugin;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/**
 * LeetCode HTTP 请求头：解析 F12 粘贴内容、合并 Cookie/CSRF、设置浏览器风格头。
 */
final class LeetCodeHttpHeaders {

    private static final String DEFAULT_USER_AGENT =
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 "
                    + "(KHTML, like Gecko) Chrome/150.0.0.0 Safari/537.36 Edg/150.0.0.0";

    private LeetCodeHttpHeaders() {
    }

    /** 解析 Extra Headers；键为小写，便于大小写不敏感查找。 */
    @NotNull
    static Map<String, String> parseExtraHeaders(@Nullable String raw) {
        Map<String, String> headers = new LinkedHashMap<>();
        if (raw == null || raw.isEmpty()) {
            return headers;
        }
        for (String line : raw.split("\\R")) {
            if (line == null) {
                continue;
            }
            String trimmed = line.trim();
            if (trimmed.isEmpty() || isIgnorableLine(trimmed)) {
                continue;
            }
            int colon = trimmed.indexOf(':');
            if (colon <= 0 || colon >= trimmed.length() - 1) {
                continue;
            }
            String name = trimmed.substring(0, colon).trim();
            String value = trimmed.substring(colon + 1).trim();
            if (name.isEmpty() || name.startsWith(":") || isUnsafeHeader(name)) {
                continue;
            }
            headers.put(name.toLowerCase(Locale.ROOT), value);
        }
        return headers;
    }

    @Nullable
    static String headerValue(@Nullable String rawExtraHeaders, @NotNull String headerName) {
        String value = parseExtraHeaders(rawExtraHeaders).get(headerName.toLowerCase(Locale.ROOT));
        return value == null || value.isEmpty() ? null : value;
    }

    @NotNull
    static String effectiveCookie(@NotNull LeetCodeSettings settings) {
        String cookie = settings.cookie == null ? "" : settings.cookie.trim();
        if (!cookie.isEmpty()) {
            return cookie;
        }
        String fromExtra = headerValue(settings.extraHeaders, "cookie");
        return fromExtra == null ? "" : fromExtra;
    }

    @NotNull
    static String effectiveCsrf(@NotNull LeetCodeSettings settings) {
        String csrf = settings.csrfToken == null ? "" : settings.csrfToken.trim();
        if (!csrf.isEmpty()) {
            return csrf;
        }
        String fromExtra = firstNonEmpty(
                headerValue(settings.extraHeaders, "x-csrftoken"),
                headerValue(settings.extraHeaders, "csrftoken")
        );
        if (fromExtra != null) {
            return fromExtra;
        }
        return extractCsrfFromCookie(effectiveCookie(settings));
    }

    static boolean hasAuth(@NotNull LeetCodeSettings settings) {
        if (settings.bearerToken != null && !settings.bearerToken.trim().isEmpty()) {
            return true;
        }
        return hasLeetCodeSession(effectiveCookie(settings));
    }

    static boolean hasCsrf(@NotNull LeetCodeSettings settings) {
        if (!effectiveCsrf(settings).isEmpty()) {
            return true;
        }
        return cookieContainsCsrf(effectiveCookie(settings));
    }

    static void validateAuth(@NotNull LeetCodeSettings settings) throws IOException {
        if (!hasAuth(settings)) {
            throw new IOException(
                    "未配置登录信息。请填写 Cookie，或将浏览器 F12 Network 中请求的 Request Headers "
                            + "整段粘贴到 Extra Headers（每行「名称: 值」），也可配置 Bearer Token。"
            );
        }
        if ((settings.bearerToken == null || settings.bearerToken.trim().isEmpty())
                && !hasLeetCodeSession(effectiveCookie(settings))) {
            throw new IOException("Cookie 缺少 LEETCODE_SESSION，请重新登录 LeetCode。");
        }
        if (!hasCsrf(settings)) {
            throw new IOException(
                    "未配置 CSRF Token。请填写 CSRF Token 字段，或在 Extra Headers 中包含 x-csrftoken，"
                            + "或确保 Cookie 中含有 csrftoken。"
            );
        }
    }

    /**
     * 设置认证与浏览器风格请求头；Extra Headers 中已有的同名头优先于默认值。
     */
    static void applyBrowserLikeHeaders(
            @NotNull HttpURLConnection connection,
            @NotNull LeetCodeSettings settings,
            @NotNull String baseUrl,
            @Nullable String titleSlug,
            @Nullable String contentType
    ) {
        Map<String, String> headers = new LinkedHashMap<>(parseExtraHeaders(settings.extraHeaders));

        if (settings.bearerToken != null && !settings.bearerToken.trim().isEmpty()) {
            headers.put("authorization", "Bearer " + settings.bearerToken.trim());
        }

        String cookie = effectiveCookie(settings);
        if (!cookie.isEmpty()) {
            headers.put("cookie", cookie);
        }

        String csrf = effectiveCsrf(settings);
        if (!csrf.isEmpty()) {
            headers.put("x-csrftoken", csrf);
        }

        putIfAbsent(headers, "accept", "application/json, text/plain, */*");
        putIfAbsent(headers, "user-agent", DEFAULT_USER_AGENT);
        putIfAbsent(headers, "origin", baseUrl);
        if (titleSlug != null && !titleSlug.isEmpty()) {
            putIfAbsent(headers, "referer", baseUrl + "/problems/" + titleSlug + "/");
        } else {
            putIfAbsent(headers, "referer", baseUrl + "/problemset/");
        }
        putIfAbsent(headers, "random-uuid", UUID.randomUUID().toString());

        if (contentType != null && !contentType.isEmpty()) {
            headers.put("content-type", contentType);
        }

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            if (isUnsafeHeader(entry.getKey())) {
                continue;
            }
            connection.setRequestProperty(toCanonicalHeaderName(entry.getKey()), entry.getValue());
        }
    }

    @NotNull
    static String escapeJsonString(@Nullable String value) {
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

    @NotNull
    static String buildSubmitJsonBody(
            @NotNull String questionId,
            @NotNull String typedCode,
            @NotNull String lang
    ) {
        return "{"
                + "\"lang\":" + escapeJsonString(lang)
                + ",\"question_id\":" + escapeJsonString(questionId)
                + ",\"typed_code\":" + escapeJsonString(typedCode)
                + "}";
    }

    private static boolean isIgnorableLine(@NotNull String trimmed) {
        String upper = trimmed.toUpperCase(Locale.ROOT);
        if (upper.startsWith("POST ") || upper.startsWith("GET ")
                || upper.startsWith("PUT ") || upper.startsWith("PATCH ")
                || upper.startsWith("DELETE ") || upper.startsWith("OPTIONS ")) {
            if (upper.contains(" HTTP/1.") || upper.contains(" HTTP/2")) {
                return true;
            }
        }
        if (trimmed.startsWith("HTTP/1.") || trimmed.startsWith("HTTP/2")) {
            return true;
        }
        int colon = trimmed.indexOf(':');
        if (colon <= 0) {
            return true;
        }
        String nameLower = trimmed.substring(0, colon).trim().toLowerCase(Locale.ROOT);
        return isMetadataHeader(nameLower);
    }

    private static boolean isMetadataHeader(@NotNull String nameLower) {
        switch (nameLower) {
            case "request url":
            case "status code":
            case "remote address":
            case "referrer policy":
            case "response headers":
            case "request headers":
            case "general":
                return true;
            default:
                return false;
        }
    }

    static boolean isUnsafeHeader(@NotNull String name) {
        String lower = name.toLowerCase(Locale.ROOT);
        if ("host".equals(lower) || "content-length".equals(lower)
                || "connection".equals(lower) || "accept-encoding".equals(lower)
                || "transfer-encoding".equals(lower) || "upgrade".equals(lower)) {
            return true;
        }
        return lower.startsWith("sec-fetch-") || lower.startsWith("sec-ch-")
                || "pragma".equals(lower) || "cache-control".equals(lower);
    }

    private static void putIfAbsent(@NotNull Map<String, String> headers, @NotNull String name, @NotNull String value) {
        headers.putIfAbsent(name.toLowerCase(Locale.ROOT), value);
    }

    @NotNull
    private static String toCanonicalHeaderName(@NotNull String lowerName) {
        if ("cookie".equals(lowerName)) {
            return "Cookie";
        }
        if ("x-csrftoken".equals(lowerName)) {
            return "x-csrftoken";
        }
        if (lowerName.indexOf('-') < 0) {
            String upper = lowerName.toUpperCase(Locale.ROOT);
            if ("accept".equals(lowerName) || "origin".equals(lowerName) || "referer".equals(lowerName)) {
                return upper.charAt(0) + lowerName.substring(1);
            }
            return upper;
        }
        StringBuilder sb = new StringBuilder(lowerName.length());
        boolean capitalizeNext = true;
        for (int i = 0; i < lowerName.length(); i++) {
            char c = lowerName.charAt(i);
            if (c == '-') {
                sb.append('-');
                capitalizeNext = true;
            } else if (capitalizeNext) {
                sb.append(Character.toUpperCase(c));
                capitalizeNext = false;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private static boolean cookieContainsCsrf(@NotNull String cookie) {
        String lower = cookie.toLowerCase(Locale.ROOT);
        return lower.contains("csrftoken=") || lower.contains("csrf=");
    }

    static boolean hasLeetCodeSession(@NotNull String cookie) {
        return cookieContains(cookie, "LEETCODE_SESSION");
    }

    private static boolean cookieContains(@NotNull String cookie, @NotNull String cookieName) {
        String lowerName = cookieName.toLowerCase(Locale.ROOT);
        for (String part : cookie.split(";")) {
            int eq = part.indexOf('=');
            if (eq <= 0) {
                continue;
            }
            String name = part.substring(0, eq).trim().toLowerCase(Locale.ROOT);
            if (lowerName.equals(name)) {
                String value = part.substring(eq + 1).trim();
                return !value.isEmpty();
            }
        }
        return false;
    }

    @NotNull
    static String extractCsrfFromCookie(@NotNull String cookie) {
        if (cookie.isEmpty()) {
            return "";
        }
        for (String part : cookie.split(";")) {
            String trimmed = part.trim();
            int eq = trimmed.indexOf('=');
            if (eq <= 0) {
                continue;
            }
            String name = trimmed.substring(0, eq).trim().toLowerCase(Locale.ROOT);
            if ("csrftoken".equals(name) || "csrf".equals(name)) {
                return trimmed.substring(eq + 1).trim();
            }
        }
        return "";
    }

    @Nullable
    private static String firstNonEmpty(@Nullable String first, @Nullable String second) {
        if (first != null && !first.isEmpty()) {
            return first;
        }
        if (second != null && !second.isEmpty()) {
            return second;
        }
        return null;
    }
}
