package letcode.normal.medium;

import java.util.Arrays;

/**
 * 435. Non-overlapping Intervals
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/non-overlapping-intervals/
 * <p>
 * Given an array of intervals intervals where intervals[i] = [start i , end i ] , return the minimum
 * number of intervals you need to remove to make the rest of the intervals non-overlapping .
 * <p>
 * Note that intervals which only touch at a point are non-overlapping . For example, [1, 2] and [2, 3]
 * are non-overlapping.
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
 * <p>
 * Example 2:
 * <p>
 * Input: intervals = [[1,2],[1,2],[1,2]]
 * Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
 * <p>
 * Example 3:
 * <p>
 * Input: intervals = [[1,2],[2,3]]
 * Output: 0
 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 * <p>
 * Constraints:
 * <p>
 * - 1 <= intervals.length <= 10 5
 * <p>
 * - intervals[i].length == 2
 * <p>
 * - -5 * 10 4 <= start i < end i <= 5 * 10 4
 */
public class _435 {

    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        int ans = 0;
        int curEnd = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < curEnd) {
                ++ans;
                curEnd = Math.min(curEnd, intervals[i][1]);
            } else {
                curEnd = intervals[i][1];
            }
        }
        return ans;
    }
}
