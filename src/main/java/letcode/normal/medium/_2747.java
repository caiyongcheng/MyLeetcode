package letcode.normal.medium;

import java.util.*;

/**
 * 2747. Count Zero Request Servers
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/count-zero-request-servers/
 * <p>
 * You are given an integer n denoting the total number of servers and a 2D 0-indexed integer array
 * logs , where logs[i] = [server_id, time] denotes that the server with id server_id received a
 * request at time time .
 * <p>
 * You are also given an integer x and a 0-indexed integer array queries .
 * <p>
 * Return a 0-indexed integer array arr of length queries.length where arr[i] represents the number of
 * servers that did not receive any requests during the time interval [queries[i] - x, queries[i]] .
 * <p>
 * Note that the time intervals are inclusive.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3, logs = [[1,3],[2,6],[1,5]], x = 5, queries = [10,11]
 * Output: [1,2]
 * Explanation:
 * For queries[0]: The servers with ids 1 and 2 get requests in the duration of [5, 10]. Hence, only
 * server 3 gets zero requests.
 * For queries[1]: Only the server with id 2 gets a request in duration of [6,11]. Hence, the servers
 * with ids 1 and 3 are the only servers that do not receive any requests during that time period.
 * <p>
 * Example 2:
 * <p>
 * Input: n = 3, logs = [[2,4],[2,1],[1,2],[3,1]], x = 2, queries = [3,4]
 * Output: [0,1]
 * Explanation:
 * For queries[0]: All servers get at least one request in the duration of [1, 3].
 * For queries[1]: Only server with id 3 gets no request in the duration [2,4].
 * <p>
 * Constraints:
 * <p>
 * - 1 <= n <= 10 5
 * <p>
 * - 1 <= logs.length <= 10 5
 * <p>
 * - 1 <= queries.length <= 10 5
 * <p>
 * - logs[i].length == 2
 * <p>
 * - 1 <= logs[i][0] <= n
 * <p>
 * - 1 <= logs[i][1] <= 10 6
 * <p>
 * - 1 <= x <= 10 5
 * <p>
 * - x < queries[i] <= 10 6
 */
public class _2747 {


    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        // 时间 -> 服务器列表
        Map<Integer, List<Integer>> time2ServerList = new HashMap<>();

        for (int[] log : logs) {
            time2ServerList
                    .computeIfAbsent(log[1], key -> new ArrayList<>())
                    .add(log[0]);
        }

        // 查询按照时间排序，同时保留原始下标
        int[][] sortQueries = new int[queries.length][2];

        for (int i = 0; i < queries.length; i++) {
            sortQueries[i][0] = queries[i];
            sortQueries[i][1] = i;
        }

        Arrays.sort(sortQueries, Comparator.comparingInt(a -> a[0]));

        // 所有出现过的时间排序
        List<Integer> sortTimeList =
                new ArrayList<>(time2ServerList.keySet());

        Collections.sort(sortTimeList);

        // 每个服务器在当前窗口内的请求次数
        int[] serverCount = new int[n + 1];

        int[] ans = new int[queries.length];

        int left = 0;
        int right = 0;

        int activeServers = 0;
        int timeSize = sortTimeList.size();

        for (int[] query : sortQueries) {

            int queryTime = query[0];
            int originalIndex = query[1];

            int startTime = queryTime - x;

            // 先扩展右边界，加入所有 time <= queryTime 的日志
            while (right < timeSize &&
                    sortTimeList.get(right) <= queryTime) {

                int time = sortTimeList.get(right);

                for (int server : time2ServerList.get(time)) {

                    if (serverCount[server] == 0) {
                        activeServers++;
                    }

                    serverCount[server]++;
                }

                right++;
            }

            // 再收缩左边界，删除所有 time < startTime 的日志
            while (left < right &&
                    sortTimeList.get(left) < startTime) {

                int time = sortTimeList.get(left);

                for (int server : time2ServerList.get(time)) {

                    serverCount[server]--;

                    if (serverCount[server] == 0) {
                        activeServers--;
                    }
                }

                left++;
            }

            // 没有请求的服务器数量
            ans[originalIndex] = n - activeServers;
        }

        return ans;
    }

}
