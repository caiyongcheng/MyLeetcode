package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * You are given an integer array nums of size n.
 * Consider a non-empty subarray from nums that has the maximum possible bitwise AND.
 * In other words, let k be the maximum value of the bitwise AND of any subarray of nums.
 * Then, only subarrays with a bitwise AND equal to k should be considered.
 * Return the length of the longest such subarray.
 * The bitwise AND of an array is the bitwise AND of all the numbers in it.
 * A subarray is a contiguous sequence of elements within an array.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-07-30 09:07
 */
public class _2419 {

    public int longestSubarray(int[] nums) {
        int ans = 1;
        int maxNum = nums[0];
        int curLen = 0;
        for (int num : nums) {
            if (num == maxNum) {
                ++curLen;
            } else if (num > maxNum) {
                maxNum = num;
                curLen = 1;
                ans = 1;
            } else {
                ans = Math.max(ans, curLen);
                curLen = 0;
            }
        }
        return Math.max(ans, curLen);
    }

}
