package letcode.normal.medium;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/11/23 9:55
 * description 「HTML 实体解析器」 是一种特殊的解析器，它将 HTML 代码作为输入，并用字符本身替换掉所有这些特殊的字符实体。
 * HTML 里这些特殊字符和它们对应的字符实体包括：  双引号：字符实体为 &quot; ，对应的字符是 " 。
 * 单引号：字符实体为 &apos; ，对应的字符是 ' 。 与符号：字符实体为 &amp; ，对应对的字符是 & 。
 * 大于号：字符实体为 &gt; ，对应的字符是 > 。 小于号：字符实体为 &lt; ，对应的字符是 < 。 斜线号：字符实体为 &frasl; ，对应的字符是 / 。
 * 给你输入字符串 text ，请你实现一个 HTML 实体解析器，返回解析器解析后的结果。
 */
public class _1440 {

    public String entityParser1(String text) {
        return text.replaceAll("&quot;", "\"")
                .replaceAll("&apos;", "'")
                .replaceAll("&gt;", ">")
                .replaceAll("&lt;", "<")
                .replaceAll("&frasl;", "/")
                .replaceAll("&amp;", "&");
    }

    static private String[] escapeArr = new String[]{ "&quot;", "&apos;", "&gt;", "&lt;", "&frasl;", "&amp;"};
    static private String[] escapeRstArr = new String[]{ "\"", "'", ">", "<", "/", "&"};

    public String entityParser(String text) {
        StringBuilder sb = new StringBuilder();
        char[] charArr = text.toCharArray();
        int j;
        int x;
        String escape;
        for (int i = 0; i < charArr.length; i++) {
            if (charArr[i] != '&') {
                sb.append(charArr[i]);
                continue;
            }
            if (i > charArr.length - 4) {
                sb.append(charArr[i]);
                continue;
            }
            for (x = 0; x < escapeArr.length; x++) {
                escape = escapeArr[x];
                for (j = 0; j < escape.length(); j++) {
                    if (j + i >= charArr.length) {
                        break;
                    }
                    if (charArr[j + i] != escape.charAt(j)) {
                        break;
                    }
                }
                if (j >= escape.length()) {
                    sb.append(escapeRstArr[x]);
                    i = i + j - 1;
                    break;
                }
            }
            if (x >= escapeArr.length) {
                sb.append(charArr[i]);
            }
        }
        return sb.toString();
    }


}
