package letcode.plugin;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTextArea;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * LeetCode 配置面板与弹窗。
 */
final class LeetCodeConfigUi {

    private LeetCodeConfigUi() {
    }

    static boolean ensureConfigured(@NotNull Project project, @NotNull LeetCodeSettings settings) {
        LeetCodeConfigDialog dialog = new LeetCodeConfigDialog(project, settings, false);
        if (!dialog.showAndGet()) {
            return false;
        }
        dialog.applyTo(settings);
        settings.save(project);
        return true;
    }

    static final class SettingsHolder {
        private final LeetCodeConfigForm form;
        private final JPanel panel;

        SettingsHolder(@NotNull Project project, @NotNull LeetCodeSettings settings) {
            form = new LeetCodeConfigForm(project, settings);
            panel = form.getRoot();
        }

        @NotNull
        JPanel getPanel() {
            return panel;
        }

        void applyAndSave(@NotNull Project project, @NotNull LeetCodeSettings settings) {
            form.applyTo(settings);
            settings.save(project);
            Messages.showInfoMessage(project, "LeetCode 配置已保存。", "LeetCode 插件");
        }
    }

    @NotNull
    static SettingsHolder createSettingsHolder(@NotNull Project project, @NotNull LeetCodeSettings settings) {
        return new SettingsHolder(project, settings);
    }

    private static final class LeetCodeConfigDialog extends DialogWrapper {

        private final LeetCodeConfigForm form;

        LeetCodeConfigDialog(@NotNull Project project,
                             @NotNull LeetCodeSettings settings,
                             boolean settingsTabMode) {
            super(project);
            setTitle(settingsTabMode ? "LeetCode 设置" : "LeetCode 每日一题设置");
            form = new LeetCodeConfigForm(project, settings);
            init();
        }

        void applyTo(LeetCodeSettings settings) {
            form.applyTo(settings);
        }

        @Nullable
        @Override
        protected JComponent createCenterPanel() {
            return form.getRoot();
        }
    }

    private static final class LeetCodeConfigForm {

        private final JTextField endpointField;
        private final JTextField bearerField;
        private final JBTextArea cookieArea;
        private final JTextField csrfField;
        private final JBTextArea extraHeadersArea;
        private final JCheckBox overwriteBox;
        private final RootPanel root;

        LeetCodeConfigForm(@NotNull Project project, @NotNull LeetCodeSettings settings) {
            Dimension fieldSize = JBUI.size(360, JBUI.scale(28));
            endpointField = new JTextField(settings.endpoint);
            endpointField.setPreferredSize(fieldSize);
            bearerField = new JTextField(settings.bearerToken);
            bearerField.setPreferredSize(fieldSize);
            cookieArea = createInputArea(settings.cookie, 3);
            csrfField = new JTextField(settings.csrfToken);
            csrfField.setPreferredSize(fieldSize);
            extraHeadersArea = createInputArea(settings.extraHeaders, 4);
            overwriteBox = new JCheckBox("覆盖已存在的 Java 文件", settings.overwriteExisting);
            root = new RootPanel(buildPanel(), this);
        }

        @NotNull
        JPanel getRoot() {
            return root;
        }

        void applyTo(@NotNull LeetCodeSettings settings) {
            settings.endpoint = endpointField.getText().trim();
            if (settings.endpoint.isEmpty()) {
                settings.endpoint = LeetCodeSettings.DEFAULT_ENDPOINT;
            }
            settings.bearerToken = bearerField.getText().trim();
            settings.cookie = cookieArea.getText().trim();
            settings.csrfToken = csrfField.getText().trim();
            settings.extraHeaders = extraHeadersArea.getText().trim();
            settings.overwriteExisting = overwriteBox.isSelected();
        }

        private JPanel buildPanel() {
            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBorder(JBUI.Borders.empty(4));
            GridBagConstraints c = new GridBagConstraints();
            c.insets = JBUI.insets(2, 0, 8, 0);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1;
            c.gridx = 0;
            c.anchor = GridBagConstraints.NORTHWEST;

            addRow(panel, c, 0, "GraphQL 接口:", endpointField);
            addRow(panel, c, 1, "Bearer Token（可选）:", bearerField);
            addRow(panel, c, 2, "Cookie（可选）:", wrap(cookieArea, 3));
            addRow(panel, c, 3, "CSRF Token（可选）:", csrfField);
            addRow(panel, c, 4, "Extra Headers（每行「名称: 值」）:", wrap(extraHeadersArea, 4));
            c.gridy = 10;
            c.weighty = 0;
            panel.add(overwriteBox, c);
            c.gridy = 11;
            c.weighty = 1;
            panel.add(new JLabel("<html><body style='width:360px'>配置保存在 IDEA 项目属性中，不会写入 git。</body></html>"), c);
            return panel;
        }

        private static JBTextArea createInputArea(String text, int rows) {
            JBTextArea area = new JBTextArea(text == null ? "" : text);
            area.setLineWrap(true);
            area.setWrapStyleWord(true);
            area.setRows(rows);
            return area;
        }

        private static void addRow(JPanel panel, GridBagConstraints c, int row, String label, JComponent field) {
            c.gridy = row * 2;
            c.weighty = 0;
            c.fill = GridBagConstraints.HORIZONTAL;
            panel.add(new JLabel("<html><body style='width:360px'>" + escapeHtml(label) + "</body></html>"), c);
            c.gridy = row * 2 + 1;
            panel.add(field, c);
        }

        private static JBScrollPane wrap(JBTextArea area, int rows) {
            area.setRows(rows);
            int lineHeight = JBUI.scale(20);
            JBScrollPane scrollPane = new JBScrollPane(area);
            scrollPane.setPreferredSize(JBUI.size(360, lineHeight * rows + JBUI.scale(8)));
            return scrollPane;
        }

        private static String escapeHtml(String text) {
            return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
        }

        private static final class RootPanel extends JPanel {
            private final LeetCodeConfigForm form;

            private RootPanel(JPanel content, LeetCodeConfigForm form) {
                super(new GridBagLayout());
                this.form = form;
                GridBagConstraints c = new GridBagConstraints();
                c.gridx = 0;
                c.gridy = 0;
                c.weightx = 1;
                c.weighty = 1;
                c.fill = GridBagConstraints.BOTH;
                add(content, c);
            }
        }
    }
}
