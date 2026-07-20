package letcode.plugin;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

/**
 * LeetCode 接口与生成选项配置弹窗。
 */
final class LeetCodeConfigDialog extends DialogWrapper {

    private final JTextField endpointField;
    private final JTextField bearerField;
    private final JTextArea cookieArea;
    private final JTextField csrfField;
    private final JTextArea extraHeadersArea;
    private final JCheckBox overwriteBox;
    private final JButton refreshCookieButton;
    private final Project project;
    private final LeetCodeSettings settings;

    LeetCodeConfigDialog(Project project, LeetCodeSettings settings, String title) {
        super(project);
        this.project = project;
        this.settings = settings;
        setTitle(title);
        endpointField = new JTextField(settings.endpoint, 48);
        bearerField = new JTextField(settings.bearerToken, 48);
        cookieArea = new JTextArea(settings.cookie, 3, 48);
        csrfField = new JTextField(settings.csrfToken, 48);
        extraHeadersArea = new JTextArea(settings.extraHeaders, 4, 48);
        overwriteBox = new JCheckBox("覆盖已存在的 Java 文件", settings.overwriteExisting);
        refreshCookieButton = new JButton("打开 LeetCode 登录并刷新 Cookie");
        refreshCookieButton.addActionListener(event -> refreshCookieFromLogin());
        init();
    }

    void applyTo(LeetCodeSettings settings) {
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

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        addRow(panel, 0, "GraphQL 接口:", endpointField);
        addRow(panel, 1, "Bearer Token（可选）:", bearerField);
        addRow(panel, 2, "Cookie（可选）:", wrap(cookieArea, 3));
        addRow(panel, 3, "CSRF Token（可选）:", csrfField);
        addRow(panel, 4, "Extra Headers:", wrap(extraHeadersArea, 4));
        addFullRow(panel, 5, refreshCookieButton);
        addFullRow(panel, 6, overwriteBox);
        addFullRow(panel, 7, new JLabel("<html>Extra Headers 支持一行一个「名称: 值」，也可以粘贴 F12 请求头；配置不会写入 git。</html>"));
        return panel;
    }

    private void refreshCookieFromLogin() {
        applyTo(settings);
        if (LeetCodeLoginCookieRefresher.refresh(project, settings)) {
            cookieArea.setText(settings.cookie);
            csrfField.setText(settings.csrfToken);
        }
    }

    private static void addRow(JPanel panel, int row, String label, JComponent field) {
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.gridx = 0;
        labelConstraints.gridy = row;
        labelConstraints.insets = new Insets(4, 4, 4, 8);
        labelConstraints.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel(label), labelConstraints);

        GridBagConstraints fieldConstraints = new GridBagConstraints();
        fieldConstraints.gridx = 1;
        fieldConstraints.gridy = row;
        fieldConstraints.insets = new Insets(4, 4, 4, 4);
        fieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        fieldConstraints.weightx = 1;
        panel.add(field, fieldConstraints);
    }

    private static void addFullRow(JPanel panel, int row, JComponent component) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = row;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        panel.add(component, constraints);
    }

    private static JScrollPane wrap(JTextArea area, int rows) {
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setRows(rows);
        return new JScrollPane(area);
    }
}
