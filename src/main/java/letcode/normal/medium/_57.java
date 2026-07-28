package letcode.normal.medium;

import letcode.utils.TestCaseOutputUtils;

/**
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。  在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 * @author CaiYongcheng
 * @since 2021-09-17 14:42
 **/
public class _57 {


    public int[][] insert(int[][] intervals, int[] newInterval) {
        /*
        可以和采取和56一样的做法
        考虑到题目给出的是无重叠的
        也就是 对于 interval 由 interval[index][1] < interval[index+1][0]
        所以对于 终点小于newInterval[0]的并无影响，先二分排除了
        剩下的部分中，找出起始点小于等于 newInterval[1] 的部分，只有这一部分才会收到影响
        合并这一部分与newInterval即可

        可以归纳成 找到 newInterval 起点在的intervals[left] 与 终点在的 intervals[right]。
        将其归纳合并即可。
         */
        if (intervals.length == 0) {
            return new int[][]{{newInterval[0], newInterval[1]}};
        }
        int[][] ans;
        int left = binarySearch(intervals, newInterval[0]);
        int right = binarySearch(intervals, newInterval[1]);
        int start;
        start = left;
        while (left <= right && left < intervals.length &&
                intervals[left][0] <= newInterval[1] && newInterval[0] <= intervals[left][1]) {
            newInterval[0] = Math.min(intervals[left][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[left][1], newInterval[1]);
            ++left;
        }
        ans = new int[intervals.length + start - left + 1][2];
        int i = 0;
        for (; i < start; ++i) {
            ans[i] = intervals[i];
        }
        ans[i++] = newInterval;
        for (; i < ans.length; ++i) {
            ans[i] = intervals[left++];
        }
        return ans;
    }


    public int binarySearch(int[][] intervals, int start) {
        if (intervals[0][1] >= start) {
            return 0;
        }
        if (intervals[intervals.length - 1][1] < start) {
            return intervals.length;
        }
        int left = 0;
        int right = intervals.length;
        int mid;
        while (right > left) {
            mid = (right + left) >> 1;
            if (mid == left) {
                break;
            }
            if (intervals[mid][1] == start) {
                return mid;
            } else if (intervals[mid][1] > start) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }


}
