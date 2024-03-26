package letcode.normal.difficult;

/**
 * 给你一个下标从 0 开始的 m x n 整数矩阵 grid 。你一开始的位置在 左上角 格子 (0, 0) 。  当你在格子 (i, j) 的时候，你可以移动到以下格子之一：  满足 j < k <= grid[i][j] + j 的格子 (i, k) （向右移动），或者 满足 i < k <= grid[i][j] + i 的格子 (k, j) （向下移动）。 请你返回到达 右下角 格子 (m - 1, n - 1) 需要经过的最少移动格子数，如果无法到达右下角格子，请你返回 -1 。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/3/22 10:23
 */
public class N_2617TwoThousandSixHundredSeventeen {

    public int minimumVisitedCells(int[][] grid) {
        /**
         * 遍历+减枝
         * 减枝策略：当前步数以及大于等于当前最优步数的话，不再继续遍历
         */
        return 0;
    }

}
