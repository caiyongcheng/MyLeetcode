package letcode.plugin;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * LeetCode 接口配置，按项目持久化到 IDE Properties（不写入 git）。
 */
final class LeetCodeSettings {

    static final String DEFAULT_ENDPOINT = "https://leetcode.cn/graphql/";

    private static final String KEY_ENDPOINT = "letcode.leetcode.endpoint";
    private static final String KEY_BEARER = "letcode.leetcode.bearer";
    private static final String KEY_COOKIE = "letcode.leetcode.cookie";
    private static final String KEY_CSRF = "letcode.leetcode.csrf";
    private static final String KEY_EXTRA_HEADERS = "letcode.leetcode.extraHeaders";
    private static final String KEY_OVERWRITE = "letcode.leetcode.overwrite";
    private static final String KEY_LAST_DAILY_ID = "letcode.leetcode.lastDailyQuestionFrontendId";
    private static final String KEY_USE_EMBEDDED_BROWSER_SESSION = "letcode.leetcode.useEmbeddedBrowserSession";

    String endpoint = DEFAULT_ENDPOINT;
    String bearerToken = "";
    String cookie = "";
    String csrfToken = "";
    String extraHeaders = "";
    boolean overwriteExisting;
    /** 内嵌 JCEF 登录会话可用时优先走浏览器上下文请求（重启后 CEF 会话可能失效）。 */
    boolean useEmbeddedBrowserSession;
    /** 上次成功生成的每日题 questionFrontendId，用于同日重复拉取时强制覆盖。 */
    String lastDailyQuestionFrontendId = "";

    static LeetCodeSettings load(@NotNull Project project) {
        PropertiesComponent props = PropertiesComponent.getInstance(project);
        LeetCodeSettings settings = new LeetCodeSettings();
        settings.endpoint = props.getValue(KEY_ENDPOINT, DEFAULT_ENDPOINT);
        settings.bearerToken = nullToEmpty(props.getValue(KEY_BEARER));
        settings.cookie = nullToEmpty(props.getValue(KEY_COOKIE));
        settings.csrfToken = nullToEmpty(props.getValue(KEY_CSRF));
        settings.extraHeaders = nullToEmpty(props.getValue(KEY_EXTRA_HEADERS));
        settings.overwriteExisting = Boolean.parseBoolean(props.getValue(KEY_OVERWRITE, "false"));
        settings.useEmbeddedBrowserSession = Boolean.parseBoolean(
                props.getValue(KEY_USE_EMBEDDED_BROWSER_SESSION, "false"));
        settings.lastDailyQuestionFrontendId = nullToEmpty(props.getValue(KEY_LAST_DAILY_ID));
        return settings;
    }

    void save(@NotNull Project project) {
        PropertiesComponent props = PropertiesComponent.getInstance(project);
        props.setValue(KEY_ENDPOINT, trimToEmpty(endpoint, DEFAULT_ENDPOINT));
        props.setValue(KEY_BEARER, trimToEmpty(bearerToken, ""));
        props.setValue(KEY_COOKIE, trimToEmpty(cookie, ""));
        props.setValue(KEY_CSRF, trimToEmpty(csrfToken, ""));
        props.setValue(KEY_EXTRA_HEADERS, trimToEmpty(extraHeaders, ""));
        props.setValue(KEY_OVERWRITE, Boolean.toString(overwriteExisting));
        props.setValue(KEY_USE_EMBEDDED_BROWSER_SESSION, Boolean.toString(useEmbeddedBrowserSession));
        props.setValue(KEY_LAST_DAILY_ID, trimToEmpty(lastDailyQuestionFrontendId, ""));
    }

    private static String nullToEmpty(String value) {
        return value == null ? "" : value;
    }

    private static String trimToEmpty(String value, String fallback) {
        if (value == null) {
            return fallback;
        }
        String trimmed = value.trim();
        return trimmed.isEmpty() ? fallback : trimmed;
    }
}
