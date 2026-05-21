package letcode.normal.medium;

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


}
