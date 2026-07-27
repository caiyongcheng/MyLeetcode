package letcode.normal.easy;

import java.util.Arrays;

/**
 * 1464. Maximum Product of Two Elements in an Array
 * Difficulty: Easy
 * Link: https://leetcode.cn/problems/maximum-product-of-two-elements-in-an-array/
 * <p>
 * Given the array of integers nums , you will choose two different indices i and j of that array.
 * Return the maximum value of (nums[i]-1)*(nums[j]-1) .
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,4,5,2]
 * Output: 12
 * Explanation: If you choose the indices i=1 and j=2 (indexed from 0), you will get the maximum value,
 * that is, (nums[1]-1)*(nums[2]-1) = (4-1)*(5-1) = 3*4 = 12.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,5,4,5]
 * Output: 16
 * Explanation: Choosing the indices i=1 and j=3 (indexed from 0), you will get the maximum value of
 * (5-1)*(5-1) = 16.
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [3,7]
 * Output: 12
 * <p>
 * Constraints:
 * <p>
 * - 2 <= nums.length <= 500
 * <p>
 * - 1 <= nums[i] <= 10^3
 */
public class _1464 {

    public int maxProduct(int[] nums) {
        Arrays.sort(nums);
        return Math.max((
                (nums[0] - 1) * (nums[1] - 1)),
                ((nums[nums.length - 1] - 1) * (nums[nums.length - 2] - 1))
        );
    }
}
