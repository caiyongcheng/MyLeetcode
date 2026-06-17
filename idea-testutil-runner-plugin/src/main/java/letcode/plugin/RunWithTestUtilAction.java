package letcode.plugin;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.ExecutionListener;
import com.intellij.execution.ExecutionManager;
import com.intellij.execution.Executor;
import com.intellij.execution.ProgramRunnerUtil;
import com.intellij.execution.RunManager;
import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.execution.application.ApplicationConfiguration;
import com.intellij.execution.application.ApplicationConfigurationType;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.RunProfile;
import com.intellij.execution.executors.DefaultDebugExecutor;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTextArea;
import com.intellij.util.ui.JBUI;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 在编辑器或项目视图中，用 TestUtilRunner 运行/调试当前 Java 题解类。
 */
public class RunWithTestUtilAction extends AnAction {

    private static final String MAIN_CLASS = "letcode.utils.TestUtilRunner";
    private static final String ACTION_TITLE = "Run via TestUtilRunner";
    // 与 TestCaseInputUtils.TEST_CASE_FILE_PATH_TEMPLATE 保持一致
    private static final String TEST_CASE_FILE_PATH_TEMPLATE = "src/main/resources/TestCase%s.txt";
    private static final String OUTPUT_DIR_RELATIVE = "build/testutil-plugin-output";

    private static final Set<Project> LISTENER_REGISTERED = ConcurrentHashMap.newKeySet();
    private static final Map<String, PendingPluginOutput> PENDING_OUTPUTS = new ConcurrentHashMap<>();

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) {
            return;
        }

        PsiClass psiClass = resolveTargetClass(e);
        if (psiClass == null) {
            Messages.showErrorDialog(project, "无法解析当前 Java 类", ACTION_TITLE);
            return;
        }

        String qualifiedName = psiClass.getQualifiedName();
        if (qualifiedName == null || qualifiedName.isEmpty()) {
            Messages.showErrorDialog(project, "当前类没有有效的全限定名", ACTION_TITLE);
            return;
        }

        String simpleName = psiClass.getName();
        String input = resolveDefaultInput(project, psiClass, simpleName);
        TestCaseDialog dialog = new TestCaseDialog(project, simpleName, input);
        if (!dialog.showAndGet()) {
            return;
        }

        input = dialog.getInput();
        try {
            saveInput(project, simpleName, input);
            Module module = ModuleUtilCore.findModuleForPsiElement(psiClass);
            LaunchMode launchMode = dialog.getLaunchMode();
            OutputMode outputMode = dialog.getOutputMode();
            Path outputFile = outputMode == OutputMode.CONSOLE ? null : createOutputFile(project);
            String configName = "TestUtilRunner: " + psiClass.getName();
            if (outputFile != null) {
                registerPendingOutput(project, configName, outputFile);
            }
            runWithTestUtil(project, psiClass, qualifiedName, input, module, launchMode, outputMode, outputFile, configName);
        } catch (Exception ex) {
            Messages.showErrorDialog(project, ex.getMessage(), ACTION_TITLE);
        }
    }

    @Override
    public void update(AnActionEvent e) {
        boolean hasProject = e.getProject() != null;
        e.getPresentation().setVisible(hasProject);
        e.getPresentation().setEnabled(hasProject && resolveTargetClass(e) != null);
    }

    @Nullable
    private PsiClass resolveTargetClass(AnActionEvent e) {
        PsiElement element = e.getData(CommonDataKeys.PSI_ELEMENT);
        if (element != null) {
            PsiClass fromElement = PsiTreeUtil.getParentOfType(element, PsiClass.class, false);
            if (fromElement != null) {
                return topLevelClass(fromElement);
            }
            if (element instanceof PsiClass) {
                return topLevelClass((PsiClass) element);
            }
        }

        PsiFile psiFile = e.getData(CommonDataKeys.PSI_FILE);
        if (psiFile instanceof PsiJavaFile) {
            Editor editor = e.getData(CommonDataKeys.EDITOR);
            if (editor != null) {
                PsiElement at = psiFile.findElementAt(editor.getCaretModel().getOffset());
                PsiClass atClass = PsiTreeUtil.getParentOfType(at, PsiClass.class, false);
                if (atClass != null) {
                    return topLevelClass(atClass);
                }
            }
            PsiClass[] classes = ((PsiJavaFile) psiFile).getClasses();
            if (classes.length >= 1) {
                return classes[0];
            }
        }

        Project project = e.getProject();
        VirtualFile vf = e.getData(CommonDataKeys.VIRTUAL_FILE);
        if (project != null && vf != null) {
            PsiFile file = PsiManager.getInstance(project).findFile(vf);
            if (file instanceof PsiJavaFile) {
                PsiClass[] classes = ((PsiJavaFile) file).getClasses();
                if (classes.length >= 1) {
                    return classes[0];
                }
            }
        }

        return null;
    }

    private static PsiClass topLevelClass(PsiClass psiClass) {
        PsiClass current = psiClass;
        PsiClass parent = current.getContainingClass();
        while (parent != null) {
            current = parent;
            parent = current.getContainingClass();
        }
        return current;
    }

    private static String resolveDefaultInput(Project project, PsiClass psiClass, String simpleName) {
        String mainInput = extractMainCommentInput(psiClass);
        if (mainInput != null && !mainInput.isEmpty()) {
            return mainInput;
        }
        return loadSavedInput(project, simpleName);
    }

    private static String extractMainCommentInput(PsiClass psiClass) {
        PsiFile file = psiClass.getContainingFile();
        if (file == null) {
            return "";
        }

        StringBuilder str = new StringBuilder();
        boolean inComment = false;
        String[] lines = file.getText().split("\\R", -1);
        for (String line : lines) {
            if (line.isEmpty()) {
                continue;
            }
            String trimLine = line.trim();
            if (trimLine.startsWith("/*")) {
                inComment = true;
            } else if (trimLine.endsWith("*/")) {
                inComment = false;
            } else if (!inComment && trimLine.startsWith("//")) {
                str.append(trimLine.substring(2));
            } else if (inComment) {
                if (trimLine.startsWith("*")) {
                    trimLine = trimLine.substring(1);
                }
                if (trimLine.matches("\\s+@param\\s*args[.\\s]*") || trimLine.matches("<p>")) {
                    continue;
                }
                str.append(trimLine);
            } else if (trimLine.startsWith("public static void main")) {
                break;
            } else {
                // 与 TestCaseInputUtils 保持一致：main 前出现普通代码时丢弃前面收集的注释。
                str.delete(0, str.length());
            }
        }
        return str.toString();
    }

    private static String loadSavedInput(Project project, String simpleName) {
        try {
            Path path = caseFile(project, simpleName);
            if (path != null && Files.exists(path)) {
                return new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
            }
        } catch (Exception ignored) {
            // 读取失败时仍允许用户手动输入，避免插件运行入口被存储问题阻断。
        }
        return "";
    }

    private static void saveInput(Project project, String simpleName, String input) throws Exception {
        Path path = caseFile(project, simpleName);
        if (path == null) {
            return;
        }
        Files.createDirectories(path.getParent());
        Files.write(path, input.getBytes(StandardCharsets.UTF_8));
    }

    @Nullable
    private static Path caseFile(Project project, String simpleName) {
        String basePath = project.getBasePath();
        if (basePath == null || basePath.isEmpty()) {
            return null;
        }
        String relative = String.format(TEST_CASE_FILE_PATH_TEMPLATE, simpleName);
        return Paths.get(basePath, relative.split("/"));
    }

    @Nullable
    private static Path createOutputFile(Project project) throws Exception {
        String basePath = project.getBasePath();
        if (basePath == null || basePath.isEmpty()) {
            return null;
        }
        Path dir = Paths.get(basePath, OUTPUT_DIR_RELATIVE.split("/"));
        Files.createDirectories(dir);
        return dir.resolve("run-" + System.currentTimeMillis() + ".txt");
    }

    private static void registerPendingOutput(Project project, String configName, Path outputFile) {
        ensureExecutionListener(project);
        PENDING_OUTPUTS.put(configName, new PendingPluginOutput(project, outputFile));
    }

    private static void ensureExecutionListener(Project project) {
        if (!LISTENER_REGISTERED.add(project)) {
            return;
        }
        project.getMessageBus().connect().subscribe(ExecutionManager.EXECUTION_TOPIC, new ExecutionListener() {
            @Override
            public void processTerminated(@NotNull String executorId,
                                          @NotNull com.intellij.execution.runners.ExecutionEnvironment env,
                                          @NotNull ProcessHandler handler,
                                          int exitCode) {
                RunProfile profile = env.getRunProfile();
                if (!(profile instanceof ApplicationConfiguration)) {
                    return;
                }
                String name = ((ApplicationConfiguration) profile).getName();
                PendingPluginOutput pending = PENDING_OUTPUTS.remove(name);
                if (pending == null) {
                    return;
                }
                ApplicationManager.getApplication().invokeLater(() ->
                        showCapturedOutput(pending.project, pending.outputFile, exitCode));
            }
        });
    }

    private static void showCapturedOutput(Project project, Path outputFile, int exitCode) {
        String content = "";
        try {
            if (outputFile != null && Files.exists(outputFile)) {
                content = new String(Files.readAllBytes(outputFile), StandardCharsets.UTF_8);
            }
        } catch (Exception ex) {
            content = "读取输出文件失败: " + ex.getMessage();
        }
        if (content.isEmpty()) {
            content = "(无输出，退出码 " + exitCode + ")";
        } else {
            content = AnsiStripper.strip(content);
        }
        new CapturedOutputDialog(project, "TestUtil 输出 (退出码 " + exitCode + ")", content).show();
    }

    private static void runWithTestUtil(Project project,
                                        PsiClass psiClass,
                                        String qualifiedName,
                                        String input,
                                        Module module,
                                        LaunchMode launchMode,
                                        OutputMode outputMode,
                                        @Nullable Path outputFile,
                                        String configName) throws ExecutionException {
        RunManager runManager = RunManager.getInstance(project);
        ConfigurationFactory factory = ApplicationConfigurationType.getInstance().getConfigurationFactories()[0];
        RunnerAndConfigurationSettings settings = runManager.createConfiguration(configName, factory);
        ApplicationConfiguration config = (ApplicationConfiguration) settings.getConfiguration();

        config.setMainClassName(MAIN_CLASS);
        config.setProgramParameters(buildProgramParameters(qualifiedName, input, outputMode, outputFile));
        if (project.getBasePath() != null) {
            config.setWorkingDirectory(project.getBasePath());
        }
        if (module != null) {
            config.setModule(module);
        }

        // 创建临时运行配置，避免每次右键都污染 Run Configurations 列表。
        runManager.setTemporaryConfiguration(settings);
        runManager.setSelectedConfiguration(settings);
        Executor executor = launchMode == LaunchMode.DEBUG
                ? DefaultDebugExecutor.getDebugExecutorInstance()
                : DefaultRunExecutor.getRunExecutorInstance();
        ProgramRunnerUtil.executeConfiguration(project, settings, executor);
    }

    private static String buildProgramParameters(String qualifiedName,
                                                 String input,
                                                 OutputMode outputMode,
                                                 @Nullable Path outputFile) {
        StringBuilder params = new StringBuilder(qualifiedName);
        if (input != null && !input.isEmpty()) {
            String encoded = Base64.getEncoder().encodeToString(input.getBytes(StandardCharsets.UTF_8));
            params.append(" --base64 ").append(encoded);
        }
        if (outputMode != OutputMode.CONSOLE) {
            params.append(" --output-mode ").append(outputMode.cliValue);
            if (outputFile != null) {
                params.append(" --output-file ").append(quoteParam(outputFile.toAbsolutePath().toString()));
            }
        }
        return params.toString();
    }

    private static String quoteParam(String value) {
        return "\"" + value.replace("\"", "\\\"") + "\"";
    }

    private enum LaunchMode {
        RUN,
        DEBUG
    }

    private enum OutputMode {
        CONSOLE("console", "仅控制台"),
        PLUGIN("plugin", "仅插件弹窗"),
        BOTH("both", "控制台 + 插件弹窗");

        private final String cliValue;
        private final String label;

        OutputMode(String cliValue, String label) {
            this.cliValue = cliValue;
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    private static final class PendingPluginOutput {
        private final Project project;
        private final Path outputFile;

        private PendingPluginOutput(Project project, Path outputFile) {
            this.project = project;
            this.outputFile = outputFile;
        }
    }

    private static class TestCaseDialog extends DialogWrapper {

        private final JBTextArea textArea;
        private final JRadioButton runButton;
        private final JRadioButton debugButton;
        private final JComboBox<OutputMode> outputModeBox;

        TestCaseDialog(Project project, String className, String input) {
            super(project);
            setTitle("TestUtil 测试用例 - " + className);
            textArea = new JBTextArea(input == null ? "" : input);
            textArea.setRows(14);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            runButton = new JRadioButton("运行 (Run)", true);
            debugButton = new JRadioButton("调试 (Debug)");
            ButtonGroup launchGroup = new ButtonGroup();
            launchGroup.add(runButton);
            launchGroup.add(debugButton);
            outputModeBox = new JComboBox<>(OutputMode.values());
            init();
        }

        String getInput() {
            return textArea.getText();
        }

        LaunchMode getLaunchMode() {
            return debugButton.isSelected() ? LaunchMode.DEBUG : LaunchMode.RUN;
        }

        OutputMode getOutputMode() {
            return (OutputMode) outputModeBox.getSelectedItem();
        }

        @Nullable
        @Override
        protected JComponent createCenterPanel() {
            JPanel panel = new JPanel(new BorderLayout(0, JBUI.scale(8)));
            panel.setBorder(JBUI.Borders.empty(4));
            JBScrollPane scrollPane = new JBScrollPane(textArea);
            scrollPane.setPreferredSize(JBUI.size(640, JBUI.scale(320)));
            panel.add(scrollPane, BorderLayout.CENTER);

            JPanel options = new JPanel(new GridLayout(0, 1, 0, 4));
            JPanel launchPanel = new JPanel(new GridLayout(1, 0, 8, 0));
            launchPanel.add(runButton);
            launchPanel.add(debugButton);
            options.add(new JLabel("启动方式:"));
            options.add(launchPanel);
            options.add(new JLabel("输出方式:"));
            options.add(outputModeBox);
            panel.add(options, BorderLayout.SOUTH);
            return panel;
        }
    }

    private static class CapturedOutputDialog extends DialogWrapper {

        private final String content;

        CapturedOutputDialog(Project project, String title, String content) {
            super(project);
            this.content = content;
            setTitle(title);
            init();
        }

        @Nullable
        @Override
        protected JComponent createCenterPanel() {
            return PluginOutputUi.wrapOutputArea(PluginOutputUi.createReadOnlyOutputArea(content, 24, 100));
        }
    }
}
