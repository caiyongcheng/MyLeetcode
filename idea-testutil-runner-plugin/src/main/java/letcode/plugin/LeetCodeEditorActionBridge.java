package letcode.plugin;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.ActionUiKind;
import com.intellij.openapi.actionSystem.impl.SimpleDataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;

/**
 * 从当前编辑器构造最小 AnActionEvent，供 Tool Window 复用已有 Action。
 */
final class LeetCodeEditorActionBridge {

    private static final String PLACE = "LeetCodeToolWindow";

    private LeetCodeEditorActionBridge() {
    }

    @NotNull
    static AnActionEvent createEvent(@NotNull Project project) {
        FileEditorManager editorManager = FileEditorManager.getInstance(project);
        VirtualFile virtualFile = editorManager.getSelectedFiles().length > 0
                ? editorManager.getSelectedFiles()[0]
                : null;
        Editor editor = editorManager.getSelectedTextEditor();
        PsiFile psiFile = virtualFile == null
                ? null
                : PsiManager.getInstance(project).findFile(virtualFile);

        DataContext context = SimpleDataContext.builder()
                .add(CommonDataKeys.PROJECT, project)
                .add(CommonDataKeys.VIRTUAL_FILE, virtualFile)
                .add(CommonDataKeys.PSI_FILE, psiFile)
                .add(CommonDataKeys.EDITOR, editor)
                .build();
        return AnActionEvent.createEvent(context, null, PLACE, ActionUiKind.NONE, null);
    }
}
