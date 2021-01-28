package letcode.normal.medium;

import java.util.HashMap;

/**
 * @program: Leetcode
 * @description: 给定不同面额的硬币 coins 和一个总金额 amount。
 * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 你可以认为每种硬币的数量是无限的。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-12-11 15:51
 */
public class N_322ThreeHundredTwentyTwo {

    private int[] coins;
    private int coinSize;
    private HashMap<String, Integer> cache;

    public int dp(int coinsIndex, int remainAount) {


        int minCost = Integer.MAX_VALUE;
        String key;
        if (remainAount < coins[coinsIndex]) {
            return Integer.MAX_VALUE;
        }
        while (remainAount >= coins[coinsIndex]) {
            remainAount -= coins[coinsIndex];
            key = remainAount + "_" + coins[coinsIndex + 1];
            if (cache.containsKey(key)
                    && cache.get(key) < minCost) {

            }
        }

        return 0;

    }


    public int coinChange(int[] coins, int amount) {
        return 0;
    }

}