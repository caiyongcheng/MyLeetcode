package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 * 给你一个字符串 s 。  你的任务是重复以下操作删除 所有 数字字符：
 * 删除 第一个数字字符 以及它左边 最近 的 非数字 字符。
 * 请你返回删除所有数字字符以后剩下的字符串。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-09-05 09:41
 */
public class _3147 {

    public String clearDigits(String s) {
        char[] charArray = s.toCharArray();
        int numCnt = 0;
        for (int i = charArray.length - 1; i >= 0; i--) {
            if (charArray[i] >= '0' && charArray[i] <= '9') {
                charArray[i] = '0';
                ++numCnt;
            } else if (numCnt > 0) {
                charArray[i] = '0';
                --numCnt;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : charArray) {
            if (c != '0') {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Example 1:
     *
     * Input: s = "abc"
     *
     * Output: "abc"
     *
     * Explanation:
     *
     * There is no digit in the string.
     *
     * Example 2:
     *
     * Input: s = "cb34"
     *
     * Output: ""
     *
     * Explanation:
     *
     * First, we apply the operation on s[2], and s becomes "c4".
     *
     * Then we apply the operation on s[1], and s becomes "".
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(_3147.class);
    }

}
