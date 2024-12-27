package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 * Given a string s, find any  substring  of length 2 which is also present in the reverse of s.
 * Return true if such a substring exists, and false otherwise.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-12-26 15:47
 */
public class _3083 {

    public boolean isSubstringPresent(String s) {
        // 也可以使用 flag[ch1] = 1 << ch2的形式
        int[] flag = new int[26 * 26];
        int len = s.length() - 1;

        int ch1;
        int ch2;
        for (int i = 0; i < len; i++) {
            ch1 = s.charAt(i) - 'a';
            ch2 = s.charAt(i + 1) - 'a';
            flag[ch1 * 26 + ch2]++;
            if (flag[ch2 * 26 + ch1] > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Example 1:
     *
     * Input: s = "leetcode"
     *
     * Output: true
     *
     * Explanation: Substring "ee" is of length 2 which is also present in reverse(s) == "edocteel".
     *
     * Example 2:
     *
     * Input: s = "abcba"
     *
     * Output: true
     *
     * Explanation: All of the substrings of length 2 "ab", "bc", "cb", "ba" are also present in reverse(s) == "abcba".
     *
     * Example 3:
     *
     * Input: s = "abcd"
     *
     * Output: false
     *
     * Explanation: There is no substring of length 2 in s, which is also present in the reverse of s.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(_3083.class);
    }

}
