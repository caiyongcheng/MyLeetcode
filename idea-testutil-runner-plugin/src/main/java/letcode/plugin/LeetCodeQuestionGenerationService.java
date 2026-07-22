package letcode.plugin;

import com.google.gson.JsonObject;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.codeStyle.CodeStyleManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * 每日题、随机题与指定题的共享生成流程。
 */
final class LeetCodeQuestionGenerationService {

    private static final int PAGE_SIZE = 100;
    // 题库按题号排序，用户本地已刷数百题时需要继续翻页，不能只检查前 500 题。
    private static final int MAX_PAGES = 100;

    private LeetCodeQuestionGenerationService() {
    }

    @FunctionalInterface
    interface GenerationCompletionListener {
        void onComplete(@NotNull LeetCodeProblemPresentation presentation);
    }

    static void runDailyGenerate(@NotNull Project project,
                                 @NotNull String basePath,
                                 @NotNull LeetCodeSettings settings,
                                 @NotNull ProgressIndicator indicator) throws Exception {
        runDailyGenerate(project, basePath, settings, indicator, null);
    }

    static void runDailyGenerate(@NotNull Project project,
                                 @NotNull String basePath,
                                 @NotNull LeetCodeSettings settings,
                                 @NotNull ProgressIndicator indicator,
                                 @Nullable GenerationCompletionListener listener) throws Exception {
        LeetCodeGraphqlClient client = new LeetCodeGraphqlClient(project, settings);
        indicator.setText("正在获取每日一题 slug...");
        String titleSlug = client.fetchDailyTitleSlug();
        indicator.setText("正在获取题目详情: " + titleSlug);
        JsonObject question = client.fetchQuestionDetail(titleSlug);
        LeetCodeDailyGenerator generator = new LeetCodeDailyGenerator(basePath, settings);
        LeetCodeDailyGenerator.GenerationResult result = generator.generate(question, titleSlug);
        LeetCodeProblemPresentation presentation = listener == null
                ? null
                : LeetCodeProblemPresentation.from(question, titleSlug, result);
        ApplicationManager.getApplication().invokeLater(() ->
                handleResult(project, basePath, settings, result, titleSlug, true,
                        "生成 LeetCode 每日一题", presentation, listener));
    }

    static void runRandomGenerate(@NotNull Project project,
                                  @NotNull String basePath,
                                  @NotNull LeetCodeSettings settings,
                                  @NotNull String difficultyLabel,
                                  @NotNull ProgressIndicator indicator) throws Exception {
        runRandomGenerate(project, basePath, settings, difficultyLabel, indicator, null);
    }

    static void runRandomGenerate(@NotNull Project project,
                                  @NotNull String basePath,
                                  @NotNull LeetCodeSettings settings,
                                  @NotNull String difficultyLabel,
                                  @NotNull ProgressIndicator indicator,
                                  @Nullable GenerationCompletionListener listener) throws Exception {
        String graphqlDifficulty = toGraphqlDifficulty(difficultyLabel);
        LeetCodeGraphqlClient client = new LeetCodeGraphqlClient(project, settings);
        LeetCodeQuestionSelector selector = new LeetCodeQuestionSelector(basePath);
        LeetCodeDailyGenerator generator = new LeetCodeDailyGenerator(basePath, settings);
        Random random = new Random();

        for (int page = 0; page < MAX_PAGES; page++) {
            indicator.setText("正在获取 " + difficultyLabel + " 题目列表（第 " + (page + 1) + " 页）...");
            List<LeetCodeGraphqlClient.QuestionListItem> pageItems =
                    client.fetchProblemsetByDifficulty(graphqlDifficulty, PAGE_SIZE, page * PAGE_SIZE);
            if (pageItems.isEmpty()) {
                break;
            }

            List<LeetCodeGraphqlClient.QuestionListItem> candidates = new ArrayList<>();
            for (LeetCodeGraphqlClient.QuestionListItem item : pageItems) {
                if (item.paidOnly) {
                    continue;
                }
                if (item.questionFrontendId == null || item.questionFrontendId.isEmpty()) {
                    continue;
                }
                if (selector.exists(item.questionFrontendId)) {
                    continue;
                }
                candidates.add(item);
            }
            if (candidates.isEmpty()) {
                continue;
            }

            Collections.shuffle(candidates, random);
            for (LeetCodeGraphqlClient.QuestionListItem picked : candidates) {
                if (selector.exists(picked.questionFrontendId)) {
                    continue;
                }
                indicator.setText("正在获取题目详情: " + picked.titleSlug);
                JsonObject question = client.fetchQuestionDetail(picked.titleSlug);
                String frontendId = LeetCodeGraphqlClient.textOrNull(question.get("questionFrontendId"));
                if (frontendId != null && selector.exists(frontendId)) {
                    continue;
                }

                LeetCodeDailyGenerator.GenerationResult result =
                        generator.generate(question, picked.titleSlug);
                if (!result.isJavaCreated()) {
                    continue;
                }

                String titleSlug = picked.titleSlug;
                LeetCodeProblemPresentation presentation = listener == null
                        ? null
                        : LeetCodeProblemPresentation.from(question, titleSlug, result);
                ApplicationManager.getApplication().invokeLater(() ->
                        handleResult(project, basePath, settings, result, titleSlug, false,
                                "获取随机新题", presentation, listener));
                return;
            }

            // 最后一页不足一整页时，后续请求不会再返回新题。
            if (pageItems.size() < PAGE_SIZE) {
                break;
            }
        }

        throw new IOException("没有可用的 " + difficultyLabel
                + " 题目（项目中已包含、均为付费题，或列表已遍历完毕）");
    }

    static void runSpecifiedGenerate(@NotNull Project project,
                                     @NotNull String basePath,
                                     @NotNull LeetCodeSettings settings,
                                     @NotNull String frontendId,
                                     @NotNull ProgressIndicator indicator) throws Exception {
        runSpecifiedGenerate(project, basePath, settings, frontendId, indicator, null);
    }

    static void runSpecifiedGenerate(@NotNull Project project,
                                     @NotNull String basePath,
                                     @NotNull LeetCodeSettings settings,
                                     @NotNull String frontendId,
                                     @NotNull ProgressIndicator indicator,
                                     @Nullable GenerationCompletionListener listener) throws Exception {
        LeetCodeGraphqlClient client = new LeetCodeGraphqlClient(project, settings);
        indicator.setText("正在按题号检索: " + frontendId);
        LeetCodeGraphqlClient.QuestionListItem item = client.fetchQuestionListItemByFrontendId(frontendId);
        if (item.paidOnly) {
            throw new IOException("题号 " + frontendId + " 为付费题目，无法下载");
        }

        indicator.setText("正在获取题目详情: " + item.titleSlug);
        JsonObject question = client.fetchQuestionDetail(item.titleSlug);
        String actualFrontendId = LeetCodeGraphqlClient.textOrNull(question.get("questionFrontendId"));
        if (actualFrontendId == null || !frontendId.equals(actualFrontendId)) {
            throw new IOException("题号不一致：请求 " + frontendId + "，实际得到 "
                    + (actualFrontendId == null ? "空" : actualFrontendId));
        }

        LeetCodeDailyGenerator generator = new LeetCodeDailyGenerator(basePath, settings);
        LeetCodeDailyGenerator.GenerationResult result =
                generator.generate(question, item.titleSlug);
        String titleSlug = item.titleSlug;
        LeetCodeProblemPresentation presentation = listener == null
                ? null
                : LeetCodeProblemPresentation.from(question, titleSlug, result);
        ApplicationManager.getApplication().invokeLater(() ->
                handleResult(project, basePath, settings, result, titleSlug, false,
                        "下载指定题目", presentation, listener));
    }

    private static void handleResult(@NotNull Project project,
                                     @NotNull String basePath,
                                     @NotNull LeetCodeSettings settings,
                                     @NotNull LeetCodeDailyGenerator.GenerationResult result,
                                     @NotNull String titleSlug,
                                     boolean updateLastDailyId,
                                     @NotNull String actionTitle,
                                     @Nullable LeetCodeProblemPresentation presentation,
                                     @Nullable GenerationCompletionListener listener) {
        refreshPath(result.javaPath);
        if (result.testCasePath != null) {
            refreshPath(result.testCasePath);
        }
        VirtualFile vf = LocalFileSystem.getInstance().refreshAndFindFileByPath(result.javaPath.toString());
        if (vf != null) {
            FileEditorManager.getInstance(project).openFile(vf, true);
        }

        if (updateLastDailyId && result.questionFrontendId != null) {
            settings.lastDailyQuestionFrontendId = result.questionFrontendId;
            settings.save(project);
        }

        if (result.isJavaCreated()) {
            formatGeneratedJavaFile(project, vf);
            GitAddHelper.addGeneratedFiles(project, basePath, result.javaPath, result.testCasePath);
            if (listener != null && presentation != null) {
                listener.onComplete(presentation);
                return;
            }
            StringBuilder msg = new StringBuilder("已生成:\n").append(result.javaPath);
            if (result.testCasePath != null) {
                msg.append("\n").append(result.testCasePath);
            }
            msg.append("\n题目: ").append(titleSlug);
            Messages.showInfoMessage(project, msg.toString(), actionTitle);
            return;
        }

        if (listener != null && presentation != null) {
            listener.onComplete(presentation);
            return;
        }

        if (result.isMetadataUpdated()) {
            Messages.showInfoMessage(
                    project,
                    "已更新题目注释，保留原有实现\n" + result.javaPath + "\n题目: " + titleSlug,
                    actionTitle
            );
            return;
        }

        Messages.showInfoMessage(
                project,
                "题目注释无需更新，保留原有实现\n" + result.javaPath + "\n题目: " + titleSlug,
                actionTitle
        );
    }

    private static void refreshPath(Path path) {
        LocalFileSystem.getInstance().refreshIoFiles(java.util.Collections.singletonList(path.toFile()));
    }

    private static void formatGeneratedJavaFile(@NotNull Project project, @Nullable VirtualFile vf) {
        if (vf == null) {
            return;
        }
        // 格式化会修改 PSI，必须在可撤销的 IDEA 写命令中执行。
        WriteCommandAction.runWriteCommandAction(project, () -> {
            PsiFile psiFile = PsiManager.getInstance(project).findFile(vf);
            if (psiFile != null) {
                CodeStyleManager.getInstance(project).reformat(psiFile);
            }
        });
    }

    @NotNull
    private static String toGraphqlDifficulty(@NotNull String difficultyLabel) {
        String lower = difficultyLabel.trim().toLowerCase(Locale.ROOT);
        if ("easy".equals(lower)) {
            return "EASY";
        }
        if ("hard".equals(lower)) {
            return "HARD";
        }
        return "MEDIUM";
    }
}
