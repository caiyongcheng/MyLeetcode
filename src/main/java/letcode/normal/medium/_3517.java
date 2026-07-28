package letcode.normal.medium;

/**
 * 3517. Smallest Palindromic Rearrangement I
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/smallest-palindromic-rearrangement-i/
 * <p>
 * You are given a palindromic string s .
 * <p>
 * Return the lexicographically smallest palindromic permutation of s .
 * <p>
 * Example 1:
 * <p>
 * Input: s = "z"
 * <p>
 * Output: "z"
 * <p>
 * Explanation:
 * <p>
 * A string of only one character is already the lexicographically smallest palindrome.
 * <p>
 * Example 2:
 * <p>
 * Input: s = "babab"
 * <p>
 * Output: "abbba"
 * <p>
 * Explanation:
 * <p>
 * Rearranging "babab" &rarr; "abbba" gives the smallest lexicographic palindrome.
 * <p>
 * Example 3:
 * <p>
 * Input: s = "daccad"
 * <p>
 * Output: "acddca"
 * <p>
 * Explanation:
 * <p>
 * Rearranging "daccad" &rarr; "acddca" gives the smallest lexicographic palindrome.
 * <p>
 * Constraints:
 * <p>
 * - 1 <= s.length <= 10 5
 * <p>
 * - s consists of lowercase English letters.
 * <p>
 * - s is guaranteed to be palindromic.
 */
public class _3517 {

    public String smallestPalindrome(String s) {
        char[] charArray = s.toCharArray();
        int[] char2Cnt = new int[26];

        for (char ch : charArray) {
            char2Cnt[ch - 'a']++;
        }

        int idx = 0;
        for (int i = 0; i < char2Cnt.length; i++) {
            if ((char2Cnt[i] & 1) == 1) {
                charArray[charArray.length >> 1] = (char) (i + 'a');
                --char2Cnt[i];
            }
            while (char2Cnt[i] > 0) {
                charArray[idx] = (char) (i + 'a');
                charArray[charArray.length - 1 - idx] = (char) (i + 'a');
                char2Cnt[i] -= 2;
                ++idx;
            }
        }

        return new String(charArray);

    }
}
