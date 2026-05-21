package letcode.normal.medium;

import letcode.utils.TestCaseInputUtils;

import java.util.Arrays;

/**
 * 给你一个长度为 n 、下标从 0 开始的整数数组 nums ，表示收集不同巧克力的成本。每个巧克力都对应一个不同的类型，最初，位于下标 i 的巧克力就对应第 i 个类型。
 * 在一步操作中，你可以用成本 x 执行下述行为：  同时修改所有巧克力的类型，将巧克力的类型 ith 修改为类型 ((i + 1) mod n)th。
 * 假设你可以执行任意次操作，请返回收集所有类型巧克力所需的最小成本。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2023/12/28 11:32
 */
public class _2735 {

    public long minCost(int[] nums, int x) {
         /*
         看到题目 第一感觉就是贪心 但是怎么贪心呢
         是把每个巧克力都移动到最低位置 然后判断成本吗 显然不是
         还是说 最多移动 nums.length 次，然后每次取最小的，或者是全取完，再判断最小成本？
         可是这样也可以每次取一部分。

         最后发现，实际上要关注的是取每个巧克力的最小成本而已。因为巧克力取走了，但是坑位还在，
         意味着巧克力在哪次被取走和其他的巧克力是没有关系的。所以只计算每个巧克力的最小成本即可。
         难怪题目要这样设计，不这样设计的话就不是中等题了。

         补充 上面的说法有问题 每个巧克力的最小成本和其他巧克力是相关的 因为移动成本是共同承担的
         所以只能从 移动次数去迭代
          */

        long cost = Long.MAX_VALUE;
        int[] curArr = Arrays.copyOf(nums, nums.length);
        long curCost;
        for (int move = 0; move < nums.length; move++) {
            curCost = (long) move * x;
            // 移动成本已经超过了 不需要再继续计算了
            if (curCost >= cost) {
                break;
            }
            // 更新最优选择 curArr[i] 表示 i 在 [0,move]次移动内的最优选择
            for (int i = 0; i < curArr.length; i++) {
                if (curArr[i] > nums[(i + move) % nums.length]) {
                    curArr[i] = nums[(i + move) % nums.length];
                }
                curCost += curArr[i];
            }
            if (curCost < cost) {
                cost = curCost;
            }
        }
        return cost;
    }


}
