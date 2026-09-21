package letcode.normal.medium;

/**
 * 3524. Find X Value of Array I
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/find-x-value-of-array-i/
 * <p>
 * You are given an array of positive integers nums , and a positive integer k .
 * <p>
 * You are allowed to perform an operation once on nums , where in each operation you can remove any
 * non-overlapping prefix and suffix from nums such that nums remains non-empty .
 * <p>
 * You need to find the x-value of nums , which is the number of ways to perform this operation so that
 * the product of the remaining elements leaves a remainder of x when divided by k .
 * <p>
 * Return an array result of size k where result[x] is the x-value of nums for 0 <= x <= k - 1 .
 * <p>
 * A prefix of an array is a subarray that starts from the beginning of the array and extends to any
 * point within it.
 * <p>
 * A suffix of an array is a subarray that starts at any point within the array and extends to the end
 * of the array.
 * <p>
 * Note that the prefix and suffix to be chosen for the operation can be empty .
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,4,5], k = 3
 * <p>
 * Output: [9,2,4]
 * <p>
 * Explanation:
 * <p>
 * - For x = 0 , the possible operations include all possible ways to remove non-overlapping
 * prefix/suffix that do not remove nums[2] == 3 .
 * <p>
 * - For x = 1 , the possible operations are:
 * <p>
 * - Remove the empty prefix and the suffix [2, 3, 4, 5] . nums becomes [1] .
 * <p>
 * - Remove the prefix [1, 2, 3] and the suffix [5] . nums becomes [4] .
 * <p>
 * - For x = 2 , the possible operations are:
 * <p>
 * - Remove the empty prefix and the suffix [3, 4, 5] . nums becomes [1, 2] .
 * <p>
 * - Remove the prefix [1] and the suffix [3, 4, 5] . nums becomes [2] .
 * <p>
 * - Remove the prefix [1, 2, 3] and the empty suffix. nums becomes [4, 5] .
 * <p>
 * - Remove the prefix [1, 2, 3, 4] and the empty suffix. nums becomes [5] .
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,2,4,8,16,32], k = 4
 * <p>
 * Output: [18,1,2,0]
 * <p>
 * Explanation:
 * <p>
 * - For x = 0 , the only operations that do not result in x = 0 are:
 * <p>
 * - Remove the empty prefix and the suffix [4, 8, 16, 32] . nums becomes [1, 2] .
 * <p>
 * - Remove the empty prefix and the suffix [2, 4, 8, 16, 32] . nums becomes [1] .
 * <p>
 * - Remove the prefix [1] and the suffix [4, 8, 16, 32] . nums becomes [2] .
 * <p>
 * - For x = 1 , the only possible operation is:
 * <p>
 * - Remove the empty prefix and the suffix [2, 4, 8, 16, 32] . nums becomes [1] .
 * <p>
 * - For x = 2 , the possible operations are:
 * <p>
 * - Remove the empty prefix and the suffix [4, 8, 16, 32] . nums becomes [1, 2] .
 * <p>
 * - Remove the prefix [1] and the suffix [4, 8, 16, 32] . nums becomes [2] .
 * <p>
 * - For x = 3 , there is no possible way to perform the operation.
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [1,1,2,1,1], k = 2
 * <p>
 * Output: [9,6]
 * <p>
 * Constraints:
 * <p>
 * - 1 <= nums[i] <= 10 9
 * <p>
 * - 1 <= nums.length <= 10 5
 * <p>
 * - 1 <= k <= 5
 */
public class _3524 {

    public long[] resultArray(int[] nums, int k) {

        long[] cur = new long[k];
        long[] ans = new long[k];
        for (int num : nums) {
            long[] next = new long[k];
            for (int i = 0; i < next.length; i++) {
                next[(i * (num % k)) % k] += cur[i];
            }
            next[num % k]++;

            for (int i = 0; i < next.length; i++) {
                ans[i] += next[i];
            }

            cur = next;
        }

        return ans;

    }


}
