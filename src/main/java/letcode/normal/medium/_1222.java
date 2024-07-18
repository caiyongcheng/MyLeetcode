/*
 * 版权所有（c）<2021><蔡永程>
 *
 * 反996许可证版本1.0
 *
 * 在符合下列条件的情况下，特此免费向任何得到本授权作品的副本（包括源代码、文件和/或相关内容，以
 * 下统称为“授权作品”）的个人和法人实体授权：被授权个人或法人实体有权以任何目的处置授权作品，包括
 * 但不限于使用、复制，修改，衍生利用、散布，发布和再许可：
 *
 * 1. 个人或法人实体必须在许可作品的每个再散布或衍生副本上包含以上版权声明和本许可证，不得自行修
 * 改。
 * 2. 个人或法人实体必须严格遵守与个人实际所在地或个人出生地或归化地、或法人实体注册地或经营地（
 * 以较严格者为准）的司法管辖区所有适用的与劳动和就业相关法律、法规、规则和标准。如果该司法管辖区
 * 没有此类法律、法规、规章和标准或其法律、法规、规章和标准不可执行，则个人或法人实体必须遵守国际
 * 劳工标准的核心公约。
 * 3. 个人或法人不得以任何方式诱导、暗示或强迫其全职或兼职员工或其独立承包人以口头或书面形式同意
 * 直接或间接限制、削弱或放弃其所拥有的，受相关与劳动和就业有关的法律、法规、规则和标准保护的权利
 * 或补救措施，无论该等书面或口头协议是否被该司法管辖区的法律所承认，该等个人或法人实体也不得以任
 * 何方法限制其雇员或独立承包人向版权持有人或监督许可证合规情况的有关当局报告或投诉上述违反许可证
 * 的行为的权利。
 *
 * 该授权作品是"按原样"提供，不做任何明示或暗示的保证，包括但不限于对适销性、特定用途适用性和非侵
 * 权性的保证。在任何情况下，无论是在合同诉讼、侵权诉讼或其他诉讼中，版权持有人均不承担因本软件或
 * 本软件的使用或其他交易而产生、引起或与之相关的任何索赔、损害或其他责任。
 */

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


    /**
     * Input: queens = {{0,1},{1,0},{4,0},{0,4},{3,3},{2,4}}, king = {0,0}
     * Output: {{0,1},{1,0},{3,3}}
     * Explanation: The diagram above shows the three queens that can directly attack the king and the three queens that cannot attack the king (i.e., marked with red dashes).
     * <p>
     * Input: queens = {{0,0},{1,1},{2,2},{3,4},{3,5},{4,4},{4,5}}, king = {3,3}
     * Output: {{2,2},{3,4},{4,4}}
     * Explanation: The diagram above shows the three queens that can directly attack the king and the three queens that cannot attack the king (i.e., marked with red dashes).
     * <p>
     * {{5,6},{7,7},{2,1},{0,7},{1,6},{5,1},{3,7},{0,3},{4,0},{1,2},{6,3},{5,0},{0,4},{2,2},{1,1},{6,4},{5,4},{0,0},{2,6},{4,5},{5,2},{1,4},{7,5},{2,3},{0,5},{4,2},{1,0},{2,7},{0,1},{4,6},{6,1},{0,6},{4,3},{1,7}}
     * {3,4}
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(TestCaseOutputUtils.formatNestList(new _1222().queensAttackTheKing(
                new int[][]{{5, 6}, {7, 7}, {2, 1}, {0, 7}, {1, 6}, {5, 1}, {3, 7}, {0, 3}, {4, 0}, {1, 2}, {6, 3}, {5, 0}, {0, 4}, {2, 2}, {1, 1}, {6, 4}, {5, 4}, {0, 0}, {2, 6}, {4, 5}, {5, 2}, {1, 4}, {7, 5}, {2, 3}, {0, 5}, {4, 2}, {1, 0}, {2, 7}, {0, 1}, {4, 6}, {6, 1}, {0, 6}, {4, 3}, {1, 7}},
                new int[]{3, 4}
        )));
    }
}
