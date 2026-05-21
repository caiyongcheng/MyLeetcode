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

}
