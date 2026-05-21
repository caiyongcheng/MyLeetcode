package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 * Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-09-10 14:12
 */
public class _977 {

    public int[] sortedSquares(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int[] ans = new int[nums.length];
        int idx = nums.length - 1;
        while (left <= right) {
            if (nums[left] * nums[left] < nums[right] * nums[right]) {
                ans[idx--] = nums[right] * nums[right];
                right--;
            } else {
                ans[idx--] = nums[left] * nums[left];
                left++;
            }
        }
        return ans;
    }

}
