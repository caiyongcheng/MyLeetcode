package letcode.normal.difficult;

/**
 * 940. Distinct Subsequences II
 * Difficulty: Hard
 * Link: https://leetcode.cn/problems/distinct-subsequences-ii/
 * <p>
 * Given a string s, return the number of distinct non-empty subsequences of s . Since the answer may
 * be very large, return it modulo 10 9 + 7 .
 * <p>
 * A subsequence of a string is a new string that is formed from the original string by deleting some
 * (can be none) of the characters without disturbing the relative positions of the remaining
 * characters. (i.e., "ace" is a subsequence of " a b c d e " while "aec" is not.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abc"
 * Output: 7
 * Explanation: The 7 distinct subsequences are "a", "b", "c", "ab", "ac", "bc", and "abc".
 * <p>
 * Example 2:
 * <p>
 * Input: s = "aba"
 * Output: 6
 * Explanation: The 6 distinct subsequences are "a", "b", "ab", "aa", "ba", and "aba".
 * <p>
 * Example 3:
 * <p>
 * Input: s = "aaa"
 * Output: 3
 * Explanation: The 3 distinct subsequences are "a", "aa" and "aaa".
 * <p>
 * Constraints:
 * <p>
 * - 1 <= s.length <= 2000
 * <p>
 * - s consists of lowercase English letters.
 */
public class _940 {

    private static final int MODE_NUM = 1_000_000_000 + 7;

    public int distinctSubseqII(String s) {
        // charArr[i] 等于 以 i + 'a' 结尾的子串数量 加上 s[i]本身
        int[] charArr = new int[26];

        int length = s.length();
        int sum = 0;
        int chNo;
        int temp;
        for (int i = 0; i < length; i++) {
            // 以s[i]结尾的子串可以由上一轮的所有子串加上s[i]得到
            chNo = s.charAt(i) - 'a';
            temp = charArr[chNo];
            charArr[chNo] = (sum + 1) % MODE_NUM;
            sum = (sum + MODE_NUM - temp + charArr[chNo]) % MODE_NUM;
        }

        return sum;

    }
}
