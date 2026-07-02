package letcode.normal.medium;

import java.util.ArrayDeque;
import java.util.List;
import java.util.SequencedCollection;

/**
 * 3286. Find a Safe Walk Through a Grid
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/find-a-safe-walk-through-a-grid/
 * You are given an m x n binary matrix grid and an integer health .
 * You start in the upper-left corner (0, 0) and would like to get to the lower-right corner (m - 1, n - 1) .
 * You can move up, down, left, or right from one cell to another adjacent cell as long as your health remains positive .
 * Cells (i, j) with grid[i][j] = 1 are considered unsafe and reduce your health by 1. Return true if you can r...
 */
public class _3286 {

    static final int[][] MOVE = new int[][]{
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
    };

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {

        int rowLen = grid.size();
        int colLen = grid.get(0).size();
        int[][] map = new int[rowLen][colLen];
        for (int row = 0; row < rowLen; row++) {
            List<Integer> rowData = grid.get(row);
            for (int col = 0; col < colLen; col++) {
                map[row][col] = rowData.get(col);
            }
        }

        int[][] healthMap = new int[rowLen][colLen];
        healthMap[0][0] = health - map[0][0];

        SequencedCollection<int[]> seq = new ArrayDeque<>();
        seq.addLast(new int[]{0, 0, healthMap[0][0]});

        while (!seq.isEmpty()) {
            int[] curPoint = seq.removeFirst();
            for (int[] nextStep : MOVE) {
                int nextRow = curPoint[0] + nextStep[0];
                int nextCol = curPoint[1] + nextStep[1];
                if (nextRow < 0 || nextRow >= rowLen || nextCol < 0 || nextCol >= colLen) {
                    continue;
                }
                int nextHealth = curPoint[2] - map[nextRow][nextCol];
                if (nextHealth <= 0) {
                    continue;
                }
                if (healthMap[nextRow][nextCol] >= nextHealth) {
                    continue;
                }
                healthMap[nextRow][nextCol] = nextHealth;
                if (nextRow == rowLen - 1 && nextCol == colLen - 1) {
                    return true;
                }
                seq.addLast(new int[]{nextRow, nextCol, nextHealth});
            }
        }
        return false;
    }



}
