package letcode.normal.easy;

/**
 * 3550. Smallest Index With Digit Sum Equal to Index
 * Difficulty: Easy
 * Link: https://leetcode.cn/problems/smallest-index-with-digit-sum-equal-to-index/
 * <p>
 * You are given an integer array nums .
 * <p>
 * Return the smallest index i such that the sum of the digits of nums[i] is equal to i .
 * <p>
 * If no such index exists, return -1 .
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3,2]
 * <p>
 * Output: 2
 * <p>
 * Explanation:
 * <p>
 * - For nums[2] = 2 , the sum of digits is 2, which is equal to index i = 2 . Thus, the output is 2.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,10,11]
 * <p>
 * Output: 1
 * <p>
 * Explanation:
 * <p>
 * - For nums[1] = 10 , the sum of digits is 1 + 0 = 1 , which is equal to index i = 1 .
 * <p>
 * - For nums[2] = 11 , the sum of digits is 1 + 1 = 2 , which is equal to index i = 2 .
 * <p>
 * - Since index 1 is the smallest, the output is 1.
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [1,2,3]
 * <p>
 * Output: -1
 * <p>
 * Explanation:
 * <p>
 * - Since no index satisfies the condition, the output is -1.
 * <p>
 * Constraints:
 * <p>
 * - 1 <= nums.length <= 100
 * <p>
 * - 0 <= nums[i] <= 1000
 */
public class _3550 {

    public int smallestIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int digitSum = 0;
            int num = nums[i];
            while (num > 0) {
                digitSum += num % 10;
                num /= 10;
            }
            if (digitSum == i) {
                return i;
            }
        }
        return -1;
    }
}
