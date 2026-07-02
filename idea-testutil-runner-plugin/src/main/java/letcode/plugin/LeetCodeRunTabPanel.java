package letcode.plugin;

import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPanel;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.NotNull;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * Tool Window「运行」页。
 */
final class LeetCodeRunTabPanel extends JBPanel<LeetCodeRunTabPanel> {

    LeetCodeRunTabPanel(@NotNull Project project) {
        super(new BorderLayout());
        setBorder(JBUI.Borders.empty(8));

        JBPanel<?> content = new JBPanel<>(new GridBagLayout());
        content.setOpaque(false);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = JBUI.insetsBottom(8);

        JButton runButton = new JButton("Run via TestUtilRunner");
        runButton.addActionListener(e -> LeetCodeActionRunner.runAction(
                project,
                this,
                "letcode.testutil.runner.action"
        ));
        content.add(runButton, c);

        c.gridy++;
        c.weighty = 1;
        content.add(new JBLabel("<html><body style='width:220px;color:gray'>"
                + "在编辑器中打开题解类后点击运行，将弹出测试用例确认框。"
                + "</body></html>"), c);

        add(content, BorderLayout.NORTH);
    }
}
