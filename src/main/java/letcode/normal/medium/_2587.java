package letcode.normal.medium;

import java.util.Arrays;

/**
 * 2587. Rearrange Array to Maximize Prefix Score
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/rearrange-array-to-maximize-prefix-score/
 * <p>
 * You are given a 0-indexed integer array nums . You can rearrange the elements of nums to any order
 * (including the given order).
 * <p>
 * Let prefix be the array containing the prefix sums of nums after rearranging it. In other words,
 * prefix[i] is the sum of the elements from 0 to i in nums after rearranging it. The score of nums is
 * the number of positive integers in the array prefix .
 * <p>
 * Return the maximum score you can achieve .
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,-1,0,1,-3,3,-3]
 * Output: 6
 * Explanation: We can rearrange the array into nums = [2,3,1,-1,-3,0,-3].
 * prefix = [2,5,6,5,2,2,-1], so the score is 6.
 * It can be shown that 6 is the maximum score we can obtain.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [-2,-3,0]
 * Output: 0
 * Explanation: Any rearrangement of the array will result in a score of 0.
 * <p>
 * Constraints:
 * <p>
 * - 1 <= nums.length <= 10 5
 * <p>
 * - -10 6 <= nums[i] <= 10 6
 */
public class _2587 {

    public int maxScore(int[] nums) {

        Arrays.sort(nums);
        int sum = 0;
        int ans = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            sum += nums[i];
            if (sum > 0) {
                ++ans;
            }
        }

        return ans;
    }
}
