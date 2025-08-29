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


    /**
     * Example 1:
     *
     * Input: grid = [[1,7,3],[9,8,2],[4,5,6]]
     *
     * Output: [[8,2,3],[9,6,7],[4,5,1]]
     *
     * Explanation:
     *
     *
     *
     * The diagonals with a black arrow (bottom-left triangle) should be sorted in non-increasing order:
     *
     * [1, 8, 6] becomes [8, 6, 1].
     * [9, 5] and [4] remain unchanged.
     * The diagonals with a blue arrow (top-right triangle) should be sorted in non-decreasing order:
     *
     * [7, 2] becomes [2, 7].
     * [3] remains unchanged.
     * Example 2:
     *
     * Input: grid = [[0,1],[1,2]]
     *
     * Output: [[2,1],[1,0]]
     *
     * Explanation:
     *
     *
     *
     * The diagonals with a black arrow must be non-increasing, so [0, 2] is changed to [2, 0]. The other diagonals are already in the correct order.
     *
     * Example 3:
     *
     * Input: grid = [[1]]
     *
     * Output: [[1]]
     *
     * Explanation:
     *
     * Diagonals with exactly one element are already in order, so no changes are needed.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
