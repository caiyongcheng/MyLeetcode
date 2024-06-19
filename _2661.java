package letcode.normal.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/12/1 17:06
 * description 给你一个下标从 0 开始的整数数组 arr 和一个 m x n 的整数 矩阵 mat 。arr 和 mat 都包含范围 [1，m * n] 内的 所有 整数。
 * 从下标 0 开始遍历 arr 中的每个下标 i ，并将包含整数 arr[i] 的 mat 单元格涂色。
 * 请你找出 arr 中在 mat 的某一行或某一列上都被涂色且下标最小的元素，并返回其下标 i 。
 */
public class _2661 {

    static int[][] matItem2RowCol = new int[100002][2];

    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int colItemLen = mat.length;
        int rowItemLen = mat[0].length;
        int[] rowArr = new int[colItemLen];
        int[] colArr = new int[mat[0].length];
        for (int row = 0; row < colItemLen; row++) {
            for (int col = 0; col < mat[row].length; col++) {
                matItem2RowCol[mat[row][col]][0] = row;
                matItem2RowCol[mat[row][col]][1] = col;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (++rowArr[matItem2RowCol[arr[i]][0]] == rowItemLen || ++colArr[matItem2RowCol[arr[i]][1]] == colItemLen) {
                return i;
            }
        }
        return 0;
    }


    /**
     * 输入：arr = {1,3,4,2}, mat = {{1,4},{2,3}}
     * 输出：2
     * 解释：遍历如上图所示，arr{2} 在矩阵中的第一行或第二列上都被涂色。
     *
     * 输入：arr = {2,8,7,4,1,3,5,6,9}, mat = {{3,2,5},{1,4,6},{8,7,9}}
     * 输出：3
     * 解释：遍历如上图所示，arr[3] 在矩阵中的第二列上都被涂色。
     * 
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2661().firstCompleteIndex(
                new int[]{1,3,4,2},
                new int[][]{{1,4},{2,3}}
        ));
    }


}
