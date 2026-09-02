package letcode.normal.medium;

import letcode.utils.SolutionTestMethod;

/**
 * 1391. Check if There is a Valid Path in a Grid
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/check-if-there-is-a-valid-path-in-a-grid/
 * <p>
 * You are given an m x n grid . Each cell of grid represents a street. The street of grid[i][j] can
 * be:
 * <p>
 * - 1 which means a street connecting the left cell and the right cell.
 * <p>
 * - 2 which means a street connecting the upper cell and the lower cell.
 * <p>
 * - 3 which means a street connecting the left cell and the lower cell.
 * <p>
 * - 4 which means a street connecting the right cell and the lower cell.
 * <p>
 * - 5 which means a street connecting the left cell and the upper cell.
 * <p>
 * - 6 which means a street connecting the right cell and the upper cell.
 * <p>
 * You will initially start at the street of the upper-left cell (0, 0) . A valid path in the grid is a
 * path that starts from the upper left cell (0, 0) and ends at the bottom-right cell (m - 1, n - 1) .
 * The path should only follow the streets .
 * <p>
 * Notice that you are not allowed to change any street.
 * <p>
 * Return true if there is a valid path in the grid or false otherwise .
 * <p>
 * Example 1:
 * <p>
 * Input: grid = [[2,4,3],[6,5,2]]
 * Output: true
 * Explanation: As shown you can start at cell (0, 0) and visit all the cells of the grid to reach (m -
 * 1, n - 1).
 * <p>
 * Example 2:
 * <p>
 * Input: grid = [[1,2,1],[1,2,1]]
 * Output: false
 * Explanation: As shown you the street at cell (0, 0) is not connected with any street of any other
 * cell and you will get stuck at cell (0, 0)
 * <p>
 * Example 3:
 * <p>
 * Input: grid = [[1,1,2]]
 * Output: false
 * Explanation: You will get stuck at cell (0, 1) and you cannot reach cell (0, 2).
 * <p>
 * Constraints:
 * <p>
 * - m == grid.length
 * <p>
 * - n == grid[i].length
 * <p>
 * - 1 <= m, n <= 300
 * <p>
 * - 1 <= grid[i][j] <= 6
 */
public class _1391 {



    private static final int[][][] NEXT_STREET_MOVE = new int[][][]{
            {{0 , 1}, {0, -1}},
            {{1 , 0}, {-1, 0}},
            {{1 , 0}, {0, -1}},
            {{1 , 0}, {0, 1}},
            {{-1 , 0}, {0, -1}},
            {{0, 1}, {-1, 0}},
    };

    private static final int[][][] NEXT_STREET_ALLOW_VALUE = new int[][][]{
            {
                    {1, 3, 5},
                    {1, 4, 6}
            },
            {
                    {2, 5, 6},
                    {2, 3, 4}
            },
            {
                    {2, 5, 6},
                    {1, 4, 6}
            },
            {
                    {2, 5, 6},
                    {1, 3, 5}
            },
            {
                    {2, 3, 4},
                    {1, 4, 6}
            },
            {
                    {1, 3, 5},
                    {2, 3, 4}
            }
    };

    @SolutionTestMethod
    public boolean hasValidPath(int[][] grid) {
        return dfs(grid, new boolean[grid.length][grid[0].length], 0, 0);
    }

    public boolean dfs(int[][] grid, boolean[][] visited, int x, int y) {
        if (x ==  grid.length - 1 && y == grid[0].length - 1) {
            return true;
        }

        int nextX;
        int nextY;

        for (int i = 0; i < NEXT_STREET_MOVE[grid[x][y] - 1].length; i++) {
            nextX = x + NEXT_STREET_MOVE[grid[x][y] - 1][i][0];
            nextY = y + NEXT_STREET_MOVE[grid[x][y] - 1][i][1];
            if (nextX < 0 || nextX >= grid.length || nextY < 0 || nextY >= grid[0].length || visited[nextX][nextY]) {
                continue;
            }
            for (int allValue : NEXT_STREET_ALLOW_VALUE[grid[x][y] - 1][i]) {
                if (grid[nextX][nextY] == allValue) {
                    visited[nextX][nextY] = true;
                    if (dfs(grid, visited, nextX, nextY)) {
                        return true;
                    }
                    visited[nextX][nextY] = false;
                    break;
                }
            }
        }
        return false;
    }

}
