package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * You are given an integer array nums of length n and a 2D array queries where queries[i] = [li, ri, vali].
 * Each queries[i] represents the following action on nums:
 * Decrement the value at each index in the range [li, ri] in nums by at most vali.
 * The amount by which each value is decremented can be chosen independently for each index.
 * A Zero Array is an array with all its elements equal to 0.
 * Return the minimum possible non-negative value of k, such that after processing the first k queries in sequence,
 * nums becomes a Zero Array. If no such k exists, return -1.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-05-21 10:31
 */
public class _3356 {

    public int minZeroArray(int[] nums, int[][] queries) {
        // 差分数组
        int[] diffArr = new int[nums.length];
        for (int i = 1; i < diffArr.length; i++) {
            diffArr[i] = nums[i] - nums[i - 1];
        }

        int currentNum = nums[0];
        int queryIdx = -1;
        for (int i = 0; i < nums.length; i++) {
            // 计算当前元素的值
            currentNum += diffArr[i];
            // 如果当前元素的值不小于0，那么多使用几个查询试试
            while (currentNum > 0 && queryIdx < queries.length - 1) {
                queryIdx++;
                diffArr[queries[queryIdx][0]] -= queries[queryIdx][2];
                if (queries[queryIdx][1] + 1 < diffArr.length) {
                    diffArr[queries[queryIdx][1] + 1] += queries[queryIdx][2];
                }
                if (i >= queries[queryIdx][0] && i <= queries[queryIdx][1]) {
                    currentNum -= queries[queryIdx][2];
                }
                if (currentNum <= 0) {
                    break;
                }
            }
            if (currentNum > 0) {
                return -1;
            }
        }
        return queryIdx + 1;
    }

    /**
     * Example 1:
     *
     * Input: nums = [2,0,2], queries = [[0,2,1],[0,2,1],[1,1,3]]
     *
     * Output: 2
     *
     * Explanation:
     *
     * For i = 0 (l = 0, r = 2, val = 1):
     * Decrement values at indices [0, 1, 2] by [1, 0, 1] respectively.
     * The array will become [1, 0, 1].
     * For i = 1 (l = 0, r = 2, val = 1):
     * Decrement values at indices [0, 1, 2] by [1, 0, 1] respectively.
     * The array will become [0, 0, 0], which is a Zero Array. Therefore, the minimum value of k is 2.
     * Example 2:
     *
     * Input: nums = [4,3,2,1], queries = [[1,3,2],[0,2,1]]
     *
     * Output: -1
     *
     * Explanation:
     *
     * For i = 0 (l = 1, r = 3, val = 2):
     * Decrement values at indices [1, 2, 3] by [2, 2, 1] respectively.
     * The array will become [4, 1, 0, 0].
     * For i = 1 (l = 0, r = 2, val = 1):
     * Decrement values at indices [0, 1, 2] by [1, 1, 0] respectively.
     * The array will become [3, 0, 0, 0], which is not a Zero Array.
     * @param args
     */
    public static void main(String[] args) {
        //TestUtil.test("=[0,8],=[[0,1,4],[0,1,1],[0,1,4],[0,1,1],[1,1,5],[0,1,2],[1,1,4],[0,1,1],[1,1,3],[0,0,2],[1,1,3],[1,1,2],[0,1,5],[1,1,2],[1,1,5]]");
        TestUtil.test();
    }

}
