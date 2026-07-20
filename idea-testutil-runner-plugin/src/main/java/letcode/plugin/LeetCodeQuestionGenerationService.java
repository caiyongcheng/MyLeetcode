package letcode.plugin;

import com.google.gson.JsonObject;
import com.intellij.openapi.application.ApplicationManager;
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
 * 每日题与随机题的共享生成流程。
 */
final class LeetCodeQuestionGenerationService {

    private static final int PAGE_SIZE = 50;
    private static final int MAX_PAGES = 10;

    private LeetCodeQuestionGenerationService() {
    }

    static void runDailyGenerate(@NotNull Project project,
                                 @NotNull String basePath,
                                 @NotNull LeetCodeSettings settings,
                                 @NotNull ProgressIndicator indicator) throws Exception {
        LeetCodeGraphqlClient client = new LeetCodeGraphqlClient(project, settings);
        indicator.setText("正在获取每日一题 slug...");
        String titleSlug = client.fetchDailyTitleSlug();
        indicator.setText("正在获取题目详情: " + titleSlug);
        JsonObject question = client.fetchQuestionDetail(titleSlug);
        String frontendId = LeetCodeGraphqlClient.textOrNull(question.get("questionFrontendId"));
        boolean sameDailyAsLast = frontendId != null
                && frontendId.equals(settings.lastDailyQuestionFrontendId);
        LeetCodeDailyGenerator generator = new LeetCodeDailyGenerator(basePath, settings);
        LeetCodeDailyGenerator.GenerationResult result =
                generator.generate(question, titleSlug, sameDailyAsLast);
        ApplicationManager.getApplication().invokeLater(() ->
                handleResult(project, basePath, settings, result, titleSlug, true, "生成 LeetCode 每日一题"));
    }

    static void runRandomGenerate(@NotNull Project project,
                                  @NotNull String basePath,
                                  @NotNull LeetCodeSettings settings,
                                  @NotNull String difficultyLabel,
                                  @NotNull ProgressIndicator indicator) throws Exception {
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
                        generator.generate(question, picked.titleSlug, false);
                if (result.alreadyExists) {
                    continue;
                }

                String titleSlug = picked.titleSlug;
                ApplicationManager.getApplication().invokeLater(() ->
                        handleResult(project, basePath, settings, result, titleSlug, false, "获取随机新题"));
                return;
            }
        }

        throw new IOException("没有可用的 " + difficultyLabel
                + " 题目（项目中已包含、均为付费题，或列表已遍历完毕）");
    }

    private static void handleResult(@NotNull Project project,
                                     @NotNull String basePath,
                                     @NotNull LeetCodeSettings settings,
                                     @NotNull LeetCodeDailyGenerator.GenerationResult result,
                                     @NotNull String titleSlug,
                                     boolean updateLastDailyId,
                                     @NotNull String actionTitle) {
        refreshPath(result.javaPath);
        if (result.testCasePath != null) {
            refreshPath(result.testCasePath);
        }
        VirtualFile vf = LocalFileSystem.getInstance().refreshAndFindFileByPath(result.javaPath.toString());
        if (vf != null) {
            FileEditorManager.getInstance(project).openFile(vf, true);
        }
        if (result.alreadyExists) {
            Messages.showInfoMessage(
                    project,
                    "文件已存在（未覆盖）:\n" + result.javaPath,
                    actionTitle
            );
            return;
        }
        formatGeneratedJavaFile(project, vf);
        if (updateLastDailyId && result.questionFrontendId != null) {
            settings.lastDailyQuestionFrontendId = result.questionFrontendId;
            settings.save(project);
        }
        GitAddHelper.addGeneratedFiles(project, basePath, result.javaPath, result.testCasePath);

        StringBuilder msg = new StringBuilder("已生成:\n").append(result.javaPath);
        if (result.testCasePath != null) {
            msg.append("\n").append(result.testCasePath);
        }
        msg.append("\n题目: ").append(titleSlug);
        Messages.showInfoMessage(project, msg.toString(), actionTitle);
    }

    private static void refreshPath(Path path) {
        LocalFileSystem.getInstance().refreshIoFiles(java.util.Collections.singletonList(path.toFile()));
    }

    private static void formatGeneratedJavaFile(@NotNull Project project, @Nullable VirtualFile vf) {
        if (vf == null) {
            return;
        }
        ApplicationManager.getApplication().runWriteAction(() -> {
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
