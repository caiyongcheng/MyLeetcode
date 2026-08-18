package letcode.normal.medium;

/**
 * 840. Magic Squares In Grid
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/magic-squares-in-grid/
 * <p>
 * A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9 such that each row,
 * column, and both diagonals all have the same sum.
 * <p>
 * Given a row x col grid of integers, how many 3 x 3 magic square subgrids are there?
 * <p>
 * Note: while a magic square can only contain numbers from 1 to 9, grid may contain numbers up to 15.
 * <p>
 * Example 1:
 * <p>
 * Input: grid = [[4,3,8,4],[9,5,1,9],[2,7,6,2]]
 * Output: 1
 * Explanation:
 * The following subgrid is a 3 x 3 magic square:
 * <p>
 * while this one is not:
 * <p>
 * In total, there is only one magic square inside the given grid.
 * <p>
 * Example 2:
 * <p>
 * Input: grid = [[8]]
 * Output: 0
 * <p>
 * Constraints:
 * <p>
 * - row == grid.length
 * <p>
 * - col == grid[i].length
 * <p>
 * - 1 <= row, col <= 10
 * <p>
 * - 0 <= grid[i][j] <= 15
 */
public class _840 {

    public int numMagicSquaresInside(int[][] grid) {
        int len = 3;
        int middleNum = (len * len + 1) >> 1;
        int sum = ((1 + len * len) * (len * len) >> 1) / len;
        int matchMask = (1 << (len * len + 1)) - 2;
        boolean match;
        int rowSum;
        int colSum;
        int diagonalSum1;
        int diagonalSum2;
        int bitMask;
        int ans = 0;
        for (int row = 0; row < grid.length - 2; row++) {
            for (int col = 0; col < grid[row].length - 2; col++) {

                if (grid[row + 1][col + 1] != middleNum) {
                    continue;
                }

                diagonalSum1 = 0;
                diagonalSum2 = 0;
                bitMask = 0;
                match = true;

                // start check
                for (int i = 0; i < len; i++) {

                    // check row sum
                    rowSum = 0;
                    for (int j = 0; j < len; j++) {
                        rowSum += grid[row + i][col + j];
                    }
                    if (rowSum != sum) {
                        match = false;
                        break;
                    }

                    // check col sum
                    colSum = 0;
                    for (int j = 0; j < len; j++) {
                        colSum += grid[row + j][col + i];
                    }
                    if (colSum != sum) {
                        match = false;
                        break;
                    }

                    diagonalSum1 += grid[row + i][col + i];
                    diagonalSum2 += grid[row + i][col + len - i - 1];
                }


                // check diagonals and num
                if (!match || diagonalSum1 != sum || diagonalSum2 != sum) {
                    continue;
                }

                for (int r = 0; r < len; r++) {
                    for (int l = 0; l < len; l++) {
                        bitMask |= (1 << grid[row + r][col + l]);
                    }
                }

                // 掩码 == bits 1~9 全置位（(1<<10)-2），保证9个数恰为互不相同的 1~9
                if (bitMask == matchMask) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}
