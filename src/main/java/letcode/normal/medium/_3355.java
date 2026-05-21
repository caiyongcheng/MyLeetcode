package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * You are given an integer array nums of length n and a 2D array queries, where queries[i] = [li, ri].
 * For each queries[i]:  Select a subset of indices within the range [li, ri] in nums.
 * Decrement the values at the selected indices by 1. A Zero Array is an array where all elements are equal to 0.
 * Return true if it is possible to transform nums into a Zero Array after processing all the queries sequentially,
 * otherwise return false.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-05-21 10:45
 */
public class _3355 {

    public boolean isZeroArray(int[] nums, int[][] queries) {
        // 计算差分数组
        int[] diffArr = new int[nums.length];
        for (int i = 1; i < diffArr.length; i++) {
            diffArr[i] = nums[i] - nums[i - 1];
        }
        for (int[] query : queries) {
            int left = query[0];
            int right = query[1];
            diffArr[left]--;
            if (right + 1 < diffArr.length) {
                diffArr[right + 1]++;
            }
        }

        int curNum = nums[0];
        for (int diff : diffArr) {
            curNum += diff;
            if (curNum > 0) {
                return false;
            }
        }
        return true;
    }

}
