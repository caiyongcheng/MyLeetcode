package letcode.plugin;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

/**
 * LeetCode 工具窗口主面板：题目生成与当前题解操作入口。
 */
final class LeetCodeToolWindowPanel extends JPanel {

    private static final String[] DIFFICULTIES = {"Easy", "Medium", "Hard"};

    private final Project project;
    private final JButton dailyButton;
    private final JComboBox<String> difficultyBox;
    private final JButton randomButton;
    private final JButton testButton;
    private final JButton submitButton;

    LeetCodeToolWindowPanel(@NotNull Project project) {
        super();
        this.project = project;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        dailyButton = new JButton("生成每日一题");
        difficultyBox = new JComboBox<>(DIFFICULTIES);
        difficultyBox.setSelectedItem("Medium");
        randomButton = new JButton("获取随机新题");
        testButton = new JButton("运行 TestUtil 测试");
        submitButton = new JButton("提交当前题解");

        add(section("题目生成", buildQuestionSection()));
        add(Box.createVerticalStrut(12));
        add(section("当前题解", buildCurrentClassSection()));

        dailyButton.addActionListener(event -> onDailyClicked());
        randomButton.addActionListener(event -> onRandomClicked());
        testButton.addActionListener(event -> onTestClicked());
        submitButton.addActionListener(event -> onSubmitClicked());
    }

    private JPanel buildQuestionSection() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel dailyRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 4));
        dailyRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        dailyRow.add(dailyButton);
        panel.add(dailyRow);

        JPanel randomRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 4));
        randomRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        randomRow.add(new JLabel("难度:"));
        difficultyBox.setPreferredSize(new Dimension(100, difficultyBox.getPreferredSize().height));
        randomRow.add(difficultyBox);
        randomRow.add(randomButton);
        panel.add(randomRow);
        return panel;
    }

    private JPanel buildCurrentClassSection() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel testRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 4));
        testRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        testRow.add(testButton);
        panel.add(testRow);

        JPanel submitRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 4));
        submitRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        submitRow.add(submitButton);
        panel.add(submitRow);
        return panel;
    }

    private static JPanel section(@NotNull String title, @NotNull JPanel content) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(title),
                BorderFactory.createEmptyBorder(4, 4, 4, 4)
        ));
        panel.add(content);
        return panel;
    }

    private void onDailyClicked() {
        String basePath = requireBasePath("生成每日一题");
        if (basePath == null) {
            return;
        }
        LeetCodeSettings settings = LeetCodeSettings.load(project);
        LeetCodeConfigDialog dialog = new LeetCodeConfigDialog(project, settings, "LeetCode 每日一题设置");
        if (!dialog.showAndGet()) {
            return;
        }
        dialog.applyTo(settings);
        settings.save(project);
        runGenerationTask("正在生成 LeetCode 每日一题", settings, basePath, null);
    }

    private void onRandomClicked() {
        String basePath = requireBasePath("获取随机新题");
        if (basePath == null) {
            return;
        }
        String difficulty = (String) difficultyBox.getSelectedItem();
        if (difficulty == null || difficulty.isEmpty()) {
            difficulty = "Medium";
        }
        LeetCodeSettings settings = LeetCodeSettings.load(project);
        LeetCodeConfigDialog dialog = new LeetCodeConfigDialog(project, settings, "LeetCode 随机新题设置");
        if (!dialog.showAndGet()) {
            return;
        }
        dialog.applyTo(settings);
        settings.save(project);
        final String selectedDifficulty = difficulty;
        runGenerationTask("正在获取随机新题", settings, basePath, selectedDifficulty);
    }

    private void runGenerationTask(@NotNull String taskTitle,
                                   @NotNull LeetCodeSettings settings,
                                   @NotNull String basePath,
                                   @org.jetbrains.annotations.Nullable String difficulty) {
        setGenerationEnabled(false);
        ProgressManager.getInstance().run(new Task.Backgroundable(project, taskTitle, true) {
            @Override
            public void run(@NotNull ProgressIndicator indicator) {
                indicator.setIndeterminate(true);
                try {
                    if (difficulty == null) {
                        LeetCodeQuestionGenerationService.runDailyGenerate(project, basePath, settings, indicator);
                    } else {
                        LeetCodeQuestionGenerationService.runRandomGenerate(
                                project, basePath, settings, difficulty, indicator);
                    }
                } catch (Exception ex) {
                    String actionTitle = difficulty == null ? "生成 LeetCode 每日一题" : "获取随机新题";
                    ApplicationManager.getApplication().invokeLater(() ->
                            Messages.showErrorDialog(project, ex.getMessage(), actionTitle));
                } finally {
                    ApplicationManager.getApplication().invokeLater(() -> setGenerationEnabled(true));
                }
            }
        });
    }

    private void onTestClicked() {
        AnActionEvent event = LeetCodeEditorActionBridge.createEvent(project);
        new RunWithTestUtilAction().actionPerformed(event);
    }

    private void onSubmitClicked() {
        AnActionEvent event = LeetCodeEditorActionBridge.createEvent(project);
        new SubmitLeetCodeSolutionAction().actionPerformed(event);
    }

    @org.jetbrains.annotations.Nullable
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
        randomButton.setEnabled(enabled);
        difficultyBox.setEnabled(enabled);
    }
}
