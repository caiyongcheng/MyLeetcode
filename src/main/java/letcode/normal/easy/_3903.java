package letcode.normal.easy;

/**
 * 3903. Smallest Stable Index I
 * Difficulty: Easy
 * Link: https://leetcode.cn/problems/smallest-stable-index-i/
 * <p>
 * You are given an integer array nums of length n and an integer k .
 * <p>
 * For each index i , define its instability score as max(nums[0..i]) - min(nums[i..n - 1]) .
 * <p>
 * In other words:
 * <p>
 * - max(nums[0..i]) is the largest value among the elements from index 0 to index i .
 * <p>
 * - min(nums[i..n - 1]) is the smallest value among the elements from index i to index n - 1 .
 * <p>
 * An index i is called stable if its instability score is less than or equal to k .
 * <p>
 * Return the smallest stable index. If no such index exists, return -1.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,0,1,4], k = 3
 * <p>
 * Output: 3
 * <p>
 * Explanation:
 * <p>
 * - At index 0: The maximum in [5] is 5, and the minimum in [5, 0, 1, 4] is 0, so the instability
 * score is 5 - 0 = 5 .
 * <p>
 * - At index 1: The maximum in [5, 0] is 5, and the minimum in [0, 1, 4] is 0, so the instability
 * score is 5 - 0 = 5 .
 * <p>
 * - At index 2: The maximum in [5, 0, 1] is 5, and the minimum in [1, 4] is 1, so the instability
 * score is 5 - 1 = 4 .
 * <p>
 * - At index 3: The maximum in [5, 0, 1, 4] is 5, and the minimum in [4] is 4, so the instability
 * score is 5 - 4 = 1 .
 * <p>
 * - This is the first index with an instability score less than or equal to k = 3 . Thus, the answer
 * is 3.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [3,2,1], k = 1
 * <p>
 * Output: -1
 * <p>
 * Explanation:
 * <p>
 * - At index 0, the instability score is 3 - 1 = 2 .
 * <p>
 * - At index 1, the instability score is 3 - 1 = 2 .
 * <p>
 * - At index 2, the instability score is 3 - 1 = 2 .
 * <p>
 * - None of these values is less than or equal to k = 1 , so the answer is -1.
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [0], k = 0
 * <p>
 * Output: 0
 * <p>
 * Explanation:
 * <p>
 * At index 0, the instability score is 0 - 0 = 0 , which is less than or equal to k = 0 . Therefore,
 * the answer is 0.
 * <p>
 * Constraints:
 * <p>
 * - 1 <= nums.length <= 100
 * <p>
 * - 0 <= nums[i] <= 10 9
 * <p>
 * - 0 <= k <= 10 9
 */
public class _3903 {

    public int firstStableIndex(int[] nums, int k) {

        int[] suffixMinArr = new int[nums.length];
        suffixMinArr[nums.length - 1] = nums[nums.length - 1];

        for (int i = nums.length - 2; i >= 0; i--) {
            suffixMinArr[i] = Math.min(suffixMinArr[i + 1], nums[i]);
        }

        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            if (max - suffixMinArr[i] <= k) {
                return i;
            }
        }
        return -1;

    }
}
