package letcode.normal.medium;

import letcode.utils.TestCaseUtils;

import java.util.Arrays;

/**
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。  计算并返回可以凑成总金额所需的 最少的硬币个数 。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1 。  你可以认为每种硬币的数量是无限的。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/3/26 11:14
 */
public class _322ThreeHundredTwentyTwo {

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        // dp[i] 表示构成金额所需的最少硬币
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + coins.length);
        for (int coin : coins) {
            if (coin > amount) {
                continue;
            } else if (coin == amount) {
                return 1;
            } else {
                dp[coin] = 1;
            }
        }
        for (int i = 1; i < dp.length; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Integer.min(dp[i], 1 + dp[i - coin]);
                }
                // 这里不需要判断几枚硬币 因为 cnt + dp[i - cnt * coin] <= 1 + dp[i - coin]
/*                for (int cnt = 0; cnt * coin <= i; cnt++) {
                    dp[i] = Integer.min(dp[i], cnt + dp[i - cnt * coin]);
                }*/
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }


    /**
     * 示例 1：
     *
     * 输入：coins = [1, 2, 5], amount = 11
     * 输出：3
     * 解释：11 = 5 + 5 + 1
     * 示例 2：
     *
     * 输入：coins = [2], amount = 3
     * 输出：-1
     * 示例 3：
     *
     * 输入：coins = [1], amount = 0
     * 输出：0
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _322ThreeHundredTwentyTwo().coinChange(
                TestCaseUtils.getIntArr("[1, 2, 5]"),
                11
        ));
    }

}
