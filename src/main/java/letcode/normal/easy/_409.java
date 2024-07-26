package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 * Given a string s which consists of lowercase or uppercase letters,
 * return the length of the longest  palindrome  that can be built with those letters.
 * Letters are case sensitive, for example, "Aa" is not considered a palindrome.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-07-20 00:01
 */
public class _409 {

    public int longestPalindrome(String s) {
        int[] countArr = new int[58];
        int len = s.length();
        for (int i = 0; i < len; i++) {
            countArr[s.charAt(i) - 'A']++;
        }

        int res = 0;
        int odd = 0;
        for (int count : countArr) {
            res += (count >> 1 << 1);
            if ((count & 1) == 1) {
                odd = 1;
            }
        }
        return res + odd;
    }

    /**
     * Example 1:
     * Input: s = "abccccdd"
     * Output: 7
     * Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.
     * Example 2:
     * Input: s = "a"
     * Output: 1
     * Explanation: The longest palindrome that can be built is "a", whose length is 1.
     * @param args
     */
    public static void main(String[] args) {
/*        TestUtil.test(_409.class, "Example 1: Input: s = \"abccccdd\" Output: 7 Explanation: One longest palindrome that can be built is \"dccaccd\", " +
                "whose length is 7. Example 2: Input: s = \"a\" Output: 1 Explanation: The longest palindrome that can be built is \"a\", whose length is 1.");*/
        TestUtil.testBatch(_409.class, "adam");
    }

}
