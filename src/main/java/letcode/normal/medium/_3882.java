package letcode.normal.medium;

import letcode.utils.SolutionTestMethod;

import java.util.*;

/**
 * 3882. Minimum XOR Path in a Grid
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/minimum-xor-path-in-a-grid/
 * <p>
 * You are given a 2D integer array grid of size m * n .
 * <p>
 * You start at the top-left cell (0, 0) and want to reach the bottom-right cell (m - 1, n - 1) .
 * <p>
 * At each step, you may move either right or down .
 * <p>
 * The cost of a path is defined as the bitwise XOR of all the values in the cells along that path,
 * including the start and end cells.
 * <p>
 * Return the minimum possible XOR value among all valid paths from (0, 0) to (m - 1, n - 1) .
 * <p>
 * Example 1:
 * <p>
 * Input: grid = [[1,2],[3,4]]
 * <p>
 * Output: 6
 * <p>
 * Explanation:
 * <p>
 * There are two valid paths:
 * <p>
 * - (0, 0) &rarr; (0, 1) &rarr; (1, 1) with XOR: 1 XOR 2 XOR 4 = 7
 * <p>
 * - (0, 0) &rarr; (1, 0) &rarr; (1, 1) with XOR: 1 XOR 3 XOR 4 = 6
 * <p>
 * The minimum XOR value among all valid paths is 6.
 * <p>
 * Example 2:
 * <p>
 * Input: grid = [[6,7],[5,8]]
 * <p>
 * Output: 9
 * <p>
 * Explanation:
 * <p>
 * There are two valid paths:
 * <p>
 * - (0, 0) &rarr; (0, 1) &rarr; (1, 1) with XOR: 6 XOR 7 XOR 8 = 9
 * <p>
 * - (0, 0) &rarr; (1, 0) &rarr; (1, 1) with XOR: 6 XOR 5 XOR 8 = 11
 * <p>
 * The minimum XOR value among all valid paths is 9.
 * <p>
 * Example 3:
 * <p>
 * Input: grid = [[2,7,5]]
 * <p>
 * Output: 0
 * <p>
 * Explanation:
 * <p>
 * There is only one valid path:
 * <p>
 * - (0, 0) &rarr; (0, 1) &rarr; (0, 2) with XOR: 2 XOR 7 XOR 5 = 0
 * <p>
 * The XOR value of this path is 0, which is the minimum possible.
 * <p>
 * Constraints:
 * <p>
 * - 1 <= m == grid.length <= 1000
 * <p>
 * - 1 <= n == grid[i].length <= 1000
 * <p>
 * - m * n <= 1000
 * <p>
 * - 0 <= grid[i][j] <= 1023​
 */
@SolutionTestMethod(method = "minCost")
public class _3882 {

    int rowLen;
    int colLen;
    int xorValueLimit = 1024;

    public int minCost(int[][] grid) {
        rowLen = grid.length;
        colLen = grid[0].length;
        HashMap<Integer, int[]> cache = new HashMap<>();
        int[] endXorValues = new int[xorValueLimit];
        endXorValues[grid[rowLen - 1][colLen - 1]] = 1;
        cache.put(rowLen * colLen - 1, endXorValues);

        int[] startXorValues = dfs(grid, 0, 0, cache);
        for (int xorValue = 0; xorValue < startXorValues.length; xorValue++) {
            if (startXorValues[xorValue] == 1) {
                return xorValue;
            }
        }
        return grid[0][0];
    }


    public int[] dfs(int[][] grid, int row, int col, Map<Integer, int[]> cache) {
        int[] curXorValues = cache.get(row * colLen + col);
        if (curXorValues == null) {
            curXorValues = new int[xorValueLimit];
        } else {
            return curXorValues;
        }

        if (row + 1 < rowLen) {
            int[] downMoveXorValues = dfs(grid, row + 1, col, cache);
            for (int xorValue = 0; xorValue < downMoveXorValues.length; xorValue++) {
                if (downMoveXorValues[xorValue] == 1) {
                    curXorValues[grid[row][col] ^ xorValue] = 1;
                }
            }
        }

        if (col + 1 < colLen) {
            int[] rightMoveXorValues = dfs(grid, row, col + 1, cache);
            for (int xorValue = 0; xorValue < rightMoveXorValues.length; xorValue++) {
                if (rightMoveXorValues[xorValue] == 1) {
                    curXorValues[grid[row][col] ^ xorValue] = 1;
                }
            }
        }

        cache.put(row * colLen + col, curXorValues);
        return curXorValues;
    }



}
