package letcode.normal.difficult;

/**
 * 115. Distinct Subsequences
 * Difficulty: Hard
 * Link: https://leetcode.cn/problems/distinct-subsequences/
 * <p>
 * Given two strings s and t, return the number of distinct subsequences of s which equals t.
 * <p>
 * The test cases are generated so that the answer fits on a 32-bit signed integer.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "rabbbit", t = "rabbit"
 * Output: 3
 * Explanation:
 * As shown below, there are 3 ways you can generate "rabbit" from s.
 * rabb b it
 * ra b bbit
 * rab b bit
 * <p>
 * Example 2:
 * <p>
 * Input: s = "babgbag", t = "bag"
 * Output: 5
 * Explanation:
 * As shown below, there are 5 ways you can generate "bag" from s.
 * ba b g bag
 * ba bgba g
 * b abgb ag
 * ba b gb ag
 * babg bag
 * <p>
 * Constraints:
 * <p>
 * - 1 <= s.length, t.length <= 1000
 * <p>
 * - s and t consist of English letters.
 */
public class _115 {

    public int numDistinct(String s, String t) {

        // dp[j] 表示：
        // 当前已经遍历过的 s 中，组成 t 前 j 个字符的方案数
        int[] dp = new int[t.length() + 1];

        // 组成空字符串永远有 1 种方式：什么都不选
        dp[0] = 1;

        int sLen = s.length();
        for (int i = 0; i < sLen; i++) {
            for (int j = dp.length - 1; j >= 1; j--) {
                if (s.charAt(i) == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        return dp[t.length()];
    }
}
