package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * The bitwise AND of an array nums is the bitwise AND of all integers in nums.
 * For example, for nums = [1, 5, 3], the bitwise AND is equal to 1 & 5 & 3 = 1.
 * Also, for nums = [7], the bitwise AND is 7. You are given an array of positive integers candidates.
 * Compute the bitwise AND for all possible combinations of elements in the candidates array.
 * Return the size of the largest combination of candidates with a bitwise AND greater than 0.
 *
 * 1 <= candidates.length <= 10^5
 * 1 <= candidates[i] <= 10^7
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-01-15 11:20
 */
public class _2275 {

    public int largestCombination(int[] candidates) {
        /*
        最后结果不为0，那么最后结果至少有一位为1，那么该组合中的所有元素该位置必须为1，求出元素中每一位1上的数量即可
        Integer.toBinaryString(10_000_000).length() == 24
         */

        int max = 0;
        for (int candidate : candidates) {
            if (candidate > max) {
                max = candidate;
            }
        }

        int length = Integer.toBinaryString(max).length();
        int ans = 0;
        int curCnt;
        for (int i = 0; i < length; i++) {
            curCnt = 0;
            for (int candidate : candidates) {
                curCnt += (candidate >> i) & 1;
            }
            if (curCnt > ans) {
                ans = curCnt;
            }
        }
        return ans;
    }


}
