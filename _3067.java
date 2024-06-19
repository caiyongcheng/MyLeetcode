package letcode.normal.medium;

import letcode.utils.FormatUtils;
import letcode.utils.TestCaseUtils;

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

    /**
     * 输入：edges = [[0,1,1],[1,2,5],[2,3,13],[3,4,9],[4,5,2]], signalSpeed = 1
     * 输出：[0,4,6,6,4,0]
     * 解释：由于 signalSpeed 等于 1 ，count[c] 等于所有从 c 开始且没有公共边的路径对数目。
     * 在输入图中，count[c] 等于服务器 c 左边服务器数目乘以右边服务器数目。
     * 输入：edges = [[0,6,3],[6,5,3],[0,3,1],[3,2,7],[3,1,6],[3,4,2]], signalSpeed = 3
     * 输出：[2,0,0,0,0,0,2]
     * 解释：通过服务器 0 ，有 2 个可连接服务器对(4, 5) 和 (4, 6) 。
     * 通过服务器 6 ，有 2 个可连接服务器对 (4, 5) 和 (0, 5) 。
     * 所有服务器对都必须通过服务器 0 或 6 才可连接，所以其他服务器对应的可连接服务器对数目都为 0 。
     */
    public static void main(String[] args) {
        System.out.println(FormatUtils.formatArray(new _3067().countPairsOfConnectableServers(
                TestCaseUtils.get2DIntArr("[[0,6,3],[6,5,3],[0,3,1],[3,2,7],[3,1,6],[3,4,2]]"),
                3
        )));
    }


}
