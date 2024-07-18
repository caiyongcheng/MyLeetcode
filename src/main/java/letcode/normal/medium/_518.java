package letcode.normal.medium;

import letcode.utils.TestCaseInputUtils;

/**
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
 * 假设每一种面额的硬币有无限个。   题目数据保证结果符合 32 位带符号整数。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/3/25 09:16
 */
public class _518 {

    int[][] chche = new int[300][5001];
    boolean[][] isCalculate = new boolean[300][5001];

    public int change(int amount, int[] coins) {
        /**
         * 记忆化搜索
         */
        return search(coins, 0, amount);
    }

    public int changeDp(int amount, int[] coins) {
        /**
         * 记忆化搜索转变化dp实现
         * dp[i][j] 表示选择到第i个金额，金额为j的组合数
         * dp[i][j] = dp[i-1][j - t*coins[i-1]];
         */
        int[][] dp = new int[coins.length][amount + 1];
        for (int tempAmount = 0; tempAmount <= amount; tempAmount += coins[0]) {
            dp[0][tempAmount] = 1;
        }
        for (int i = 1; i < coins.length -  1; i++) {
            for (int j = 0; j <= amount; j++) {
                for (int tempJ = 0; tempJ <= j; tempJ += coins[i]) {
                    dp[i][j] += dp[i - 1][j - tempJ];
                }
            }
        }
        if (coins.length > 1) {
            for (int tempJ = 0; tempJ <= amount; tempJ += coins[coins.length - 1]) {
                dp[coins.length - 1][amount] += dp[coins.length - 2][amount - tempJ];
            }
        }
        return dp[coins.length - 1][amount];
    }

    public int changeDp2(int amount, int[] coins) {
        /**
         * 记忆化搜索转变化dp实现
         * dp[i][j] 表示选择到第i个金额，金额为j的组合数
         * dp[i][j] = dp[i-1][j - t*coins[i-1]];
         *
         * 换一种思路 dp[j] = 表示金额为j的组合数
         * dp[j] = sum(dp[j - coins[i])
         * 不能外层枚举amount 因为会发生重复
         */
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    public int search(int[] coins, int idx, int target) {
        if (idx >= coins.length) {
            return target == 0 ? 1 : 0;
        }
        if (isCalculate[idx][target]) {
            return chche[idx][target];
        }
        int cnt = 0;
        int tempTarget = target;
        while (tempTarget >= 0) {
            cnt += search(coins,idx + 1, tempTarget);
            tempTarget -= coins[idx];
        }
        isCalculate[idx][target] = true;
        chche[idx][target] = cnt;
        return chche[idx][target];
    }

    /**
     * 示例 1：
     *
     * 输入：amount = 5, coins = [1, 2, 5]
     * 输出：4
     * 解释：有四种方式可以凑成总金额：
     * 5=5
     * 5=2+2+1
     * 5=2+1+1+1
     * 5=1+1+1+1+1
     * 示例 2：
     *
     * 输入：amount = 3, coins = [2]
     * 输出：0
     * 解释：只用面额 2 的硬币不能凑成总金额 3 。
     * 示例 3：
     *
     * 输入：amount = 10, coins = [10]
     * 输出：1
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _518().change(
                5,
                TestCaseInputUtils.getIntArr("[1, 2, 5]")
        ));
        System.out.println(new _518().changeDp(
                5,
                TestCaseInputUtils.getIntArr("[1, 2, 5]")
        ));
        System.out.println(new _518().changeDp2(
                5,
                TestCaseInputUtils.getIntArr("[1, 2, 5]")
        ));
    }

}
