package letcode.normal.medium;

import letcode.utils.TestCaseUtils;

/**
 * 给你一个下标从 0 开始的 8 x 8 网格 board ，其中 board[r][c] 表示游戏棋盘上的格子 (r, c) 。
 * 棋盘上空格用 '.' 表示，白色格子用 'W' 表示，黑色格子用 'B' 表示。  游戏中每次操作步骤为：选择一个空格子，
 * 将它变成你正在执行的颜色（要么白色，要么黑色）。但是，合法 操作必须满足：涂色后这个格子是 好线段的一个端点
 * （好线段可以是水平的，竖直的或者是对角线）。
 * 好线段 指的是一个包含 三个或者更多格子（包含端点格子）的线段，线段两个端点格子为 同一种颜色 ，
 * 且中间剩余格子的颜色都为 另一种颜色 （线段上不能有任何空格子）。你可以在下图找到好线段的例子：
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-07-08 10:08
 */
public class _1958 {

    public boolean checkMove(char[][] board, int rMove, int cMove, char color) {
        board[rMove][cMove] = color;
        return match(board, rMove, cMove, 0, -1)
                || match(board, rMove, cMove, 0, 1)
                || match(board, rMove, cMove, 1, 0)
                || match(board, rMove, cMove, -1, 0)
                || match(board, rMove, cMove, -1, -1)
                || match(board, rMove, cMove, -1, 1)
                || match(board, rMove, cMove, 1, -1)
                || match(board, rMove, cMove, 1, 1);
    }


    public boolean match(char[][] board, int rMove, int cMove, int rowStep, int colStep) {
        char endColor = board[rMove][cMove];
        int len = 0;
        while (true) {
            rMove += rowStep;
            cMove += colStep;
            if (rMove < 0 || rMove >= board.length || cMove < 0 || cMove >= board[0].length) {
                return false;
            }
            if (board[rMove][cMove] == '.') {
                return false;
            }
            if (board[rMove][cMove] == endColor) {
                return len >= 1;
            } else {
                ++len;
            }
        }
    }

    /**
     * 输入：board = {{'.','.','.','B','.','.','.','.'},{'.','.','.','W','.','.','.','.'},{'.','.','.','W','.','.','.','.'},{'.','.','.','W','.','.','.','.'},{'W','B','B','.','W','W','W','B'},{'.','.','.','B','.','.','.','.'},{'.','.','.','B','.','.','.','.'},{'.','.','.','W','.','.','.','.'}}, 
     * rMove = 4, cMove = 3, color = 'B'
     * 输出：true
     * 解释：'.'，'W' 和 'B' 分别用颜色蓝色，白色和黑色表示。格子 (rMove, cMove) 用 'X' 标记。
     * 以选中格子为端点的两个好线段在上图中用红色矩形标注出来了。
     * 
     * 输入：board = {{'.','.','.','.','.','.','.','.'},{'.','B','.','.','W','.','.','.'},{'.','.','W','.','.','.','.','.'},{'.','.','.','W','B','.','.','.'},{'.','.','.','.','.','.','.','.'},{'.','.','.','.','B','W','.','.'},{'.','.','.','.','.','.','W','.'},{'.','.','.','.','.','.','.','B'}},
     * rMove = 4, cMove = 4, color = 'W'
     * 输出：false
     * 解释：虽然选中格子涂色后，棋盘上产生了好线段，但选中格子是作为中间格子，没有产生以选中格子为端点的好线段。
     * @param args
     */
    public static void main(String[] args) {

        System.out.println(new _1958().checkMove(
                TestCaseUtils.get2DCharArr(
                       "[[\"B\",\"W\",\"B\",\"B\",\"W\",\"W\",\".\",\"W\"],[\"W\",\"W\",\"B\",\"W\",\"B\",\"B\",\"W\",\".\"],[\"B\",\"W\",\".\",\"W\",\".\",\".\",\"B\",\"B\"],[\".\",\"B\",\"W\",\".\",\"B\",\"W\",\"B\",\"W\"],[\"B\",\"B\",\"W\",\"B\",\".\",\"B\",\"W\",\"B\"],[\"B\",\"B\",\"B\",\"W\",\"B\",\"W\",\"W\",\"B\"],[\".\",\"W\",\".\",\"W\",\".\",\"W\",\"W\",\"W\"],[\".\",\"W\",\"W\",\"B\",\"B\",\"B\",\"B\",\".\"]]"
                ),
                7, 7, 'B'
        ));
    }

}
