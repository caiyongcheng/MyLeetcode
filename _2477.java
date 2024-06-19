package letcode.normal.medium;

import java.util.*;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/12/6 9:14
 * description 给你一棵 n 个节点的树（一个无向、连通、无环图），每个节点表示一个城市，编号从 0 到 n - 1 ，且恰好有 n - 1 条路。
 * 0 是首都。给你一个二维整数数组 roads ，其中 roads[i] = [ai, bi] ，表示城市 ai 和 bi 之间有一条 双向路 。
 * 每个城市里有一个代表，他们都要去首都参加一个会议。  每座城市里有一辆车。给你一个整数 seats 表示每辆车里面座位的数目。
 * 城市里的代表可以选择乘坐所在城市的车，或者乘坐其他城市的车。相邻城市之间一辆车的油耗是一升汽油。  请你返回到达首都最少需要多少升汽油。
 */
public class _2477 {



    long ans;

    public long minimumFuelCost(int[][] roads, int seats) {
        /**
         * 贪心 尽可能的把车坐满
         * 所以到达城市a后，所有人下车，求和判断需要几辆车
         * 所以需要保存每辆车到达a后，车上人的数目，以及耗费的汽油数
         *
         * 优化 到下一个城市的耗费汽油数 汇总即是结果 所以只需要求当前城市到下一城市的耗费汽油数和即可
         */
        List<Integer>[] map = new List[100001];
        for (int[] road : roads) {
            if (map[road[0]] == null) {
                map[road[0]] = new ArrayList<>();
            }
            if (map[road[1]] == null) {
                map[road[1]] = new ArrayList<>();
            }
            map[road[0]].add(road[1]);
            map[road[1]].add(road[0]);
        }
        boolean[] visitArr = new boolean[100001];
        visitArr[0] = true;
        ans = 0;
        if (map[0] == null) {
            return ans;
        }
        for (Integer startPoint : map[0]) {
            dfs(startPoint, map, visitArr, seats);
        }
        return ans;
    }


    public long dfs(int start, List<Integer>[] map, boolean[] visited, int seats) {
        visited[start] = true;
        List<Integer> nextList = map[start];
        long people = 1;
        for (Integer next : nextList) {
            if (!visited[next]) {
                people += dfs(next, map, visited, seats);
            }
        }
        ans += (people + seats - 1) / seats;
        return people;
    }


    /**
     * 输入：roads = {{0,1},{0,2},{0,3}}, seats = 5
     * 输出：3
     * 解释：
     * - 代表 1 直接到达首都，消耗 1 升汽油。
     * - 代表 2 直接到达首都，消耗 1 升汽油。
     * - 代表 3 直接到达首都，消耗 1 升汽油。
     * 最少消耗 3 升汽油。
     *
     * 输入：roads = {{3,1},{3,2},{1,0},{0,4},{0,5},{4,6}}, seats = 2
     * 输出：7
     * 解释：
     * - 代表 2 到达城市 3 ，消耗 1 升汽油。
     * - 代表 2 和代表 3 一起到达城市 1 ，消耗 1 升汽油。
     * - 代表 2 和代表 3 一起到达首都，消耗 1 升汽油。
     * - 代表 1 直接到达首都，消耗 1 升汽油。
     * - 代表 5 直接到达首都，消耗 1 升汽油。
     * - 代表 6 到达城市 4 ，消耗 1 升汽油。
     * - 代表 4 和代表 6 一起到达首都，消耗 1 升汽油。
     * 最少消耗 7 升汽油。
     * 
     * 输入：roads = {}, seats = 1
     * 输出：0
     *
     * {{0,1},{0,2},{1,3},{1,4}} , 5                  //02->1 01{3,4} 2 2
     *
     * 解释：没有代表需要从别的城市到达首都。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2477().minimumFuelCost(
                new int[][]{{0,1},{0,2},{1,3},{1,4}},
                5
        ));
    }




}
