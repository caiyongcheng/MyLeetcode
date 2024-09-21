package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * An alphabetical continuous string is a string consisting of consecutive letters in the alphabet.
 * In other words, it is any substring of the string "abcdefghijklmnopqrstuvwxyz".  For example,
 * "abc" is an alphabetical continuous string, while "acb" and "za" are not. Given a string s consisting of
 * lowercase letters only, return the length of the longest alphabetical continuous substring.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-09-21 14:19
 */
public class _2414 {

    public int longestContinuousSubstring(String s) {
        byte[] bytes = s.getBytes();
        int ans = 0;
        int count = 1;
        for (int i = 1; i < bytes.length; i++) {
            if (bytes[i] - bytes[i - 1] == 1) {
                count++;
            } else {
                if (count > ans) {
                    ans = count;
                }
                count = 1;
            }
        }
        if (count > ans) {
            ans = count;
        }
        return ans;
    }

    /**
     * Example 1:
     *
     * Input: s = "abacaba"
     * Output: 2
     * Explanation: There are 4 distinct continuous substrings: "a", "b", "c" and "ab".
     * "ab" is the longest continuous substring.
     * Example 2:
     *
     * Input: s = "abcde"
     * Output: 5
     * Explanation: "abcde" is the longest continuous substring.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(_2414.class);
    }


}
