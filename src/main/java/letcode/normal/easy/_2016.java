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


    /**
     * Example 1:
     *
     * Input: nums = [7,1,5,4]
     * Output: 4
     * Explanation:
     * The maximum difference occurs with i = 1 and j = 2, nums[j] - nums[i] = 5 - 1 = 4.
     * Note that with i = 1 and j = 0, the difference nums[j] - nums[i] = 7 - 1 = 6, but i > j, so it is not valid.
     * Example 2:
     *
     * Input: nums = [9,4,3,2]
     * Output: -1
     * Explanation:
     * There is no i and j such that i < j and nums[i] < nums[j].
     * Example 3:
     *
     * Input: nums = [1,5,2,10]
     * Output: 9
     * Explanation:
     * The maximum difference occurs with i = 0 and j = 3, nums[j] - nums[i] = 10 - 1 = 9.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
