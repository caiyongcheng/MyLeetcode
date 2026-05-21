package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 * You are given an integer array nums of length n.  A partition is defined as an index i where 0 <= i < n - 1, splitting the array into two non-empty subarrays such that:  Left subarray contains indices [0, i]. Right subarray contains indices [i + 1, n - 1]. Return the number of partitions where the difference between the sum of the left and right subarrays is even.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-12-05 16:34
 */
public class _3432 {

    public int countPartitions(int[] nums) {
        // return (Arrays.stream(nums).sum() & 1) == 0 ? nums.length - 1 : 0;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return (sum & 1) == 0 ? nums.length - 1 : 0;
    }


}
