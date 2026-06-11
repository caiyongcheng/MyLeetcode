package letcode.normal.difficult;

import letcode.utils.TestUtil;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 3691. Maximum Total Subarray Value II
 * Difficulty: Hard
 * Link: https://leetcode.cn/problems/maximum-total-subarray-value-ii/
 * You are given an integer array nums of length n and an integer k .
 * You must select exactly k distinct non-empty subarrays nums[l..r] of nums .
 * Subarrays may overlap, but the exact same subarray (same l and r ) cannot be chosen more than once.
 * The value of a subarray nums[l..r] is defined as: max(nums[l..r]) - min(nums[l..r]) .
 * The total value is the sum of the values of all chosen subarrays. Retur...
 */
public class _3691 {

    public long maxTotalValue(int[] nums, int k) {
        Integer[] idxArr = new Integer[nums.length];
        for (int i = 0; i < idxArr.length; i++) {
            idxArr[i] = i;
        }
        Arrays.sort(idxArr, Comparator.comparingInt(( a) -> nums[a]));

        int numIdxLeftLimit = -1;
        int numIdxRightLimit = idxArr.length;
        int idxArrLeftIdx = 0;
        int idxArrRightIdx = idxArr.length - 1;
        long rst = 0L;

        int numLeftIdx;
        int numRightIdx;
        while ((numLeftIdx = idxArr[idxArrLeftIdx]) != (numRightIdx = idxArr[idxArrRightIdx])) {
            if (numLeftIdx > numRightIdx) {
                int temp = numLeftIdx;
                numLeftIdx = numRightIdx;
                numRightIdx = temp;
            }

            int subArrCnt = (Math.max(numLeftIdx - numIdxLeftLimit, 1)) * (Math.max(numIdxRightLimit - numRightIdx, 1));
            if (subArrCnt >= k) {
                subArrCnt = k;
            }
            rst = rst + subArrCnt * Math.abs(nums[numLeftIdx] - nums[numRightIdx]);
            k -= subArrCnt;
            if (k <= 0) {
                break;
            }
            numIdxLeftLimit = numLeftIdx;
            numIdxRightLimit = numRightIdx;

            int nextLeftIdx = idxArrLeftIdx + 1;
            while (nextLeftIdx < idxArrRightIdx) {
                if (idxArr[nextLeftIdx] >= numLeftIdx && idxArr[nextLeftIdx] <= numRightIdx) {
                    break;
                }
                ++nextLeftIdx;
            }

            int nextRightIdx = idxArrRightIdx - 1;
            while (nextRightIdx > idxArrLeftIdx) {
                if (idxArr[nextRightIdx] >= numLeftIdx && idxArr[nextRightIdx] <= numRightIdx) {
                    break;
                }
                --nextRightIdx;
            }

            if (Math.abs(nums[idxArr[nextLeftIdx]] - nums[numRightIdx]) > Math.abs(nums[numLeftIdx] - nums[idxArr[nextRightIdx]])) {
                idxArrLeftIdx = nextLeftIdx;
            } else {
                idxArrRightIdx = nextRightIdx;
            }

        }

        return rst;

    }


    public static void main(String[] args) {
        TestUtil.test("=[18,36,6], =3");
    }

}
