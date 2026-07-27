package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * 59. Spiral Matrix II
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/spiral-matrix-ii/
 * <p>
 * Given a positive integer n , generate an n x n matrix filled with elements from 1 to n 2 in spiral
 * order.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3
 * Output: [[1,2,3],[8,9,4],[7,6,5]]
 * <p>
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: [[1]]
 * <p>
 * Constraints:
 * <p>
 * - 1 <= n <= 20
 */
public class _59 {

    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int num = 1;
        for (int i = 0; i < n; i++) {
            int row = i;
            int col = i;
            // right
            while (col < n - i) {
                matrix[row][col++] = num++;
            }
            // down
            --col;
            ++row;
            while (row < n - i) {
                matrix[row++][col] = num++;
            }
            // left
            --row;
            --col;
            while (col >= i) {
                matrix[row][col--] = num++;
            }
            // up
            ++col;
            --row;
            while (row > i) {
                matrix[row--][col] = num++;
            }
        }
        return matrix;
    }

    static void main() {
        TestUtil.test("=10");
    }
}
