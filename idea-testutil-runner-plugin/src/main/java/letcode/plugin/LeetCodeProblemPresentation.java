package letcode.plugin;

import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 工具窗口题目预览用的不可变展示模型。
 */
final class LeetCodeProblemPresentation {

    final String frontendId;
    final String title;
    final String difficulty;
    final String titleSlug;
    final String plainDescription;
    final LeetCodeDailyGenerator.GenerationResult result;

    LeetCodeProblemPresentation(@NotNull String frontendId,
                                @NotNull String title,
                                @NotNull String difficulty,
                                @NotNull String titleSlug,
                                @NotNull String plainDescription,
                                @NotNull LeetCodeDailyGenerator.GenerationResult result) {
        this.frontendId = frontendId;
        this.title = title;
        this.difficulty = difficulty;
        this.titleSlug = titleSlug;
        this.plainDescription = plainDescription;
        this.result = result;
    }

    @NotNull
    static LeetCodeProblemPresentation from(@NotNull JsonObject question,
                                            @NotNull String titleSlug,
                                            @NotNull LeetCodeDailyGenerator.GenerationResult result) {
        String frontendId = firstNonEmpty(
                LeetCodeGraphqlClient.textOrNull(question.get("questionFrontendId")),
                result.questionFrontendId,
                ""
        );
        String title = firstNonEmpty(
                LeetCodeGraphqlClient.textOrNull(question.get("title")),
                LeetCodeGraphqlClient.textOrNull(question.get("translatedTitle")),
                titleSlug
        );
        String difficulty = firstNonEmpty(
                LeetCodeGraphqlClient.textOrNull(question.get("difficulty")),
                ""
        );
        String content = LeetCodeGraphqlClient.textOrNull(question.get("content"));
        String translated = LeetCodeGraphqlClient.textOrNull(question.get("translatedContent"));
        String plain = firstNonEmpty(
                LeetCodeDailyGenerator.htmlToPlain(content),
                LeetCodeDailyGenerator.htmlToPlain(translated)
        );
        return new LeetCodeProblemPresentation(frontendId, title, difficulty, titleSlug, plain, result);
    }

    @NotNull
    private static String firstNonEmpty(@Nullable String... values) {
        if (values == null) {
            return "";
        }
        for (String value : values) {
            if (value != null && !value.isEmpty()) {
                return value;
            }
        }
        return "";
    }
}
