package letcode.normal.medium;

import letcode.utils.TestCaseInputUtils;
import letcode.utils.TestCaseOutputUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一棵无根带权树，树中总共有 n 个节点，分别表示 n 个服务器，服务器从 0 到 n - 1 编号。
 * 同时给你一个数组 edges ，其中 edges[i] = [ai, bi, weighti] 表示节点 ai 和 bi 之间有一条双向边，边的权值为 weighti 。
 * 再给你一个整数 signalSpeed 。
 * 如果两个服务器 a ，b 和 c 满足以下条件，那么我们称服务器 a 和 b 是通过服务器 c 可连接的 ：
 * a < b ，a != c 且 b != c 。
 * 从 c 到 a 的距离是可以被 signalSpeed 整除的。
 * 从 c 到 b 的距离是可以被 signalSpeed 整除的。
 * 从 c 到 b 的路径与从 c 到 a 的路径没有任何公共边。
 * 请你返回一个长度为 n 的整数数组 count ，其中 count[i] 表示通过服务器 i 可连接 的服务器对的 数目 。
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-06-04 09:20
 */
public class _3067 {

    @SuppressWarnings("all")
    public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        /*
        只有一条出入边的点 一定是起始点 所以要找的中继点一定有一条以上出入边 在最坏情况下 每个点要遍历全图 也就是o(n^2)复杂度不会超时
        另外一种做法 限定顺序 找到一条路径 统计路径上符合条件的点 这样的话时间复杂度应该是o(e^2) 复杂度也不会超时
        考虑到给定数据 n = e + 1，以及实现简便性，使用遍历中继点方式
         */

        // 因为是树 点的数量=边的数量+1
        int[] ans = new int[edges.length + 1];
        // 邻接表构建图
        List<int[]>[] graph = new ArrayList[edges.length + 1];
        for (int i = 0; i < edges.length + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(new int[]{edge[1], edge[2]});
            graph[edge[1]].add(new int[]{edge[0], edge[2]});
        }
        for (int point = 0; point < graph.length; point++) {
            if (graph[point].size() < 2) {
                continue;
            }
            List<Integer> matchPointCount = new ArrayList<>();
            for (int[] next : graph[point]) {
                int matchCount = search(graph, point, next[0], next[1] % signalSpeed, signalSpeed);
                if (matchCount != 0) {
                    matchPointCount.add(matchCount);
                }
            }
            for (int i = 0; i < matchPointCount.size(); i++) {
                for (int j = i + 1; j < matchPointCount.size(); j++) {
                    ans[point] += matchPointCount.get(i) * matchPointCount.get(j);
                }
            }
        }
        return ans;
    }

    public int search(List<int[]>[] graph, int pre, int cur, int curDist, int signalSpeed) {
        int matchPointCount = curDist % signalSpeed == 0 ? 1 : 0;
        for (int[] next : graph[cur]) {
            if (next[0] == pre) {
                continue;
            }
            matchPointCount += search(graph, cur, next[0], (curDist + next[1]) % signalSpeed, signalSpeed);
        }
        return matchPointCount;
    }

}
