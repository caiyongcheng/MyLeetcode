package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 * A fancy string is a string where no three consecutive characters are equal.
 * Given a string s, delete the minimum possible number of characters from s to make it fancy.
 * Return the final string after the deletion. It can be shown that the answer will always be unique.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-07-21 16:41
 */
public class _1957 {

    public String makeFancyString(String s) {
        int length = s.length();
        if (length <= 2) {
            return s;
        }
        char fistCh = s.charAt(0);
        char secondCh = s.charAt(1);
        char thirdCh;
        StringBuilder ans = new StringBuilder();
        ans.append(fistCh).append(secondCh);
        for (int i = 2; i < length; i++) {
            thirdCh = s.charAt(i);
            if (thirdCh != fistCh || thirdCh != secondCh) {
                ans.append(thirdCh);
                fistCh = secondCh;
                secondCh = thirdCh;
            }
        }
        return ans.toString();
    }

    /**
     * Example 1:
     *
     * Input: s = "leeetcode"
     * Output: "leetcode"
     * Explanation:
     * Remove an 'e' from the first group of 'e's to create "leetcode".
     * No three consecutive characters are equal, so return "leetcode".
     * Example 2:
     *
     * Input: s = "aaabaaaa"
     * Output: "aabaa"
     * Explanation:
     * Remove an 'a' from the first group of 'a's to create "aabaaaa".
     * Remove two 'a's from the second group of 'a's to create "aabaa".
     * No three consecutive characters are equal, so return "aabaa".
     * Example 3:
     *
     * Input: s = "aab"
     * Output: "aab"
     * Explanation: No three consecutive characters are equal, so return "aab".
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
