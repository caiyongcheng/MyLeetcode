package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * Given an integer array nums, return the number of subarrays filled with 0.
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-08-19 09:05
 */
public class _2348 {

    public long zeroFilledSubarray(int[] nums) {
        long ans = 0;
        long zeroCnt = 0;
        for (int num : nums) {
            if (num == 0) {
                ++zeroCnt;
            } else if (zeroCnt > 0) {
                ans += (zeroCnt * (zeroCnt + 1)) >> 1;
                zeroCnt = 0;
            }
        }
        ans += (zeroCnt * (zeroCnt + 1)) >> 1;
        return ans;
    }

}
