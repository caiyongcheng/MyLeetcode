package letcode.normal.medium;

import java.util.Arrays;

/**
 * 1288. Remove Covered Intervals
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/remove-covered-intervals/
 *
 * Given an array intervals where intervals[i] = [l i , r i ] represent the interval [l i , r i ) ,
 * remove all intervals that are covered by another interval in the list.
 *
 * The interval [a, b) is covered by the interval [c, d) if and only if c <= a and b <= d .
 *
 * Return the number of remaining intervals .
 *
 * Example 1:
 *
 * Input: intervals = [[1,4],[3,6],[2,8]]
 * Output: 2
 * Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
 *
 * Example 2:
 *
 * Input: intervals = [[1,4],[2,3]]
 * Output: 1
 *
 * Constraints:
 *
 * - 1 <= intervals.length <= 1000
 *
 * - intervals[i].length == 2
 *
 * - 0 <= l i < r i <= 10 5
 *
 * - All the given intervals are unique .
 */
public class _1288 {

    class Section {
        int s;
        int e;

        public Section(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }

    public int removeCoveredIntervals(int[][] intervals) {
        Section[] sections = new Section[intervals.length];
        boolean[] obsoletes = new boolean[intervals.length];
        int ans = intervals.length;
        for (int i = 0; i < intervals.length; i++) {
            sections[i] = new Section(intervals[i][0], intervals[i][1]);
        }
        Arrays.sort(sections, (o1, o2) -> o1.s < o2.s ? -1 : o1.s == o2.s ? o1.e > o2.e ? -1 : 1 : 1);
        for (int i = 0; i < sections.length; i++) {
            if (obsoletes[i]) {
                continue;
            }
            for (int j = sections.length - 1; j > i; j--) {
                if (obsoletes[j]) {
                    continue;
                }
                if (sections[i].s <= sections[j].s && sections[i].e >= sections[j].e) {
                    obsoletes[j] = true;
                    --ans;
                }
            }
        }
        return ans;
    }


}
