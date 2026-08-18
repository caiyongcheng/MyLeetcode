package letcode.normal.easy;

/**
 * 3471. Find the Largest Almost Missing Integer
 * Difficulty: Easy
 * Link: https://leetcode.cn/problems/find-the-largest-almost-missing-integer/
 * <p>
 * You are given an integer array nums and an integer k .
 * <p>
 * An integer x is almost missing from nums if x appears in exactly one subarray of size k within nums
 * .
 * <p>
 * Return the largest almost missing integer from nums . If no such integer exists, return -1 .
 * <p>
 * A subarray is a contiguous sequence of elements within an array.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,9,2,1,7], k = 3
 * <p>
 * Output: 7
 * <p>
 * Explanation:
 * <p>
 * - 1 appears in 2 subarrays of size 3: [9, 2, 1] and [2, 1, 7] .
 * <p>
 * - 2 appears in 3 subarrays of size 3: [3, 9, 2] , [9, 2, 1] , [2, 1, 7] .
 * <p>
 * - 3 appears in 1 subarray of size 3: [3, 9, 2] .
 * <p>
 * - 7 appears in 1 subarray of size 3: [2, 1, 7] .
 * <p>
 * - 9 appears in 2 subarrays of size 3: [3, 9, 2] , and [9, 2, 1] .
 * <p>
 * We return 7 since it is the largest integer that appears in exactly one subarray of size k .
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [3,9,7,2,1,7], k = 4
 * <p>
 * Output: 3
 * <p>
 * Explanation:
 * <p>
 * - 1 appears in 2 subarrays of size 4: [9, 7, 2, 1] , [7, 2, 1, 7] .
 * <p>
 * - 2 appears in 3 subarrays of size 4: [3, 9, 7, 2] , [9, 7, 2, 1] , [7, 2, 1, 7] .
 * <p>
 * - 3 appears in 1 subarray of size 4: [3, 9, 7, 2] .
 * <p>
 * - 7 appears in 3 subarrays of size 4: [3, 9, 7, 2] , [9, 7, 2, 1] , [7, 2, 1, 7] .
 * <p>
 * - 9 appears in 2 subarrays of size 4: [3, 9, 7, 2] , [9, 7, 2, 1] .
 * <p>
 * We return 3 since it is the largest and only integer that appears in exactly one subarray of size k
 * .
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [0,0], k = 1
 * <p>
 * Output: -1
 * <p>
 * Explanation:
 * <p>
 * There is no integer that appears in only one subarray of size 1.
 * <p>
 * Constraints:
 * <p>
 * - 1 <= nums.length <= 50
 * <p>
 * - 0 <= nums[i] <= 50
 * <p>
 * - 1 <= k <= nums.length
 */
public class _3471 {

    public int largestInteger(int[] nums, int k) {
        if (nums.length < k) {
            return -1;
        }

        int[] map = new int[51];
        for (int num : nums) {
            map[num]++;
        }

        int ans = -1;
        if (nums.length == k) {
            for (int num : nums) {
                if (map[num] > 0) {
                    ans = Math.max(ans, num);
                }
            }
            return ans;
        }

        if (k == 1) {
            for (int num : nums) {
                if (map[num] == 1) {
                    ans = Math.max(ans, num);
                }
            }
            return ans;
        }

        if (map[nums[0]] == 1 && map[nums[nums.length - 1]] == 1) {
            return Math.max(nums[0], nums[nums.length - 1]);
        }
        if (map[nums[0]] != 1 && map[nums[nums.length - 1]] != 1) {
            return -1;
        }
        if (map[nums[0]] == 1 && map[nums[nums.length - 1]] != 1) {
            return nums[0];
        }
        if (map[nums[0]] != 1 && map[nums[nums.length - 1]] == 1) {
            return nums[nums.length - 1];
        }
        return -1;

    }


}
