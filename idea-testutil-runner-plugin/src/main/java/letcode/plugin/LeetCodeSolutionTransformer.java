package letcode.plugin;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

/**
 * 将仓库题解 Java 源码转换为 LeetCode 可提交的代码（无 package，类名为 Solution）。
 */
final class LeetCodeSolutionTransformer {

    private LeetCodeSolutionTransformer() {
    }

    @NotNull
    static String toSubmitCode(@NotNull String source, @NotNull String className) {
        String body = source.replaceAll("(?m)^\\s*package\\s+[^;]+;\\s*\\R?", "");
        String quoted = Pattern.quote(className);
        if (body.matches("(?s).*\\bpublic\\s+class\\s+" + quoted + "\\b.*")) {
            body = body.replaceFirst("\\bpublic\\s+class\\s+" + quoted + "\\b", "class Solution");
        } else {
            body = body.replaceFirst("\\bclass\\s+" + quoted + "\\b", "class Solution");
        }
        if (!body.contains("class Solution")) {
            throw new IllegalArgumentException("未找到可提交的顶层类: " + className);
        }
        return body.trim();
    }
}
