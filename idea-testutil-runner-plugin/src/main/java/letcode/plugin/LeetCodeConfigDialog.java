package letcode.plugin;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.Nullable;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Dimension;
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
        cookieArea = new JTextArea(settings.cookie, 5, 48);
        csrfField = new JTextField(settings.csrfToken, 48);
        extraHeadersArea = new JTextArea(settings.extraHeaders, 6, 48);
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
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel form = new JPanel(new GridBagLayout());
        int row = 0;
        row = addLabeledField(form, row, "GraphQL 接口", endpointField);
        row = addLabeledField(form, row, "Bearer Token（可选）", bearerField);
        row = addLabeledField(form, row, "Cookie（可选）", wrap(cookieArea));
        row = addLabeledField(form, row, "CSRF Token（可选）", csrfField);
        row = addLabeledField(form, row, "Extra Headers", wrap(extraHeadersArea));
        row = addFullWidth(form, row, refreshCookieButton);
        row = addFullWidth(form, row, new JLabel("已有题目仅更新题目注释，不覆盖实现和测试用例。"));
        addFullWidth(form, row, new JLabel(
                "<html>Extra Headers 支持一行一个「名称: 值」，也可以粘贴 F12 请求头；配置不会写入 git。</html>"));

        JScrollPane scroll = new JScrollPane(form);
        scroll.setBorder(JBUI.Borders.empty());
        scroll.setPreferredSize(new Dimension(520, 420));
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        return scroll;
    }

    private void refreshCookieFromLogin() {
        applyTo(settings);
        if (LeetCodeLoginCookieRefresher.refresh(project, settings)) {
            cookieArea.setText(settings.cookie);
            csrfField.setText(settings.csrfToken);
        }
    }

    private static int addLabeledField(JPanel panel, int row, String label, JComponent field) {
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.gridx = 0;
        labelConstraints.gridy = row;
        labelConstraints.insets = new Insets(8, 4, 2, 4);
        labelConstraints.anchor = GridBagConstraints.WEST;
        labelConstraints.fill = GridBagConstraints.HORIZONTAL;
        labelConstraints.weightx = 1;
        panel.add(new JLabel(label), labelConstraints);

        GridBagConstraints fieldConstraints = new GridBagConstraints();
        fieldConstraints.gridx = 0;
        fieldConstraints.gridy = row + 1;
        fieldConstraints.insets = new Insets(0, 4, 4, 4);
        fieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        fieldConstraints.weightx = 1;
        panel.add(field, fieldConstraints);
        return row + 2;
    }

    private static int addFullWidth(JPanel panel, int row, JComponent component) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = row;
        constraints.insets = new Insets(8, 4, 4, 4);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        panel.add(component, constraints);
        return row + 1;
    }

    private static JScrollPane wrap(JTextArea area) {
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        return new JScrollPane(area);
    }
}
