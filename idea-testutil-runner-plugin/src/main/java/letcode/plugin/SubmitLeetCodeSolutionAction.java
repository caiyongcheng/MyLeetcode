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
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
            Messages.showErrorDialog(project, "Cannot resolve the current Java class.", ACTION_TITLE);
            return;
        }

        String className = psiClass.getName();
        if (className == null || className.isEmpty()) {
            Messages.showErrorDialog(project, "Invalid class name.", ACTION_TITLE);
            return;
        }

        String titleSlug = LeetCodeTitleSlugResolver.fromPsiClass(psiClass);
        if (titleSlug == null || titleSlug.isEmpty()) {
            Messages.showErrorDialog(
                    project,
                    "No Link found in file Javadoc (e.g. Link: https://leetcode.cn/problems/two-sum/).\n"
                            + "Use \"Generate LeetCode Daily Question\" or add a Link line manually.",
                    ACTION_TITLE
            );
            return;
        }

        PsiFile file = psiClass.getContainingFile();
        if (file == null) {
            Messages.showErrorDialog(project, "Cannot read the current file.", ACTION_TITLE);
            return;
        }

        final String source = file.getText();
        final String slug = titleSlug;
        final String topClassName = className;
        final String projectBasePath = project.getBasePath();
        final Path javaPath = resolveJavaPath(file);
        LeetCodeSettings settings = LeetCodeSettings.load(project);

        ProgressManager.getInstance().run(new Task.Backgroundable(project, "Submitting to LeetCode", true) {
            @Override
            public void run(@NotNull ProgressIndicator indicator) {
                indicator.setIndeterminate(false);
                try {
                    runSubmitWithAuthRetry(
                            project,
                            settings,
                            indicator,
                            source,
                            topClassName,
                            slug,
                            projectBasePath,
                            javaPath,
                            file
                    );
                } catch (Exception ex) {
                    ApplicationManager.getApplication().invokeLater(() ->
                            Messages.showErrorDialog(project, ex.getMessage(), ACTION_TITLE));
                }
            }
        });
    }

    private static void runSubmitWithAuthRetry(Project project,
                                               LeetCodeSettings settings,
                                               ProgressIndicator indicator,
                                               String source,
                                               String topClassName,
                                               String slug,
                                               @Nullable String projectBasePath,
                                               @Nullable Path javaPath,
                                               PsiFile file) throws Exception {
        Exception lastError = null;
        int maxAttempts = 3;
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            try {
                indicator.setText("Preparing submission...");
                String typedCode = LeetCodeSolutionTransformer.toSubmitCode(source, topClassName);

                LeetCodeGraphqlClient graphql = new LeetCodeGraphqlClient(project, settings);
                indicator.setText("Fetching questionId: " + slug);
                String questionId = graphql.fetchQuestionId(slug);

                LeetCodeSubmitClient submitClient = new LeetCodeSubmitClient(project, settings);
                indicator.setText("Submitting to LeetCode...");
                String submissionId = submitClient.submit(slug, questionId, typedCode);

                indicator.setText("Waiting for judge result...");
                LeetCodeSubmissionResult result = submitClient.pollUntilDone(submissionId);
                GitCommitPushResult gitResult = null;
                if (result.accepted && projectBasePath != null && !projectBasePath.isEmpty() && javaPath != null) {
                    indicator.setText("Committing accepted solution...");
                    String packageName = file instanceof PsiJavaFile ? ((PsiJavaFile) file).getPackageName() : null;
                    Path testCasePath = GitAddHelper.resolveTestCasePath(projectBasePath, topClassName);
                    String commitMessage = GitAddHelper.buildCommitMessage(packageName, javaPath);
                    gitResult = GitAddHelper.commitAndPushAcceptedSolution(
                            projectBasePath,
                            javaPath,
                            testCasePath,
                            commitMessage
                    );
                }
                GitCommitPushResult finalGitResult = gitResult;
                ApplicationManager.getApplication().invokeLater(() ->
                        showResult(project, result, finalGitResult));
                return;
            } catch (Exception ex) {
                lastError = ex;
                if (attempt < maxAttempts - 1 && LeetCodeLoginCookieRefresher.isAuthExpired(ex)
                        && refreshLoginOnUi(project, settings)) {
                    indicator.setText("登录信息已刷新，正在重试...");
                    continue;
                }
                throw ex;
            }
        }
        if (lastError != null) {
            throw lastError;
        }
    }

    private static boolean refreshLoginOnUi(Project project, LeetCodeSettings settings)
            throws InvocationTargetException, InterruptedException {
        final boolean[] refreshed = {false};
        ApplicationManager.getApplication().invokeAndWait(() ->
                refreshed[0] = LeetCodeLoginCookieRefresher.refresh(project, settings));
        return refreshed[0];
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        boolean hasProject = e.getProject() != null;
        // Tools 菜单没有稳定的 PSI 上下文，保持入口可见，点击后再校验当前题解类。
        e.getPresentation().setVisible(hasProject);
        e.getPresentation().setEnabled(hasProject);
    }

    private static void showResult(Project project,
                                   LeetCodeSubmissionResult result,
                                   @Nullable GitCommitPushResult gitResult) {
        String title = result.accepted ? "LeetCode Accepted" : "LeetCode Failed";
        new SubmissionResultDialog(project, title, result.formatForDialog(gitResult)).show();
    }

    @Nullable
    private static Path resolveJavaPath(@NotNull PsiFile file) {
        if (file.getVirtualFile() == null) {
            return null;
        }
        return Paths.get(file.getVirtualFile().getPath());
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
