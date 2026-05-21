package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * There is a 1-indexed 8 x 8 chessboard containing 3 pieces.  You are given 6 integers a, b, c, d, e,
 * and f where:  (a, b) denotes the position of the white rook. (c, d) denotes the position of the white bishop. (e, f)
 * denotes the position of the black queen. Given that you can only move the white pieces, return
 * the minimum number of moves required to capture the black queen.  Note that:  Rooks can move any
 * number of squares either vertically or horizontally, but cannot jump over other pieces. Bishops
 * can move any number of squares diagonally, but cannot jump over other pieces. A rook or a bishop can
 * capture the queen if it is located in a square that they can move to. The queen does not move.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-12-05 09:10
 */
public class _3001 {

    public int minMovesToCaptureTheQueen(int a, int b, int c, int d, int e, int f) {
        /*
        车 如果和皇后在同一行或同一列 且路径上不存在象，那么只需要1步即可
        车 如果和皇后在同一行或同一列 且存在象，那么需要2步
        车 如果和皇不在同一行和同一列 那么需要2步
        象 如果和皇后在同一对角线 且路径上不存在车 那么需要1步
        象 如果和皇后在同一对角线 且路径上存在车 那么需要2步
        象 如果和皇后在同一颜色的格子，且象和皇后都未被车堵住，那么需要2步
        象 如果和皇后在同一颜色的格子，且象或皇后都被车堵住，那么需要3步
        象 如果和皇后不在同一颜色的格子，那么永远不可抵达
         */

        if (a == e && (c != a || (d < Math.min(b, f) || d > Math.max(b, f)))) {
            return 1;
        }
        if (b == f && (d != b || (c < Math.min(a, e) || c > Math.max(a, e)))) {
            return 1;
        }
        boolean notInLine = a < Math.min(c, e) || a > Math.max(c, e);
        // 这一步可以改为使用斜率判断是否在同一直线上
        if ((c + d) == (e + f) && ((c + d) != (a + b) || notInLine)) {
            return 1;
        }
        if ((c - d) == (e - f) && ((c - d) != (a - b) || notInLine)) {
            return 1;
        }
        return 2;
    }

}
