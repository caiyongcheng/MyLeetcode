package letcode.plugin;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    private final String projectBasePath;
    private final LeetCodeSettings settings;

    LeetCodeDailyGenerator(@NotNull String projectBasePath, @NotNull LeetCodeSettings settings) {
        this.projectBasePath = projectBasePath;
        this.settings = settings;
    }

    @NotNull
    GenerationResult generate(@NotNull JsonObject question,
                              @NotNull String titleSlug,
                              boolean sameDailyAsLast) throws IOException {
        String frontendId = requireText(question, "questionFrontendId");
        String className = toClassName(frontendId);
        String difficulty = normalizeDifficulty(requireText(question, "difficulty"));
        PackageInfo pkg = packageForDifficulty(difficulty);
        boolean allowOverwrite = settings.overwriteExisting || sameDailyAsLast;

        Path javaPath = Paths.get(projectBasePath, pkg.relativeDir, className + ".java");
        if (Files.exists(javaPath) && !allowOverwrite) {
            return GenerationResult.existing(javaPath);
        }

        String title = firstNonEmpty(
                LeetCodeGraphqlClient.textOrNull(question.get("title")),
                LeetCodeGraphqlClient.textOrNull(question.get("translatedTitle")),
                titleSlug
        );
        String description = buildBriefDescription(question, titleSlug);
        String javaSource = buildJavaSource(pkg, className, frontendId, title, difficulty, titleSlug, description, question);
        Files.createDirectories(javaPath.getParent());
        Files.write(javaPath, javaSource.getBytes(StandardCharsets.UTF_8));

        Path testCasePath = null;
        boolean wroteTestCase = false;
        String testCaseContent = tryBuildTestCaseContent(question);
        if (testCaseContent != null) {
            testCasePath = Paths.get(projectBasePath, "src/main/resources", "TestCase_" + className.substring(1) + ".txt");
            if (!Files.exists(testCasePath) || allowOverwrite) {
                Files.createDirectories(testCasePath.getParent());
                Files.write(testCasePath, testCaseContent.getBytes(StandardCharsets.UTF_8));
                wroteTestCase = true;
            } else {
                testCasePath = null;
            }
        }

        return GenerationResult.created(javaPath, wroteTestCase ? testCasePath : null, frontendId);
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
        String link = "https://leetcode.cn/problems/" + titleSlug + "/";

        StringBuilder file = new StringBuilder();
        file.append("package ").append(pkg.packageName).append(";\n\n");
        file.append("/**\n");
        file.append(" * ").append(escapeJavadoc(frontendId)).append(". ").append(escapeJavadoc(title)).append('\n');
        file.append(" * Difficulty: ").append(escapeJavadoc(difficulty)).append('\n');
        file.append(" * Link: ").append(escapeJavadoc(link)).append('\n');
        if (!description.isEmpty()) {
            file.append(" * ").append(escapeJavadoc(description)).append('\n');
        }
        file.append(" */\n");
        file.append(body);
        if (!body.endsWith("\n")) {
            file.append('\n');
        }
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
        return insertBlankLineAfterClassOpen(body, className).trim() + "\n";
    }

    private static String insertBlankLineAfterClassOpen(String body, String className) {
        Pattern open = Pattern.compile(
                "(public\\s+class\\s+" + Pattern.quote(className) + "\\s*\\{)\\s*",
                Pattern.MULTILINE
        );
        Matcher matcher = open.matcher(body);
        return matcher.find() ? matcher.replaceFirst("$1\n\n") : body;
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

    private static String buildBriefDescription(JsonObject question, String titleSlug) {
        String content = LeetCodeGraphqlClient.textOrNull(question.get("content"));
        String translated = LeetCodeGraphqlClient.textOrNull(question.get("translatedContent"));
        String plain = firstNonEmpty(htmlToPlain(content), htmlToPlain(translated));
        if (plain.isEmpty()) {
            return titleSlug;
        }
        int exampleIdx = indexOfExample(plain);
        String head = exampleIdx >= 0 ? plain.substring(0, exampleIdx) : plain;
        head = head.replaceAll("\\s+", " ").trim();
        return head.length() > 400 ? head.substring(0, 400) + "..." : head;
    }

    private static int indexOfExample(String plain) {
        Matcher matcher = Pattern.compile("Example\\s*\\d+", Pattern.CASE_INSENSITIVE).matcher(plain);
        return matcher.find() ? matcher.start() : -1;
    }

    static String htmlToPlain(String html) {
        if (html == null || html.isEmpty()) {
            return "";
        }
        String text = html;
        text = text.replaceAll("(?is)<(script|style)[^>]*>.*?</\\1>", " ");
        text = text.replaceAll("(?i)<br\\s*/?>", "\n");
        text = text.replaceAll("(?i)</p>", "\n");
        text = text.replaceAll("(?i)<li>", "\n- ");
        text = text.replaceAll("<[^>]+>", " ");
        text = text.replace("&nbsp;", " ");
        text = text.replace("&lt;", "<");
        text = text.replace("&gt;", ">");
        text = text.replace("&amp;", "&");
        text = text.replace("&quot;", "\"");
        text = text.replace("&#39;", "'");
        text = text.replaceAll("[ \\t\\x0B\\f\\r]+", " ");
        text = text.replaceAll("\\n{3,}", "\n\n");
        return text.trim();
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

    private static final class PackageInfo {
        private final String packageName;
        private final String relativeDir;

        private PackageInfo(String packageName, String relativeDir) {
            this.packageName = packageName;
            this.relativeDir = relativeDir;
        }
    }

    static final class GenerationResult {
        final boolean alreadyExists;
        final Path javaPath;
        @Nullable
        final Path testCasePath;
        @Nullable
        final String questionFrontendId;

        private GenerationResult(boolean alreadyExists,
                                 Path javaPath,
                                 @Nullable Path testCasePath,
                                 @Nullable String questionFrontendId) {
            this.alreadyExists = alreadyExists;
            this.javaPath = javaPath;
            this.testCasePath = testCasePath;
            this.questionFrontendId = questionFrontendId;
        }

        static GenerationResult existing(Path javaPath) {
            return new GenerationResult(true, javaPath, null, null);
        }

        static GenerationResult created(Path javaPath, @Nullable Path testCasePath, String questionFrontendId) {
            return new GenerationResult(false, javaPath, testCasePath, questionFrontendId);
        }
    }
}
