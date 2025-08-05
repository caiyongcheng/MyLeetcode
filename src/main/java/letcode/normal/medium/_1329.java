package letcode.normal.medium;

import letcode.utils.TestCaseInputUtils;
import letcode.utils.TestCaseOutputUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 矩阵对角线 是一条从矩阵最上面行或者最左侧列中的某个元素开始的对角线，沿右下方向一直到矩阵末尾的元素。
 * 例如，矩阵 mat 有 6 行 3 列，从 mat[2][0] 开始的 矩阵对角线 将会经过 mat[2][0]、mat[3][1] 和 mat[4][2] 。
 * 给你一个 m * n 的整数矩阵 mat ，请你将同一条 矩阵对角线 上的元素按升序排序后，返回排好序的矩阵。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/29 09:07
 */
public class _1329 {

    public int[][] diagonalSort(int[][] mat) {
        int len = mat[0].length;
        int row;
        int col;
        for (int i = 0; i < mat.length; i++) {
            row = i;
            col = 0;
            sort(mat, row, col, len);
        }
        for (int i = 0; i < len; i++) {
            row = 0;
            col = i;
            sort(mat, row, col, len);
        }
        return mat;
    }

    private static void sort(int[][] mat, int row, int col, int len) {
        int idx = 0;
        List<int[]> orginalList = new ArrayList<>();
        while (row < mat.length && col < len) {
            orginalList.add(new int[]{mat[row][col], row, col});
            ++row;
            ++col;
            ++idx;
        }
        List<int[]> sortList = orginalList.stream()
                .sorted(Comparator.comparing(item -> item[0]))
                .collect(Collectors.toList());
        int size = orginalList.size();
        int[] item;
        for (int i = 0; i < size; i++) {
            item = orginalList.get(i);
            mat[item[1]][item[2]] = sortList.get(i)[0];
        }
    }

    /**
     * 输入：mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
     * 输出：[[1,1,1,1],[1,2,2,2],[1,2,3,3]]
     * 示例 2：
     *
     * 输入：mat = [[11,25,66,1,69,7],[23,55,17,45,15,52],[75,31,36,44,58,8],[22,27,33,25,68,4],[84,28,14,11,5,50]]
     * 输出：[[5,17,4,1,52,7],[11,11,25,45,8,69],[14,23,25,44,58,15],[22,27,31,36,50,66],[84,28,75,33,55,68]]
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(
                TestCaseOutputUtils.format2DArray(
                        new _1329().diagonalSort(
                                TestCaseInputUtils.get2DimensionIntArr("[[11,25,66,1,69,7],[23,55,17,45,15,52],[75,31,36,44,58,8],[22,27,33,25,68,4],[84,28,14,11,5,50]]")
                        )
                )
        );
    }



}
