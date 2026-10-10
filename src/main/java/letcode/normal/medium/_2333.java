package letcode.normal.medium;

import java.util.Arrays;

/**
 * 2333. Minimum Sum of Squared Difference
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/minimum-sum-of-squared-difference/
 * <p>
 * You are given two positive 0-indexed integer arrays nums1 and nums2 , both of length n .
 * <p>
 * The sum of squared difference of arrays nums1 and nums2 is defined as the sum of (nums1[i] -
 * nums2[i]) 2 for each 0 <= i < n .
 * <p>
 * You are also given two positive integers k1 and k2 . You can modify any of the elements of nums1 by
 * +1 or -1 at most k1 times. Similarly, you can modify any of the elements of nums2 by +1 or -1 at
 * most k2 times.
 * <p>
 * Return the minimum sum of squared difference after modifying array nums1 at most k1 times and
 * modifying array nums2 at most k2 times .
 * <p>
 * Note : You are allowed to modify the array elements to become negative integers.
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,2,3,4], nums2 = [2,10,20,19], k1 = 0, k2 = 0
 * Output: 579
 * Explanation: The elements in nums1 and nums2 cannot be modified because k1 = 0 and k2 = 0.
 * The sum of square difference will be: (1 - 2) 2 + (2 - 10) 2 + (3 - 20) 2 + (4 - 19) 2 = 579.
 * <p>
 * Example 2:
 * <p>
 * Input: nums1 = [1,4,10,12], nums2 = [5,8,6,9], k1 = 1, k2 = 1
 * Output: 43
 * Explanation: One way to obtain the minimum sum of square difference is:
 * - Increase nums1[0] once.
 * - Increase nums2[2] once.
 * The minimum of the sum of square difference will be:
 * (2 - 5) 2 + (4 - 8) 2 + (10 - 7) 2 + (12 - 9) 2 = 43.
 * Note that, there are other ways to obtain the minimum of the sum of square difference, but there is
 * no way to obtain a sum smaller than 43.
 * <p>
 * Constraints:
 * <p>
 * - n == nums1.length == nums2.length
 * <p>
 * - 1 <= n <= 10 5
 * <p>
 * - 0 <= nums1[i], nums2[i] <= 10 5
 * <p>
 * - 0 <= k1, k2 <= 10 9
 */
public class _2333 {

    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        /*
        在 nums1[i] - nums2[i] 都是正整数的情况下
        假设 diff[i] = |nums1[i] - nums2[i]|
        要结果最小 那么就要 diff[i] 最小
        (diff[i] ^ 2) - [(diff[i] - x)] ^ 2 = 2diff[i]x - x^2 = x(2diff[i] - x)
        那么如果x固定，显然越大的diff[i]收益越高，diff[i]固定，x越大收益也越高
        那么每次优先调整最大的diff[i]。
        同时注意，对于调整diff[i]，可用通过调整nums1[i]或nums2[i]，所以k1、k2的总计才有意义。
         */

        long[] diff = new long[nums1.length + 1];
        for (int i = 0; i < nums1.length; i++) {
            diff[i + 1] = Math.abs(nums1[i] - nums2[i]);
        }

        Arrays.sort(diff);

        if (diff[diff.length - 1] == 0) {
            return 0;
        }

        long optCnt = k1 + k2;
        int rightIdx = diff.length - 1;
        while (rightIdx >= 0) {
            // 此时需要调整
            if (diff[rightIdx] != diff[diff.length - 1]) {
                long changeByItem = diff[diff.length - 1] - diff[rightIdx];
                int sameLen = diff.length - 1 - rightIdx;
                long exceptOptCnt = sameLen * changeByItem;
                // 可用调整次数足够
                if (exceptOptCnt <= optCnt) {
                    optCnt -= exceptOptCnt;
                    for (int i = diff.length - 1; i > rightIdx; i--) {
                        diff[i] -= changeByItem;
                    }
                } else {
                    long batchCnt = optCnt / sameLen;
                    long remain = optCnt % sameLen;
                    for (int i = diff.length - 1; i > rightIdx; i--) {
                        diff[i] -= batchCnt;
                    }
                    for (int i = 0; i < remain; i++) {
                        diff[diff.length - 1 - i]--;
                    }
                    break;
                }
            } else {
                --rightIdx;
            }
        }

        long ans = 0;
        for (long l : diff) {
            ans += l * l;
        }
        return ans;

    }
}
