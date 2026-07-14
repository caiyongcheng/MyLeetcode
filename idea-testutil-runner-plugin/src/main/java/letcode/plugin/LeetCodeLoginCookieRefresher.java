package letcode.plugin;

import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.application.ApplicationManager;
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
        private static final String OK_BUTTON_VERIFYING_TEXT = "正在验证登录状态...";

        private final String baseUrl;
        private final LeetCodeSettings settings;
        private final JBCefBrowser browser;
        private volatile boolean verifying;
        private volatile boolean disposed;

        LoginDialog(@NotNull Project project, @NotNull String baseUrl, @NotNull LeetCodeSettings settings) {
            super(project);
            this.baseUrl = baseUrl;
            this.settings = settings;
            this.browser = new JBCefBrowser(baseUrl + LOGIN_PATH);
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

            ApplicationManager.getApplication().executeOnPooledThread(() -> {
                try {
                    if (disposed) {
                        return;
                    }
                    LeetCodeBrowserHttpClient.UserStatus status =
                            LeetCodeBrowserHttpClient.verifyUserStatus(browser, settings.endpoint.trim());
                    if (disposed) {
                        return;
                    }
                    if (!status.signedIn) {
                        ApplicationManager.getApplication().invokeLater(() -> handleNotSignedIn(status));
                        return;
                    }
                    String cookie = readReadableLeetCodeCookie();
                    if (disposed) {
                        return;
                    }
                    ApplicationManager.getApplication().invokeLater(() -> handleVerificationSuccess(cookie));
                } catch (Exception ex) {
                    ApplicationManager.getApplication().invokeLater(() -> handleVerificationFailure(ex));
                }
            });
        }

        private void handleNotSignedIn(@NotNull LeetCodeBrowserHttpClient.UserStatus status) {
            if (disposed) {
                return;
            }
            restoreOkButton();
            Messages.showErrorDialog(
                    "登录页面已打开，但 LeetCode 会话尚未建立（userStatus.isSignedIn=false）。\n"
                            + "请确认已完成登录，或稍等页面跳转后再试。\n"
                            + "HTTP " + status.httpStatus,
                    "LeetCode 登录"
            );
        }

        private void handleVerificationFailure(@NotNull Exception ex) {
            if (disposed) {
                return;
            }
            restoreOkButton();
            LeetCodeBrowserSession.clearBrowserSession(settings);
            Messages.showErrorDialog(
                    "验证登录状态失败: " + safeErrorMessage(ex),
                    "LeetCode 登录"
            );
        }

        private void handleVerificationSuccess(@NotNull String cookie) {
            if (disposed) {
                return;
            }
            settings.cookie = cookie;
            settings.csrfToken = LeetCodeHttpHeaders.extractCsrfFromCookie(cookie);
            LeetCodeBrowserSession.markBrowserSessionActive(settings);
            LoginDialog.super.doOKAction();
        }

        private void restoreOkButton() {
            verifying = false;
            setOKActionEnabled(true);
            setOKButtonText(OK_BUTTON_TEXT);
        }

        @Override
        protected void dispose() {
            disposed = true;
            browser.dispose();
            super.dispose();
        }

        @NotNull
        private String readReadableLeetCodeCookie() throws Exception {
            JBCefCookieManager manager = browser.getJBCefCookieManager();
            List<JBCefCookie> cookies = new ArrayList<>();
            cookies.addAll(readUrlCookies(manager, baseUrl));
            cookies.addAll(readUrlCookies(manager, baseUrl + "/"));
            cookies.addAll(readUrlCookies(manager, baseUrl + LOGIN_PATH));
            cookies.addAll(readUrlCookies(manager, baseUrl + "/problemset/"));
            cookies.addAll(readUrlCookies(manager, baseUrl + "/graphql/"));
            cookies.addAll(manager.getCookies());

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

    @NotNull
    private static String safeErrorMessage(@NotNull Exception ex) {
        String message = ex.getMessage();
        if (message == null || message.isEmpty()) {
            return ex.getClass().getSimpleName();
        }
        if (message.length() > 300) {
            return message.substring(0, 300) + "...";
        }
        return message;
    }
}
