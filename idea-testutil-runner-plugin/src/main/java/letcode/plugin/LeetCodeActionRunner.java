package letcode.plugin;

import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import javax.swing.JComponent;

/**
 * 从 Tool Window 触发已有菜单 Action。
 */
final class LeetCodeActionRunner {

    private LeetCodeActionRunner() {
    }

    static void runAction(@NotNull Project project,
                          @NotNull JComponent source,
                          @NotNull String actionId) {
        AnAction action = ActionManager.getInstance().getAction(actionId);
        if (action == null) {
            return;
        }
        DataContext context = createDataContext(project, source);
        AnActionEvent event = AnActionEvent.createFromAnAction(action, null, ActionPlaces.TOOLWINDOW_CONTENT, context);
        action.actionPerformed(event);
    }

    @NotNull
    private static DataContext createDataContext(@NotNull Project project, @NotNull JComponent source) {
        Editor editor = FileEditorManager.getInstance(project).getSelectedTextEditor();
        VirtualFile file = editor != null ? editor.getVirtualFile() : null;
        return dataId -> {
            if (CommonDataKeys.PROJECT.is(dataId)) {
                return project;
            }
            if (CommonDataKeys.EDITOR.is(dataId)) {
                return editor;
            }
            if (CommonDataKeys.VIRTUAL_FILE.is(dataId)) {
                return file;
            }
            if (CommonDataKeys.PSI_FILE.is(dataId) && editor != null) {
                return com.intellij.psi.PsiDocumentManager.getInstance(project).getPsiFile(editor.getDocument());
            }
            if (PlatformDataKeys.CONTEXT_COMPONENT.is(dataId)) {
                return source;
            }
            return null;
        };
    }
}
