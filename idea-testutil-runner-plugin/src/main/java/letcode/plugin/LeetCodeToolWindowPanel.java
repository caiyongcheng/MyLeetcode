package letcode.plugin;

import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBTabbedPane;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.NotNull;

import javax.swing.JComponent;

/**
 * LeetCode 插件右侧 Tool Window 主面板。
 */
final class LeetCodeToolWindowPanel {

    private final JComponent root;

    LeetCodeToolWindowPanel(@NotNull Project project) {
        JBTabbedPane tabs = new JBTabbedPane();
        tabs.setBorder(JBUI.Borders.emptyTop(4));
        tabs.addTab("拉题", new LeetCodeFetchTabPanel(project));
        tabs.addTab("运行", new LeetCodeRunTabPanel(project));
        tabs.addTab("提交", new LeetCodeSubmitTabPanel(project));
        tabs.addTab("设置", new LeetCodeSettingsTabPanel(project));
        root = tabs;
    }

    @NotNull
    JComponent getRoot() {
        return root;
    }
}
