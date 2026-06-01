package letcode.plugin;

import org.jetbrains.annotations.NotNull;

/**
 * 轻量 Java snippet 格式化，将 LeetCode 紧凑代码骨架转为项目缩进风格。
 */
final class JavaSnippetFormatter {

    private static final String INDENT = "    ";

    private JavaSnippetFormatter() {
    }

    @NotNull
    static String format(@NotNull String code) {
        String normalized = insertNewlinesAroundBraces(code.trim());
        return applyIndent(normalized);
    }

    private static String insertNewlinesAroundBraces(String code) {
        StringBuilder out = new StringBuilder(code.length() + 64);
        boolean inString = false;
        char stringDelim = 0;
        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            if (inString) {
                out.append(c);
                if (c == stringDelim && (i == 0 || code.charAt(i - 1) != '\\')) {
                    inString = false;
                }
                continue;
            }
            if (c == '"' || c == '\'') {
                inString = true;
                stringDelim = c;
                out.append(c);
                continue;
            }
            if (c == '{') {
                trimTrailingWhitespace(out);
                out.append(" {\n");
                continue;
            }
            if (c == '}') {
                trimTrailingWhitespace(out);
                out.append("\n}");
                continue;
            }
            if (c == ';') {
                out.append(';');
                int j = i + 1;
                while (j < code.length() && Character.isWhitespace(code.charAt(j))) {
                    j++;
                }
                if (j < code.length() && code.charAt(j) != '}') {
                    out.append('\n');
                }
                continue;
            }
            out.append(c);
        }
        return out.toString();
    }

    private static String applyIndent(String code) {
        String[] lines = code.split("\n");
        StringBuilder sb = new StringBuilder(code.length() + 32);
        int indent = 0;
        for (String raw : lines) {
            String line = raw.trim();
            if (line.isEmpty()) {
                continue;
            }
            if (line.startsWith("}")) {
                indent = Math.max(0, indent - 1);
            }
            for (int i = 0; i < indent; i++) {
                sb.append(INDENT);
            }
            sb.append(line).append('\n');
            if (line.endsWith("{")) {
                if (line.contains("class ")) {
                    sb.append('\n');
                }
                indent++;
            }
        }
        String result = sb.toString().trim();
        return result.isEmpty() ? "\n" : result + "\n";
    }

    private static void trimTrailingWhitespace(StringBuilder sb) {
        while (sb.length() > 0 && Character.isWhitespace(sb.charAt(sb.length() - 1))) {
            sb.setLength(sb.length() - 1);
        }
    }
}
