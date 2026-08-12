package letcode.normal.unansweredquestions.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 1140. Stone Game II
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/stone-game-ii/
 * <p>
 * Alice and Bob continue their games with piles of stones. There are a number of piles arranged in a
 * row , and each pile has a positive integer number of stones piles[i] . The objective of the game is
 * to end with the most stones.
 * <p>
 * Alice and Bob take turns, with Alice starting first.
 * <p>
 * On each player's turn, that player can take all the stones in the first X remaining piles, where 1
 * <= X <= 2M . Then, we set M = max(M, X) . Initially, M = 1.
 * <p>
 * The game continues until all the stones have been taken.
 * <p>
 * Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.
 * <p>
 * Example 1:
 * <p>
 * Input: piles = [2,7,9,4,4]
 * <p>
 * Output: 10
 * <p>
 * Explanation:
 * <p>
 * - If Alice takes one pile at the beginning, Bob takes two piles, then Alice takes 2 piles again.
 * Alice can get 2 + 4 + 4 = 10 stones in total.
 * <p>
 * - If Alice takes two piles at the beginning, then Bob can take all three piles left. In this case,
 * Alice get 2 + 7 = 9 stones in total.
 * <p>
 * So we return 10 since it's larger.
 * <p>
 * Example 2:
 * <p>
 * Input: piles = [1,2,3,4,5,100]
 * <p>
 * Output: 104
 * <p>
 * Constraints:
 * <p>
 * - 1 <= piles.length <= 100
 * <p>
 * - 1 <= piles[i] <= 10 4
 */
public class _1140 {

    public int stoneGameII(int[] piles) {
        // 假设 dp[i]表示从i开始选择的最大收益
        // 那么 dp[i] = suffixSum[i] - min(dp[i+x])

        int[] suffixSum = new int[piles.length];
        suffixSum[suffixSum.length - 1] = piles[suffixSum.length - 1];
        for (int i = suffixSum.length - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }

        List<Integer>[] dp = new ArrayList[piles.length + 1];
        return 0;
    }


    private int search(int[] suffixSum, int idx, int m) {
        return 0;
    }







}
