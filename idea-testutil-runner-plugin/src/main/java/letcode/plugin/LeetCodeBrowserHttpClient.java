package letcode.plugin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.intellij.ui.jcef.JBCefApp;
import com.intellij.ui.jcef.JBCefBrowser;
import com.intellij.ui.jcef.JBCefBrowserBase;
import com.intellij.ui.jcef.JBCefClient;
import com.intellij.ui.jcef.JBCefJSQuery;
import org.cef.browser.CefBrowser;
import org.cef.handler.CefLoadHandler;
import org.cef.handler.CefLoadHandlerAdapter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 通过 JCEF 浏览器上下文（credentials: include）发起 LeetCode 同源请求，避免脚本直连触发 WAF。
 */
final class LeetCodeBrowserHttpClient {

    private static final long PAGE_LOAD_TIMEOUT_SEC = 30L;
    private static final long REQUEST_TIMEOUT_SEC = 45L;

    private LeetCodeBrowserHttpClient() {
    }

    static boolean isSupported() {
        return JBCefApp.isSupported();
    }

    static final class HttpResult {
        final int status;
        @NotNull
        final String body;

        HttpResult(int status, @NotNull String body) {
            this.status = status;
            this.body = body;
        }
    }

    static final class UserStatus {
        final boolean signedIn;
        @Nullable
        final String username;
        final int httpStatus;

        UserStatus(boolean signedIn, @Nullable String username, int httpStatus) {
            this.signedIn = signedIn;
            this.username = username;
            this.httpStatus = httpStatus;
        }
    }

    @NotNull
    static HttpResult postJson(@NotNull String pageUrl, @NotNull String requestUrl, @NotNull String jsonBody)
            throws IOException {
        return execute(pageUrl, requestUrl, "POST", jsonBody);
    }

    @NotNull
    static HttpResult getJson(@NotNull String pageUrl, @NotNull String requestUrl) throws IOException {
        return execute(pageUrl, requestUrl, "GET", null);
    }

    @NotNull
    static UserStatus verifyUserStatus(@NotNull JBCefBrowser browser,
                                       @NotNull String graphqlUrl) throws IOException {
        String query = "query { userStatus { isSignedIn username } }";
        String body = LeetCodeGraphqlBodies.buildGraphqlBody(query, null);
        HttpResult result = executeFetch(browser, graphqlUrl, "POST", body);
        if (result.status < 200 || result.status >= 300) {
            throw new IOException("userStatus HTTP " + result.status + ": " + truncateSafe(result.body, 300));
        }
        JsonObject data = parseGraphqlData(result.body);
        JsonObject userStatus = data.getAsJsonObject("userStatus");
        if (userStatus == null || userStatus.isJsonNull()) {
            throw new IOException("userStatus 响应为空（HTTP " + result.status + "）");
        }
        boolean signedIn = userStatus.has("isSignedIn") && userStatus.get("isSignedIn").getAsBoolean();
        String username = LeetCodeGraphqlClient.textOrNull(userStatus.get("username"));
        return new UserStatus(signedIn, username, result.status);
    }

    @NotNull
    private static HttpResult execute(@NotNull String pageUrl,
                                      @NotNull String requestUrl,
                                      @NotNull String method,
                                      @Nullable String jsonBody) throws IOException {
        JBCefBrowser browser = new JBCefBrowser();
        try {
            browser.createImmediately();
            ensurePageLoaded(browser, pageUrl);
            return executeFetch(browser, requestUrl, method, jsonBody);
        } finally {
            browser.dispose();
        }
    }

    static void ensurePageLoaded(@NotNull JBCefBrowser browser, @NotNull String pageUrl) throws IOException {
        CefBrowser cefBrowser = browser.getCefBrowser();
        CountDownLatch latch = new CountDownLatch(1);
        AtomicReference<String> loadError = new AtomicReference<>();

        JBCefClient client = browser.getJBCefClient();
        CefLoadHandlerAdapter handler = new CefLoadHandlerAdapter() {
            @Override
            public void onLoadingStateChange(CefBrowser browser,
                                             boolean isLoading,
                                             boolean canGoBack,
                                             boolean canGoForward) {
                if (!isLoading) {
                    latch.countDown();
                }
            }

            @Override
            public void onLoadError(CefBrowser browser,
                                    org.cef.browser.CefFrame frame,
                                    CefLoadHandler.ErrorCode errorCode,
                                    String errorText,
                                    String failedUrl) {
                if (failedUrl != null && (failedUrl.equals(pageUrl) || failedUrl.startsWith(pageUrl))) {
                    loadError.set("页面加载失败: " + safeMessage(errorText));
                    latch.countDown();
                }
            }
        };
        client.addLoadHandler(handler, cefBrowser);
        try {
            String currentUrl = cefBrowser.getURL();
            if (currentUrl == null || currentUrl.isEmpty() || !currentUrl.startsWith(pageUrl)) {
                browser.loadURL(pageUrl);
            } else if (!cefBrowser.isLoading()) {
                latch.countDown();
            }
            if (!latch.await(PAGE_LOAD_TIMEOUT_SEC, TimeUnit.SECONDS)) {
                throw new IOException("浏览器页面加载超时（" + PAGE_LOAD_TIMEOUT_SEC + "s）");
            }
            if (loadError.get() != null) {
                throw new IOException(loadError.get());
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new IOException("浏览器页面加载被中断", ex);
        } finally {
            client.removeLoadHandler(handler, cefBrowser);
        }
    }

    @NotNull
    private static HttpResult executeFetch(@NotNull JBCefBrowser browser,
                                           @NotNull String requestUrl,
                                           @NotNull String method,
                                           @Nullable String jsonBody) throws IOException {
        JBCefJSQuery jsQuery = JBCefJSQuery.create((JBCefBrowserBase) browser);
        CountDownLatch latch = new CountDownLatch(1);
        AtomicReference<String> callbackPayload = new AtomicReference<>();
        AtomicReference<String> callbackError = new AtomicReference<>();

        jsQuery.addHandler(payload -> {
            try {
                callbackPayload.set(payload);
            } catch (Exception ex) {
                callbackError.set(safeMessage(ex.getMessage()));
            } finally {
                latch.countDown();
            }
            return new JBCefJSQuery.Response("ok");
        });

        try {
            String inject = jsQuery.inject("payload");
            String headerPart = "'Accept':'application/json, text/plain, */*'";
            String bodyPart = "";
            if ("POST".equals(method) && jsonBody != null) {
                headerPart += ",'Content-Type':'application/json'";
                bodyPart = "body:" + jsStringLiteral(jsonBody) + ",";
            }

            String script =
                    "(function(){"
                            + "var payload;"
                            + "fetch(" + jsStringLiteral(requestUrl) + ",{"
                            + "method:" + jsStringLiteral(method) + ","
                            + "credentials:'include',"
                            + "headers:{" + headerPart + "},"
                            + bodyPart
                            + "})"
                            + ".then(function(r){return r.text().then(function(t){"
                            + "payload=JSON.stringify({status:r.status,body:t});"
                            + inject
                            + ";});})"
                            + ".catch(function(e){"
                            + "payload=JSON.stringify({status:0,error:" + jsStringLiteral("fetch failed") + "});"
                            + inject
                            + ";});"
                            + "})();";

            browser.getCefBrowser().executeJavaScript(script, browser.getCefBrowser().getURL(), 0);

            if (!latch.await(REQUEST_TIMEOUT_SEC, TimeUnit.SECONDS)) {
                throw new IOException("浏览器请求超时（" + REQUEST_TIMEOUT_SEC + "s）");
            }
            if (callbackError.get() != null) {
                throw new IOException("浏览器回调失败: " + callbackError.get());
            }
            String payload = callbackPayload.get();
            if (payload == null || payload.isEmpty()) {
                throw new IOException("浏览器请求未返回结果");
            }
            return parseCallbackPayload(payload);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new IOException("浏览器请求被中断", ex);
        } finally {
            jsQuery.dispose();
        }
    }

    @NotNull
    private static HttpResult parseCallbackPayload(@NotNull String payload) throws IOException {
        JsonElement root;
        try {
            root = new JsonParser().parse(payload);
        } catch (Exception ex) {
            throw new IOException("浏览器回调不是有效 JSON", ex);
        }
        if (!root.isJsonObject()) {
            throw new IOException("浏览器回调格式错误");
        }
        JsonObject obj = root.getAsJsonObject();
        if (obj.has("error") && !obj.get("error").isJsonNull()) {
            String error = LeetCodeGraphqlClient.textOrNull(obj.get("error"));
            throw new IOException("浏览器 fetch 失败: " + (error == null ? "unknown" : error));
        }
        int status = obj.has("status") ? obj.get("status").getAsInt() : 0;
        String body = obj.has("body") ? LeetCodeGraphqlClient.textOrNull(obj.get("body")) : "";
        return new HttpResult(status, body == null ? "" : body);
    }

    @NotNull
    private static JsonObject parseGraphqlData(@NotNull String responseText) throws IOException {
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

    @NotNull
    private static String jsStringLiteral(@NotNull String value) {
        return LeetCodeHttpHeaders.escapeJsonString(value);
    }

    @NotNull
    private static String safeMessage(@Nullable String message) {
        if (message == null || message.isEmpty()) {
            return "unknown";
        }
        return truncateSafe(message, 200);
    }

    @NotNull
    private static String truncateSafe(@Nullable String text, int max) {
        if (text == null) {
            return "";
        }
        if (text.length() <= max) {
            return text;
        }
        return text.substring(0, max) + "...";
    }
}
