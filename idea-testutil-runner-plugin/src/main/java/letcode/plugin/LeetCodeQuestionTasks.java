package letcode.plugin;

import com.google.gson.JsonObject;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.codeStyle.CodeStyleManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.Collections;

/**
 * 拉取并生成 LeetCode 题解的共享后台任务。
 */
final class LeetCodeQuestionTasks {

    private LeetCodeQuestionTasks() {
    }

    static void generateDaily(@NotNull Project project) {
        if (!ensureProjectBase(project)) {
            return;
        }
        LeetCodeSettings settings = LeetCodeSettings.load(project);
        if (!LeetCodeConfigUi.ensureConfigured(project, settings)) {
            return;
        }
        String basePath = project.getBasePath();
        ProgressManager.getInstance().run(new Task.Backgroundable(project, "正在生成 LeetCode 每日一题", true) {
            @Override
            public void run(@NotNull ProgressIndicator indicator) {
                indicator.setIndeterminate(true);
                try {
                    LeetCodeGraphqlClient client = new LeetCodeGraphqlClient(settings);
                    indicator.setText("正在获取每日一题 slug...");
                    String titleSlug = client.fetchDailyTitleSlug();
                    generateQuestion(project, basePath, settings, client, titleSlug, true, indicator);
                } catch (Exception ex) {
                    showError(project, "生成 LeetCode 每日一题", ex.getMessage());
                }
            }
        });
    }

    static void generateRandom(@NotNull Project project, @NotNull LeetCodeDifficulty difficulty) {
        if (!ensureProjectBase(project)) {
            return;
        }
        LeetCodeSettings settings = LeetCodeSettings.load(project);
        if (!LeetCodeConfigUi.ensureConfigured(project, settings)) {
            return;
        }
        String basePath = project.getBasePath();
        String difficultyLabel = difficulty.label;
        ProgressManager.getInstance().run(new Task.Backgroundable(project, "正在随机拉取 LeetCode 题目", true) {
            @Override
            public void run(@NotNull ProgressIndicator indicator) {
                indicator.setIndeterminate(true);
                try {
                    LeetCodeGraphqlClient client = new LeetCodeGraphqlClient(settings);
                    indicator.setText("正在随机获取题目（" + difficultyLabel + "）...");
                    String titleSlug = client.fetchRandomTitleSlug(difficulty);
                    generateQuestion(project, basePath, settings, client, titleSlug, false, indicator);
                } catch (Exception ex) {
                    showError(project, "随机拉取 LeetCode 题目", ex.getMessage());
                }
            }
        });
    }

    private static void generateQuestion(@NotNull Project project,
                                         @NotNull String basePath,
                                         @NotNull LeetCodeSettings settings,
                                         @NotNull LeetCodeGraphqlClient client,
                                         @NotNull String titleSlug,
                                         boolean daily,
                                         @NotNull ProgressIndicator indicator) throws Exception {
        indicator.setText("正在获取题目详情: " + titleSlug);
        JsonObject question = client.fetchQuestionDetail(titleSlug);
        String frontendId = LeetCodeGraphqlClient.textOrNull(question.get("questionFrontendId"));
        boolean sameDailyAsLast = daily
                && frontendId != null
                && frontendId.equals(settings.lastDailyQuestionFrontendId);
        LeetCodeDailyGenerator generator = new LeetCodeDailyGenerator(basePath, settings);
        LeetCodeDailyGenerator.GenerationResult result =
                generator.generate(question, titleSlug, sameDailyAsLast);
        ApplicationManager.getApplication().invokeLater(() ->
                handleResult(project, basePath, settings, result, titleSlug, daily));
    }

    private static void handleResult(@NotNull Project project,
                                     @NotNull String basePath,
                                     @NotNull LeetCodeSettings settings,
                                     @NotNull LeetCodeDailyGenerator.GenerationResult result,
                                     @NotNull String titleSlug,
                                     boolean daily) {
        String title = daily ? "生成 LeetCode 每日一题" : "随机拉取 LeetCode 题目";
        refreshPath(result.javaPath);
        if (result.testCasePath != null) {
            refreshPath(result.testCasePath);
        }
        VirtualFile vf = LocalFileSystem.getInstance().refreshAndFindFileByPath(result.javaPath.toString());
        if (vf != null) {
            FileEditorManager.getInstance(project).openFile(vf, true);
        }
        if (result.alreadyExists) {
            Messages.showInfoMessage(project, "文件已存在（未覆盖）:\n" + result.javaPath, title);
            return;
        }
        formatGeneratedJavaFile(project, vf);
        if (daily && result.questionFrontendId != null) {
            settings.lastDailyQuestionFrontendId = result.questionFrontendId;
            settings.save(project);
        }
        GitAddHelper.addGeneratedFiles(project, basePath, result.javaPath, result.testCasePath);

        StringBuilder msg = new StringBuilder("已生成:\n").append(result.javaPath);
        if (result.testCasePath != null) {
            msg.append("\n").append(result.testCasePath);
        }
        msg.append("\n题目: ").append(titleSlug);
        Messages.showInfoMessage(project, msg.toString(), title);
    }

    private static boolean ensureProjectBase(@NotNull Project project) {
        String basePath = project.getBasePath();
        if (basePath == null || basePath.isEmpty()) {
            Messages.showErrorDialog(project, "无法获取项目根目录。", "LeetCode 插件");
            return false;
        }
        return true;
    }

    private static void refreshPath(Path path) {
        LocalFileSystem.getInstance().refreshIoFiles(Collections.singletonList(path.toFile()));
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

    private static void showError(@NotNull Project project, @NotNull String title, @Nullable String message) {
        ApplicationManager.getApplication().invokeLater(() ->
                Messages.showErrorDialog(project, message == null ? "未知错误" : message, title));
    }
}
