package letcode.normal.difficult;

import letcode.utils.TestCaseInputUtils;

import java.util.*;

/**
 * 给你一个下标从 1 开始、大小为 m x n 的整数矩阵 mat，你可以选择任一单元格作为 起始单元格 。
 * 从起始单元格出发，你可以移动到 同一行或同一列 中的任何其他单元格，但前提是目标单元格的值 严格大于 当前单元格的值。
 * 你可以多次重复这一过程，从一个单元格移动到另一个单元格，直到无法再进行任何移动。
 * 请你找出从某个单元开始访问矩阵所能访问的 单元格的最大数量 。
 * 返回一个表示可访问单元格最大数量的整数。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-06-19 09:47
 */
public class _2713 {

    public int maxIncreasingCells(int[][] mat) {
        /*
        排序+动态规划
         */

        int rowSize = mat.length;
        int colSize = mat[0].length;

        int[][][] rowDp = new int[rowSize][2][2];
        int[][][] colDp = new int[colSize][2][2];
        initDp(rowDp);
        initDp(colDp);


        Integer[] idxArr = new Integer[rowSize * colSize];
        initAndSort(mat, idxArr, colSize);

        int ans = 1;
        int row;
        int col;
        int cur;
        for (int idx : idxArr) {
            row = idx / colSize;
            col = idx % colSize;
            cur = Math.max(getValue(mat, rowDp[row], row, col), getValue(mat, colDp[col], row, col));
            ++cur;
            update(mat, rowDp[row], row, col, cur);
            update(mat, colDp[col], row, col, cur);
            ans = Math.max(ans, cur);
        }
        return ans;
    }

    private static int getValue(int[][] mat, int[][] dp, int row, int col) {
        if (dp[0][1] > mat[row][col]) {
            return dp[0][0];
        }
        return dp[1][0];
    }

    private static void update(int[][] mat, int[][] dp, int row, int col, int value) {
        if (mat[row][col] == dp[0][1]) {
            dp[0][0] = Math.max(value, dp[0][0]);
        } else {
            dp[1][0] = dp[0][0];
            dp[1][1] = dp[0][1];
            dp[0][0] = value;
            dp[0][1] = mat[row][col];
        }
    }

    private static void initAndSort(int[][] mat, Integer[] idxArr, int colSize) {
        int idx = 0;
        for (int[] rowData : mat) {
            for (int ignored : rowData) {
                idxArr[idx] = idx;
                ++idx;
            }
        }
        Arrays.sort(idxArr, (a, b) -> mat[b / colSize][b % colSize] - mat[a / colSize][a % colSize]);
    }

    private static void initDp(int[][][] dp) {
        for (int[][] item : dp) {
            item[0][0] = 0;
            item[0][1] = Integer.MAX_VALUE;
            item[1][0] = 0;
            item[1][1] = Integer.MAX_VALUE;
        }
    }

    /**
     * 输入：mat = [[1,1],[1,1]]
     * 输出：1
     * 解释：由于目标单元格必须严格大于当前单元格，在本示例中只能访问 1 个单元格。
     *
     * 输入：mat = [[3,1,6],[-9,5,7]]
     * 输出：4
     * 解释：上图展示了从第 2 行、第 1 列的单元格开始，可以访问 4 个单元格。可以证明，无论从哪个单元格开始，最多只能访问 4 个单元格，因此答案是 4 。
     *
     * [[5,2,9],[-6,2,-5],[-1,0,-5]]
     * @param args
     */
    public static void main(String[] args) {
        /*
        5,2,9
        -6,2,-5
        -1,0,-5
         */
        System.out.println(new _2713().maxIncreasingCells(
                TestCaseInputUtils.get2DimensionIntArr("[[5,2,9],[-6,2,-5],[-1,0,-5]]")
        ));
    }


}
