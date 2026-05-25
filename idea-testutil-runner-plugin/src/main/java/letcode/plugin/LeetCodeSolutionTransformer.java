package letcode.plugin;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

/**
 * Converts repository solution Java source into LeetCode-submittable code
 * (no package, class name Solution, no local TestUtil harness).
 */
final class LeetCodeSolutionTransformer {

    private static final Pattern PACKAGE_LINE = Pattern.compile("(?m)^\\s*package\\s+[^;]+;\\s*\\R?");
    private static final Pattern LETCODE_UTILS_IMPORT =
            Pattern.compile("(?m)^\\s*import\\s+(?:static\\s+)?letcode\\.utils\\.[^;]+;\\s*\\R?");
    private static final Pattern MAIN_SIGNATURE =
            Pattern.compile("public\\s+static\\s+void\\s+main\\s*\\(\\s*String\\s*\\[\\s*\\]\\s*args\\s*\\)");

    private LeetCodeSolutionTransformer() {
    }

    @NotNull
    static String toSubmitCode(@NotNull String source, @NotNull String className) {
        String body = source;
        body = PACKAGE_LINE.matcher(body).replaceAll("");
        body = LETCODE_UTILS_IMPORT.matcher(body).replaceAll("");
        body = removeSolutionTestAnnotations(body);
        body = removeTestOnlyMainMethods(body);
        String quoted = Pattern.quote(className);
        if (body.matches("(?s).*\\bpublic\\s+class\\s+" + quoted + "\\b.*")) {
            body = body.replaceFirst("\\bpublic\\s+class\\s+" + quoted + "\\b", "class Solution");
        } else {
            body = body.replaceFirst("\\bclass\\s+" + quoted + "\\b", "class Solution");
        }
        if (!body.contains("class Solution")) {
            throw new IllegalArgumentException("Top-level class not found for submit: " + className);
        }
        return body.trim();
    }

    /** Removes @SolutionTestMethod / @SolutionTestMethods, including multi-line argument lists. */
    @NotNull
    private static String removeSolutionTestAnnotations(@NotNull String source) {
        StringBuilder out = new StringBuilder(source.length());
        int i = 0;
        while (i < source.length()) {
            int at = indexOfSolutionTestAnnotation(source, i);
            if (at < 0) {
                out.append(source, i, source.length());
                break;
            }
            int lineStart = at;
            while (lineStart > 0 && source.charAt(lineStart - 1) != '\n' && source.charAt(lineStart - 1) != '\r') {
                lineStart--;
            }
            boolean lineOnlyAnnotation = true;
            for (int j = lineStart; j < at; j++) {
                if (!Character.isWhitespace(source.charAt(j))) {
                    lineOnlyAnnotation = false;
                    break;
                }
            }
            out.append(source, i, lineOnlyAnnotation ? lineStart : at);
            i = skipSolutionTestAnnotation(source, at);
            while (i < source.length()) {
                char c = source.charAt(i);
                if (c == ' ' || c == '\t') {
                    i++;
                    continue;
                }
                if (c == '\r') {
                    i++;
                    if (i < source.length() && source.charAt(i) == '\n') {
                        i++;
                    }
                    break;
                }
                if (c == '\n') {
                    i++;
                }
                break;
            }
        }
        return out.toString();
    }

    private static int indexOfSolutionTestAnnotation(@NotNull String source, int from) {
        int i = from;
        while (true) {
            int at = source.indexOf("@SolutionTestMethod", i);
            if (at < 0) {
                return -1;
            }
            int after = at + "@SolutionTestMethod".length();
            if (after < source.length() && source.charAt(after) == 's') {
                int afterMethods = after + 1;
                if (afterMethods >= source.length() || !Character.isJavaIdentifierPart(source.charAt(afterMethods))) {
                    return at;
                }
            } else if (after >= source.length() || !Character.isJavaIdentifierPart(source.charAt(after))) {
                return at;
            }
            i = at + 1;
        }
    }

    private static int skipSolutionTestAnnotation(@NotNull String source, int at) {
        int i = at + 1;
        if (!source.startsWith("SolutionTestMethod", i)) {
            return at;
        }
        i += "SolutionTestMethod".length();
        if (i < source.length() && source.charAt(i) == 's') {
            i++;
        }
        while (i < source.length() && Character.isWhitespace(source.charAt(i))) {
            i++;
        }
        if (i < source.length() && source.charAt(i) == '(') {
            i = skipBalanced(source, i, '(', ')') + 1;
        }
        return i;
    }

    @NotNull
    private static String removeTestOnlyMainMethods(@NotNull String source) {
        StringBuilder out = new StringBuilder(source.length());
        int i = 0;
        while (i < source.length()) {
            java.util.regex.Matcher matcher = MAIN_SIGNATURE.matcher(source);
            if (!matcher.find(i)) {
                out.append(source, i, source.length());
                break;
            }
            int mainStart = matcher.start();
            int braceStart = matcher.end();
            while (braceStart < source.length() && Character.isWhitespace(source.charAt(braceStart))) {
                braceStart++;
            }
            if (braceStart >= source.length() || source.charAt(braceStart) != '{') {
                out.append(source, i, matcher.end());
                i = matcher.end();
                continue;
            }
            int bodyEnd = skipBalanced(source, braceStart, '{', '}');
            if (bodyEnd < 0) {
                out.append(source, i, source.length());
                break;
            }
            String body = source.substring(braceStart + 1, bodyEnd);
            if (isTestUtilOnlyMainBody(body)) {
                out.append(source, i, mainStart);
                i = bodyEnd + 1;
            } else {
                out.append(source, i, bodyEnd + 1);
                i = bodyEnd + 1;
            }
        }
        return out.toString();
    }

    /** True when main body only invokes TestUtil test entry points (optional semicolons / whitespace). */
    private static boolean isTestUtilOnlyMainBody(@NotNull String body) {
        String stripped = stripComments(body).trim();
        if (stripped.isEmpty()) {
            return false;
        }
        String[] statements = stripped.split(";");
        for (String statement : statements) {
            String stmt = statement.trim();
            if (stmt.isEmpty()) {
                continue;
            }
            if (!stmt.matches("TestUtil\\.(test|testUseTestFile|testBatch)\\(.*")) {
                return false;
            }
        }
        return true;
    }

    @NotNull
    private static String stripComments(@NotNull String text) {
        StringBuilder sb = new StringBuilder(text.length());
        int i = 0;
        while (i < text.length()) {
            if (i + 1 < text.length() && text.charAt(i) == '/' && text.charAt(i + 1) == '/') {
                i += 2;
                while (i < text.length() && text.charAt(i) != '\n' && text.charAt(i) != '\r') {
                    i++;
                }
                continue;
            }
            if (i + 1 < text.length() && text.charAt(i) == '/' && text.charAt(i + 1) == '*') {
                i += 2;
                while (i + 1 < text.length() && !(text.charAt(i) == '*' && text.charAt(i + 1) == '/')) {
                    i++;
                }
                i += 2;
                continue;
            }
            sb.append(text.charAt(i));
            i++;
        }
        return sb.toString();
    }

    /** Returns index of closing delimiter, or -1 if unmatched. */
    private static int skipBalanced(@NotNull String source, int openIndex, char open, char close) {
        if (openIndex < 0 || openIndex >= source.length() || source.charAt(openIndex) != open) {
            return -1;
        }
        int depth = 0;
        boolean inString = false;
        boolean inChar = false;
        boolean lineComment = false;
        boolean blockComment = false;
        for (int i = openIndex; i < source.length(); i++) {
            char c = source.charAt(i);
            char next = i + 1 < source.length() ? source.charAt(i + 1) : '\0';
            if (lineComment) {
                if (c == '\n' || c == '\r') {
                    lineComment = false;
                }
                continue;
            }
            if (blockComment) {
                if (c == '*' && next == '/') {
                    blockComment = false;
                    i++;
                }
                continue;
            }
            if (!inString && !inChar && c == '/' && next == '/') {
                lineComment = true;
                i++;
                continue;
            }
            if (!inString && !inChar && c == '/' && next == '*') {
                blockComment = true;
                i++;
                continue;
            }
            if (!inChar && c == '"' && !isEscaped(source, i)) {
                inString = !inString;
                continue;
            }
            if (!inString && c == '\'' && !isEscaped(source, i)) {
                inChar = !inChar;
                continue;
            }
            if (inString || inChar) {
                continue;
            }
            if (c == open) {
                depth++;
            } else if (c == close) {
                depth--;
                if (depth == 0) {
                    return i;
                }
            }
        }
        return -1;
    }

    private static boolean isEscaped(@NotNull String source, int quoteIndex) {
        int backslashes = 0;
        for (int i = quoteIndex - 1; i >= 0 && source.charAt(i) == '\\'; i--) {
            backslashes++;
        }
        return (backslashes & 1) == 1;
    }
}
