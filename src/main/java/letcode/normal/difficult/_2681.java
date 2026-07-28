package letcode.normal.difficult;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/8/1 9:04
 * description 给你一个下标从 0 开始的整数数组 nums ，它表示英雄的能力值。
 * 如果我们选出一部分英雄，这组英雄的 力量 定义为：  i0 ，i1 ，... ik 表示这组英雄在数组中的下标。
 * 那么这组英雄的力量为 max(nums[i0],nums[i1] ... nums[ik])2 * min(nums[i0],nums[i1] ... nums[ik]) 。
 * 请你返回所有可能的 非空 英雄组的 力量 之和。由于答案可能非常大，请你将结果对 109 + 7 取余。
 */
public class _2681 {

    static BigDecimal MOD_NUM = BigDecimal.valueOf(1000000000 + 7);


    public int sumOfPower(int[] nums) {
        /*
        英雄组共有(2^n)-1个集合数量 所以暴力一定会超时
        考虑 问题的关键在于 集合内的最大值与最小值 集合内元素数量
        所以先将元素 按从小到大排序 得到序列 a0、a1、a2、a3 .... an
        其中
        [a0, aj] 的结果为 aj * aj * dp[j]  dp[j]表示为(a0, aj) 所有子序列中的最小值的和
        [a0, a[j+1]]的结果为 a[j+1] * a[j+1] * dp[j+1]  那么 已知 dp[j] 如何求出 dp[j+1]
        首先 dp[j]表示为(a0, aj) 所有包含[aj]子序列中的最小值的和
        那么 dp[j+1]表示为(a0, a[j+1]) 所有包含a[j+1]子序列中的最小值的和，
        (a0, a[j+1]) 所有子序列可以看作是由dp[0]、dp[1]、...dp[j]的每个子序列加上a[j+1]得到，再加上单独的一个a[j+1]

         */

        BigDecimal rst = BigDecimal.ZERO;
        //排序
        Arrays.sort(nums);
        BigDecimal[] numsWrap = new BigDecimal[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsWrap[i] = BigDecimal.valueOf(nums[i]);
        }
        //计算前缀和
        BigDecimal preSum = BigDecimal.ZERO;
        BigDecimal dpNum = BigDecimal.ZERO;
        //遍历得出结果
        for (int i = 0; i < nums.length; i++) {
            dpNum = preSum.add(numsWrap[i]).remainder(MOD_NUM);
            rst = rst.add(
                    numsWrap[i].pow(2).remainder(MOD_NUM).multiply(dpNum).remainder(MOD_NUM)
            ).remainder(MOD_NUM);
            preSum = preSum.add(MOD_NUM).add(dpNum).remainder(MOD_NUM);
        }
        return rst.intValue();
    }

}
