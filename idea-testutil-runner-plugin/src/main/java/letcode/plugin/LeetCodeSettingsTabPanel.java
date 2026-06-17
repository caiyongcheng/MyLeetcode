package letcode.plugin;

import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.NotNull;

import javax.swing.JButton;
import java.awt.BorderLayout;

/**
 * Tool Window「设置」页。
 */
final class LeetCodeSettingsTabPanel extends JBPanel<LeetCodeSettingsTabPanel> {

    private final LeetCodeConfigUi.SettingsHolder settingsHolder;
    private final LeetCodeSettings settings;

    LeetCodeSettingsTabPanel(@NotNull Project project) {
        super(new BorderLayout(0, JBUI.scale(8)));
        setBorder(JBUI.Borders.empty(8));
        settings = LeetCodeSettings.load(project);
        settingsHolder = LeetCodeConfigUi.createSettingsHolder(project, settings);

        JBScrollPane scrollPane = new JBScrollPane(settingsHolder.getPanel());
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        JButton saveButton = new JButton("保存配置");
        saveButton.addActionListener(e -> settingsHolder.applyAndSave(project, settings));
        JBPanel<?> footer = new JBPanel<>(new BorderLayout());
        footer.setOpaque(false);
        footer.setBorder(JBUI.Borders.emptyTop(4));
        footer.add(saveButton, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
    }
}
