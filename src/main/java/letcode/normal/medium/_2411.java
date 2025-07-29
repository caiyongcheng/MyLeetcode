package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.Arrays;

/**
 * You are given a 0-indexed array nums of length n,
 * consisting of non-negative integers. For each index i from 0 to n - 1,
 * you must determine the size of the minimum sized non-empty subarray of nums starting at i (inclusive)
 * that has the maximum possible bitwise OR.  In other words, let Bij be the bitwise OR of the subarray nums[i...j].
 * You need to find the smallest subarray starting at i,
 * such that bitwise OR of this subarray is equal to max(Bik) where i <= k <= n - 1.
 * The bitwise OR of an array is the bitwise OR of all the numbers in it.
 * Return an integer array answer of size n
 * where answer[i] is the length of the minimum sized subarray starting at i with maximum bitwise OR.
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-07-29 14:11
 */
public class _2411 {

    public int[] smallestSubarrays(int[] nums) {
        int[] idxArr = new int[31];
        Arrays.fill(idxArr, -nums.length + 1);
        for (int i = 0; i < idxArr.length; i++) {
            for (int idx = 0; idx < nums.length; idx++) {
                if ((nums[idx] & (1 << i)) != 0) {
                    idxArr[i] = idx;
                    break;
                }
            }
        }

        int[] ans = new int[nums.length];
        int maxNumIdx;
        for (int i = 0; i < nums.length; i++) {
            maxNumIdx = i;
            for (int idx : idxArr) {
                maxNumIdx = Math.max(maxNumIdx, idx);
            }
            ans[i] = maxNumIdx - i + 1;
            for (int j = 0; j < idxArr.length; j++) {
                if (idxArr[j] == i) {
                    for (int nextIdx = i + 1; nextIdx < nums.length; nextIdx++) {
                        if ((nums[nextIdx] & (1 << j)) != 0) {
                            idxArr[j] = nextIdx;
                            break;
                        }
                    }
                }
            }
        }
        return ans;
    }


    /**
     * Example 1:
     *
     * Input: nums = [1,0,2,1,3]
     * Output: [3,3,2,2,1]
     * Explanation:
     * The maximum possible bitwise OR starting at any index is 3.
     * - Starting at index 0, the shortest subarray that yields it is [1,0,2].
     * - Starting at index 1, the shortest subarray that yields the maximum bitwise OR is [0,2,1].
     * - Starting at index 2, the shortest subarray that yields the maximum bitwise OR is [2,1].
     * - Starting at index 3, the shortest subarray that yields the maximum bitwise OR is [1,3].
     * - Starting at index 4, the shortest subarray that yields the maximum bitwise OR is [3].
     * Therefore, we return [3,3,2,2,1].
     * Example 2:
     *
     * Input: nums = [1,2]
     * Output: [2,1]
     * Explanation:
     * Starting at index 0, the shortest subarray that yields the maximum bitwise OR is of length 2.
     * Starting at index 1, the shortest subarray that yields the maximum bitwise OR is of length 1.
     * Therefore, we return [2,1].
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
