package letcode.normal.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 2054. Two Best Non-Overlapping Events
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/two-best-non-overlapping-events/
 *
 * You are given a 0-indexed 2D integer array of events where events[i] = [startTime i , endTime i ,
 * value i ] . The i th event starts at startTime i and ends at endTime i , and if you attend this
 * event, you will receive a value of value i . You can choose at most two non-overlapping events to
 * attend such that the sum of their values is maximized .
 *
 * Return this maximum sum.
 *
 * Note that the start time and end time is inclusive : that is, you cannot attend two events where one
 * of them starts and the other ends at the same time. More specifically, if you attend an event with
 * end time t , the next event must start at or after t + 1 .
 *
 * Example 1:
 *
 * Input: events = [[1,3,2],[4,5,2],[2,4,3]]
 * Output: 4
 * Explanation: Choose the green events, 0 and 1 for a sum of 2 + 2 = 4.
 *
 * Example 2:
 *
 * Input: events = [[1,3,2],[4,5,2],[1,5,5]]
 * Output: 5
 * Explanation: Choose event 2 for a sum of 5.
 *
 * Example 3:
 *
 * Input: events = [[1,5,3],[1,5,1],[6,6,5]]
 * Output: 8
 * Explanation: Choose events 0 and 2 for a sum of 3 + 5 = 8.
 *
 * Constraints:
 *
 * - 2 <= events.length <= 10 5
 *
 * - events[i].length == 3
 *
 * - 1 <= startTime i <= endTime i <= 10 9
 *
 * - 1 <= value i <= 10 6
 */
public class _2054 {

    public int maxTwoEvents(int[][] events) {

        int[][] orderByStartTime = new int[events.length][3];
        int ans = 0;

        for (int i = 0; i < events.length; i++) {
            orderByStartTime[i][0] = events[i][0];
            orderByStartTime[i][1] = events[i][1];
            orderByStartTime[i][2] = events[i][2];
            ans = Math.max(ans, events[i][2]);
        }

        Arrays.sort(events, Comparator.comparingInt(e -> e[0]));
        Arrays.sort(orderByStartTime, Comparator.comparingInt(e -> e[1]));

        int first = 0;
        int second = 0;
        int i = 0;
        for (int[] event : events) {
            while (i < events.length && event[0] > orderByStartTime[i][1]) {
                if (orderByStartTime[i][2] > orderByStartTime[first][2]) {
                    second = first;
                    first = i;
                } else if (orderByStartTime[i][2] > orderByStartTime[second][2]) {
                    second = i;
                }
                ++i;
            }
            if (event[0] > orderByStartTime[first][1]) {
                ans = Math.max(ans, event[2] + orderByStartTime[first][2]);
            } else if (event[0] > orderByStartTime[second][1]) {
                ans = Math.max(ans, event[2] + orderByStartTime[second][2]);
            }

        }

        return ans;

    }

}
