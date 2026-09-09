package letcode.normal.medium;

/**
 * 3719. Longest Balanced Subarray I
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/longest-balanced-subarray-i/
 * <p>
 * You are given an integer array nums .
 * <p>
 * A subarray is called balanced if the number of distinct even numbers in the subarray is equal to the
 * number of distinct odd numbers.
 * <p>
 * Return the length of the longest balanced subarray.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,5,4,3]
 * <p>
 * Output: 4
 * <p>
 * Explanation:
 * <p>
 * - The longest balanced subarray is [2, 5, 4, 3] .
 * <p>
 * - It has 2 distinct even numbers [2, 4] and 2 distinct odd numbers [5, 3] . Thus, the answer is 4.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [3,2,2,5,4]
 * <p>
 * Output: 5
 * <p>
 * Explanation:
 * <p>
 * - The longest balanced subarray is [3, 2, 2, 5, 4] .
 * <p>
 * - It has 2 distinct even numbers [2, 4] and 2 distinct odd numbers [3, 5] . Thus, the answer is 5.
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [1,2,3,2]
 * <p>
 * Output: 3
 * <p>
 * Explanation:
 * <p>
 * - The longest balanced subarray is [2, 3, 2] .
 * <p>
 * - It has 1 distinct even number [2] and 1 distinct odd number [3] . Thus, the answer is 3.
 * <p>
 * Constraints:
 * <p>
 * - 1 <= nums.length <= 1500
 * <p>
 * - 1 <= nums[i] <= 10 5
 */
public class _3719 {

    public int longestBalanced(int[] nums) {

        int[] count = new int[2];
        int[] numCountArr = new int[100001];

        int ans = 0;
        int idx;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                idx = nums[j] & 1;
                ++numCountArr[nums[j]];
                if (numCountArr[nums[j]] == 1) {
                    count[idx]++;
                }
                if (count[0] == count[1]) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
            for (int j = 0; j < nums.length; j++) {
                --numCountArr[nums[j]];
            }
            count[0] = 0;
            count[1] = 0;
        }
        return ans;
    }

}
