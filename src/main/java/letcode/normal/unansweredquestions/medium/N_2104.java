package letcode.normal.unansweredquestions.medium;

/**
 * 给你一个整数数组 nums 。nums 中，子数组的 范围 是子数组中最大元素和最小元素的差值。  返回 nums 中 所有 子数组范围的 和 。
 * 子数组是数组中一个连续 非空 的元素序列。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/sum-of-subarray-ranges 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-03-17 09:59
 **/
public class N_2104 {

    public long subArrayRanges(int[] nums) {
        /*
        假设求出来 nums[0]的子数组范围的和， 要求nums[1]的。 那么就需要知道nums[0]不在后的影响。
        也就是如果nums[0]是最大值或者最小值的情况。如果这时候使用单调栈，即可解决问题。
        单调自增栈为例： 我们就可以知道 哪些点 是局部的大点
         */
        return 0;
    }

}
