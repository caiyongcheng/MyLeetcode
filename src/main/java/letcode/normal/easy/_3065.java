package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 * You are given a 0-indexed integer array nums, and an integer k.
 * In one operation, you can remove one occurrence of the smallest element of nums.
 * Return the minimum number of operations needed so that all elements of the array are greater than or equal to k.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-01-14 09:10
 */
public class _3065 {

    public int minOperations(int[] nums, int k) {
        int ans = 0;
        for (int num : nums) {
            if (num < k) {
                ans++;
            }
        }
        return ans;
    }

}
