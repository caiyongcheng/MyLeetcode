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
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTextArea;
import com.intellij.util.ui.JBUI;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
        formatGeneratedJavaFile(project, vf);
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

    // 生成后用 IDE 代码风格再格式化一次，保证与项目一致
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

    @Override
    public void update(@NotNull AnActionEvent e) {
        e.getPresentation().setEnabledAndVisible(e.getProject() != null);
    }

    private static final class LeetCodeConfigDialog extends DialogWrapper {

        private final JTextField endpointField;
        private final JTextField bearerField;
        private final JBTextArea cookieArea;
        private final JTextField csrfField;
        private final JBTextArea extraHeadersArea;
        private final JCheckBox overwriteBox;

        LeetCodeConfigDialog(Project project, LeetCodeSettings settings) {
            super(project);
            setTitle("LeetCode 每日一题设置");
            Dimension fieldSize = JBUI.size(520, JBUI.scale(28));
            endpointField = new JTextField(settings.endpoint);
            endpointField.setPreferredSize(fieldSize);
            bearerField = new JTextField(settings.bearerToken);
            bearerField.setPreferredSize(fieldSize);
            cookieArea = createInputArea(settings.cookie, 3);
            csrfField = new JTextField(settings.csrfToken);
            csrfField.setPreferredSize(fieldSize);
            extraHeadersArea = createInputArea(settings.extraHeaders, 4);
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
            panel.setBorder(JBUI.Borders.empty(4));
            GridBagConstraints c = new GridBagConstraints();
            c.insets = JBUI.insets(2, 0, 8, 0);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1;
            c.gridx = 0;
            c.anchor = GridBagConstraints.NORTHWEST;

            addRow(panel, c, 0, "GraphQL 接口:", endpointField);
            addRow(panel, c, 1, "Bearer Token（可选）:", bearerField);
            addRow(panel, c, 2, "Cookie（可选）:", wrap(cookieArea, 3));
            addRow(panel, c, 3, "CSRF Token（可选）:", csrfField);
            addRow(panel, c, 4, "Extra Headers（每行「名称: 值」，可粘贴 F12 请求头）:", wrap(extraHeadersArea, 4));
            c.gridy = 10;
            c.weighty = 0;
            panel.add(overwriteBox, c);
            c.gridy = 11;
            c.weighty = 1;
            panel.add(new JLabel("<html><body style='width:520px'>配置保存在 IDEA 项目属性中，不会写入 git。</body></html>"), c);
            return panel;
        }

        private static JBTextArea createInputArea(String text, int rows) {
            JBTextArea area = new JBTextArea(text == null ? "" : text);
            area.setLineWrap(true);
            area.setWrapStyleWord(true);
            area.setRows(rows);
            return area;
        }

        private static void addRow(JPanel panel, GridBagConstraints c, int row, String label, JComponent field) {
            c.gridy = row * 2;
            c.weighty = 0;
            c.fill = GridBagConstraints.HORIZONTAL;
            panel.add(new JLabel("<html><body style='width:520px'>" + escapeHtml(label) + "</body></html>"), c);
            c.gridy = row * 2 + 1;
            panel.add(field, c);
        }

        private static JBScrollPane wrap(JBTextArea area, int rows) {
            area.setRows(rows);
            int lineHeight = JBUI.scale(20);
            JBScrollPane scrollPane = new JBScrollPane(area);
            scrollPane.setPreferredSize(JBUI.size(520, lineHeight * rows + JBUI.scale(8)));
            return scrollPane;
        }

        private static String escapeHtml(String text) {
            return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
        }
    }
}
