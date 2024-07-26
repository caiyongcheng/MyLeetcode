package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.Arrays;

/**
 * 给你一个 正 整数数组 nums 。  将 nums 分成两个数组：nums1 和 nums2 ，
 * 并满足下述条件：  数组 nums 中的每个元素都属于数组 nums1 或数组 nums2 。 两个数组都 非空 。 分区值 最小 。
 * 分区值的计算方法是 |max(nums1) - min(nums2)| 。
 * 其中，max(nums1) 表示数组 nums1 中的最大元素，min(nums2) 表示数组 nums2 中的最小元素。  返回表示分区值的整数。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-07-26 10:38
 */
public class _2740 {

    public int findValueOfPartition(int[] nums) {
        /*
        数组进行排序 任意元素的差值的最小值 = 排序后相邻元素差值的最小值
        排序后的数组又满足分区值的计算方法
         */
        Arrays.sort(nums);
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            ans = Math.min(ans, nums[i] - nums[i - 1]);
        }
        return ans;
    }

    /**
     * Example 1:
     *
     * Input: nums = [1,3,2,4]
     * Output: 1
     * Explanation: We can partition the array nums into nums1 = [1,2] and nums2 = [3,4].
     * - The maximum element of the array nums1 is equal to 2.
     * - The minimum element of the array nums2 is equal to 3.
     * The value of the partition is |2 - 3| = 1.
     * It can be proven that 1 is the minimum value out of all partitions.
     * Example 2:
     *
     * Input: nums = [100,1,10]
     * Output: 9
     * Explanation: We can partition the array nums into nums1 = [10] and nums2 = [100,1].
     * - The maximum element of the array nums1 is equal to 10.
     * - The minimum element of the array nums2 is equal to 1.
     * The value of the partition is |10 - 1| = 9.
     * It can be proven that 9 is the minimum value out of all partitions.
     * <p>
     * 示例 1：
     *
     * 输入：nums = [1,3,2,4]
     * 输出：1
     * 解释：可以将数组 nums 分成 nums1 = [1,2] 和 nums2 = [3,4] 。
     * - 数组 nums1 的最大值等于 2 。
     * - 数组 nums2 的最小值等于 3 。
     * 分区值等于 |2 - 3| = 1 。
     * 可以证明 1 是所有分区方案的最小值。
     * 示例 2：
     *
     * 输入：nums = [100,1,10]
     * 输出：9
     * 解释：可以将数组 nums 分成 nums1 = [10] 和 nums2 = [100,1] 。
     * - 数组 nums1 的最大值等于 10 。
     * - 数组 nums2 的最小值等于 1 。
     * 分区值等于 |10 - 1| = 9 。
     * 可以证明 9 是所有分区方案的最小值。
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(_2740.class,"示例 1： 输入：nums = [1,3,2,4] 输出：1 解释：可以将数组 nums 分成 nums1 = [1,2] " +
                "和 nums2 = [3,4] 。 - 数组 nums1 的最大值等于 2 。 - 数组 nums2 的最小值等于 3 。 分区值等于 |2 - 3| = 1 。" +
                " 可以证明 1 是所有分区方案的最小值。 示例 2： 输入：nums = [100,1,10] 输出：9 解释：可以将数组 nums 分成 nums1 " +
                "= [10] 和 nums2 = [100,1] 。 - 数组 nums1 的最大值等于 10 。 - 数组 nums2 的最小值等于 1 。 " +
                "分区值等于 |10 - 1| = 9 。 可以证明 9 是所有分区方案的最小值。");
    }


}
