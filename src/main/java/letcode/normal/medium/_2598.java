package letcode.normal.medium;

/**
 * 2598. Smallest Missing Non-negative Integer After Operations
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/smallest-missing-non-negative-integer-after-operations/
 * <p>
 * You are given a 0-indexed integer array nums and an integer value .
 * <p>
 * In one operation, you can add or subtract value from any element of nums .
 * <p>
 * - For example, if nums = [1,2,3] and value = 2 , you can choose to subtract value from nums[0] to
 * make nums = [-1,2,3] .
 * <p>
 * The MEX (minimum excluded) of an array is the smallest missing non-negative integer in it.
 * <p>
 * - For example, the MEX of [-1,2,3] is 0 while the MEX of [1,0,3] is 2 .
 * <p>
 * Return the maximum MEX of nums after applying the mentioned operation any number of times .
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,-10,7,13,6,8], value = 5
 * Output: 4
 * Explanation: One can achieve this result by applying the following operations:
 * - Add value to nums[1] twice to make nums = [1, 0 ,7,13,6,8]
 * - Subtract value from nums[2] once to make nums = [1,0, 2 ,13,6,8]
 * - Subtract value from nums[3] twice to make nums = [1,0,2, 3 ,6,8]
 * The MEX of nums is 4. It can be shown that 4 is the maximum MEX we can achieve.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,-10,7,13,6,8], value = 7
 * Output: 2
 * Explanation: One can achieve this result by applying the following operation:
 * - subtract value from nums[2] once to make nums = [1,-10, 0 ,13,6,8]
 * The MEX of nums is 2. It can be shown that 2 is the maximum MEX we can achieve.
 * <p>
 * Constraints:
 * <p>
 * - 1 <= nums.length, value <= 10 5
 * <p>
 * - -10 9 <= nums[i] <= 10 9
 */
public class _2598 {

    public int findSmallestInteger(int[] nums, int value) {
        int[] countArr = new int[value];
        for (int num : nums) {
            if (num >= 0) {
                countArr[num % value]++;
            } else {
                countArr[(value + num % value) % value]++;
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < countArr.length; i++) {
            ans = Math.min(ans, countArr[i] * value + i);
        }
        return ans;

    }

}
