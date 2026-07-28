package letcode.normal.difficult;

import java.util.*;

/**
 * 城市用一个 双向连通 图表示，图中有 n 个节点，从 1 到 n 编号（包含 1 和 n）。
 * 图中的边用一个二维整数数组 edges 表示，其中每个 edges[i] = [ui, vi]表示一条节点ui 和节点vi 之间的双向连通边。
 * 每组节点对由 最多一条 边连通，顶点不存在连接到自身的边。穿过任意一条边的时间是 time分钟。
 * 每个节点都有一个交通信号灯，每 change 分钟改变一次，从绿色变成红色，再由红色变成绿色，循环往复。所有信号灯都同时 改变。
 * 你可以在 任何时候 进入某个节点，但是 只能 在节点信号灯是绿色时 才能离开。
 * 如果信号灯是 绿色 ，你 不能 在节点等待，必须离开。
 * 第二小的值 是严格大于 最小值的所有值中最小的值。  例如，[2, 3, 4] 中第二小的值是 3 ，而 [2, 2, 4] 中第二小的值是 4 。
 * 给你 n、edges、time 和 change ，返回从节点 1 到节点 n 需要的 第二短时间 。
 * 注意：  你可以 任意次 穿过任意顶点，包括 1 和 n 。 你可以假设在 启程时 ，所有信号灯刚刚变成 绿色 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/second-minimum-time-to-reach-destination
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-01-24 09:03
 **/
public class _2045 {

    public int secondMinimum(int n, int[][] edges, int time, int change) {
        /*
            因为1.所有信号灯都同时改变。2.穿过任意一条边的时间是time分钟。所以长路径相对于短路径而言需要时间更多。
        也就是题目求的是严格次短路径。
            传统使用BFS求单源最短路径时，都是一步步更新起始点到每个点的最短距离。如果将严格次短距离加上即可求出结果。
        最后根据距离计算时间即可。只需要保存起点到每个点的最短、次短路径即可。
         */
        List<Integer>[] graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        int[][] path = new int[n + 1][2];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(path[i], Integer.MAX_VALUE);
        }
        path[1][0] = 0;
        Queue<int[]> queue = new ArrayDeque<int[]>();
        queue.offer(new int[]{1, 0});
        //BFS 层级的递增的 所以找到的第一个就是严格次短路径
        while (path[n][1] == Integer.MAX_VALUE) {
            int[] arr = queue.poll();
            int cur = arr[0], len = arr[1];
            for (int next : graph[cur]) {
                if (len + 1 < path[next][0]) {
                    path[next][0] = len + 1;
                    queue.offer(new int[]{next, len + 1});
                    //保证严格次短
                } else if (len + 1 > path[next][0] && len + 1 < path[next][1]) {
                    path[next][1] = len + 1;
                    queue.offer(new int[]{next, len + 1});
                }
            }
        }

        int ret = 0;
        for (int i = 0; i < path[n][1]; i++) {
            if (ret % (2 * change) >= change) {
                ret = ret + (2 * change - ret % (2 * change));
            }
            ret = ret + time;
        }
        return ret;
    }

}
