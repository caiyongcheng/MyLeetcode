package letcode.normal.medium;

import java.util.Arrays;

/**
 * 3080. Mark Elements on Array by Performing Queries
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/mark-elements-on-array-by-performing-queries/
 * <p>
 * You are given a 0-indexed array nums of size n consisting of positive integers.
 * <p>
 * You are also given a 2D array queries of size m where queries[i] = [index i , k i ] .
 * <p>
 * Initially all elements of the array are unmarked .
 * <p>
 * You need to apply m queries on the array in order, where on the i th query you do the following:
 * <p>
 * - Mark the element at index index i if it is not already marked.
 * <p>
 * - Then mark k i unmarked elements in the array with the smallest values. If multiple such elements
 * exist, mark the ones with the smallest indices. And if less than k i unmarked elements exist, then
 * mark all of them.
 * <p>
 * Return an array answer of size m where answer[i] is the sum of unmarked elements in the array after
 * the i th query .
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,2,1,2,3,1], queries = [[1,2],[3,3],[4,2]]
 * <p>
 * Output: [8,3,0]
 * <p>
 * Explanation:
 * <p>
 * We do the following queries on the array:
 * <p>
 * - Mark the element at index 1 , and 2 of the smallest unmarked elements with the smallest indices if
 * they exist, the marked elements now are nums = [ 1 , 2 ,2, 1 ,2,3,1] . The sum of unmarked elements
 * is 2 + 2 + 3 + 1 = 8 .
 * <p>
 * - Mark the element at index 3 , since it is already marked we skip it. Then we mark 3 of the
 * smallest unmarked elements with the smallest indices, the marked elements now are nums = [ 1 , 2 , 2
 * , 1 , 2 ,3, 1 ] . The sum of unmarked elements is 3 .
 * <p>
 * - Mark the element at index 4 , since it is already marked we skip it. Then we mark 2 of the
 * smallest unmarked elements with the smallest indices if they exist, the marked elements now are nums
 * = [ 1 , 2 , 2 , 1 , 2 , 3 , 1 ] . The sum of unmarked elements is 0 .
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,4,2,3], queries = [[0,1]]
 * <p>
 * Output: [7]
 * <p>
 * Explanation: We do one query which is mark the element at index 0 and mark the smallest element
 * among unmarked elements. The marked elements will be nums = [ 1 ,4, 2 ,3] , and the sum of unmarked
 * elements is 4 + 3 = 7 .
 * <p>
 * Constraints:
 * <p>
 * - n == nums.length
 * <p>
 * - m == queries.length
 * <p>
 * - 1 <= m <= n <= 10 5
 * <p>
 * - 1 <= nums[i] <= 10 5
 * <p>
 * - queries[i].length == 2
 * <p>
 * - 0 <= index i , k i <= n - 1
 */
public class _3080 {

    public long[] unmarkedSumArray(int[] nums, int[][] queries) {
        long unmaskElSum = 0;
        for (int num : nums) {
            unmaskElSum += num;
        }

        Integer[] sortArr = new Integer[nums.length];
        for (int i = 0; i < sortArr.length; i++) {
            sortArr[i] = i;
        }
        Arrays.sort(sortArr, (a, b) -> nums[a] == nums[b] ? a - b : nums[a] - nums[b]);

        boolean[] marked = new boolean[nums.length];

        long[] ans = new long[queries.length];
        int unmaskIdx = 0;
        for (int i = 0; i < queries.length; i++) {
            if (!marked[queries[i][0]]) {
                marked[queries[i][0]] = true;
                unmaskElSum -= nums[queries[i][0]];
            }
            while (queries[i][1] > 0 && unmaskIdx < sortArr.length) {
                if (!marked[sortArr[unmaskIdx]]) {
                    marked[sortArr[unmaskIdx]] = true;
                    unmaskElSum -= nums[sortArr[unmaskIdx]];
                    --queries[i][1];
                }
                ++unmaskIdx;
            }
            ans[i] = unmaskElSum;
        }

        return ans;
    }

}
