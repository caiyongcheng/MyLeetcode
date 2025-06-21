package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.PriorityQueue;

/**
* You are given an 0-indexed integer array prices where prices[i] denotes the number of coins needed to purchase
 * the (i + 1)th fruit.  The fruit market has the following reward for each fruit:  If you purchase the (i + 1)th
 * fruit at prices[i] coins, you can get any number of the next i fruits for free. Note that even if you can
 * take fruit j for free, you can still purchase it for prices[j - 1] coins to receive its reward.
 * Return the minimum number of coins needed to acquire all the fruits.
* @version  1.0.0
* @author   蔡永程  
* @since    2025-02-10 14:32
*/
public class _2944 {


    public int minimumCoins(int[] prices) {

        if (prices.length == 1) {
            return prices[0];
        }
        if (prices.length == 2) {
            return prices[0];
        }

        /*
            考虑贪心的做法 对于每个水果，要不直接购买，要不通过购买之前的水果免费赠送。
            但是对于这样的情况，p1、p2、p3, 为了获得p2，需要购买p1，然后为了购买p3，
            又需要获得p2, 既然这样直接购买p2明显更好。

            所以只用动态规划的方式, dp[i] 从i开始，获取所有结果需要的最小花费
            那么dp[i] = prices[i] + min(j from:i+2, to:i * 2, dp[j])
            对于[i + 1, i * 2]范围内的元素，可以使用最小堆进行维护,
         */
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(
                (a, b) -> {
                    if (prices[a] == prices[b]) {
                        return a - b;
                    }
                    return prices[a] - prices[b];
                }
        );
        for (int i = ((prices.length + 1) >> 1) - 1; i < prices.length; i++) {
            priorityQueue.add(i);
        }
        for (int i = ((prices.length + 1) >> 1) - 2; i > 0; i--) {
            while (!priorityQueue.isEmpty()) {
                int idx = priorityQueue.poll();
                if (idx <= (i << 1) + 2) {
                    prices[i] += prices[idx];
                    priorityQueue.offer(idx);
                    break;
                }
            }
            priorityQueue.add(i);
        }
        return prices[0] + Math.min(prices[1], prices[2]);
    }

    /**
     * Example 1:
     *
     * Input: prices = [3,1,2]
     *
     * Output: 4
     *
     * Explanation:
     *
     * Purchase the 1st fruit with prices[0] = 3 coins, you are allowed to take the 2nd fruit for free.
     * Purchase the 2nd fruit with prices[1] = 1 coin, you are allowed to take the 3rd fruit for free.
     * Take the 3rd fruit for free.
     * Note that even though you could take the 2nd fruit for free as a reward of buying 1st fruit, you purchase it to receive its reward, which is more optimal.
     *
     * Example 2:
     *
     * Input: prices = [1,10,1,1]
     *
     * Output: 2
     *
     * Explanation:
     *
     * Purchase the 1st fruit with prices[0] = 1 coin, you are allowed to take the 2nd fruit for free.
     * Take the 2nd fruit for free.
     * Purchase the 3rd fruit for prices[2] = 1 coin, you are allowed to take the 4th fruit for free.
     * Take the 4th fruit for free.
     * Example 3:
     *
     * Input: prices = [26,18,6,12,49,7,45,45]
     *
     * Output: 39
     *
     * Explanation:
     *
     * Purchase the 1st fruit with prices[0] = 26 coin, you are allowed to take the 2nd fruit for free.
     * Take the 2nd fruit for free.
     * Purchase the 3rd fruit for prices[2] = 6 coin, you are allowed to take the 4th, 5th and 6th (the next three) fruits for free.
     * Take the 4th fruit for free.
     * Take the 5th fruit for free.
     * Purchase the 6th fruit with prices[5] = 7 coin, you are allowed to take the 8th and 9th fruit for free.
     * Take the 7th fruit for free.
     * Take the 8th fruit for free.
     * Note that even though you could take the 6th fruit for free as a reward of buying 3rd fruit, you purchase it to receive its reward, which is more optimal.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test("=[27,17,29,45,3,39,42,26]");
    }

}
