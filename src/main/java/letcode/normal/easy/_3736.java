package letcode.normal.easy;

/**
 * 3736. Minimum Moves to Equal Array Elements III
 * Difficulty: Easy
 * Link: https://leetcode.cn/problems/minimum-moves-to-equal-array-elements-iii/
 * <p>
 * You are given an integer array nums .
 * <p>
 * In one move, you may increase the value of any single element nums[i] by 1.
 * <p>
 * Return the minimum total number of moves required so that all elements in nums become equal .
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,1,3]
 * <p>
 * Output: 3
 * <p>
 * Explanation:
 * <p>
 * To make all elements equal:
 * <p>
 * - Increase nums[0] = 2 by 1 to make it 3.
 * <p>
 * - Increase nums[1] = 1 by 1 to make it 2.
 * <p>
 * - Increase nums[1] = 2 by 1 to make it 3.
 * <p>
 * Now, all elements of nums are equal to 3. The minimum total moves is 3 .
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [4,4,5]
 * <p>
 * Output: 2
 * <p>
 * Explanation:
 * <p>
 * To make all elements equal:
 * <p>
 * - Increase nums[0] = 4 by 1 to make it 5.
 * <p>
 * - Increase nums[1] = 4 by 1 to make it 5.
 * <p>
 * Now, all elements of nums are equal to 5. The minimum total moves is 2 .
 * <p>
 * Constraints:
 * <p>
 * - 1 <= nums.length <= 100
 * <p>
 * - 1 <= nums[i] <= 100
 */
public class _3736 {

    public int minMoves(int[] nums) {
        int sum = 0;
        int mexItem = nums[0];
        for (int num : nums) {
            mexItem = Math.max(mexItem, num);
            sum += num;
        }
        return mexItem * nums.length - sum;
    }
}
