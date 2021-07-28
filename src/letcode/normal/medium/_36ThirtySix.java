package letcode.medium;

/**
 * Leetcode
 * 判断一个9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 * 数字1-9在每一行只能出现一次。 数字1-9在每一列只能出现一次。 数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/valid-sudoku 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : CaiYongcheng
 * @date : 2020-07-05 15:49
 **/
public class _36ThirtySix {

    static boolean[] used = new boolean[10];

    /**
     * 示例 1:
     * 输入:
     * [
     * ["5","3",".",".","7",".",".",".","."],
     * ["6",".",".","1","9","5",".",".","."],
     * [".","9","8",".",".",".",".","6","."],
     * ["8",".",".",".","6",".",".",".","3"],
     * ["4",".",".","8",".","3",".",".","1"],
     * ["7",".",".",".","2",".",".",".","6"],
     * [".","6",".",".",".",".","2","8","."],
     * [".",".",".","4","1","9",".",".","5"],
     * [".",".",".",".","8",".",".","7","9"]
     * ]
     * 输出: true
     * <p>
     * 示例2:
     * 输入:
     * [
     *  ["8","3",".",".","7",".",".",".","."],
     *  ["6",".",".","1","9","5",".",".","."],
     *  [".","9","8",".",".",".",".","6","."],
     *  ["8",".",".",".","6",".",".",".","3"],
     *  ["4",".",".","8",".","3",".",".","1"],
     *  ["7",".",".",".","2",".",".",".","6"],
     *  [".","6",".",".",".",".","2","8","."],
     *  [".",".",".","4","1","9",".",".","5"],
     *  [".",".",".",".","8",".",".","7","9"]
     * ]
     * 输出: false
     * 解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
     * 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
     *
     * @param board
     * @return
     */
    public static boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; ++i) {
            if (checkRow(board[i]) == false
                    || checkCol(board, i) == false
                    || checkPartOfBoard(board, i) == false) {
                return false;
            }
        }
        return true;
    }

    public static void recoverUsed() {
        for (int i = 1; i < 10; ++i) {
            used[i] = false;
        }
    }

    public static boolean checkRow(char[] board) {
        recoverUsed();
        for (char c : board) {
            if (c == '.') continue;
            if (used[c - '0']) return false;
            used[c - '0'] = true;
        }
        return true;
    }

    public static boolean checkCol(char[][] board, int col) {
        recoverUsed();
        for (int i = 0; i < 9; ++i) {
            if (board[i][col] == '.') continue;
            if (used[board[i][col] - '0']) return false;
            used[board[i][col] - '0'] = true;
        }
        return true;
    }

    public static boolean checkPartOfBoard(char[][] board, int index) {
        recoverUsed();
        int startRow = index / 3 * 3;
        int startCol = index % 3 * 3;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (board[startRow + i][startCol + j] == '.') continue;
                if (used[board[startRow + i][startCol + j] - '0']) return false;
                used[board[startRow + i][startCol + j] - '0'] = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        char[][] board1 = {
                {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println(isValidSudoku(board));
        System.out.println(isValidSudoku(board1));
    }
}
