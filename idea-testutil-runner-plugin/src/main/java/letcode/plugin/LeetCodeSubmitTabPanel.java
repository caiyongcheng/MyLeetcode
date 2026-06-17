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
 * Tool Window「提交」页。
 */
final class LeetCodeSubmitTabPanel extends JBPanel<LeetCodeSubmitTabPanel> {

    LeetCodeSubmitTabPanel(@NotNull Project project) {
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

        JButton submitButton = new JButton("Submit LeetCode Solution");
        submitButton.addActionListener(e -> LeetCodeActionRunner.runAction(
                project,
                this,
                "letcode.leetcode.submit.action"
        ));
        content.add(submitButton, c);

        c.gridy++;
        c.weighty = 1;
        content.add(new JBLabel("<html><body style='width:220px;color:gray'>"
                + "提交当前编辑器中的题解到 LeetCode，需在 Javadoc 中包含 Link 行。"
                + "</body></html>"), c);

        add(content, BorderLayout.NORTH);
    }
}
