package letcode.plugin;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Generates project-style Java solution files and optional TestCase files from LeetCode question data.
 */
final class LeetCodeDailyGenerator {

    private static final Pattern EXAMPLE_BLOCK = Pattern.compile(
            "Example\\s*(\\d+)\\s*:\\s*([\\s\\S]*?)(?=Example\\s*\\d+\\s*:|\\z)",
            Pattern.CASE_INSENSITIVE
    );
    private static final Pattern INPUT_LINE = Pattern.compile("(?:^|\\n)\\s*Input\\s*:\\s*(.+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern OUTPUT_LINE = Pattern.compile("(?:^|\\n)\\s*Output\\s*:\\s*(.+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern EXPLANATION_LINE = Pattern.compile(
            "(?:^|\\n)\\s*Explanation\\s*:\\s*([\\s\\S]*)",
            Pattern.CASE_INSENSITIVE
    );
    private static final String PLUGIN_LINK_MARKER = "Link: https://leetcode.cn/problems/";

    private final String projectBasePath;

    LeetCodeDailyGenerator(@NotNull String projectBasePath, @NotNull LeetCodeSettings settings) {
        this.projectBasePath = projectBasePath;
        // settings 仅保留构造兼容；生成逻辑不再读取 overwriteExisting
    }

    @NotNull
    GenerationResult generate(@NotNull JsonObject question, @NotNull String titleSlug) throws IOException {
        String frontendId = requireText(question, "questionFrontendId");
        String className = toClassName(frontendId);
        String difficulty = normalizeDifficulty(requireText(question, "difficulty"));
        PackageInfo pkg = packageForDifficulty(difficulty);

        Path preferredJavaPath = Paths.get(projectBasePath, pkg.relativeDir, className + ".java");
        Path existingJavaPath = resolveExistingJavaPath(className, preferredJavaPath);

        String title = firstNonEmpty(
                LeetCodeGraphqlClient.textOrNull(question.get("title")),
                LeetCodeGraphqlClient.textOrNull(question.get("translatedTitle")),
                titleSlug
        );
        String description = buildDescription(question, titleSlug);

        if (existingJavaPath != null) {
            return updateMetadataOnly(existingJavaPath, className, frontendId, title, difficulty, titleSlug, description);
        }

        String javaSource = buildJavaSource(pkg, className, frontendId, title, difficulty, titleSlug, description, question);
        Files.createDirectories(preferredJavaPath.getParent());
        Files.write(preferredJavaPath, javaSource.getBytes(StandardCharsets.UTF_8));

        Path testCasePath = null;
        boolean wroteTestCase = false;
        String testCaseContent = tryBuildTestCaseContent(question);
        if (testCaseContent != null) {
            Path candidate = Paths.get(projectBasePath, "src/main/resources", "TestCase_" + className.substring(1) + ".txt");
            if (!Files.exists(candidate)) {
                Files.createDirectories(candidate.getParent());
                Files.write(candidate, testCaseContent.getBytes(StandardCharsets.UTF_8));
                testCasePath = candidate;
                wroteTestCase = true;
            }
        }

        return GenerationResult.created(preferredJavaPath, wroteTestCase ? testCasePath : null, frontendId);
    }

    @NotNull
    private GenerationResult updateMetadataOnly(@NotNull Path javaPath,
                                                @NotNull String className,
                                                @NotNull String frontendId,
                                                @NotNull String title,
                                                @NotNull String difficulty,
                                                @NotNull String titleSlug,
                                                @NotNull String description) throws IOException {
        String original = new String(Files.readAllBytes(javaPath), StandardCharsets.UTF_8);
        String javadoc = buildMetadataJavadoc(frontendId, title, difficulty, titleSlug, description);
        String blockComment = buildMetadataBlockComment(frontendId, title, difficulty, titleSlug, description);
        String updated = replaceOrInsertMetadata(original, className, javadoc, blockComment);
        if (updated.equals(original)) {
            return GenerationResult.metadataUnchanged(javaPath, frontendId);
        }
        Files.write(javaPath, updated.getBytes(StandardCharsets.UTF_8));
        return GenerationResult.metadataUpdated(javaPath, frontendId);
    }

    @NotNull
    static String replaceOrInsertMetadata(@NotNull String source,
                                          @NotNull String className,
                                          @NotNull String javadoc,
                                          @NotNull String blockComment) {
        Matcher classMatcher = Pattern.compile("\\bpublic\\s+class\\s+" + Pattern.quote(className) + "\\b")
                .matcher(source);
        if (!classMatcher.find()) {
            return source;
        }
        int classIndex = classMatcher.start();

        CommentSpan adjacent = findAdjacentComment(source, classIndex);
        if (adjacent != null && adjacent.contentContains(source, PLUGIN_LINK_MARKER)) {
            return source.substring(0, adjacent.start) + javadocOrKeepStyle(adjacent, source, javadoc, blockComment)
                    + source.substring(adjacent.end);
        }
        if (adjacent != null && adjacent.isJavadoc(source)) {
            CommentSpan beforeJavadoc = findAdjacentComment(source, adjacent.start);
            if (beforeJavadoc != null && beforeJavadoc.contentContains(source, PLUGIN_LINK_MARKER)) {
                return source.substring(0, beforeJavadoc.start)
                        + blockComment
                        + source.substring(beforeJavadoc.end);
            }
            return source.substring(0, adjacent.start) + blockComment + "\n" + source.substring(adjacent.start);
        }
        if (adjacent != null) {
            CommentSpan before = findAdjacentComment(source, adjacent.start);
            if (before != null && before.contentContains(source, PLUGIN_LINK_MARKER)) {
                return source.substring(0, before.start) + blockComment + source.substring(before.end);
            }
        }
        return source.substring(0, classIndex) + blockComment + "\n" + source.substring(classIndex);
    }

    @NotNull
    private static String javadocOrKeepStyle(@NotNull CommentSpan adjacent,
                                             @NotNull String source,
                                             @NotNull String javadoc,
                                             @NotNull String blockComment) {
        return adjacent.isJavadoc(source) ? javadoc : blockComment;
    }

    @Nullable
    private static CommentSpan findAdjacentComment(@NotNull String source, int beforeIndex) {
        int i = beforeIndex - 1;
        while (i >= 0 && Character.isWhitespace(source.charAt(i))) {
            i--;
        }
        if (i < 1 || source.charAt(i) != '/' || source.charAt(i - 1) != '*') {
            return null;
        }
        int end = i + 1;
        int start = source.lastIndexOf("/*", i - 1);
        if (start < 0) {
            return null;
        }
        return new CommentSpan(start, end);
    }

    @Nullable
    private Path resolveExistingJavaPath(@NotNull String className, @NotNull Path preferredPath) throws IOException {
        if (Files.exists(preferredPath)) {
            return preferredPath;
        }
        Path letcodeRoot = Paths.get(projectBasePath, "src/main/java/letcode");
        if (!Files.isDirectory(letcodeRoot)) {
            return null;
        }
        String fileName = className + ".java";
        final Path[] found = {null};
        Files.walkFileTree(letcodeRoot, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(@NotNull Path file, @NotNull BasicFileAttributes attrs) {
                if (fileName.equals(file.getFileName().toString())) {
                    found[0] = file;
                    return FileVisitResult.TERMINATE;
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(@NotNull Path file, @NotNull IOException exc) {
                return FileVisitResult.CONTINUE;
            }
        });
        return found[0];
    }

    @NotNull
    private String buildJavaSource(PackageInfo pkg,
                                   String className,
                                   String frontendId,
                                   String title,
                                   String difficulty,
                                   String titleSlug,
                                   String description,
                                   JsonObject question) {
        String body = buildClassBody(className, pickJavaSnippet(question));
        StringBuilder file = new StringBuilder();
        file.append("package ").append(pkg.packageName).append(";\n\n");
        file.append(buildMetadataJavadoc(frontendId, title, difficulty, titleSlug, description));
        file.append('\n');
        file.append(body);
        if (!body.endsWith("\n")) {
            file.append('\n');
        }
        return file.toString();
    }

    @NotNull
    private String buildMetadataJavadoc(String frontendId,
                                        String title,
                                        String difficulty,
                                        String titleSlug,
                                        String description) {
        return buildMetadataComment(true, frontendId, title, difficulty, titleSlug, description);
    }

    @NotNull
    private String buildMetadataBlockComment(String frontendId,
                                             String title,
                                             String difficulty,
                                             String titleSlug,
                                             String description) {
        return buildMetadataComment(false, frontendId, title, difficulty, titleSlug, description);
    }

    @NotNull
    private String buildMetadataComment(boolean javadoc,
                                        String frontendId,
                                        String title,
                                        String difficulty,
                                        String titleSlug,
                                        String description) {
        String link = "https://leetcode.cn/problems/" + titleSlug + "/";
        StringBuilder file = new StringBuilder();
        file.append(javadoc ? "/**\n" : "/*\n");
        file.append(" * ").append(escapeJavadoc(frontendId)).append(". ").append(escapeJavadoc(title)).append('\n');
        file.append(" * Difficulty: ").append(escapeJavadoc(difficulty)).append('\n');
        file.append(" * ").append(PLUGIN_LINK_MARKER).append(escapeJavadoc(titleSlug)).append("/\n");
        if (!description.isEmpty()) {
            file.append(" *\n");
            appendJavadocText(file, description);
        }
        file.append(" */");
        return file.toString();
    }

    @NotNull
    private static String buildClassBody(String className, @Nullable String snippet) {
        if (snippet == null || snippet.isEmpty()) {
            return "public class " + className + " {\n\n}\n";
        }
        String body = snippet.replaceAll("(?m)^\\s*package\\s+[^;]+;\\s*", "");
        body = body.replaceFirst("\\bpublic\\s+class\\s+Solution\\b", "public class " + className);
        body = body.replaceFirst("\\bclass\\s+Solution\\b", "public class " + className);
        if (!body.contains("class " + className)) {
            return "public class " + className + " {\n\n}\n";
        }
        return JavaSnippetFormatter.format(body);
    }

    @Nullable
    private static String pickJavaSnippet(JsonObject question) {
        JsonElement snippetsEl = question.get("codeSnippets");
        if (snippetsEl == null || !snippetsEl.isJsonArray()) {
            return null;
        }
        JsonArray snippets = snippetsEl.getAsJsonArray();
        String fallback = null;
        for (JsonElement element : snippets) {
            if (!element.isJsonObject()) {
                continue;
            }
            JsonObject snippet = element.getAsJsonObject();
            String lang = LeetCodeGraphqlClient.textOrNull(snippet.get("lang"));
            String langSlug = LeetCodeGraphqlClient.textOrNull(snippet.get("langSlug"));
            String code = LeetCodeGraphqlClient.textOrNull(snippet.get("code"));
            if (code == null || code.isEmpty()) {
                continue;
            }
            if ("java".equalsIgnoreCase(langSlug) || (lang != null && lang.toLowerCase(Locale.ROOT).contains("java"))) {
                return code;
            }
            if (fallback == null) {
                fallback = code;
            }
        }
        return fallback;
    }

    @Nullable
    private static String tryBuildTestCaseContent(JsonObject question) {
        String content = LeetCodeGraphqlClient.textOrNull(question.get("content"));
        String translated = LeetCodeGraphqlClient.textOrNull(question.get("translatedContent"));
        String plain = firstNonEmpty(htmlToPlain(content), htmlToPlain(translated));
        String fromHtml = extractExamplesFromPlain(plain);
        return isValidTestUtilCase(fromHtml) ? fromHtml : null;
    }

    @Nullable
    private static String extractExamplesFromPlain(String plain) {
        if (plain == null || plain.isEmpty()) {
            return null;
        }
        Matcher matcher = EXAMPLE_BLOCK.matcher(plain);
        List<String> blocks = new ArrayList<>();
        while (matcher.find()) {
            String formatted = formatExampleBlock(matcher.group(2).trim());
            if (formatted != null) {
                blocks.add("Example " + matcher.group(1) + ":\n" + formatted);
            }
        }
        if (!blocks.isEmpty()) {
            return String.join("\n\n", blocks);
        }
        String single = formatExampleBlock(plain.trim());
        return isValidTestUtilCase(single) ? single : null;
    }

    @Nullable
    private static String formatExampleBlock(String block) {
        Matcher inputMatcher = INPUT_LINE.matcher(block);
        Matcher outputMatcher = OUTPUT_LINE.matcher(block);
        if (!inputMatcher.find() || !outputMatcher.find()) {
            return null;
        }
        String input = inputMatcher.group(1).trim();
        String output = outputMatcher.group(1).trim();
        int explanationStart = block.indexOf("Explanation", outputMatcher.start());
        if (explanationStart >= 0 && explanationStart > outputMatcher.start()) {
            Matcher outOnly = OUTPUT_LINE.matcher(block.substring(outputMatcher.start(), explanationStart));
            if (outOnly.find()) {
                output = outOnly.group(1).trim();
            }
        }
        Matcher explanationMatcher = EXPLANATION_LINE.matcher(block);
        String explanation = explanationMatcher.find(outputMatcher.end()) ? explanationMatcher.group(1).trim() : "";

        StringBuilder sb = new StringBuilder();
        sb.append("Input: ").append(input).append('\n');
        sb.append("Output: ").append(output);
        if (!explanation.isEmpty()) {
            sb.append('\n').append("Explanation: ").append(explanation);
        }
        return sb.toString();
    }

    private static boolean isValidTestUtilCase(@Nullable String text) {
        return text != null && !text.isEmpty() && text.contains("Input:") && text.contains("Output:");
    }

    private static String buildDescription(JsonObject question, String titleSlug) {
        String content = LeetCodeGraphqlClient.textOrNull(question.get("content"));
        String translated = LeetCodeGraphqlClient.textOrNull(question.get("translatedContent"));
        String plain = firstNonEmpty(htmlToPlain(content), htmlToPlain(translated));
        if (plain.isEmpty()) {
            return titleSlug;
        }
        return plain;
    }

    private static void appendJavadocText(StringBuilder file, String description) {
        String[] lines = description.split("\\R", -1);
        boolean previousBlank = false;
        for (String rawLine : lines) {
            String line = rawLine.trim();
            if (line.isEmpty()) {
                if (!previousBlank) {
                    file.append(" *\n");
                }
                previousBlank = true;
                continue;
            }
            previousBlank = false;
            appendWrappedJavadocLine(file, escapeJavadoc(line), 100);
        }
    }

    private static void appendWrappedJavadocLine(StringBuilder file, String line, int maxLength) {
        if (line.length() <= maxLength) {
            file.append(" * ").append(line).append('\n');
            return;
        }
        int start = 0;
        while (start < line.length()) {
            int end = Math.min(start + maxLength, line.length());
            if (end < line.length()) {
                int space = line.lastIndexOf(' ', end);
                if (space > start + 20) {
                    end = space;
                }
            }
            file.append(" * ").append(line, start, end).append('\n');
            start = end;
            while (start < line.length() && line.charAt(start) == ' ') {
                start++;
            }
        }
    }

    static String htmlToPlain(String html) {
        if (html == null || html.isEmpty()) {
            return "";
        }
        String text = html;
        text = text.replaceAll("(?is)<(script|style)[^>]*>.*?</\\1>", " ");
        text = text.replaceAll("(?i)<pre[^>]*>", "\n");
        text = text.replaceAll("(?i)</pre>", "\n");
        text = text.replaceAll("(?i)<br\\s*/?>", "\n");
        text = text.replaceAll("(?i)</h[1-6]>", "\n\n");
        text = text.replaceAll("(?i)<p[^>]*>", "\n");
        text = text.replaceAll("(?i)</p>", "\n");
        text = text.replaceAll("(?i)</div>", "\n");
        text = text.replaceAll("(?i)</tr>", "\n");
        text = text.replaceAll("(?i)</ul>", "\n");
        text = text.replaceAll("(?i)</ol>", "\n");
        text = text.replaceAll("(?i)<li[^>]*>", "\n- ");
        text = text.replaceAll("<[^>]+>", " ");
        text = text.replace("&nbsp;", " ");
        text = text.replace("&lt;", "<");
        text = text.replace("&gt;", ">");
        text = text.replace("&amp;", "&");
        text = text.replace("&quot;", "\"");
        text = text.replace("&#39;", "'");
        text = text.replace("&apos;", "'");
        text = decodeNumericEntities(text);
        text = text.replaceAll("[ \\t\\x0B\\f\\r]+", " ");
        text = text.replaceAll("(?m)^ +", "");
        text = text.replaceAll("(?m) +$", "");
        text = text.replaceAll("\\n{3,}", "\n\n");
        return text.trim();
    }

    private static String decodeNumericEntities(String text) {
        Matcher matcher = Pattern.compile("&#(x[0-9a-fA-F]+|\\d+);").matcher(text);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String value = matcher.group(1);
            int codePoint;
            try {
                codePoint = value.startsWith("x") || value.startsWith("X")
                        ? Integer.parseInt(value.substring(1), 16)
                        : Integer.parseInt(value);
                matcher.appendReplacement(sb, Matcher.quoteReplacement(new String(Character.toChars(codePoint))));
            } catch (RuntimeException ex) {
                matcher.appendReplacement(sb, " ");
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    static String toClassName(String frontendId) {
        String safe = frontendId.replaceAll("[^A-Za-z0-9_]", "_");
        if (safe.isEmpty()) {
            safe = "Unknown";
        }
        return safe.startsWith("_") ? safe : "_" + safe;
    }

    private static String normalizeDifficulty(String difficulty) {
        if (difficulty == null) {
            return "Medium";
        }
        String lower = difficulty.trim().toLowerCase(Locale.ROOT);
        if (lower.contains("easy")) {
            return "Easy";
        }
        if (lower.contains("hard")) {
            return "Hard";
        }
        return "Medium";
    }

    private static PackageInfo packageForDifficulty(String difficulty) {
        switch (difficulty) {
            case "Easy":
                return new PackageInfo("letcode.normal.easy", "src/main/java/letcode/normal/easy");
            case "Hard":
                return new PackageInfo("letcode.normal.difficult", "src/main/java/letcode/normal/difficult");
            default:
                return new PackageInfo("letcode.normal.medium", "src/main/java/letcode/normal/medium");
        }
    }

    private static String requireText(JsonObject obj, String field) throws IOException {
        String value = LeetCodeGraphqlClient.textOrNull(obj.get(field));
        if (value == null || value.isEmpty()) {
            throw new IOException("Missing field: " + field);
        }
        return value;
    }

    private static String firstNonEmpty(String... values) {
        for (String value : values) {
            if (value != null && !value.trim().isEmpty()) {
                return value.trim();
            }
        }
        return "";
    }

    private static String escapeJavadoc(String text) {
        return text.replace("*/", "* /");
    }

    private static final class CommentSpan {
        private final int start;
        private final int end;

        private CommentSpan(int start, int end) {
            this.start = start;
            this.end = end;
        }

        private boolean isJavadoc(@NotNull String source) {
            return end - start >= 3 && source.startsWith("/**", start);
        }

        private boolean contentContains(@NotNull String source, @NotNull String marker) {
            return source.substring(start, end).contains(marker);
        }
    }

    private static final class PackageInfo {
        private final String packageName;
        private final String relativeDir;

        private PackageInfo(String packageName, String relativeDir) {
            this.packageName = packageName;
            this.relativeDir = relativeDir;
        }
    }

    enum GenerationStatus {
        JAVA_CREATED,
        METADATA_UPDATED,
        METADATA_UNCHANGED
    }

    static final class GenerationResult {
        final GenerationStatus status;
        final Path javaPath;
        @Nullable
        final Path testCasePath;
        @Nullable
        final String questionFrontendId;

        private GenerationResult(GenerationStatus status,
                                 Path javaPath,
                                 @Nullable Path testCasePath,
                                 @Nullable String questionFrontendId) {
            this.status = status;
            this.javaPath = javaPath;
            this.testCasePath = testCasePath;
            this.questionFrontendId = questionFrontendId;
        }

        boolean isJavaCreated() {
            return status == GenerationStatus.JAVA_CREATED;
        }

        boolean isMetadataUpdated() {
            return status == GenerationStatus.METADATA_UPDATED;
        }

        boolean isMetadataUnchanged() {
            return status == GenerationStatus.METADATA_UNCHANGED;
        }

        static GenerationResult created(Path javaPath, @Nullable Path testCasePath, String questionFrontendId) {
            return new GenerationResult(GenerationStatus.JAVA_CREATED, javaPath, testCasePath, questionFrontendId);
        }

        static GenerationResult metadataUpdated(Path javaPath, String questionFrontendId) {
            return new GenerationResult(GenerationStatus.METADATA_UPDATED, javaPath, null, questionFrontendId);
        }

        static GenerationResult metadataUnchanged(Path javaPath, String questionFrontendId) {
            return new GenerationResult(GenerationStatus.METADATA_UNCHANGED, javaPath, null, questionFrontendId);
        }
    }
}
