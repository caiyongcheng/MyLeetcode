package letcode.normal.easy;

import letcode.utils.TestCaseUtils;

/**
 * 给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。
 * 如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-07-12 09:14
 */
public class _766 {

    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int col = 0; col < matrix[0].length; col++) {
            int nextRow = 0;
            int nextCol = col;
            while (nextRow < matrix.length && nextCol < matrix[0].length) {
                if (matrix[nextRow][nextCol] != matrix[0][col]) {
                    return false;
                }
                nextRow++;
                nextCol++;
            }
        }
        for (int row = 1; row < matrix.length; row++) {
            int nextRow = row;
            int nextCol = 0;
            while (nextRow < matrix.length && nextCol < matrix[0].length) {
                if (matrix[nextRow][nextCol] != matrix[row][0]) {
                    return false;
                }
                nextRow++;
                nextCol++;
            }
        }
        return true;
    }

    /**
     * 输入：matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
     * 输出：true
     * 解释：
     * 在上述矩阵中, 其对角线为:
     * "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]"。
     * 各条对角线上的所有元素均相同, 因此答案是 True 。
     *
     * [[1,2],[2,2]]
     * false
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _766().isToeplitzMatrix(
                TestCaseUtils.get2DIntArr("[[1,2],[2,2]]")
        ));
    }

}
