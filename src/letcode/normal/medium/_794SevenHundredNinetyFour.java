package normal.medium;

/**
 * @program: Leetcode
 * @description: 用字符串数组作为井字游戏的游戏板 board。当且仅当在井字游戏过程中，玩家有可能将字符放置成游戏板所显示的状态时，才返回 true。  该游戏板是一个 3 x 3 数组，由字符 " "，
 * "X" 和 "O" 组成。字符 " " 代表一个空位。
 * 以下是井字游戏的规则：  玩家轮流将字符放入空位（" "）中。
 * 第一个玩家总是放字符 “X”，且第二个玩家总是放字符 “O”。 “X” 和 “O” 只允许放置在空位中，不允许对已放有字符的位置进行填充。
 * 当有 3 个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。
 * 当所有位置非空时，也算为游戏结束。
 * 如果游戏结束，玩家不允许再放置字符
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-tic-tac-toe-state 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2021-01-21 10:51
 */
public class _794SevenHundredNinetyFour {


    public boolean validTicTacToe(String[] board) {

        int isfunishX = 0;
        int isfunishO = 0;
        int xAcount = 0;
        int oAcount = 0;
        

        final char[][] boards = new char[3][3];
        for (int i = 0; i < board.length; i++) {
            boards[i] = board[i].toCharArray();
        }

        if (boards[0][0] == boards[1][1] && boards[1][1] == boards[2][2] && boards[2][2] != ' ') {
            if (boards[0][0] == 'X') {
                ++isfunishX;
            } else {
                ++isfunishO;
            }
        }
        if (boards[2][0] == boards[1][1] && boards[1][1] == boards[0][2] && boards[1][1] != ' ') {
            if (boards[1][1] == 'X') {
                ++isfunishX;
            } else {
                ++isfunishO;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (boards[i][0] == boards[i][1] && boards[i][1] == boards[i][2] && boards[i][2] != ' ') {
                if (boards[i][0] == 'X') {
                    ++isfunishX;
                } else {
                    ++isfunishO;
                }
            }
            if (boards[0][i] == boards[1][i] && boards[1][i] == boards[2][i] && boards[2][i] != ' ') {
                if (boards[0][i] == 'X') {
                    ++isfunishX;
                } else {
                    ++isfunishO;
                }
            }
            if (isfunishX != 0 && isfunishO != 0) {
                return false;
            }
            for (int j = i+1; j<3; ++j) {
                if (boards[i][j] == 'X') {
                    ++xAcount;
                } else if (boards[i][j] == 'O'){
                    ++oAcount;
                }
                if (boards[j][i] == 'X') {
                    ++xAcount;
                } else if (boards[j][i] == 'O'){
                    ++oAcount;
                }
            }
            if (boards[i][i] == 'X') {
                ++xAcount;
            } else if (boards[i][i] == 'O'){
                ++oAcount;
            }
        }
        if (isfunishX > 0) {
            return xAcount -1 == oAcount;
        }
        if (isfunishO > 0) {
            return xAcount == oAcount;
        }
        return xAcount == oAcount || xAcount - 1 == oAcount;
    }

    /**
     * 示例 1:
     * 输入: board = ["O  ", "   ", "   "]
     * 输出: false
     * 解释: 第一个玩家总是放置“X”。
     *
     * 示例 2:
     * 输入: board = ["XOX", " X ", "   "]
     * 输出: false
     * 解释: 玩家应该是轮流放置的。
     *
     * 示例 3:
     * 输入: board = ["XXX", "   ", "OOO"]
     * 输出: false
     *
     * 示例 4:
     * 输入: board = ["XOX", "O O", "XOX"]
     * 输出: true
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/valid-tic-tac-toe-state
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        final String[] strings = new String[]{"OXX","XOX","OXO"};
        System.out.println(new _794SevenHundredNinetyFour().validTicTacToe(strings));
    }

}