package letcode.normal.medium;

/**
 * 在一个n x n的国际象棋棋盘上，一个骑士从单元格 (row, column)开始，并尝试进行 k 次移动。行和列是 从 0 开始 的，
 * 所以左上单元格是 (0,0) ，右下单元格是 (n - 1, n - 1) 。
 * 象棋骑士有8种可能的走法，如下图所示。每次移动在基本方向上是两个单元格，然后在正交方向上是一个单元格。
 * 每次骑士要移动时，它都会随机从8种可能的移动中选择一种(即使棋子会离开棋盘)，然后移动到那里。
 * 骑士继续移动，直到它走了 k 步或离开了棋盘。  返回 骑士在棋盘停止移动后仍留在棋盘上的概率 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/knight-probability-in-chessboard 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-02-17 09:06
 **/
public class _688 {


    private final int[][] moved = new int[][]{{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};

    public double knightProbability(int n, int k, int row, int column) {
        if (k == 0) {
            return 1;
        }
        double[][][] probabilities = new double[k][n][n];
        int nextR;
        int nextC;
        for (int step = 0; step < k - 1; step++) {
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    for (int[] move : moved) {
                        nextR = r + move[0];
                        nextC = c + move[1];
                        if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= n) {
                            continue;
                        }
                        if (step == 0) {
                            probabilities[step + 1][nextR][nextC] += 0.125;
                        } else {
                            probabilities[step + 1][nextR][nextC] += 0.125 * probabilities[step][r][c];
                        }
                    }
                }
            }
        }
        double probability = 0;
        int lastR;
        int lastC;
        for (int[] move : moved) {
            lastR = row - move[0];
            lastC = column - move[1];
            if (lastR < 0 || lastR >= n || lastC < 0 || lastC >= n) {
                continue;
            }
            probability += (k - 1 == 0 ? 1 : probabilities[k - 1][lastR][lastC]) * 0.125;
        }
        return probability;
    }


}
