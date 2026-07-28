package letcode.normal.easy;

import datastructure.utils.FormatPrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个 m * n 的矩阵，矩阵中的数字 各不相同 。请你按 任意 顺序返回矩阵中的所有幸运数。
 * 幸运数是指矩阵中满足同时下列两个条件的元素：  在同一行的所有元素中最小 在同一列的所有元素中最大
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/lucky-numbers-in-a-matrix 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-02-15 09:01
 **/
public class _1380 {


    public List<Integer> luckyNumbers(int[][] matrix) {
        /*
         * 设一个符合条件的数是[ri, cj]，那么对于[ri, *] 都有 [ri, a] > [ri, cj] > [b, cj], a != cj, b != ri,
         * 假设存在其他的幸运数[rp, cq], 则有 [rp, cq] < [rp, cj] < [ri, cj], [rp, cq] > [ri, cp] > [ri, cj], 矛盾
         * 故只能存在对多一个幸运数
         */
        int[] row = new int[matrix.length];
        int[] col = new int[matrix[0].length];
        Arrays.fill(row, 1000000);
        ArrayList<Integer> ans = new ArrayList<>();
        for (int ri = 0; ri < matrix.length; ri++) {
            for (int ci = 0; ci < matrix[ri].length; ci++) {
                if (matrix[ri][ci] < row[ri]) {
                    row[ri] = matrix[ri][ci];
                }
                if (matrix[ri][ci] > col[ci]) {
                    col[ci] = matrix[ri][ci];
                }
            }
        }
        for (int r : row) {
            for (int c : col) {
                if (r == c) {
                    ans.add(r);
                }
            }
        }
        return ans;
    }


}
