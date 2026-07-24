package letcode.plugin;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.nio.file.Path;

/**
 * LeetCode 工具窗口工作台：紧凑操作区 + 题目预览。
 */
final class LeetCodeToolWindowPanel extends JPanel {

    private static final String[] DIFFICULTIES = {"Easy", "Medium", "Hard"};
    private static final String EMPTY_PREVIEW = "";

    private final Project project;
    private final JLabel loginStatusLabel;
    private final JButton settingsButton;
    private final JButton dailyButton;
    private final JComboBox<String> sourceCombo;
    private final JComboBox<String> difficultyBox;
    private final JTextField frontendIdField;
    private final JPanel sourceParamPanel;
    private final JButton fetchButton;
    private final JButton downloadPreviewButton;
    private final JButton testButton;
    private final JButton submitButton;
    private final JButton openSolutionButton;
    private final JLabel statusLabel;
    private final JLabel previewIdLabel;
    private final JLabel previewTitleLabel;
    private final JLabel previewDifficultyLabel;
    private final JLabel previewMetaLabel;
    private final JTextArea previewBody;

    @Nullable
    private LeetCodeProblemPresentation currentPresentation;

    LeetCodeToolWindowPanel(@NotNull Project project) {
        super(new BorderLayout());
        this.project = project;

        loginStatusLabel = new JLabel();
        settingsButton = new JButton(AllIcons.General.GearPlain);
        settingsButton.setToolTipText("LeetCode 设置");
        settingsButton.setMargin(JBUI.insets(2));

        dailyButton = new JButton("每日一题", AllIcons.Actions.Refresh);
        sourceCombo = new JComboBox<>(new String[]{"随机题", "指定题"});
        difficultyBox = new JComboBox<>(DIFFICULTIES);
        difficultyBox.setSelectedItem("Medium");
        frontendIdField = new JTextField();
        frontendIdField.setColumns(6);
        sourceParamPanel = new JPanel(new CardLayout());
        sourceParamPanel.add(difficultyBox, "随机题");
        sourceParamPanel.add(frontendIdField, "指定题");
        fetchButton = new JButton("获取题目", AllIcons.Actions.Find);
        downloadPreviewButton = new JButton("下载当前题目", AllIcons.Actions.Download);
        downloadPreviewButton.setEnabled(false);
        testButton = new JButton("运行 TestUtil", AllIcons.Actions.Execute);
        submitButton = new JButton("提交题解", AllIcons.Actions.Upload);
        openSolutionButton = new JButton("打开题解", AllIcons.Actions.EditSource);
        openSolutionButton.setEnabled(false);

        statusLabel = new JLabel("就绪");
        previewIdLabel = new JLabel("题号: —");
        previewTitleLabel = new JLabel("标题: —");
        previewDifficultyLabel = new JLabel("难度: —");
        previewMetaLabel = new JLabel("链接/路径: —");
        previewBody = new JTextArea(EMPTY_PREVIEW);
        previewBody.setEditable(false);
        previewBody.setLineWrap(true);
        previewBody.setWrapStyleWord(true);
        previewBody.setBorder(JBUI.Borders.empty(4));

        JPanel root = new JPanel(new BorderLayout(0, 6));
        root.setBorder(JBUI.Borders.empty(8, 8, 8, 8));
        root.add(buildHeader(), BorderLayout.NORTH);
        root.add(buildWorkbench(), BorderLayout.CENTER);
        add(root, BorderLayout.CENTER);

        refreshLoginStatus();
        settingsButton.addActionListener(event -> onSettingsClicked());
        dailyButton.addActionListener(event -> onDailyClicked());
        sourceCombo.addActionListener(event -> onSourceChanged());
        fetchButton.addActionListener(event -> onFetchClicked());
        downloadPreviewButton.addActionListener(event -> onDownloadPreviewClicked());
        testButton.addActionListener(event -> onTestClicked());
        submitButton.addActionListener(event -> onSubmitClicked());
        openSolutionButton.addActionListener(event -> onOpenSolutionClicked());
        // 工具窗口打开后优先输入题号，避免键盘焦点仍停留在编辑器。
        ApplicationManager.getApplication().invokeLater(sourceCombo::requestFocusInWindow);
    }

    private JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout(8, 0));
        JLabel title = new JLabel("LeetCode");
        Font base = title.getFont();
        title.setFont(base.deriveFont(Font.BOLD, base.getSize() + 1f));
        header.add(title, BorderLayout.WEST);

        JPanel right = new JPanel(new BorderLayout(6, 0));
        loginStatusLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        right.add(loginStatusLabel, BorderLayout.CENTER);
        right.add(settingsButton, BorderLayout.EAST);
        header.add(right, BorderLayout.EAST);
        return header;
    }

    private JPanel buildWorkbench() {
        JPanel workbench = new JPanel(new BorderLayout(0, 6));
        workbench.add(buildControls(), BorderLayout.NORTH);
        workbench.add(buildPreview(), BorderLayout.CENTER);
        return workbench;
    }

    private JPanel buildControls() {
        JPanel controls = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = baseConstraints();
        int row = 0;

        addFullWidth(controls, constraints, row++, dailyButton);
        addFullWidth(controls, constraints, row++, buildUnifiedFetchRow());
        addFullWidth(controls, constraints, row++, buildCurrentClassRow());
        addFullWidth(controls, constraints, row, statusLabel);
        return controls;
    }

    private JPanel buildPreview() {
        JPanel preview = new JPanel(new BorderLayout(0, 4));
        JPanel meta = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = baseConstraints();
        int row = 0;
        addFullWidth(meta, constraints, row++, compactLabel("题目预览"));
        addFullWidth(meta, constraints, row++, previewIdLabel);
        addFullWidth(meta, constraints, row++, previewTitleLabel);
        addFullWidth(meta, constraints, row++, previewDifficultyLabel);
        addFullWidth(meta, constraints, row++, previewMetaLabel);
        addFullWidth(meta, constraints, row++, downloadPreviewButton);
        addFullWidth(meta, constraints, row, openSolutionButton);

        JScrollPane scroll = new JScrollPane(previewBody);
        scroll.setBorder(JBUI.Borders.empty());
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        preview.add(meta, BorderLayout.NORTH);
        preview.add(scroll, BorderLayout.CENTER);
        return preview;
    }

    private JPanel buildUnifiedFetchRow() {
        JPanel row = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = baseConstraints();
        constraints.weighty = 0;
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 0;

        sourceCombo.setPreferredSize(new Dimension(84, sourceCombo.getPreferredSize().height));
        row.add(sourceCombo, constraints);

        constraints.gridx = 1;
        constraints.insets = new Insets(0, 6, 0, 6);
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        sourceParamPanel.setPreferredSize(new Dimension(20, difficultyBox.getPreferredSize().height));
        row.add(sourceParamPanel, constraints);

        constraints.gridx = 2;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.weightx = 0;
        constraints.fill = GridBagConstraints.NONE;
        row.add(fetchButton, constraints);
        return row;
    }

    private JPanel buildCurrentClassRow() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 8, 0));
        panel.add(testButton);
        panel.add(submitButton);
        return panel;
    }

    private static JLabel compactLabel(String text) {
        JLabel label = new JLabel(text);
        Font font = label.getFont();
        label.setFont(font.deriveFont(Font.BOLD));
        return label;
    }

    private static void addFullWidth(JPanel panel,
                                     GridBagConstraints constraints,
                                     int row,
                                     java.awt.Component component) {
        constraints.gridx = 0;
        constraints.gridy = row;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(0, 0, 6, 0);
        panel.add(component, constraints);
    }

    private static GridBagConstraints baseConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        return constraints;
    }

    private void onSettingsClicked() {
        LeetCodeSettings settings = LeetCodeSettings.load(project);
        LeetCodeConfigDialog dialog = new LeetCodeConfigDialog(project, settings, "LeetCode 设置");
        if (!dialog.showAndGet()) {
            return;
        }
        dialog.applyTo(settings);
        settings.save(project);
        refreshLoginStatus();
        setStatus("设置已保存");
    }

    private void onDailyClicked() {
        String basePath = requireBasePath("生成每日一题");
        if (basePath == null) {
            return;
        }
        LeetCodeSettings settings = LeetCodeSettings.load(project);
        runGenerationTask("正在生成 LeetCode 每日一题", "生成 LeetCode 每日一题",
                indicator -> LeetCodeQuestionGenerationService.runDailyGenerate(
                        project, basePath, settings, indicator, this::onGenerationComplete));
    }

    private void onFetchClicked() {
        String source = (String) sourceCombo.getSelectedItem();
        if ("随机题".equals(source)) {
            fetchRandomPreview();
        } else {
            fetchSpecifiedPreview();
        }
    }

    /** 切换题目来源时，切换对应的参数控件（难度下拉框 / 题号输入框）。 */
    private void onSourceChanged() {
        String source = (String) sourceCombo.getSelectedItem();
        CardLayout cl = (CardLayout) sourceParamPanel.getLayout();
        cl.show(sourceParamPanel, source);
        if ("指定题".equals(source)) {
            frontendIdField.requestFocusInWindow();
        } else {
            difficultyBox.requestFocusInWindow();
        }
    }

    private void fetchRandomPreview() {
        String basePath = requireBasePath("预览随机新题");
        if (basePath == null) {
            return;
        }
        String difficulty = (String) difficultyBox.getSelectedItem();
        if (difficulty == null || difficulty.isEmpty()) {
            difficulty = "Medium";
        }
        LeetCodeSettings settings = LeetCodeSettings.load(project);
        final String selectedDifficulty = difficulty;
        clearPreviewForNewRequest();
        runGenerationTask("正在预览随机新题", "预览随机新题",
                indicator -> LeetCodeQuestionGenerationService.runRandomPreview(
                        project, basePath, settings, selectedDifficulty, indicator, this::onPreviewComplete));
    }

    private void fetchSpecifiedPreview() {
        String frontendId = frontendIdField.getText() == null ? "" : frontendIdField.getText().trim();
        if (frontendId.isEmpty()) {
            Messages.showErrorDialog(project, "请输入题号。", "预览指定题目");
            return;
        }
        if (!frontendId.matches("^[1-9]\\d*$")) {
            Messages.showErrorDialog(project, "题号必须是正整数。", "预览指定题目");
            return;
        }
        LeetCodeSettings settings = LeetCodeSettings.load(project);
        final String id = frontendId;
        clearPreviewForNewRequest();
        runGenerationTask("正在预览指定题目", "预览指定题目",
                indicator -> LeetCodeQuestionGenerationService.runSpecifiedPreview(
                        project, settings, id, indicator, this::onPreviewComplete));
    }

    private void onDownloadPreviewClicked() {
        if (currentPresentation == null || currentPresentation.frontendId.isEmpty()) {
            return;
        }
        String basePath = requireBasePath("下载当前题目");
        if (basePath == null) {
            return;
        }
        LeetCodeSettings settings = LeetCodeSettings.load(project);
        String frontendId = currentPresentation.frontendId;
        runGenerationTask("正在下载题目 " + frontendId, "下载当前题目",
                indicator -> LeetCodeQuestionGenerationService.runSpecifiedGenerate(
                        project, basePath, settings, frontendId, indicator, this::onGenerationComplete));
    }

    /** 避免下载失败后继续展示上一题，误导为本次检索结果。 */
    private void clearPreviewForNewRequest() {
        currentPresentation = null;
        previewIdLabel.setText("题号: —");
        previewTitleLabel.setText("标题: —");
        previewDifficultyLabel.setText("难度: —");
        previewMetaLabel.setText("链接: —");
        previewBody.setText(EMPTY_PREVIEW);
        downloadPreviewButton.setEnabled(false);
        openSolutionButton.setEnabled(false);
    }

    private void runGenerationTask(@NotNull String taskTitle,
                                   @NotNull String actionTitle,
                                   @NotNull GenerationWork work) {
        setGenerationEnabled(false);
        setStatus("正在获取…");
        ProgressManager.getInstance().run(new Task.Backgroundable(project, taskTitle, true) {
            @Override
            public void run(@NotNull ProgressIndicator indicator) {
                indicator.setIndeterminate(true);
                try {
                    work.run(indicator);
                } catch (Exception ex) {
                    ApplicationManager.getApplication().invokeLater(() -> {
                        setStatus("失败: " + readableError(ex));
                        Messages.showErrorDialog(project, ex.getMessage(), actionTitle);
                    });
                } finally {
                    ApplicationManager.getApplication().invokeLater(() -> setGenerationEnabled(true));
                }
            }
        });
    }

    private void onGenerationComplete(@NotNull LeetCodeProblemPresentation presentation) {
        currentPresentation = presentation;
        applyPreview(presentation);
        setStatus(statusForResult(presentation.result));
    }

    private void onPreviewComplete(@NotNull LeetCodeProblemPresentation presentation) {
        currentPresentation = presentation;
        applyPreview(presentation);
        downloadPreviewButton.setEnabled(true);
        setStatus("已获取，确认后可下载");
    }

    private void applyPreview(@NotNull LeetCodeProblemPresentation presentation) {
        previewIdLabel.setText("题号: " + emptyAsDash(presentation.frontendId));
        previewTitleLabel.setText("标题: " + emptyAsDash(presentation.title));
        previewDifficultyLabel.setText("难度: " + emptyAsDash(presentation.difficulty));
        String link = presentation.titleSlug.isEmpty()
                ? "—"
                : "https://leetcode.cn/problems/" + presentation.titleSlug + "/";
        String path = presentation.result == null || presentation.result.javaPath == null
                ? "—"
                : presentation.result.javaPath.toString();
        previewMetaLabel.setText("<html>链接: " + escapeHtml(link)
                + "<br>路径: " + escapeHtml(path) + "</html>");
        String body = presentation.plainDescription == null || presentation.plainDescription.isEmpty()
                ? EMPTY_PREVIEW
                : presentation.plainDescription;
        previewBody.setText(body);
        previewBody.setCaretPosition(0);
        openSolutionButton.setEnabled(presentation.result != null && presentation.result.javaPath != null);
    }

    private void onOpenSolutionClicked() {
        if (currentPresentation == null || currentPresentation.result == null
                || currentPresentation.result.javaPath == null) {
            return;
        }
        Path javaPath = currentPresentation.result.javaPath;
        VirtualFile vf = LocalFileSystem.getInstance().refreshAndFindFileByPath(javaPath.toString());
        if (vf == null) {
            Messages.showErrorDialog(project, "找不到题解文件:\n" + javaPath, "打开题解");
            return;
        }
        FileEditorManager.getInstance(project).openFile(vf, true);
    }

    private void onTestClicked() {
        AnActionEvent event = LeetCodeEditorActionBridge.createEvent(project);
        new RunWithTestUtilAction().actionPerformed(event);
    }

    private void onSubmitClicked() {
        AnActionEvent event = LeetCodeEditorActionBridge.createEvent(project);
        new SubmitLeetCodeSolutionAction().actionPerformed(event);
    }

    private void refreshLoginStatus() {
        LeetCodeSettings settings = LeetCodeSettings.load(project);
        if (LeetCodeHttpHeaders.hasAuth(settings)) {
            loginStatusLabel.setText("已配置登录");
        } else {
            loginStatusLabel.setText("只读模式");
        }
    }

    private void setStatus(@NotNull String text) {
        statusLabel.setText(text);
    }

    @NotNull
    private static String statusForResult(@Nullable LeetCodeDailyGenerator.GenerationResult result) {
        if (result == null) {
            return "已获取，确认后可下载";
        }
        if (result.isJavaCreated()) {
            return "已生成";
        }
        if (result.isMetadataUpdated()) {
            return "已更新注释，保留原有实现";
        }
        return "题目注释无需更新，保留原有实现";
    }

    @Nullable
    private String requireBasePath(@NotNull String actionTitle) {
        String basePath = project.getBasePath();
        if (basePath == null || basePath.isEmpty()) {
            Messages.showErrorDialog(project, "无法获取项目根目录。", actionTitle);
            return null;
        }
        return basePath;
    }

    private void setGenerationEnabled(boolean enabled) {
        dailyButton.setEnabled(enabled);
        sourceCombo.setEnabled(enabled);
        difficultyBox.setEnabled(enabled);
        frontendIdField.setEnabled(enabled);
        fetchButton.setEnabled(enabled);
        downloadPreviewButton.setEnabled(enabled && currentPresentation != null);
        settingsButton.setEnabled(enabled);
    }

    @NotNull
    private static String readableError(@NotNull Exception ex) {
        String message = ex.getMessage();
        if (message == null || message.isEmpty()) {
            return ex.getClass().getSimpleName();
        }
        return message;
    }

    @NotNull
    private static String emptyAsDash(@Nullable String value) {
        return value == null || value.isEmpty() ? "—" : value;
    }

    @NotNull
    private static String escapeHtml(@NotNull String value) {
        return value
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }

    @FunctionalInterface
    private interface GenerationWork {
        void run(@NotNull ProgressIndicator indicator) throws Exception;
    }
}
