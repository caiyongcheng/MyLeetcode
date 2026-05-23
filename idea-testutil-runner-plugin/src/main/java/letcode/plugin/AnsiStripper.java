package letcode.plugin;

import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

/**
 * 剥离 ANSI 转义序列，供插件弹窗/输出文件展示纯文本。
 */
final class AnsiStripper {

    private static final Pattern ANSI_ESCAPE = Pattern.compile(
            "\u001B\\[[0-9;?]*[ -/]*[@-~]|\u001B[@-Z\\\\-_]|\u001B\\].*?(?:\u0007|\u001B\\\\)|\u009B[0-9;?]*[ -/]*[@-~]"
    );

    private AnsiStripper() {
    }

    static String strip(String text) {
        if (text == null || text.isEmpty()) {
            return text == null ? "" : text;
        }
        return ANSI_ESCAPE.matcher(text).replaceAll("");
    }

    static byte[] stripBytes(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return bytes == null ? new byte[0] : bytes;
        }
        return strip(new String(bytes, StandardCharsets.UTF_8)).getBytes(StandardCharsets.UTF_8);
    }
}
