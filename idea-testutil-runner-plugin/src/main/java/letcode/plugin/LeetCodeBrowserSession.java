package letcode.plugin;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.ui.jcef.JBCefApp;
import com.intellij.ui.jcef.JBCefBrowser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * 登录成功后按项目保留一个 JCEF 浏览器实例，供认证请求复用同一份 Cookie 上下文。
 */
final class LeetCodeBrowserSession {

    private static final Logger LOG = Logger.getInstance(LeetCodeBrowserSession.class);
    private static final Map<Project, JBCefBrowser> ACTIVE_BROWSERS = new WeakHashMap<>();

    private LeetCodeBrowserSession() {
    }

    static synchronized boolean canUseBrowserSession(@NotNull Project project, @NotNull LeetCodeSettings settings) {
        return settings.useEmbeddedBrowserSession
                && ACTIVE_BROWSERS.containsKey(project)
                && JBCefApp.isSupported();
    }

    static void markBrowserSessionActive(@NotNull Project project,
                                         @NotNull LeetCodeSettings settings,
                                         @NotNull JBCefBrowser browser) {
        JBCefBrowser previous;
        synchronized (LeetCodeBrowserSession.class) {
            previous = ACTIVE_BROWSERS.put(project, browser);
        }
        settings.useEmbeddedBrowserSession = true;
        if (previous != null && previous != browser) {
            LeetCodeJcefBrowserFactory.disposeBrowser(previous);
        }
        Disposer.register(project, () -> clearBrowserSession(project, settings, browser));
        LOG.info("LeetCode JCEF session established for project: " + project.getName());
    }

    @Nullable
    static synchronized JBCefBrowser getActiveBrowser(@NotNull Project project, @NotNull LeetCodeSettings settings) {
        return canUseBrowserSession(project, settings) ? ACTIVE_BROWSERS.get(project) : null;
    }

    static void clearBrowserSession(@NotNull Project project, @NotNull LeetCodeSettings settings) {
        clearBrowserSession(project, settings, null);
    }

    private static void clearBrowserSession(@NotNull Project project,
                                            @NotNull LeetCodeSettings settings,
                                            @Nullable JBCefBrowser expectedBrowser) {
        JBCefBrowser browser;
        synchronized (LeetCodeBrowserSession.class) {
            browser = ACTIVE_BROWSERS.get(project);
            if (expectedBrowser != null && browser != expectedBrowser) {
                return;
            }
            ACTIVE_BROWSERS.remove(project);
            settings.useEmbeddedBrowserSession = false;
        }
        if (browser != null) {
            LeetCodeJcefBrowserFactory.disposeBrowser(browser);
            LOG.info("LeetCode JCEF session released for project: " + project.getName());
        }
    }
}
