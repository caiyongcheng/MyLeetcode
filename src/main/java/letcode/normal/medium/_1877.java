package letcode.normal.medium;

import java.util.Arrays;

/**
 * 一个数对(a,b)的 数对和等于a + b。最大数对和是一个数对数组中最大的数对和。  
 * 比方说，如果我们有数对(1,5)，(2,3)和(4,4)，最大数对和为max(1+5, 2+3, 4+4) = max(6, 5, 8) = 8。 
 * 给你一个长度为 偶数n的数组nums，请你将 nums中的元素分成 n / 2个数对，
 * 使得：  nums中每个元素恰好在 一个数对中，且 最大数对和的值 最小。 请你在最优数对划分的方案下，返回最小的 最大数对和。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/minimize-maximum-pair-sum-in-array 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-07-20 09:35
 **/
public class _1877 {

    public int minPairSum(int[] nums) {
        /**
         * 题目的要求 可以表述为 将数组划分为两个 子数组a和b 要求 最大的a[i]+b[i]尽可能的小
         * 按顺序排列 前n/2个属于a，剩余倒叙排列属于b。
         * 如果 a[i] + b[i]是最大的，要减小和。只能改变i，若和i-t交换
         * 则在之前 a[i-t] + b[i-t] < a[i] + b[i] 且 a[i-t] < a[i], b[i-t] > b[i]
         * 如果调换 则  a[i] + b[i] < a[i] + b[i-t] 最大值变得更大
         * 若和i+t进行交换
         * 则在之前 a[i+t] + b[i+t] < a[i] + b[i] 且 a[i+t] > a[i], b[i+t] < b[i]
         * 如果调换 a[i+t] + b[i] > a[i] + b[i] 最大值变得更大
         * 故按此排列方式，得到的最大值是最小的。任意的更改都会增加最大值。
         */
        int ans;
        int index = 0;
        int n = (nums.length >>> 1);
        Arrays.sort(nums);
        ans = nums[index] + nums[nums.length - index -1];
        ++index;
        while (index < n) {
            ans = Math.max(nums[index] + nums[nums.length - index - 1], ans);
            ++index;
        }
        return ans;
    }

}
