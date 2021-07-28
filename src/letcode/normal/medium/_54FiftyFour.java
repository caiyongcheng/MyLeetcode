package letcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Leetcode
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 *
 * @author : CaiYongcheng
 * @date : 2020-07-10 08:58
 **/
public class _54FiftyFour {

    /**
     * 示例1:
     * 输入:
     * [
     * [ 1, 2, 3 ],
     * [ 4, 5, 6 ],
     * [ 7, 8, 9 ]
     * ]
     * 输出: [1,2,3,6,9,8,7,4,5]
     * <p>
     * 示例2:
     * 输入:
     * [
     * [1, 2, 3, 4],
     * [5, 6, 7, 8],
     * [9,10,11,12]
     * ]
     * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
     *
     * @param matrix
     * @return
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        int row = 0;
        int col = 0;
        int rowSize = matrix.length;
        int colSize = matrix[0].length;
        int circleSize = Math.max((rowSize + 1) / 2, (colSize + 1) / 2);
        for (int i = 0; i < circleSize; ++i) {

        }

        return list;
    }

    public static void main(String[] args) {
        int[][] ints = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        int[][] ints1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {10, 11, 12}
        };
        /*
        1 1 1 1 1
        1 1 1 1 1
        1 1 1 1 1
        1 1 1 1 1
        1 1 1 1 1
        1 1 1 1 1
         */
        List<Integer> integers = spiralOrder(ints);
        System.out.println(Arrays.toString(integers.toArray()));
    }


}
