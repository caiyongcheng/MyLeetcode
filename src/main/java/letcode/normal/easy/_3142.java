package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 * You are given a 2D matrix grid of size m x n. You need to check if each cell grid[i][j] is:
 * Equal to the cell below it, i.e. grid[i][j] == grid[i + 1][j] (if it exists). Different from the cell to its right,
 * i.e. grid[i][j] != grid[i][j + 1] (if it exists).
 * Return true if all the cells satisfy these conditions, otherwise, return false.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-08-29 09:02
 */
public class _3142 {

    public boolean satisfiesConditions(int[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (col >= 1 && grid[row][col] == grid[row][col - 1]) {
                    return false;
                }
                if (row >= 1 && grid[row][col] != grid[row - 1][col]) {
                    return false;
                }
            }
        }
        return true;
    }


}
