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

}
