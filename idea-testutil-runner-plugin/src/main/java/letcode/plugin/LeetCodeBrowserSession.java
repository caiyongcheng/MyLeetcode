package letcode.plugin;

import com.intellij.ui.jcef.JBCefApp;
import org.jetbrains.annotations.NotNull;

/**
 * 内嵌 JCEF 浏览器会话是否可用于 LeetCode 请求（登录后由插件标记，重启后可能失效）。
 */
final class LeetCodeBrowserSession {

    private LeetCodeBrowserSession() {
    }

    static boolean canUseBrowserSession(@NotNull LeetCodeSettings settings) {
        return settings.useEmbeddedBrowserSession && JBCefApp.isSupported();
    }

    static void markBrowserSessionActive(@NotNull LeetCodeSettings settings) {
        settings.useEmbeddedBrowserSession = true;
    }

    static void clearBrowserSession(@NotNull LeetCodeSettings settings) {
        settings.useEmbeddedBrowserSession = false;
    }
}
