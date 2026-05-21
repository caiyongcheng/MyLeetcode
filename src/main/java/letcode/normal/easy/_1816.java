package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 * 句子 是一个单词列表，列表中的单词之间用单个空格隔开，且不存在前导或尾随空格。每个单词仅由大小写英文字母组成（不含标点符号）。
 * 例如，"Hello World"、"HELLO" 和 "hello world hello world" 都是句子。
 * 给你一个句子 s 和一个整数 k ，
 * 请你将 s 截断 ，使截断后的句子仅含 前 k 个单词。
 * 返回 截断 s 后得到的句子。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-07-16 16:36
 */
public class _1816 {

    public String truncateSentence(String s, int k) {
        int spaceCnt = 0;
        StringBuilder sb = new StringBuilder();
        int length = s.length();
        char ch;
        for (int i = 0; i < length; i++) {
            ch = s.charAt(i);
            if (ch == ' ') {
                spaceCnt++;
                if (spaceCnt == k) {
                    break;
                }
            }
            sb.append(ch);
        }
        return sb.toString();
    }

}
