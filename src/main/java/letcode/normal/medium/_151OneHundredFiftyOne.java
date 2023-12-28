package letcode.normal.medium;

import java.util.Arrays;
import java.util.Stack;

/**
 * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。  单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。  注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。
 * 返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2023/12/27 16:35
 */
public class _151OneHundredFiftyOne {

    public String reverseWords(String s) {


        Stack<String> stack = new Stack<>();
        int len = s.length();
        StringBuilder sb = new StringBuilder();
        char ch;
        for (int i = 0; i < len; i++) {
            ch = s.charAt(i);
            if (ch == ' ') {
                if (sb.length() > 0) {
                    stack.push(sb.toString());
                    sb.delete(0, sb.length());
                }
            } else {
                sb.append(ch);
            }
        }
        if (sb.length() > 0) {
            stack.push(sb.toString());
            sb.delete(0, sb.length());
        }
        while (!stack.empty()) {
            sb.append(stack.pop()).append(' ');
        }
        if (sb.charAt(sb.length() - 1) == ' ') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

}
