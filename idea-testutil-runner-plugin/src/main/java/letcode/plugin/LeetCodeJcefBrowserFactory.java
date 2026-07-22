package letcode.plugin;

import com.intellij.ui.jcef.JBCefApp;
import com.intellij.ui.jcef.JBCefBrowser;
import com.intellij.ui.jcef.JBCefClient;
import org.jetbrains.annotations.NotNull;

/**
 * 统一创建可执行 JS 回调的浏览器。
 */
final class LeetCodeJcefBrowserFactory {

    private static final int JS_QUERY_POOL_SIZE = 8;

    private LeetCodeJcefBrowserFactory() {
    }

    @NotNull
    static JBCefBrowser createBrowser(@NotNull String url) {
        JBCefClient client = JBCefApp.getInstance().createClient();
        // 必须在创建 Browser 前设置，否则 IDEA 2026.1 会拒绝后续 JBCefJSQuery.create 调用。
        client.setProperty(JBCefClient.Properties.JS_QUERY_POOL_SIZE, JS_QUERY_POOL_SIZE);
        return new JBCefBrowser(client, url);
    }

    static void disposeBrowser(@NotNull JBCefBrowser browser) {
        JBCefClient client = browser.getJBCefClient();
        try {
            browser.dispose();
        } finally {
            if (!client.isDisposed()) {
                client.dispose();
            }
        }
    }
}
