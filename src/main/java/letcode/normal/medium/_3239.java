package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * You are given an m x n binary matrix grid.  A row or column is considered palindromic if its values read the
 * same forward and backward.  You can flip any number of cells in grid from 0 to 1, or from 1 to 0.
 * Return the minimum number of cells that need to be flipped to make either all rows palindromic or all columns palindromic.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-11-15 17:15
 */
public class _3239 {



    public int minFlipsPretty(int[][] grid) {
        int left;
        int right;
        int rowLen = grid.length;
        int colLen = grid[0].length;
        int rowCnt = 0;
        int colCnt = 0;
        for (int[] row : grid) {
            for (left = 0, right = colLen - 1; left < right; left++, right--) {
                rowCnt += row[left] ^ row[right];
            }
        }

        if (rowCnt == 0) {
            return 0;
        }

        for (int col = 0; col < colLen; col++) {
            for (left = 0, right = rowLen - 1; left < right; left++, right--) {
                colCnt += grid[left][col] ^ grid[right][col];
                // 这一行要考虑是不是有必要 考虑数学期望 应该没什么意义
/*                if (colCnt >= rowCnt) {
                    return rowCnt;
                }*/
            }
        }

        return Math.min(rowCnt, colCnt);

    }


    private int minFlips(int[][] grid) {
        // 我只是闲着无聊才写那么复杂的
        int flipByRow = flips(
                grid,
                new int[]{0, 0, 0, grid[0].length - 1},
                new int[]{0, 1, 0, -1},
                new int[]{1, 0},
                grid.length,
                grid.length * grid[0].length
        );
        return Math.min(
                flipByRow,
                flips(
                        grid,
                        new int[]{0, 0, grid.length - 1, 0},
                        new int[]{1, 0, -1, 0},
                        new int[]{0, 1},
                        grid[0].length,
                        flipByRow
                )
        );
    }

    private int flips(int[][] grid, int[] startIdx, int[] gridMoveStep, int[] lineMoveStep, int limit, int culFlips) {
        int count = 0;
        for (int i = 0; i < limit; i++) {
            count += itemFlips(grid, startIdx, gridMoveStep);
            if (count >= culFlips) {
                return culFlips;
            }
            startIdx[0] += lineMoveStep[0];
            startIdx[1] += lineMoveStep[1];
            startIdx[2] += lineMoveStep[0];
            startIdx[3] += lineMoveStep[1];
        }
        return count;
    }

    private int itemFlips(int[][] grid, int[] startIdx, int[] gridMoveStep) {
        int leftRow = startIdx[0];
        int leftCol = startIdx[1];
        int rightRow = startIdx[2];
        int rightCol = startIdx[3];
        int count = 0;
        while (leftRow <= rightRow && leftCol <= rightCol) {
            count += grid[leftRow][leftCol] ^ grid[rightRow][rightCol];
            leftRow += gridMoveStep[0];
            leftCol += gridMoveStep[1];
            rightRow += gridMoveStep[2];
            rightCol += gridMoveStep[3];
        }
        return count;
    }


    /**
     * Example 1:
     *
     * Input: grid = [[1,0,0],[0,0,0],[0,0,1]]
     *
     * Output: 2
     *
     * Explanation:
     *
     *
     *
     * Flipping the highlighted cells makes all the rows palindromic.
     *
     * Example 2:
     *
     * Input: grid = [[0,1],[0,1],[0,0]]
     *
     * Output: 1
     *
     * Explanation:
     *
     *
     *
     * Flipping the highlighted cell makes all the columns palindromic.
     *
     * Example 3:
     *
     * Input: grid = [[1],[0]]
     *
     * Output: 0
     *
     * Explanation:
     *
     * All rows are already palindromic.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(_3239.class);
    }

}
