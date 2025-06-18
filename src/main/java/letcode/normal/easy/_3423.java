package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 * Given a circular array nums, find the maximum absolute difference between adjacent elements.
 * Note: In a circular array, the first and last elements are adjacent.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-06-12 10:58
 */
public class _3423 {


    public int maxAdjacentDistance(int[] nums) {
        int ans = Math.abs(nums[0] - nums[nums.length - 1]);
        for (int i = 1; i < nums.length; i++) {
            ans = Integer.max(ans, Math.abs(nums[i] - nums[i - 1]));
        }
        return ans;
    }

    /**
     * Example 1:
     *
     * Input: nums = [1,2,4]
     *
     * Output: 3
     *
     * Explanation:
     *
     * Because nums is circular, nums[0] and nums[2] are adjacent. They have the maximum absolute difference of |4 - 1| = 3.
     *
     * Example 2:
     *
     * Input: nums = [-5,-10,-5]
     *
     * Output: 5
     *
     * Explanation:
     *
     * The adjacent elements nums[0] and nums[1] have the maximum absolute difference of |-5 - (-10)| = 5.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
