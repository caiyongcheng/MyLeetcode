package letcode.plugin;

import com.intellij.ui.components.JBScrollPane;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.awt.Font;

/**
 * 插件输出区 UI：等宽字体、不自动换行，布局尽量贴近控制台。
 */
final class PluginOutputUi {

    private PluginOutputUi() {
    }

    static JTextArea createReadOnlyOutputArea(String content, int rows, int columns) {
        JTextArea textArea = new JTextArea(content == null ? "" : content);
        textArea.setEditable(false);
        textArea.setLineWrap(false);
        textArea.setWrapStyleWord(false);
        textArea.setFont(monospacedFont(textArea));
        textArea.setTabSize(4);
        int lineHeight = Math.max(UIUtil.getLineHeight(textArea), JBUI.scale(16));
        textArea.setPreferredSize(new Dimension(JBUI.scale(columns * 8), lineHeight * rows + JBUI.scale(8)));
        return textArea;
    }

    static JScrollPane wrapOutputArea(JTextArea textArea) {
        JBScrollPane scrollPane = new JBScrollPane(textArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(textArea.getPreferredSize());
        return scrollPane;
    }

    private static Font monospacedFont(JTextArea textArea) {
        Font base = textArea.getFont() != null ? textArea.getFont() : UIUtil.getLabelFont();
        return new Font(Font.MONOSPACED, Font.PLAIN, base.getSize());
    }
}
