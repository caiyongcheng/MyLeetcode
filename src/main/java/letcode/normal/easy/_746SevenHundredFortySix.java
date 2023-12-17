package letcode.normal.easy;


import letcode.utils.TestCaseUtils;

/**
 * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 * 请你计算并返回达到楼梯顶部的最低花费。
 *
 * @author CaiYongcheng
 * @date 2022-01-13 09:00
 **/
public class _746SevenHundredFortySix {


    public int minCostClimbingStairs(int[] cost) {
        if (cost.length == 1) {
            return cost[0];
        }
        /*
        典型的动态规划
         */
        int[] minCostArr = new int[cost.length];
        minCostArr[0] = cost[0];
        minCostArr[1] = cost[1];
        for (int i = 2; i < minCostArr.length; i++) {
            minCostArr[i] = Integer.min(minCostArr[i-1], minCostArr[i-2]) + cost[i];
        }
        return Integer.min(minCostArr[minCostArr.length - 1], minCostArr[minCostArr.length - 2]);
    }


    /**
     * 示例 1：
     *
     * 输入：cost = [10,15,20]
     * 输出：15
     * 解释：你将从下标为 1 的台阶开始。
     * - 支付 15 ，向上爬两个台阶，到达楼梯顶部。
     * 总花费为 15 。
     * 示例 2：
     *
     * 输入：cost = [1,100,1,1,1,100,1,1,100,1]
     * 输出：6
     * 解释：你将从下标为 0 的台阶开始。
     * - 支付 1 ，向上爬两个台阶，到达下标为 2 的台阶。
     * - 支付 1 ，向上爬两个台阶，到达下标为 4 的台阶。
     * - 支付 1 ，向上爬两个台阶，到达下标为 6 的台阶。
     * - 支付 1 ，向上爬一个台阶，到达下标为 7 的台阶。
     * - 支付 1 ，向上爬两个台阶，到达下标为 9 的台阶。
     * - 支付 1 ，向上爬一个台阶，到达楼梯顶部。
     * 总花费为 6 。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _746SevenHundredFortySix().minCostClimbingStairs(
                TestCaseUtils.getIntArr("[10,15,20]")
        ));
    }
}
