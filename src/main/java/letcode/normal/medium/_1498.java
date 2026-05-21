package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.Arrays;

/**
 * Return the number of non-empty subsequences of nums such that the sum of the minimum and maximum element on
 * it is less or equal to target. Since the answer may be too large, return it modulo 109 + 7.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-06-29 23:12
 */
public class _1498 {

    static int[] modArr = new int[100001];
    static int mod = 1000000007;

    static {
        modArr[0] = 1;
        for (int i = 1; i < modArr.length; i++) {
            modArr[i] = (modArr[i - 1] << 1) % mod;
        }
    }

    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);

        long ans = 0;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            if (nums[left] + nums[right] <= target) {
                ans = (ans + modArr[right - left]) % mod;
                ++left;
            } else {
                --right;
            }
        }
        return (int) ans;
    }



}
