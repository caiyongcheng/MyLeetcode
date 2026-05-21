package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 * Given a 0-indexed integer array nums of size n, find the maximum difference between nums[i] and nums[j]
 * (i.e., nums[j] - nums[i]), such that 0 <= i < j < n and nums[i] < nums[j].  Return the maximum difference.
 * If no such i and j exists, return -1.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-06-16 10:02
 */
public class _2016 {

    public int maximumDifference(int[] nums) {
        int ans = -1;
        int max = nums[nums.length  - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            if (max > nums[i]) {
                ans = Integer.max(ans, max - nums[i]);
            } else {
                max = nums[i];
            }
        }
        return ans;
    }


}
