package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-08-20 09:47
 */
public class _1277 {


    public int countSquares(int[][] matrix) {
        int leftPointLen = 0;
        int[] rowCache = new int[matrix[0].length + 1];

        int ans = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == 0) {
                    continue;
                }
                leftPointLen = expansion(matrix, row, col, Math.max(leftPointLen, rowCache[col + 1]) - 1);
                rowCache[col + 1] = leftPointLen;
                ans += leftPointLen;
            }
            leftPointLen = 0;
        }
        return ans;
    }

    private int expansion(int[][] matrix, int row, int col, int len) {
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        int curRow = row + len;
        int curCol = col + len;
        while (curRow < rowLen && curCol < colLen) {
            for (int delta = 0; delta <= len; delta++) {
                if (col + delta >= colLen || matrix[curRow][col + delta] == 0) {
                    return len;
                }
                if (row + delta >= rowLen || matrix[row + delta][curCol] == 0) {
                    return len;
                }
            }
            ++curRow;
            ++curCol;
            ++len;
        }
        return len;
    }

    private int countSquaresOnBruteForce(int[][] matrix) {
        int ans = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                int len = 1;
                while (exist(matrix, row, col, len)) {
                    ++ans;
                    ++len;
                }
            }
        }
        return ans;
    }

    private boolean exist(int[][] matrix, int rowIdx, int colIdx, int len) {
        if (rowIdx + len > matrix.length) {
            return false;
        }
        if (colIdx + len > matrix[0].length) {
            return false;
        }
        for (int deltaRowIdx = 0; deltaRowIdx < len; deltaRowIdx++) {
            for (int deltaColIdx = 0; deltaColIdx < len; deltaColIdx++) {
                if (matrix[rowIdx + deltaRowIdx][colIdx + deltaColIdx] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Example 1:
     *
     * Input: matrix =
     * [
     *   [0,1,1,1],
     *   [1,1,1,1],
     *   [0,1,1,1]
     * ]
     * Output: 15
     * Explanation:
     * There are 10 squares of side 1.
     * There are 4 squares of side 2.
     * There is  1 square of side 3.
     * Total number of squares = 10 + 4 + 1 = 15.
     * Example 2:
     *
     * Input: matrix =
     * [
     *   [1,0,1],
     *   [1,1,0],
     *   [1,1,0]
     * ]
     * Output: 7
     * Explanation:
     * There are 6 squares of side 1.
     * There is 1 square of side 2.
     * Total number of squares = 6 + 1 = 7.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test("=[[1,1],[0,0],[1,1]]");
    }





}
