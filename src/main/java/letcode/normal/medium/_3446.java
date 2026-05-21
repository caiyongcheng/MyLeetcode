package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.PriorityQueue;

/**
 * You are given an n x n square matrix of integers grid. Return the matrix such that:
 * The diagonals in the bottom-left triangle (including the middle diagonal) are sorted in non-increasing order.
 * The diagonals in the top-right triangle are sorted in non-decreasing order.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-08-28 10:53
 */
public class _3446 {

    public int[][] sortMatrix(int[][] grid) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(grid.length, (a, b) -> b - a);
        for (int row = 0; row < grid.length; row++) {
            putOnLeftDown(grid, row, priorityQueue);
        }

        PriorityQueue<Integer> priorityQueueCol = new PriorityQueue<>(grid.length);
        for (int col = 1; col < grid.length; col++) {
            putOnRightUp(grid, col, priorityQueueCol);
        }
        return grid;
    }


    private void putOnLeftDown(int[][] grid, int startRowIdx, PriorityQueue<Integer> priorityQueue) {
        for (int row = startRowIdx, col = 0; row < grid.length; ++row, ++col) {
            priorityQueue.add(grid[row][col]);
        }
        for (int row = startRowIdx, col = 0; row < grid.length; ++row, ++col) {
            grid[row][col] = priorityQueue.poll();
        }
    }

    private void putOnRightUp(int[][] grid, int startColIdx, PriorityQueue<Integer> priorityQueue) {
        for (int row = 0, col = startColIdx; col < grid.length; ++row, ++col) {
            priorityQueue.add(grid[row][col]);
        }
        for (int row = 0, col = startColIdx; col < grid.length; ++row, ++col) {
            grid[row][col] = priorityQueue.poll();
        }
    }


}
