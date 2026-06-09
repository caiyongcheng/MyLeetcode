package letcode.normal.medium;

/**
 * 3689. Maximum Total Subarray Value I
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/maximum-total-subarray-value-i/
 * You are given an integer array nums of length n and an integer k .
 * You need to choose exactly k non-empty subarrays nums[l..r] of nums .
 * Subarrays may overlap, and the exact same subarray (same l and r ) can be chosen more than once.
 * The value of a subarray nums[l..r] is defined as: max(nums[l..r]) - min(nums[l..r]) .
 * The total value is the sum of the values of all chosen subarrays. Return the max...
 */
public class _3689 {

    public long maxTotalValue(int[] nums, int k) {
        int maxNum = nums[0];
        int minNum = nums[0];
        for (int num : nums) {
            if (num > maxNum) {
                maxNum = num;
            } else if (num < minNum) {
                minNum = num;
            }
        }
        return (long) (maxNum - minNum) * k;
    }
}
