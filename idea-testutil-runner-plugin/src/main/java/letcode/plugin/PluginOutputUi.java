package letcode.plugin;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;

/**
 * 插件输出区 UI：等宽字体、不自动换行，布局尽量贴近控制台。
 */
final class PluginOutputUi {

    private PluginOutputUi() {
    }

    static JTextArea createReadOnlyOutputArea(String content, int rows, int columns) {
        JTextArea textArea = new JTextArea(content == null ? "" : content, rows, columns);
        textArea.setEditable(false);
        textArea.setLineWrap(false);
        textArea.setWrapStyleWord(false);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        textArea.setTabSize(4);
        return textArea;
    }

    static JScrollPane wrapOutputArea(JTextArea textArea) {
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        return scrollPane;
    }
}
