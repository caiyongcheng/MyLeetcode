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
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.Nullable;

/**
 * 在编辑器或项目视图中，用 TestUtilRunner 运行当前 Java 题解类。
 */
public class RunWithTestUtilAction extends AnAction {

    private static final String MAIN_CLASS = "letcode.utils.TestUtilRunner";
    private static final String ACTION_TITLE = "Run via TestUtilRunner";

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

        Module module = ModuleUtilCore.findModuleForPsiElement(psiClass);
        try {
            runWithTestUtil(project, psiClass, qualifiedName, module);
        } catch (ExecutionException ex) {
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

    private static void runWithTestUtil(Project project, PsiClass psiClass, String qualifiedName, Module module)
            throws ExecutionException {
        RunManager runManager = RunManager.getInstance(project);
        ConfigurationFactory factory = ApplicationConfigurationType.getInstance().getConfigurationFactories()[0];
        String configName = "TestUtilRunner: " + psiClass.getName();
        RunnerAndConfigurationSettings settings = runManager.createConfiguration(configName, factory);
        ApplicationConfiguration config = (ApplicationConfiguration) settings.getConfiguration();

        config.setMainClassName(MAIN_CLASS);
        config.setProgramParameters(qualifiedName);
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
}
