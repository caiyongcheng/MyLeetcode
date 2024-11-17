package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。
 * 你从其中的一个加油站出发，开始时油箱为空。
 * 给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-10-10 14:13
 */
public class _134 {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int curBenefitGasIdx = 0;
        int curBenefitGas = 0;
        for (int i = 0; i < gas.length << 1; i++) {
            if (i - curBenefitGasIdx == gas.length) {
                return curBenefitGasIdx;
            }
            // 还能往前走就继续走
            if (curBenefitGas + gas[i % gas.length] - cost[i % gas.length] >= 0) {
                curBenefitGas += gas[i % gas.length] - cost[i % gas.length];
            } else {
                // 否则说明从curBenefitGasIdx开始的不行
                // 那么从curBenefitGasIdx到i之间的某段开始行不行呢
                // 也是不行的 因为这一段开始能得到的gas值只会小于等于从curBenefitGasIdx开始的gas值
                curBenefitGasIdx = i + 1;
                curBenefitGas = 0;
            }
        }
        return -1;
    }

    /**
     * Example 1:
     *
     * Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
     * Output: 3
     * Explanation:
     * Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
     * Travel to station 4. Your tank = 4 - 1 + 5 = 8
     * Travel to station 0. Your tank = 8 - 2 + 1 = 7
     * Travel to station 1. Your tank = 7 - 3 + 2 = 6
     * Travel to station 2. Your tank = 6 - 4 + 3 = 5
     * Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
     * Therefore, return 3 as the starting index.
     * Example 2:
     *
     * Input: gas = [2,3,4], cost = [3,4,3]
     * Output: -1
     * Explanation:
     * You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
     * Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
     * Travel to station 0. Your tank = 4 - 3 + 2 = 3
     * Travel to station 1. Your tank = 3 - 3 + 3 = 3
     * You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
     * Therefore, you can't travel around the circuit once no matter where you start.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(_134.class);
    }

}
