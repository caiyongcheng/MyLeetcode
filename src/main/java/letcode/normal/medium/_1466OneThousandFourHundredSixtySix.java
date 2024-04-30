package letcode.normal.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/12/7 14:23
 * description n 座城市，从 0 到 n-1 编号，其间共有 n-1 条路线。因此，要想在两座不同城市之间旅行只有唯一一条路线可供选择（路线网形成一颗树）。
 * 去年，交通运输部决定重新规划路线，以改变交通拥堵的状况。
 * 路线用 connections 表示，其中 connections[i] = [a, b] 表示从城市 a 到 b 的一条有向路线。
 * 今年，城市 0 将会举办一场大型比赛，很多游客都想前往城市 0 。
 * 请你帮助重新规划路线方向，使每个城市都可以访问城市 0 。返回需要变更方向的最小路线数。
 * 题目数据 保证 每个城市在重新规划路线方向后都能到达城市 0 。
 */
public class _1466OneThousandFourHundredSixtySix {

    int ans = 0;

    public int minReorder(int n, int[][] connections) {
        ans = 0;
        // 根据题意 路线形成一个树 也就意味着 每个点到达0点的途径只有一种 原始图中一个点也只有0个或1个出度
        // 将图按无向图处理 然后判断方向不一致的即可
        List<Integer>[] map = new List[connections.length + 1];
        boolean[] visited = new boolean[connections.length + 1];
        for (int i = 0; i < map.length; i++) {
            map[i] = new ArrayList<>();
        }
        for (int[] connection : connections) {
            map[connection[0]].add(connection[1]);
            // 表示这是原来不存在的路径
            map[connection[1]].add(connection[0] + 100000);
        }
        dfs(0, map, visited);
        return ans;
    }

    public void dfs(int start, List<Integer>[] map, boolean[] visited) {
        visited[start] = true;
        List<Integer> nextList = map[start];
        if (nextList.size() == 0) {
            return;
        }
        for (Integer next : nextList) {
            if (visited[next % 100000]) {
                continue;
            }
            if (next < 100000) {
                ++ans;
                dfs(next, map, visited);
            } else {
                dfs(next - 100000, map, visited);
            }
        }
    }

}
