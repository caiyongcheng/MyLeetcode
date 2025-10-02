package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * You are given two integers numBottles and numExchange.
 * numBottles represents the number of full water bottles that you initially have.
 * In one operation, you can perform one of the following operations:
 * Drink any number of full water bottles turning them into empty bottles.
 * Exchange numExchange empty bottles with one full water bottle.
 * Then, increase numExchange by one.
 * Note that you cannot exchange multiple batches of empty bottles for the same value of numExchange.
 * For example, if numBottles == 3 and numExchange == 1, you cannot exchange 3 empty water bottles for 3 full bottles.
 * Return the maximum number of water bottles you can drink.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-10-02 12:41
 */
public class _3100 {

    public int maxBottlesDrunk(int numBottles, int numExchange) {
        // 也可以设交换次数为t,然后解方程
        int ans = numBottles;
        while (numBottles >= numExchange) {
            numBottles -= numExchange;
            ++ans;
            ++numBottles;
            ++numExchange;
        }
        return ans;
    }

    /**
     * Input: numBottles = 13, numExchange = 6
     * Output: 15
     * Explanation: The table above shows the number of full water bottles, empty water bottles, the value of numExchange, and the number of bottles drunk.
     * Example 2:
     *
     *
     * Input: numBottles = 10, numExchange = 3
     * Output: 13
     * Explanation: The table above shows the number of full water bottles, empty water bottles, the value of numExchange, and the number of bottles drunk.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
