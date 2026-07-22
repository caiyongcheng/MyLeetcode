package letcode.plugin;

import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.jcef.JBCefApp;
import com.intellij.ui.jcef.JBCefBrowser;
import com.intellij.ui.jcef.JBCefCookie;
import com.intellij.ui.jcef.JBCefCookieManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 在 IDE 内打开 LeetCode 登录页，通过浏览器上下文 userStatus 验证登录并刷新插件认证信息。
 */
final class LeetCodeLoginCookieRefresher {

    private static final String LOGIN_PATH = "/accounts/login/";
    private static final Logger LOG = Logger.getInstance(LeetCodeLoginCookieRefresher.class);

    private LeetCodeLoginCookieRefresher() {
    }

    static boolean refresh(@NotNull Project project, @NotNull LeetCodeSettings settings) {
        String baseUrl = LeetCodeSubmitClient.resolveBaseUrl(settings.endpoint);
        if (!JBCefApp.isSupported()) {
            BrowserUtil.browse(baseUrl + LOGIN_PATH);
            Messages.showErrorDialog(
                    project,
                    "当前 IDE 不支持内嵌浏览器，已打开系统浏览器。\n"
                            + "系统浏览器的 Cookie 无法由插件自动读取，请登录后手动复制 Cookie 到插件设置。",
                    "LeetCode 登录"
            );
            return false;
        }

        LoginDialog dialog = new LoginDialog(project, baseUrl, settings);
        if (!dialog.showAndGet()) {
            return false;
        }
        settings.save(project);
        return true;
    }

    static boolean isAuthExpired(@NotNull Throwable error) {
        Throwable current = error;
        while (current != null) {
            String message = current.getMessage();
            if (message != null) {
                String lower = message.toLowerCase(Locale.ROOT);
                if (lower.contains("http 405")) {
                    return false;
                }
                if (lower.contains("http 401")
                        || lower.contains("http 403")
                        || lower.contains("unauthorized")
                        || lower.contains("forbidden")
                        || lower.contains("未配置登录信息")
                        || lower.contains("未配置 csrf token")
                        || lower.contains("cookie 缺少 leetcode_session")
                        || lower.contains("not authenticated")
                        || lower.contains("user is not authenticated")
                        || lower.contains("please log in")
                        || lower.contains("usersignin")
                        || lower.contains("isnotsignedin")) {
                    return true;
                }
            }
            current = current.getCause();
        }
        return false;
    }

    private static final class LoginDialog extends DialogWrapper {

        private static final String OK_BUTTON_TEXT = "我已登录，刷新 Cookie";
        private static final String OK_BUTTON_VERIFYING_TEXT = "正在保存登录会话...";

        private final Project project;
        private final String baseUrl;
        private final LeetCodeSettings settings;
        private final JBCefBrowser browser;
        private volatile boolean verifying;
        private volatile boolean disposed;
        private volatile boolean browserSessionRetained;

        LoginDialog(@NotNull Project project, @NotNull String baseUrl, @NotNull LeetCodeSettings settings) {
            super(project);
            this.project = project;
            this.baseUrl = baseUrl;
            this.settings = settings;
            this.browser = LeetCodeJcefBrowserFactory.createBrowser(baseUrl + LOGIN_PATH);
            setTitle("LeetCode 登录");
            setOKButtonText(OK_BUTTON_TEXT);
            setCancelButtonText("取消");
            init();
        }

        @Nullable
        @Override
        protected JComponent createCenterPanel() {
            JPanel panel = new JPanel(new BorderLayout(0, 8));
            panel.add(new JLabel("请在下方页面完成登录，登录成功后点击「我已登录，刷新 Cookie」。"), BorderLayout.NORTH);
            JComponent component = browser.getComponent();
            component.setPreferredSize(new Dimension(1100, 760));
            panel.add(component, BorderLayout.CENTER);
            return panel;
        }

        @Override
        protected void doOKAction() {
            if (verifying || disposed) {
                return;
            }
            verifying = true;
            setOKActionEnabled(false);
            setOKButtonText(OK_BUTTON_VERIFYING_TEXT);

            // 用户已确认登录，直接复用当前 JCEF 会话。真正的认证校验由后续提交请求完成，
            // 避免 userStatus 请求被网络层挂起后让登录窗口永久卡住。
            LeetCodeBrowserSession.markBrowserSessionActive(project, settings, browser);
            browserSessionRetained = true;
            LoginDialog.super.doOKAction();

            ApplicationManager.getApplication().executeOnPooledThread(() -> {
                try {
                    // Cookie 持久化不能依赖 GraphQL 校验；网络/WAF 拦截校验请求时，已登录会话仍应保留。
                    String cookie = readReadableLeetCodeCookie();
                    if (!cookie.isEmpty()) {
                        ApplicationManager.getApplication().invokeLater(() -> saveCookie(cookie), ModalityState.any());
                    } else {
                        LOG.warn("LeetCode embedded session has no readable cookies after login confirmation");
                    }

                    LeetCodeBrowserHttpClient.UserStatus status =
                            LeetCodeBrowserHttpClient.verifyUserStatus(browser, settings.endpoint.trim());
                    if (!status.signedIn) {
                        LOG.warn("LeetCode embedded session is not signed in after login confirmation");
                        LeetCodeBrowserSession.clearBrowserSession(project, settings);
                        return;
                    }
                    LOG.info("LeetCode embedded session verified for user: "
                            + (status.username == null ? "unknown" : status.username));
                } catch (Exception ex) {
                    // 验证只用于诊断和 Cookie 持久化，绝不能再次阻塞已关闭的登录窗口。
                    LOG.warn("LeetCode embedded session verification failed", ex);
                }
            });
        }

        private void saveCookie(@NotNull String cookie) {
            if (project.isDisposed()) {
                return;
            }
            settings.cookie = cookie;
            settings.csrfToken = LeetCodeHttpHeaders.extractCsrfFromCookie(cookie);
            settings.save(project);
        }

        @Override
        protected void dispose() {
            disposed = true;
            if (!browserSessionRetained) {
                LeetCodeJcefBrowserFactory.disposeBrowser(browser);
            }
            super.dispose();
        }

        @NotNull
        private String readReadableLeetCodeCookie() throws Exception {
            JBCefCookieManager manager = browser.getJBCefCookieManager();
            // 根域 URL 已覆盖同域和父域 Cookie，避免多次串行等待造成后台任务长时间滞留。
            List<JBCefCookie> cookies = new ArrayList<>(readUrlCookies(manager, baseUrl + "/"));

            Map<String, JBCefCookie> byName = new LinkedHashMap<>();
            for (JBCefCookie cookie : cookies) {
                String domain = cookie.getDomain();
                if (domain == null) {
                    continue;
                }
                String lower = domain.toLowerCase(Locale.ROOT);
                if ("leetcode.cn".equals(lower) || lower.endsWith(".leetcode.cn")) {
                    byName.put(cookie.getName(), cookie);
                }
            }
            List<JBCefCookie> leetcodeCookies = new ArrayList<>(byName.values());
            leetcodeCookies.sort(Comparator.comparing(JBCefCookie::getName));
            StringBuilder sb = new StringBuilder();
            for (JBCefCookie cookie : leetcodeCookies) {
                if (cookie.getName() == null || cookie.getName().isEmpty()) {
                    continue;
                }
                if (sb.length() > 0) {
                    sb.append("; ");
                }
                sb.append(cookie.getName()).append('=').append(cookie.getValue() == null ? "" : cookie.getValue());
            }
            return sb.toString();
        }

        @NotNull
        private static List<JBCefCookie> readUrlCookies(@NotNull JBCefCookieManager manager,
                                                         @NotNull String url) throws Exception {
            return manager.getCookies(url, true).get(8, TimeUnit.SECONDS);
        }

    }

}
