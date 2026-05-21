package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 * You are given two strings s1 and s2, both of length 4, consisting of lowercase English letters.
 * You can apply the following operation on any of the two strings any number of times:
 * Choose any two indices i and j such that j - i = 2, then swap the two characters at those indices in the string.
 * Return true if you can make the strings s1 and s2 equal, and false otherwise.
 *
 * Constraints:
 *
 * s1.length == s2.length == 4
 * s1 and s2 consist only of lowercase English letters.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2026-03-29 21:14
 */
public class _2839 {

    public boolean canBeEqual(String s1, String s2) {
        return equal(s1, s2, 0, 2) && equal(s1, s2, 1, 2);
    }

    private boolean equal(String s1, String s2, int startIdx, int step) {
        int[] chArr = new int[26];
        int len = s1.length();
        for (int i = startIdx; i < len; i+=step) {
            chArr[s1.charAt(i) - 'a']++;
            chArr[s2.charAt(i) - 'a']--;
        }

        for (int chCnt : chArr) {
            if (chCnt != 0) {
                return false;
            }
        }
        return true;
    }

}
