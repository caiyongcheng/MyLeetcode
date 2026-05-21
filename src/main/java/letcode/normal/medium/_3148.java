package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.Arrays;
import java.util.List;

/**
 * 给你一个由 正整数 组成、大小为 m x n 的矩阵 grid。你可以从矩阵中的任一单元格移动到另一个位于正下方或正右侧的任意单元格（不必相邻）。
 * 从值为 c1 的单元格移动到值为 c2 的单元格的得分为 c2 - c1 。  你可以从 任一 单元格开始，并且必须至少移动一次。
 * 返回你能得到的 最大 总得分。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-08-16 09:19
 */
public class _3148 {

    public int maxScore(List<List<Integer>> grid) {
        // 从 a 到 c， 如果经过b，那么分数为 b - a + (c - b) = c - a, 说明只和起始点有关
        int ans = Integer.MIN_VALUE;
        int rowSize = grid.size();
        int colSize = grid.get(0).size();
        int curMax;
        int[] colMaxNums = new int[colSize + 1];
        Arrays.fill(colMaxNums, Integer.MIN_VALUE >> 1);
        for (int row = rowSize - 1; row >= 0; row--) {
            List<Integer> rowData = grid.get(row);
            for (int col = colSize - 1; col >= 0; col--) {
                curMax = Math.max(colMaxNums[col], colMaxNums[col + 1]);
                ans = Integer.max(ans, curMax - rowData.get(col));
                colMaxNums[col] = Integer.max(rowData.get(col), curMax);
            }
        }
        return ans;
    }


}
