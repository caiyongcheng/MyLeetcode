package letcode.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.nio.file.Path;

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
        LeetCodeConfigDialog dialog = new LeetCodeConfigDialog(project, settings);
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
                    LeetCodeGraphqlClient client = new LeetCodeGraphqlClient(settings);
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
                            handleResult(project, basePath, settings, result, titleSlug));
                } catch (Exception ex) {
                    ApplicationManager.getApplication().invokeLater(() ->
                            Messages.showErrorDialog(project, ex.getMessage(), ACTION_TITLE));
                }
            }
        });
    }

    private static void handleResult(Project project,
                                     String basePath,
                                     LeetCodeSettings settings,
                                     LeetCodeDailyGenerator.GenerationResult result,
                                     String titleSlug) {
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
                    ACTION_TITLE
            );
            return;
        }
        if (result.questionFrontendId != null) {
            settings.lastDailyQuestionFrontendId = result.questionFrontendId;
            settings.save(project);
        }
        GitAddHelper.addGeneratedFiles(project, basePath, result.javaPath, result.testCasePath);

        StringBuilder msg = new StringBuilder("已生成:\n").append(result.javaPath);
        if (result.testCasePath != null) {
            msg.append("\n").append(result.testCasePath);
        }
        msg.append("\n题目: ").append(titleSlug);
        Messages.showInfoMessage(project, msg.toString(), ACTION_TITLE);
    }

    private static void refreshPath(Path path) {
        LocalFileSystem.getInstance().refreshIoFiles(java.util.Collections.singletonList(path.toFile()));
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        e.getPresentation().setEnabledAndVisible(e.getProject() != null);
    }

    private static final class LeetCodeConfigDialog extends DialogWrapper {

        private final JTextField endpointField;
        private final JTextField bearerField;
        private final JTextArea cookieArea;
        private final JTextField csrfField;
        private final JTextArea extraHeadersArea;
        private final JCheckBox overwriteBox;

        LeetCodeConfigDialog(Project project, LeetCodeSettings settings) {
            super(project);
            setTitle("LeetCode 每日一题设置");
            endpointField = new JTextField(settings.endpoint, 48);
            bearerField = new JTextField(settings.bearerToken, 48);
            cookieArea = new JTextArea(settings.cookie, 3, 48);
            csrfField = new JTextField(settings.csrfToken, 48);
            extraHeadersArea = new JTextArea(settings.extraHeaders, 4, 48);
            overwriteBox = new JCheckBox("覆盖已存在的 Java 文件", settings.overwriteExisting);
            init();
        }

        void applyTo(LeetCodeSettings settings) {
            settings.endpoint = endpointField.getText().trim();
            if (settings.endpoint.isEmpty()) {
                settings.endpoint = LeetCodeSettings.DEFAULT_ENDPOINT;
            }
            settings.bearerToken = bearerField.getText().trim();
            settings.cookie = cookieArea.getText().trim();
            settings.csrfToken = csrfField.getText().trim();
            settings.extraHeaders = extraHeadersArea.getText().trim();
            settings.overwriteExisting = overwriteBox.isSelected();
        }

        @Nullable
        @Override
        protected JComponent createCenterPanel() {
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(4, 4, 4, 4);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1;
            c.gridx = 0;

            addRow(panel, c, 0, "GraphQL 接口:", endpointField);
            addRow(panel, c, 1, "Bearer Token（可选）:", bearerField);
            addRow(panel, c, 2, "Cookie（可选）:", wrap(cookieArea, 3));
            addRow(panel, c, 3, "CSRF Token（可选）:", csrfField);
            addRow(panel, c, 4, "Extra Headers（每行「名称: 值」，可粘贴 F12 请求头）:", wrap(extraHeadersArea, 4));
            c.gridy = 5;
            panel.add(overwriteBox, c);
            c.gridy = 6;
            c.weighty = 1;
            panel.add(new JLabel("<html>配置保存在 IDEA 项目属性中，不会写入 git。</html>"), c);
            return panel;
        }

        private static void addRow(JPanel panel, GridBagConstraints c, int row, String label, JComponent field) {
            c.gridy = row * 2;
            c.weighty = 0;
            panel.add(new JLabel(label), c);
            c.gridy = row * 2 + 1;
            panel.add(field, c);
        }

        private static JScrollPane wrap(JTextArea area, int rows) {
            area.setLineWrap(true);
            area.setWrapStyleWord(true);
            area.setRows(rows);
            return new JScrollPane(area);
        }
    }
}
