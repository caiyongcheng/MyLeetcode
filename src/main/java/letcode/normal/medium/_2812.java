package letcode.normal.medium;

import java.util.*;

/**
 * 2812. Find the Safest Path in a Grid
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/find-the-safest-path-in-a-grid/
 * You are given a 0-indexed 2D matrix grid of size n x n ,
 * where (r, c) represents:
 * - A cell containing a thief if grid[r][c] = 1
 * - An empty cell if grid[r][c] = 0
 * You are initially positioned at cell (0, 0) .
 * In one move, you can move to any adjacent cell in the grid,
 * including cells containing thieves.
 * The safeness factor of a path on the grid is defined as the minimum manhattan distance from any ...
 */
public class _2812 {

    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) {
            return 0;
        }

        // 第一步：多源 BFS 预处理每个格子到最近小偷的曼哈顿距离
        int[][] dist = buildDistances(grid, n);

        // 第二步：最大瓶颈路径。普通 FIFO 的 BFS 不行，必须优先扩展当前安全因子更大的状态。
        // 到达 (r,c) 的路径安全因子 = min(沿途各格 dist)，转移时取 min(当前安全因子, dist[邻居])。
        int[][] best = new int[n][n];
        for (int[] row : best) {
            Arrays.fill(row, -1);
        }

        // 大根堆：按当前路径安全因子从大到小扩展，等价于 Dijkstra 求「边权为 dist、目标是最大化路径最小值」
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[2] - a[2]);
        pq.offer(new int[]{0, 0, dist[0][0]});
        best[0][0] = dist[0][0];

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int row = cur[0];
            int col = cur[1];
            int safeness = cur[2];
            if (safeness < best[row][col]) {
                continue;
            }
            if (row == n - 1 && col == n - 1) {
                return safeness;
            }
            for (int[] dir : DIRS) {
                int nextRow = row + dir[0];
                int nextCol = col + dir[1];
                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) {
                    continue;
                }
                int nextSafeness = Math.min(safeness, dist[nextRow][nextCol]);
                if (nextSafeness > best[nextRow][nextCol]) {
                    best[nextRow][nextCol] = nextSafeness;
                    pq.offer(new int[]{nextRow, nextCol, nextSafeness});
                }
            }
        }
        return best[n - 1][n - 1];
    }

    /**
     * 多源 BFS：把所有小偷作为起点同时向外扩散，得到每个格子的最近曼哈顿距离。
     */
    private int[][] buildDistances(List<List<Integer>> grid, int n) {
        int[][] dist = new int[n][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        Queue<int[]> queue = new ArrayDeque<>();
        for (int row = 0; row < n; row++) {
            List<Integer> rowData = grid.get(row);
            for (int col = 0; col < n; col++) {
                if (rowData.get(col) == 1) {
                    dist[row][col] = 0;
                    queue.offer(new int[]{row, col});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] dir : DIRS) {
                int nextRow = cur[0] + dir[0];
                int nextCol = cur[1] + dir[1];
                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) {
                    continue;
                }
                if (dist[nextRow][nextCol] > dist[cur[0]][cur[1]] + 1) {
                    dist[nextRow][nextCol] = dist[cur[0]][cur[1]] + 1;
                    queue.offer(new int[]{nextRow, nextCol});
                }
            }
        }
        return dist;
    }

}
