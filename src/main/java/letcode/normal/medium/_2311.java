package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * You are given a binary string s and a positive integer k.
 * Return the length of the longest subsequence of s that makes up a binary number less than or equal to k.
 * Note:  The subsequence can contain leading zeroes.
 * The empty string is considered to be equal to 0.
 * A subsequence is a string that can be derived from another string by deleting some
 * or no characters without changing the order of the remaining characters.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-06-26 11:08
 */
public class _2311 {

    public int longestSubsequence(String s, int k) {
        int length = s.length();
        int base = 0;
        int endIdx = length;
        long curNum = 0;
        while (curNum <= k) {
            --endIdx;
            if (endIdx < 0) {
                return length;
            }
            curNum += (long) (s.charAt(endIdx) - '0') << base;
            base++;
        }

        int ans = length - 1 - endIdx;
        for (int startIdx = 0; startIdx < endIdx; startIdx++) {
            if (s.charAt(startIdx) == '0') {
                ++ans;
            }
        }
        return ans;
    }

    /**
     * Example 1:
     *
     * Input: s = "1001010", k = 5
     * Output: 5
     * Explanation: The longest subsequence of s that makes up a binary number less than or equal to 5 is "00010", as this number is equal to 2 in decimal.
     * Note that "00100" and "00101" are also possible, which are equal to 4 and 5 in decimal, respectively.
     * The length of this subsequence is 5, so 5 is returned.
     * Example 2:
     *
     * Input: s = "00101001", k = 1
     * Output: 6
     * Explanation: "000001" is the longest subsequence of s that makes up a binary number less than or equal to 1, as this number is equal to 1 in decimal.
     * The length of this subsequence is 6, so 6 is returned.
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
