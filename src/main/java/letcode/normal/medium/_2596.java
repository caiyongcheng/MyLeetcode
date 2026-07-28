package letcode.normal.medium;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/9/13 9:23
 * description 骑士在一张 n x n 的棋盘上巡视。在有效的巡视方案中，骑士会从棋盘的 左上角 出发，并且访问棋盘上的每个格子 恰好一次 。
 * 给你一个 n x n 的整数矩阵 grid ，由范围 [0, n * n - 1] 内的不同整数组成，
 * 其中 grid[row][col] 表示单元格 (row, col) 是骑士访问的第 grid[row][col] 个单元格。骑士的行动是从下标 0 开始的。
 * 如果 grid 表示了骑士的有效巡视方案，返回 true；否则返回 false。
 * 注意，骑士行动时可以垂直移动两个格子且水平移动一个格子，或水平移动两个格子且垂直移动一个格子。
 * 下图展示了骑士从某个格子出发可能的八种行动路线。
 */
public class _2596 {

    private final int[][] move = new int[][]{
            {-2, 1},
            {-1, 2},
            {1, 2},
            {2, 1},
            {2, -1},
            {1, -2},
            {-1, -2},
            {-2, -1}
    };

    private int cnt;

    public boolean checkValidGrid(int[][] grid) {
        if (grid[0][0] != 0) {
            return false;
        }
        int len = grid.length;
        int nextRow = 0;
        int nextCol = 0;
        int limit = len * len - 1;
        boolean arrive;
        for (int row = 0; row < len; row++) {
            for (int col = 0; col < len; col++) {
                arrive = false;
                for (int[] step : move) {
                    nextRow = row + step[0];
                    nextCol = col + step[1];
                    if (nextRow > -1 && nextRow < len
                            && nextCol > -1 && nextCol < len
                            && (grid[nextRow][nextCol] == grid[row][col] + 1 || grid[row][col] == limit)) {
                        arrive = true;
                        break;
                    }
                }
                if (!arrive) {
                    return false;
                }
            }
        }
        return true;
    }


}
