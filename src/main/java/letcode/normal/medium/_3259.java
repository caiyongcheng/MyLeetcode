package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * You are given two integer arrays energyDrinkA and energyDrinkB of the same length n by a
 * futuristic sports scientist. These arrays represent the energy boosts per hour provided
 * by two different energy drinks, A and B, respectively.
 * You want to maximize your total energy boost by drinking one energy drink per hour. However,
 * if you want to switch from consuming one energy drink to the other, you need to wait
 * for one hour to cleanse your system (meaning you won't get any energy boost in that hour).
 * Return the maximum total energy boost you can gain in the next n hours.
 * Note that you can start consuming either of the two energy drinks.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-11-01 09:43
 */
public class _3259 {

    public long maxEnergyBoost(int[] energyDrinkA, int[] energyDrinkB) {
        /**
         * 明显就是使用动态规划 只需要4个参数就够了
         */
        int length = energyDrinkA.length;
        if (length == 1) {
            return Math.max(energyDrinkA[0], energyDrinkB[0]);
        }
        if (length == 2) {
            return Math.max(energyDrinkA[0] + energyDrinkB[1], energyDrinkB[0] + energyDrinkA[1]);
        }

        long dp[][] = new long[length][2];
        dp[length - 1][0] = energyDrinkA[length - 1];
        dp[length - 1][1] = energyDrinkB[length - 1];
        dp[length - 2][0] = energyDrinkA[length - 1] + energyDrinkA[length - 2];
        dp[length - 2][1] = energyDrinkB[length - 1] + energyDrinkB[length - 2];

        for (int i = dp.length - 3; i >= 0; i--) {
            dp[i][0] = energyDrinkA[i] + Long.max(
                    dp[i + 2][1],
                    dp[i + 1][0]
            );
            dp[i][1] = energyDrinkB[i] + Long.max(
                    dp[i + 2][0],
                    dp[i + 1][1]
            );
        }
        return Long.max(dp[0][0], dp[0][1]);
    }

    /**
     * Example 1:
     *
     * Input: energyDrinkA = [1,3,1], energyDrinkB = [3,1,1]
     *
     * Output: 5
     *
     * Explanation:
     *
     * To gain an energy boost of 5, drink only the energy drink A (or only B).
     *
     * Example 2:
     *
     * Input: energyDrinkA = [4,1,1], energyDrinkB = [1,1,3]
     *
     * Output: 7
     *
     * Explanation:
     *
     * To gain an energy boost of 7:
     *
     * Drink the energy drink A for the first hour.
     * Switch to the energy drink B and we lose the energy boost of the second hour.
     * Gain the energy boost of the drink B in the third hour.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(_3259.class);
    }

}
