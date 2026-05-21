package letcode.normal.easy;

import letcode.utils.TestCaseInputUtils;
import letcode.utils.TestCaseOutputUtils;

/**
 * 给你一个下标从 0 开始、大小为 m x n 的整数矩阵 matrix ，新建一个下标从 0 开始、名为 answer 的矩阵。
 * 使 answer 与 matrix 相等，接着将其中每个值为 -1 的元素替换为所在列的 最大 元素。
 * 返回矩阵 answer 。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-07-05 09:15
 */
public class _3033 {

    public int[][] modifiedMatrix(int[][] matrix) {

        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        int[][] ans = new int[rowLen][colLen];

        int maxByCol;
        for (int colIdx = 0; colIdx < colLen; colIdx++) {
            maxByCol = Integer.MIN_VALUE;
            for (int[] row : matrix) {
                if (row[colIdx] > maxByCol) {
                    maxByCol = row[colIdx];
                }
            }
            for (int rowIdx = 0; rowIdx < rowLen; rowIdx++) {
                if (matrix[rowIdx][colIdx] == -1) {
                    ans[rowIdx][colIdx] = maxByCol;
                } else {
                    ans[rowIdx][colIdx] = matrix[rowIdx][colIdx];
                }
            }
        }

        return ans;
    }


}
