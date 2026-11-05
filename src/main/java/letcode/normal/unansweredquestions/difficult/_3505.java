package letcode.normal.difficult;

/**
 * 3505. Minimum Operations to Make Elements Within K Subarrays Equal
 * Difficulty: Hard
 * Link: https://leetcode.cn/problems/minimum-operations-to-make-elements-within-k-subarrays-equal/
 * <p>
 * You are given an integer array nums and two integers, x and k . You can perform the following
 * operation any number of times ( including zero ):
 * <p>
 * - Increase or decrease any element of nums by 1.
 * <p>
 * Return the minimum number of operations needed to have at least k non-overlapping subarrays of size
 * exactly x in nums , where all elements within each subarray are equal.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,-2,1,3,7,3,6,4,-1], x = 3, k = 2
 * <p>
 * Output: 8
 * <p>
 * Explanation:
 * <p>
 * - Use 3 operations to add 3 to nums[1] and use 2 operations to subtract 2 from nums[3] . The
 * resulting array is [5, 1, 1, 1, 7, 3, 6, 4, -1] .
 * <p>
 * - Use 1 operation to add 1 to nums[5] and use 2 operations to subtract 2 from nums[6] . The
 * resulting array is [5, 1, 1, 1, 7, 4, 4, 4, -1] .
 * <p>
 * - Now, all elements within each subarray [1, 1, 1] (from indices 1 to 3) and [4, 4, 4] (from indices
 * 5 to 7) are equal. Since 8 total operations were used, 8 is the output.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [9,-2,-2,-2,1,5], x = 2, k = 2
 * <p>
 * Output: 3
 * <p>
 * Explanation:
 * <p>
 * - Use 3 operations to subtract 3 from nums[4] . The resulting array is [9, -2, -2, -2, -2, 5] .
 * <p>
 * - Now, all elements within each subarray [-2, -2] (from indices 1 to 2) and [-2, -2] (from indices 3
 * to 4) are equal. Since 3 operations were used, 3 is the output.
 * <p>
 * Constraints:
 * <p>
 * - 2 <= nums.length <= 10 5
 * <p>
 * - -10 6 <= nums[i] <= 10 6
 * <p>
 * - 2 <= x <= nums.length
 * <p>
 * - 1 <= k <= 15
 * <p>
 * - 2 <= k * x <= nums.length
 */
public class _3505 {

    public long minOperations(int[] nums, int x, int k) {
    }
}
