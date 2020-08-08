package letcode.difficult;

import java.util.Arrays;

/**
 * Leetcode
 * 编写一个程序，通过已填充的空格来解决数独问题。  一个数独的解法需遵循如下规则：  数字 1-9 在每一行只能出现一次。 数字 1-9 在每一列只能出现一次。 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。  来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/sudoku-solver 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : CaiYongcheng
 * @date : 2020-07-31 09:06
 **/
public class _37ThirtyserSeven {

    private boolean[][] mass = new boolean[9][9];
    private boolean[][] rows = new boolean[9][9];
    private boolean[][] cols = new boolean[9][9];
    private char[][] datas;

    private void init(){
        int data = 0;
        for (int row=0; row < 9; ++row) {
            for (int col=0; col < 9; ++col){
                if (datas[row][col] != '.') {
                    data = datas[row][col] - '0' - 1;
                    rows[row][data] = true;
                    cols[col][data] = true;
                    mass[row/3*3 + col/3][data] = true;
                }
            }
        }
    }

    private boolean dfs(int row, int col){
        // 找到下一个填充位置
        for (; row < 9; ++row) {
            for (col = 0; col < 9; ++col) {
                if (datas[row][col] == '.') {
                    break;
                }
            }
            if (col < 9) {
                break;
            }
        }
        // 填充完成表明填充成功
        if (row >= 9){
            return true;
        }
        int massIndex = row/3*3 + col/3;
        for (int index = 0; index < 9; ++index) {
            // 这个数字可以使用
            if ((!rows[row][index]) && (!cols[col][index])
                    && (!mass[massIndex][index])) {
                rows[row][index] = true;
                cols[col][index] = true;
                mass[massIndex][index] = true;
                datas[row][col] = (char) (index + '1');
                if (dfs(row, col)) {
                    return true;
                }
                rows[row][index] = false;
                cols[col][index] = false;
                mass[massIndex][index] = false;
                datas[row][col] = '.';
            }
        }
        return false;
    }


    /**
     *
     *     {
     *     {'5','3','.','.','7','.','.','.','.'},
     *     {'6','.','.','1','9','5','.','.','.'},
     *     {'.','9','8','.','.','.','.','6','.'},
     *     {'8','.','.','.','6','.','.','.','3'},
     *     {'4','.','.','8','.','3','.','.','1'},
     *     {'7','.','.','.','2','.','.','.','6'},
     *     {'.','6','.','.','.','.','2','8','.'},
     *     {'.','.','.','4','1','9','.','.','5'},
     *     {'.','.','.','.','8','.','.','7','9'}
     *     }
     *     {
     *       {'5','3','4','6','7','8','9','1','2'},
     *       {'6','7','2','1','9','5','3','4','8'},
     *       {'1','9','8','3','4','2','5','6','7'},
     *       {'8','5','9','7','6','1','4','2','3'},
     *       {'4','2','6','8','5','3','7','9','1'},
     *       {'7','1','3','9','2','4','8','5','6'},
     *       {'9','6','1','5','3','7','2','8','4'},
     *       {'2','8','7','4','1','9','6','3','5'},
     *       {'3','4','5','2','8','6','1','7','9'}
     *      }
     */
    public void solveSudoku(char[][] board) {
        datas = board;
        init();
        dfs(0, 0);
    }

    public static void main(String[] args) {
        char[][] board =  {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        new _37ThirtyserSeven().solveSudoku(board);
        for (char[] chars : board) {
            System.out.println(Arrays.toString(chars));
        }
    }
}
