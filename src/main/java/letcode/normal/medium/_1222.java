package letcode.normal.medium;

import letcode.utils.TestCaseOutputUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/9/14 15:47
 * description On a 0-indexed 8 x 8 chessboard, there can be multiple black queens ad one white king.
 * You are given a 2D integer array queens where queens[i] = [xQueeni, yQueeni] represents the position of the ith
 * black queen on the chessboard. You are also given an integer array king of length 2
 * where king = [xKing, yKing] represents the position of the white king.  Return the coordinates
 * of the black queens that can directly attack the king. You may return the answer in any order.
 */
public class _1222 {

    public List<List<Integer>> queensAttackTheKing(int[][] queens, int[] king) {
        //模拟即可
        int[][] chessBoard = new int[8][8];
        for (int[] queen : queens) {
            chessBoard[queen[0]][queen[1]] = 1;
        }

        List<List<Integer>> ans = new ArrayList<>(8);
        int kingRow = king[0];
        int kingCol = king[1];

        //同行
        //左边最近
        for (int col = kingCol - 1; col > -1; col--) {
            if (chessBoard[kingRow][col] == 1) {
                ans.add(Arrays.asList(kingRow, col));
                break;
            }
        }
        //右边最近
        for (int col = kingCol + 1; col < 8; ++col) {
            if (chessBoard[kingRow][col] == 1) {
                ans.add(Arrays.asList(kingRow, col));
                break;
            }
        }

        //同列
        //上边最近
        for (int row = kingRow - 1; row > -1; row--) {
            if (chessBoard[row][kingCol] == 1) {
                ans.add(Arrays.asList(row, kingCol));
                break;
            }
        }
        //下边最近
        for (int row = kingRow + 1; row < 8; ++row) {
            if (chessBoard[row][kingCol] == 1) {
                ans.add(Arrays.asList(row, kingCol));
                break;
            }
        }

        //同对角线 左上到右下
        //左上最近
        int row = kingRow - 1;
        int col = kingCol - 1;
        while (row > -1 && col > -1) {
            if (chessBoard[row][col] == 1) {
                ans.add(Arrays.asList(row, col));
                break;
            }
            --row;
            --col;
        }
        //右下最近
        row = kingRow + 1;
        col = kingCol + 1;
        while (row < 8 && col < 8) {
            if (chessBoard[row][col] == 1) {
                ans.add(Arrays.asList(row, col));
                break;
            }
            ++row;
            ++col;
        }

        //同对角线 右上到左下
        //右上
        row = kingRow - 1;
        col = kingCol + 1;
        while (row > -1 && col < 8) {
            if (chessBoard[row][col] == 1) {
                ans.add(Arrays.asList(row, col));
                break;
            }
            --row;
            ++col;
        }
        //左下
        row = kingRow + 1;
        col = kingCol - 1;
        while (row < 8 && col > -1) {
            if (chessBoard[row][col] == 1) {
                ans.add(Arrays.asList(row, col));
                break;
            }
            ++row;
            --col;
        }

        return ans;

    }


}
