package letcode.normal.medium;

/**
 * 给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。
 *
 * @author CaiYongcheng
 * @since 2021-09-25 16:03
 **/
public class _583 {

    public int minDistance(String word1, String word2) {
        /*
         * 等价求出两个字符串的最长子序列
         * 显然使用动态规划去解决
         * dp[i][j] 表示包含i，j位置的最长子序列
         * dp[i][j] = chs1[i] == chs2[j] ? (1 + dp[i+1][j+1]) : Math.max(dp[i+1][j], dp[i][j+1])
         * 所以 要求 dp[i][j] 则 要求 dp[i+1][j+1] dp[i+1][j] dp[i][j+1]
         */
        char[] chs1 = word1.toCharArray();
        char[] chs2 = word2.toCharArray();
        int[][] dp = new int[chs1.length + 1][chs2.length + 1];
        for (int i = dp.length - 2; i >= 0; i--) {
            for (int j = dp[i].length - 2; j >= 0; j--) {
                if (chs1[i] == chs2[j]) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return chs1.length - dp[0][0] + chs2.length - dp[0][0];
    }

}
