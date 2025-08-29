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

    /**
     * Example 1:
     *
     * Input: nums = [1,3,0,0,2,0,0,4]
     * Output: 6
     * Explanation:
     * There are 4 occurrences of [0] as a subarray.
     * There are 2 occurrences of [0,0] as a subarray.
     * There is no occurrence of a subarray with a size more than 2 filled with 0. Therefore, we return 6.
     * Example 2:
     *
     * Input: nums = [0,0,0,2,0,0]
     * Output: 9
     * Explanation:
     * There are 5 occurrences of [0] as a subarray.
     * There are 3 occurrences of [0,0] as a subarray.
     * There is 1 occurrence of [0,0,0] as a subarray.
     * There is no occurrence of a subarray with a size more than 3 filled with 0. Therefore, we return 9.
     * Example 3:
     *
     * Input: nums = [2,10,2019]
     * Output: 0
     * Explanation: There is no subarray filled with 0. Therefore, we return 0.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
