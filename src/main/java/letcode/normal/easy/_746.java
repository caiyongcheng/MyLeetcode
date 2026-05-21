package letcode.normal.easy;


import letcode.utils.TestCaseInputUtils;

/**
 * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 * 请你计算并返回达到楼梯顶部的最低花费。
 *
 * @author CaiYongcheng
 * @since 2022-01-13 09:00
 **/
public class _746 {


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


}
