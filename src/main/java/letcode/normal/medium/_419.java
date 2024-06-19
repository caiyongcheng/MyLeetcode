package letcode.normal.medium;

import letcode.utils.TestCaseUtils;

/**
 * Given an m x n matrix board where each cell is a battleship 'X' or empty '.', return the number of the battleships on board.
 * Battleships can only be placed horizontally or vertically on board. In other words,
 * they can only be made of the shape 1 x k (1 row, k columns) or k x 1 (k rows, 1 column), where k can be of any size.
 * At least one horizontal or vertical cell separates between two battleships (i.e., there are no adjacent battleships).
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-06-11 09:06
 */
public class _419 {


    public int countBattleships(char[][] board) {
        int ans = 0;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (
                        (board[row][col] == 'X')
                        && (row - 1 < 0 || board[row - 1][col] != 'X')
                        && (col - 1 < 0 || board[row][col - 1] != 'X')
                ) {
                    ++ans;
                }
            }
        }
        return ans;
    }


    /**
     * Example 1:
     *
     *
     * Input: board = [["X",".",".","X"],[".",".",".","X"],[".",".",".","X"]]
     * Output: 2
     * Example 2:
     *
     * Input: board = [["."]]
     * Output: 0
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _419().countBattleships(
                TestCaseUtils.get2DArr(
                        "[[\".\"]]",
                        ",",
                        TestCaseUtils::getCharArr,
                        new char[0][0]
                )
        ));
    }

}
