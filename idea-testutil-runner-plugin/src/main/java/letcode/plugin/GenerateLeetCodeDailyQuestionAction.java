package letcode.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

/**
 * Tools 菜单：拉取 LeetCode 每日一题并在项目中生成 Java 题解骨架。
 */
public class GenerateLeetCodeDailyQuestionAction extends AnAction {

    private static final String ACTION_TITLE = "生成 LeetCode 每日一题";

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) {
            return;
        }
        String basePath = project.getBasePath();
        if (basePath == null || basePath.isEmpty()) {
            Messages.showErrorDialog(project, "无法获取项目根目录。", ACTION_TITLE);
            return;
        }

        LeetCodeSettings settings = LeetCodeSettings.load(project);
        LeetCodeConfigDialog dialog = new LeetCodeConfigDialog(project, settings, "LeetCode 每日一题设置");
        if (!dialog.showAndGet()) {
            return;
        }
        dialog.applyTo(settings);
        settings.save(project);

        ProgressManager.getInstance().run(new Task.Backgroundable(project, "正在生成 LeetCode 每日一题", true) {
            @Override
            public void run(@NotNull ProgressIndicator indicator) {
                indicator.setIndeterminate(true);
                try {
                    LeetCodeQuestionGenerationService.runDailyGenerate(project, basePath, settings, indicator);
                } catch (Exception ex) {
                    ApplicationManager.getApplication().invokeLater(() ->
                            Messages.showErrorDialog(project, ex.getMessage(), ACTION_TITLE));
                }
            }
        });
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        e.getPresentation().setEnabledAndVisible(e.getProject() != null);
    }
}
