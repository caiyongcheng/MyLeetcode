package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.Arrays;

/**
 * You are given an integer array nums of size n where n is a multiple of 3 and a positive integer k.
 * Divide the array nums into n / 3 arrays of size 3 satisfying the following condition:
 * The difference between any two elements in one array is less than or equal to k. Return a 2D array
 * containing the arrays. If it is impossible to satisfy the conditions, return an empty array.
 * And if there are multiple answers, return any of them.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-06-18 11:35
 */
public class _2966 {

    public int[][] divideArray(int[] nums, int k) {
        int[][] ans = new int[nums.length / 3][];
        Arrays.sort(nums);
        for (int i = 2; i < nums.length; i+=3) {
            if (nums[i] - nums[i-2] > k) {
                return new int[0][];
            }
            ans[i / 3] = new int[]{nums[i], nums[i - 1], nums[i - 2]};
        }
        return ans;
    }


}
