package letcode.normal.medium;

import java.util.Arrays;

/**
 * 462. Minimum Moves to Equal Array Elements II
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/minimum-moves-to-equal-array-elements-ii/
 * <p>
 * Given an integer array nums of size n , return the minimum number of moves required to make all
 * array elements equal .
 * <p>
 * In one move, you can increment or decrement an element of the array by 1 .
 * <p>
 * Test cases are designed so that the answer will fit in a 32-bit integer.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * Output: 2
 * Explanation:
 * Only two moves are needed (remember each move increments or decrements one element):
 * [ 1 ,2,3] => [2,2, 3 ] => [2,2,2]
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,10,2,9]
 * Output: 16
 * <p>
 * Constraints:
 * <p>
 * - n == nums.length
 * <p>
 * - 1 <= nums.length <= 10 5
 * <p>
 * - -10 9 <= nums[i] <= 10 9
 */
public class _462 {

    public int minMoves2(int[] nums) {
        /*
        将nums从小到达排列 假设最优结果时，相等的数值为 t
        那么最终结果是
            ( nt - presum ) + ( (sum - presum) - (len - n)t )
            = nt - presum + sum - presum - (len - n)t
            = (2n - len)t + sum - 2presum
        其中 presum表示原来小于等于t的数的前缀和，n是该前缀和的元素数量
        对于每个前缀和 n 是固定的，那么要想整体最小，t就应该最小，且t应该要满足预测条件，所以t要取等于前缀和最后一个数，也就是第n大个数
         */

        Arrays.sort(nums);
        long ans = Integer.MAX_VALUE;
        long sum = 0;
        long preSum = 0;

        for (int num : nums) {
            sum += num;
        }

        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            ans = Math.min(ans, ((i + 1) * 2 - nums.length) * 1L * nums[i] + sum - preSum - preSum);
        }

        return (int) ans;

    }
}
