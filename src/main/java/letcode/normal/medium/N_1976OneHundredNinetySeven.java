package letcode.normal.medium;

/**
 * 你在一个城市里，城市由 n 个路口组成，路口编号为 0 到 n - 1 ，某些路口之间有 双向 道路。输入保证你可以从任意路口出发到达其他任意路口，且任意两个路口之间最多有一条路。
 * 给你一个整数 n 和二维整数数组 roads ，其中 roads[i] = [ui, vi, timei] 表示在路口 ui 和 vi 之间有一条需要花费 timei 时间才能通过的道路。
 * 你想知道花费 最少时间 从路口 0 出发到达路口 n - 1 的方案数。
 * 请返回花费 最少时间 到达目的地的 路径数目 。由于答案可能很大，将结果对 109 + 7 取余 后返回。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/3/7 14:48
 */
public class N_1976OneHundredNinetySeven {

    private int ans = 0;
    private int minCost = Integer.MAX_VALUE;

    public int countPaths(int n, int[][] roads) {
        /**
         * 最简单的想法 穷举遍历+记录即可
         */
        return 0;
    }

    public void dfs(int cur, int dest, int curCost, int[] visit) {
        if (cur == dest) {
            if (curCost == minCost) {
                ++ans;
            } else if (curCost < minCost) {
                ans = 1;
                minCost = curCost;
            }
            return ;
        }
        
    }

}
