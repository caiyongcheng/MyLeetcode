package letcode.plugin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LeetCode 判题轮询结果，兼容 CN/国际站常见字段名。
 */
final class LeetCodeSubmissionResult {

    final boolean accepted;
    @NotNull
    final String statusMsg;
    @Nullable
    final String failedTestcase;
    @Nullable
    final String lastTestcase;
    @Nullable
    final String actualOutput;
    @Nullable
    final String expectedOutput;
    @Nullable
    final String runtime;
    @Nullable
    final String memory;
    @Nullable
    final String submissionId;

    private LeetCodeSubmissionResult(boolean accepted,
                                     @NotNull String statusMsg,
                                     @Nullable String failedTestcase,
                                     @Nullable String lastTestcase,
                                     @Nullable String actualOutput,
                                     @Nullable String expectedOutput,
                                     @Nullable String runtime,
                                     @Nullable String memory,
                                     @Nullable String submissionId) {
        this.accepted = accepted;
        this.statusMsg = statusMsg;
        this.failedTestcase = failedTestcase;
        this.lastTestcase = lastTestcase;
        this.actualOutput = actualOutput;
        this.expectedOutput = expectedOutput;
        this.runtime = runtime;
        this.memory = memory;
        this.submissionId = submissionId;
    }

    @NotNull
    static LeetCodeSubmissionResult fromCheckResponse(@NotNull JsonObject obj, @Nullable String submissionId) {
        String statusMsg = firstNonEmpty(
                text(obj, "status_msg"),
                text(obj, "statusMsg"),
                text(obj, "message"),
                "Unknown"
        );
        int statusCode = intOrDefault(obj, "status_code", intOrDefault(obj, "statusCode", -1));
        boolean accepted = statusCode == 10 || "Accepted".equalsIgnoreCase(statusMsg);

        return new LeetCodeSubmissionResult(
                accepted,
                statusMsg,
                firstNonEmpty(
                        text(obj, "failed_testcase"),
                        text(obj, "failedTestCase"),
                        text(obj, "failed_test_case")
                ),
                firstNonEmpty(
                        text(obj, "last_testcase"),
                        text(obj, "lastTestCase"),
                        text(obj, "last_test_case"),
                        text(obj, "input_formatted"),
                        text(obj, "input")
                ),
                firstNonEmpty(
                        text(obj, "code_output"),
                        text(obj, "std_output"),
                        text(obj, "stdout"),
                        text(obj, "output")
                ),
                firstNonEmpty(
                        text(obj, "expected_output"),
                        text(obj, "expectedOutput"),
                        text(obj, "expected_stdout")
                ),
                firstNonEmpty(text(obj, "runtime"), text(obj, "runtime_ms")),
                firstNonEmpty(text(obj, "memory"), text(obj, "memory_mb")),
                submissionId
        );
    }

    @NotNull
    String formatForDialog() {
        StringBuilder sb = new StringBuilder(256);
        if (accepted) {
            sb.append("Accepted");
            appendLine(sb, "状态", statusMsg);
            appendLine(sb, "提交 ID", submissionId);
            appendLine(sb, "运行时间", runtime);
            appendLine(sb, "内存", memory);
            return sb.toString();
        }
        appendLine(sb, "状态", statusMsg);
        appendLine(sb, "提交 ID", submissionId);
        appendLine(sb, "失败用例", failedTestcase);
        appendLine(sb, "最后执行用例", lastTestcase);
        appendLine(sb, "实际输出", actualOutput);
        appendLine(sb, "预期输出", expectedOutput);
        appendLine(sb, "运行时间", runtime);
        appendLine(sb, "内存", memory);
        String text = sb.toString().trim();
        return text.isEmpty() ? statusMsg : text;
    }

    private static void appendLine(StringBuilder sb, String label, @Nullable String value) {
        if (value == null || value.isEmpty()) {
            return;
        }
        sb.append(label).append(": ").append(value).append('\n');
    }

    @Nullable
    private static String text(JsonObject obj, String field) {
        if (!obj.has(field)) {
            return null;
        }
        return LeetCodeGraphqlClient.textOrNull(obj.get(field));
    }

    private static int intOrDefault(JsonObject obj, String field, int fallback) {
        if (!obj.has(field)) {
            return fallback;
        }
        JsonElement el = obj.get(field);
        if (el == null || el.isJsonNull()) {
            return fallback;
        }
        try {
            return el.getAsInt();
        } catch (Exception ignored) {
            try {
                return Integer.parseInt(el.getAsString().trim());
            } catch (Exception ignored2) {
                return fallback;
            }
        }
    }

    @Nullable
    private static String firstNonEmpty(String... values) {
        for (String value : values) {
            if (value != null && !value.trim().isEmpty()) {
                return value.trim();
            }
        }
        return null;
    }
}
