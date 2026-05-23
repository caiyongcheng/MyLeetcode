package letcode.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.JComponent;

/**
 * 将当前 Java 题解提交到 LeetCode 并展示判题结果。
 */
public class SubmitLeetCodeSolutionAction extends AnAction {

    private static final String ACTION_TITLE = "Submit LeetCode Solution";

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) {
            return;
        }

        PsiClass psiClass = resolveTargetClass(e);
        if (psiClass == null) {
            Messages.showErrorDialog(project, "无法解析当前 Java 类", ACTION_TITLE);
            return;
        }

        String className = psiClass.getName();
        if (className == null || className.isEmpty()) {
            Messages.showErrorDialog(project, "当前类名无效", ACTION_TITLE);
            return;
        }

        String titleSlug = LeetCodeTitleSlugResolver.fromPsiClass(psiClass);
        if (titleSlug == null || titleSlug.isEmpty()) {
            Messages.showErrorDialog(
                    project,
                    "未在文件 Javadoc 中找到 Link（例如 Link: https://leetcode.cn/problems/two-sum/）。\n"
                            + "请使用「Generate LeetCode Daily Question」生成题解，或手动补充 Link 行。",
                    ACTION_TITLE
            );
            return;
        }

        PsiFile file = psiClass.getContainingFile();
        if (file == null) {
            Messages.showErrorDialog(project, "无法读取当前文件内容", ACTION_TITLE);
            return;
        }

        final String source = file.getText();
        final String slug = titleSlug;
        final String topClassName = className;
        LeetCodeSettings settings = LeetCodeSettings.load(project);

        ProgressManager.getInstance().run(new Task.Backgroundable(project, "Submitting to LeetCode", true) {
            @Override
            public void run(@NotNull ProgressIndicator indicator) {
                indicator.setIndeterminate(false);
                try {
                    indicator.setText("准备提交代码...");
                    String typedCode = LeetCodeSolutionTransformer.toSubmitCode(source, topClassName);

                    LeetCodeGraphqlClient graphql = new LeetCodeGraphqlClient(settings);
                    indicator.setText("获取题目 questionId: " + slug);
                    String questionId = graphql.fetchQuestionId(slug);

                    LeetCodeSubmitClient submitClient = new LeetCodeSubmitClient(settings);
                    indicator.setText("提交到 LeetCode...");
                    String submissionId = submitClient.submit(slug, questionId, typedCode);

                    indicator.setText("等待判题结果...");
                    LeetCodeSubmissionResult result = submitClient.pollUntilDone(submissionId);
                    ApplicationManager.getApplication().invokeLater(() ->
                            showResult(project, result));
                } catch (Exception ex) {
                    ApplicationManager.getApplication().invokeLater(() ->
                            Messages.showErrorDialog(project, ex.getMessage(), ACTION_TITLE));
                }
            }
        });
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        boolean hasProject = e.getProject() != null;
        e.getPresentation().setVisible(hasProject);
        e.getPresentation().setEnabled(hasProject && resolveTargetClass(e) != null);
    }

    private static void showResult(Project project, LeetCodeSubmissionResult result) {
        String title = result.accepted ? "LeetCode Accepted" : "LeetCode 未通过";
        new SubmissionResultDialog(project, title, result.formatForDialog()).show();
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

    private static final class SubmissionResultDialog extends DialogWrapper {

        private final String content;

        SubmissionResultDialog(Project project, String title, String content) {
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
