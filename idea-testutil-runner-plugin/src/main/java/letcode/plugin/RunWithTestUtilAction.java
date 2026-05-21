package letcode.plugin;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.ProgramRunnerUtil;
import com.intellij.execution.RunManager;
import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.execution.application.ApplicationConfiguration;
import com.intellij.execution.application.ApplicationConfigurationType;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.Nullable;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * 在编辑器或项目视图中，用 TestUtilRunner 运行当前 Java 题解类。
 */
public class RunWithTestUtilAction extends AnAction {

    private static final String MAIN_CLASS = "letcode.utils.TestUtilRunner";
    private static final String ACTION_TITLE = "Run via TestUtilRunner";
    // 与 TestCaseInputUtils.TEST_CASE_FILE_PATH_TEMPLATE 保持一致
    private static final String TEST_CASE_FILE_PATH_TEMPLATE = "src/main/resources/TestCase%s.txt";

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
            runWithTestUtil(project, psiClass, qualifiedName, input, module);
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

    private static void runWithTestUtil(Project project, PsiClass psiClass, String qualifiedName, String input, Module module)
            throws ExecutionException {
        RunManager runManager = RunManager.getInstance(project);
        ConfigurationFactory factory = ApplicationConfigurationType.getInstance().getConfigurationFactories()[0];
        String configName = "TestUtilRunner: " + psiClass.getName();
        RunnerAndConfigurationSettings settings = runManager.createConfiguration(configName, factory);
        ApplicationConfiguration config = (ApplicationConfiguration) settings.getConfiguration();

        config.setMainClassName(MAIN_CLASS);
        config.setProgramParameters(buildProgramParameters(qualifiedName, input));
        if (project.getBasePath() != null) {
            config.setWorkingDirectory(project.getBasePath());
        }
        if (module != null) {
            config.setModule(module);
        }

        // 创建临时运行配置，避免每次右键都污染 Run Configurations 列表。
        runManager.setTemporaryConfiguration(settings);
        runManager.setSelectedConfiguration(settings);
        Executor executor = DefaultRunExecutor.getRunExecutorInstance();
        ProgramRunnerUtil.executeConfiguration(project, settings, executor);
    }

    private static String buildProgramParameters(String qualifiedName, String input) {
        if (input == null || input.isEmpty()) {
            return qualifiedName;
        }
        String encoded = Base64.getEncoder().encodeToString(input.getBytes(StandardCharsets.UTF_8));
        return qualifiedName + " --base64 " + encoded;
    }

    private static class TestCaseDialog extends DialogWrapper {

        private final JTextArea textArea;

        TestCaseDialog(Project project, String className, String input) {
            super(project);
            setTitle("TestUtil 测试用例 - " + className);
            textArea = new JTextArea(input == null ? "" : input, 14, 80);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            init();
        }

        String getInput() {
            return textArea.getText();
        }

        @Nullable
        @Override
        protected JComponent createCenterPanel() {
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
            return panel;
        }
    }
}
