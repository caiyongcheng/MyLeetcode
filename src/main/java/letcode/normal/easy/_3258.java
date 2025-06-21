package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 * You are given a binary string s and an integer k.  A binary string satisfies the k-constraint if either of the
 * following conditions holds:  The number of 0's in the string is at most k. The number of 1's in the string is at
 * most k. Return an integer denoting the number of  substrings  of s that satisfy the k-constraint.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-11-13 15:12
 */
public class _3258 {

    public int countKConstraintSubstrings(String s, int k) {
        int[] num2Cnt = new int[2];
        int length = s.length();

        int left = 0;
        int right = 0;
        int ans = 0;

        for (; left < length; left++) {
            while (right < length) {
                num2Cnt[s.charAt(right) - '0']++;
                if (num2Cnt[0] > k && num2Cnt[1] > k) {
                    num2Cnt[s.charAt(right) - '0']--;
                    break;
                }
                right++;
            }
            ans += right - left;
            num2Cnt[s.charAt(left) - '0']--;
        }
        return ans;
    }

    /**
     * Example 1:
     *
     * Input: s = "10101", k = 1
     *
     * Output: 12
     *
     * Explanation:
     *
     * Every substring of s except the substrings "1010", "10101", and "0101" satisfies the k-constraint.
     *
     * Example 2:
     *
     * Input: s = "1010101", k = 2
     *
     * Output: 25
     *
     * Explanation:
     *
     * Every substring of s except the substrings with a length greater than 5 satisfies the k-constraint.
     *
     * Example 3:
     *
     * Input: s = "11111", k = 1
     *
     * Output: 15
     *
     * Explanation:
     *
     * All substrings of s satisfy the k-constraint.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(_3258.class);
    }

}
