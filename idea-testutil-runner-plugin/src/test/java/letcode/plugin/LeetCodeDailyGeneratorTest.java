package letcode.plugin;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LeetCodeDailyGeneratorTest {

    @Test
    void htmlToPlainPreservesPreBlockLines() {
        String html = "<p><strong class=\"example\">示例 1：</strong></p><pre><strong>输入：</strong>nums = [2,7,11,15], target = 9\n"
                + "<strong>输出：</strong>[0,1]</pre>";
        String plain = LeetCodeDailyGenerator.htmlToPlain(html);
        assertTrue(plain.contains("输入："));
        assertTrue(plain.contains("nums = [2,7,11,15], target = 9"));
        assertTrue(plain.contains("输出："));
    }

    @Test
    void extractsChineseExamplesForTestCase() throws Exception {
        String json = new String(
                Files.readAllBytes(fixturePath("two-sum-question.json")),
                StandardCharsets.UTF_8
        );
        JsonObject question = JsonParser.parseString(json).getAsJsonObject();
        Path tempDir = Files.createTempDirectory("leetcode-plugin-test");
        try {
            LeetCodeSettings settings = new LeetCodeSettings();
            settings.endpoint = "https://leetcode.cn/graphql/";
            LeetCodeDailyGenerator generator = new LeetCodeDailyGenerator(tempDir.toString(), settings);
            String testCase = invokeTryBuildTestCaseContent(generator, question);
            assertNotNull(testCase);
            assertTrue(testCase.contains("Input:"));
            assertTrue(testCase.contains("Output:"));
            assertTrue(testCase.contains("[0,1]"));
        } finally {
            deleteRecursively(tempDir);
        }
    }

    @Test
    void prefersTranslatedDescriptionForCnEndpoint() throws Exception {
        String json = new String(
                Files.readAllBytes(fixturePath("two-sum-question.json")),
                StandardCharsets.UTF_8
        );
        JsonObject question = JsonParser.parseString(json).getAsJsonObject();
        Path tempDir = Files.createTempDirectory("leetcode-plugin-test");
        try {
            LeetCodeSettings settings = new LeetCodeSettings();
            settings.endpoint = "https://leetcode.cn/graphql/";
            settings.overwriteExisting = true;
            LeetCodeDailyGenerator.GenerationResult result =
                    new LeetCodeDailyGenerator(tempDir.toString(), settings).generate(question, "two-sum", false);
            String source = new String(Files.readAllBytes(result.javaPath), StandardCharsets.UTF_8);
            assertTrue(source.contains("两数之和") || source.contains("整数数组"));
            assertTrue(source.contains("Link: https://leetcode.cn/problems/two-sum/"));
        } finally {
            deleteRecursively(tempDir);
        }
    }

    private static Path fixturePath(String name) {
        return Path.of("src", "test", "resources", name);
    }

    private static String invokeTryBuildTestCaseContent(LeetCodeDailyGenerator generator, JsonObject question)
            throws Exception {
        var method = LeetCodeDailyGenerator.class.getDeclaredMethod("tryBuildTestCaseContent", JsonObject.class);
        method.setAccessible(true);
        return (String) method.invoke(generator, question);
    }

    private static void deleteRecursively(Path root) throws Exception {
        if (!Files.exists(root)) {
            return;
        }
        try (var paths = Files.walk(root)) {
            paths.sorted((a, b) -> b.compareTo(a)).forEach(path -> {
                try {
                    Files.deleteIfExists(path);
                } catch (Exception ignored) {
                    // best effort cleanup for temp test dirs
                }
            });
        }
    }
}
