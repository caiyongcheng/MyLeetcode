package letcode.plugin;

import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPanel;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.NotNull;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * Tool Window「拉题」页：每日一题与按难度随机拉题。
 */
final class LeetCodeFetchTabPanel extends JBPanel<LeetCodeFetchTabPanel> {

    LeetCodeFetchTabPanel(@NotNull Project project) {
        super(new BorderLayout(0, JBUI.scale(8)));
        setBorder(JBUI.Borders.empty(8));

        JBPanel<?> content = new JBPanel<>(new GridBagLayout());
        content.setOpaque(false);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = JBUI.insetsBottom(8);

        JButton dailyButton = new JButton("生成每日一题");
        dailyButton.addActionListener(e -> LeetCodeQuestionTasks.generateDaily(project));
        content.add(dailyButton, c);

        c.gridy++;
        content.add(new JBLabel("按难度随机拉题："), c);

        c.gridy++;
        JComboBox<LeetCodeDifficulty> difficultyBox = new JComboBox<>(LeetCodeDifficulty.values());
        difficultyBox.setSelectedItem(LeetCodeDifficulty.MEDIUM);
        content.add(difficultyBox, c);

        c.gridy++;
        JButton randomButton = new JButton("随机获取一题");
        randomButton.addActionListener(e -> {
            LeetCodeDifficulty selected = (LeetCodeDifficulty) difficultyBox.getSelectedItem();
            if (selected != null) {
                LeetCodeQuestionTasks.generateRandom(project, selected);
            }
        });
        content.add(randomButton, c);

        c.gridy++;
        c.weighty = 1;
        JBLabel hint = new JBLabel("<html><body style='width:220px;color:gray'>"
                + "随机题目从 LeetCode 题库中抽取，自动跳过会员题。"
                + "</body></html>");
        content.add(hint, c);

        add(content, BorderLayout.NORTH);
    }
}
