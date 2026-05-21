package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 * You are given two strings s and t such that every character occurs at most once in s and t is a permutation of s.
 * The permutation difference between s and t is defined as the sum of the absolute difference between the index of
 * the occurrence of each character in s and the index of the occurrence of the same character in t.
 * Return the permutation difference between s and t.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-08-26 16:33
 */
public class _3146 {

    public int findPermutationDifference(String s, String t) {
        int[] char2Idx = new int[26];
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char2Idx[s.charAt(i) - 'a'] = i;
        }

        int ans = 0;
        for (int i = 0; i < length; i++) {
            char c = t.charAt(i);
            int idx = char2Idx[c - 'a'];
            ans += Math.abs(idx - i);
        }
        return ans;
    }

}
