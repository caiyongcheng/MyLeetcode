package letcode.normal.medium;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/10/24 9:28
 * description 这里有 n 个一样的骰子，每个骰子上都有 k 个面，分别标号为 1 到 k 。  给定三个整数 n ,  k 和 target ，
 * 返回可能的方式(从总共 kn 种方式中)滚动骰子的数量，使正面朝上的数字之和等于 target 。  答案可能很大，你需要对 109 + 7 取模 。
 */
public class _1155OneThousandOneHundredFiftyFive {


    public int numRollsToTarget(int n, int k, int target) {
        /**
         * 设 cul[a][b] 表示和为b，使用k个筛子的方式
         * 那么 cul[a][b] = for(i=0; i<a; ++i) for(j=0; j<n; ++j) sum(cul[i][j] * cul[a-i][b-j])
         */
        int[][] f = new int[n + 1][target + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= target; ++j) {
                for (int x = 1; x <= k; ++x) {
                    if (j - x >= 0) {
                        f[i][j] = (f[i][j] + f[i - 1][j - x]) % 1000000007;
                    }
                }
            }
        }
        return f[n][target];
    }

}
