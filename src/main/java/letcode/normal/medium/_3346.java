package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * You are given an integer array nums and two integers k and numOperations.
 * You must perform an operation numOperations times on nums, where in each operation you:
 * Select an index i that was not selected in any previous operations. Add an integer in the range [-k, k] to nums[i].
 * Return the maximum possible frequency of any element in nums after performing the operations.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-10-22 00:08
 */
public class _3346 {

    public int maxFrequency(int[] nums, int k, int numOperations) {
        int length = 100001;
        int[] sortArr = new int[length];
        for (int num : nums) {
            sortArr[num]++;
        }

        int[] preSum = new int[length];
        preSum[0] = sortArr[0];
        for (int i = 1; i < length; i++) {
            preSum[i] = preSum[i - 1] + sortArr[i];
        }

        // 这里考虑到数据规模就这样去做了 实际上使用二分对 [min(nums),max(nums)]范围的数进行判断，对每个数i判断[i-k, i+k]
        int ans = 0;
        int frequency;
        for (int num = 0; num < length; num++) {
            frequency = Math.min(
                    preSum[Math.min(length - 1, num + k)] - preSum[Math.max(0, num - k - 1)] - sortArr[num],
                    numOperations
            );
            ans = Math.max(ans, frequency + sortArr[num]);
        }

        return ans;
    }


    /**
     * Example 1:
     *
     * Input: nums = [1,4,5], k = 1, numOperations = 2
     *
     * Output: 2
     *
     * Explanation:
     *
     * We can achieve a maximum frequency of two by:
     *
     * Adding 0 to nums[1]. nums becomes [1, 4, 5].
     * Adding -1 to nums[2]. nums becomes [1, 4, 4].
     * Example 2:
     *
     * Input: nums = [5,11,20,20], k = 5, numOperations = 1
     *
     * Output: 2
     *
     * Explanation:
     *
     * We can achieve a maximum frequency of two by:
     *
     * Adding 0 to nums[1].
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
