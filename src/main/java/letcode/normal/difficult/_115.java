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

        int[][] dp = new int[s.length() + 1][t.length() + 1];

        for (int row = 0; row < dp.length; row++) {
            dp[row][0] = 1;
        }

        for (int row = 1; row < dp.length; row++) {
            for (int col = 1; col < dp[row].length; col++) {
                dp[row][col] = dp[row - 1][col] + (s.charAt(row - 1) == t.charAt(col - 1) ? dp[row - 1][col - 1] : 0);
            }
        }

        return dp[dp.length - 1][dp[0].length - 1];

    }
}
