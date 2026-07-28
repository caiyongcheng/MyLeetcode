package letcode.normal.difficult;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Caiyongcheng
 * @description 给你一棵由 n 个顶点组成的无向树，顶点编号从 1 到 n。青蛙从 顶点 1 开始起跳。规则如下：
 * 在一秒内，青蛙从它所在的当前顶点跳到另一个 未访问 过的顶点（如果它们直接相连）。 青蛙无法跳回已经访问过的顶点。
 * 如果青蛙可以跳到多个不同顶点，那么它跳到其中任意一个顶点上的机率都相同。
 * 如果青蛙不能跳到任何未访问过的顶点上，那么它每次跳跃都会停留在原地。
 * 无向树的边用数组 edges 描述，其中 edges[i] = [ai, bi] 意味着存在一条直接连通 ai 和 bi 两个顶点的边。
 * 返回青蛙在 t 秒后位于目标顶点 target 上的概率。与实际答案相差不超过 10-5 的结果将被视为正确答案。
 * 来源：力扣（LeetCode） 链接：<a href="https://leetcode.cn/problems/frog-position-after-t-seconds">...</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @since 2023/5/24 9:07
 */
public class _1377 {

    /**
     * 最终概率结果
     */
    BigDecimal ansProbability = BigDecimal.ZERO;
    int times = 0;
    int target = 0;
    int[] visited;
    int[][] map;

    public double frogPosition(int n, int[][] edges, int t, int target) {
        this.visited = new int[n + 1];
        this.map = new int[n + 1][n + 1];
        this.times = t;
        this.target = target;
        for (int[] edge : edges) {
            map[edge[0]][edge[1]] = 1;
            map[edge[1]][edge[0]] = 1;
        }
        visited[1] = 1;
        dfs(1, 0, BigDecimal.ONE);
        return ansProbability.doubleValue();
    }

    /**
     * dfs计算概率和
     *
     * @param current            当前节点
     * @param currentTimes       当前时间
     * @param currentProbability 到达当前节点概率
     */
    public void dfs(int current, int currentTimes, BigDecimal currentProbability) {
        //到达规定时间 判断是否到达目标节点
        if (currentTimes == times) {
            if (current == target) {
                ansProbability = ansProbability.add(currentProbability);
            }
            return;
        }
        //计算下一秒可选择的节点数
        int totalNextCnt = 0;
        for (int next = 0; next < map[current].length; next++) {
            if (this.visited[next] == 0 && map[current][next] == 1) {
                ++totalNextCnt;
            }
        }
        //没有可选择的节点数 并且当前节点在目标节点 则满足条件
        if (totalNextCnt == 0 && current == target) {
            ansProbability = ansProbability.add(currentProbability);
        }
        //遍历下一节点
        for (int next = 0; next < map[current].length; next++) {
            if (this.visited[next] == 0 && map[current][next] == 1) {
                this.visited[next] = 1;
                dfs(next, currentTimes + 1, currentProbability.divide(BigDecimal.valueOf(totalNextCnt), 8, RoundingMode.HALF_DOWN));
                this.visited[next] = 0;
            }
        }
    }


}
