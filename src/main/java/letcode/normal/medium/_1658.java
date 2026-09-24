package letcode.normal.medium;

/**
 * 1658. Minimum Operations to Reduce X to Zero
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/minimum-operations-to-reduce-x-to-zero/
 * <p>
 * You are given an integer array nums and an integer x . In one operation, you can either remove the
 * leftmost or the rightmost element from the array nums and subtract its value from x . Note that this
 * modifies the array for future operations.
 * <p>
 * Return the minimum number of operations to reduce x to exactly 0 if it is possible , otherwise,
 * return -1 .
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,4,2,3], x = 5
 * Output: 2
 * Explanation: The optimal solution is to remove the last two elements to reduce x to zero.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [5,6,7,8,9], x = 4
 * Output: -1
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [3,2,20,1,1,3], x = 10
 * Output: 5
 * Explanation: The optimal solution is to remove the last three elements and the first two elements (5
 * operations in total) to reduce x to zero.
 * <p>
 * Constraints:
 * <p>
 * - 1 <= nums.length <= 10 5
 * <p>
 * - 1 <= nums[i] <= 10 4
 * <p>
 * - 1 <= x <= 10 9
 */
public class _1658 {

    public int minOperations(int[] nums, int x) {
        int ans = Integer.MAX_VALUE;


        // 计算只选左边的
        int leftSum = 0;
        int left = 0;
        for (; left < nums.length; left++) {
            leftSum += nums[left];
            if (leftSum >= x) {
                break;
            }
        }

        // 整体和都要小于x
        if (left == nums.length) {
            return -1;
        }

        int right = nums.length;
        int rightSum = 0;
        while (right > left) {
            if (leftSum + rightSum == x) {
                ans = Math.min(ans, nums.length - right + left + 1);
                if (left == -1) {
                    break;
                }
                leftSum -= nums[left--];
                rightSum += nums[--right];
            } else if (leftSum + rightSum < x) {
                if (right <= left + 1) {
                    if (left == -1) {
                        break;
                    }
                    leftSum -= nums[left--];
                }
                rightSum += nums[--right];
            } else {
                if (left == -1) {
                    break;
                }
                leftSum -= nums[left--];
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
