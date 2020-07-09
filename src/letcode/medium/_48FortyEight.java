package letcode.medium;

import java.util.Arrays;

/**
 * Leetcode
 * 给定一个 n × n 的二维矩阵表示一个图像。  将图像顺时针旋转 90 度。
 * 说明：  你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/rotate-image
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : CaiYongcheng
 * @date : 2020-07-08 12:42
 **/
public class _48FortyEight {

    /**
     * 给定 matrix =
     * [
     *   [1,2,3],
     *   [4,5,6],
     *   [7,8,9]
     * ],
     * 原地旋转输入矩阵，使其变为:
     * [
     *   [7,4,1],
     *   [8,5,2],
     *   [9,6,3]
     * ]
     * @param matrix
     */
    public static void rotate(int[][] matrix) {
        int circleSize = (matrix.length + 1) / 2;
        int tmp = 0;
        for (int i=0; i<circleSize; ++i){
            for (int j = i; j < matrix.length - 1 - i; ++j){
                //上边元素
                tmp = matrix[i][j];
                //左边覆盖上边元素
                matrix[i][j] = matrix[matrix.length-j-1][i];
                //下边覆盖左边元素
                matrix[matrix.length-j-1][i] = matrix[matrix.length-1-i][matrix.length-1-j];
                //右边覆盖下边元素
                matrix[matrix.length-1-i][matrix.length-1-j] = matrix[j][matrix.length-1-i];
                //上边覆盖右边
                matrix[j][matrix.length-1-i] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        rotate(matrix);
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
