package letcode.normal.medium;

/**
 * @author Caiyongcheng
 * @description 给你一个 n x n 的 方形 整数数组matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
 * 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。
 * 在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。
 * 具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。
 * 来源：力扣（LeetCode） 链接：https://leetcode.cn/problems/minimum-falling-path-sum 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 * @since 2023/7/13 17:02
 */
public class _931 {

    public int minFallingPathSum(int[][] matrix) {
        /*
        经典动态规划
         */
        int min;
        for (int i = matrix.length - 2; i >= 0; i--) {
            for (int j = 0; j < matrix[i].length; j++) {
                min = matrix[i + 1][j];
                if (j - 1 > -1) {
                    min = Math.min(min, matrix[i + 1][j - 1]);
                }
                if (j + 1 < matrix[i].length) {
                    min = Math.min(min, matrix[i + 1][j + 1]);
                }
                matrix[i][j] += min;
            }
        }
        min = matrix[0][0];
        for (int i = 0; i < matrix[0].length; i++) {
            min = Math.min(min, matrix[0][i]);
        }
        return min;
    }

}
