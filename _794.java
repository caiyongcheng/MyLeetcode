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
public class _794 {


    /**
     * 示例 1:
     * 输入: board = ["O  ", "   ", "   "]
     * 输出: false
     * 解释: 第一个玩家总是放置“X”。
     * <p>
     * 示例 2:
     * 输入: board = ["XOX", " X ", "   "]
     * 输出: false
     * 解释: 玩家应该是轮流放置的。
     * <p>
     * 示例 3:
     * 输入: board = ["X XX", "   ", "O OO"]
     * 输出: false
     * <p>
     * 示例 4:
     * 输入: board = ["XOX", "O O", "XOX"]
     * 输出: true
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/valid-tic-tac-toe-state
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        final String[] strings = new String[]{"OXX", "XOX", "OXO"};
        System.out.println(new _794().validTicTacToe(strings));
    }

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
            for (int j = i + 1; j < 3; ++j) {
                if (boards[i][j] == 'X') {
                    ++xAcount;
                } else if (boards[i][j] == 'O') {
                    ++oAcount;
                }
                if (boards[j][i] == 'X') {
                    ++xAcount;
                } else if (boards[j][i] == 'O') {
                    ++oAcount;
                }
            }
            if (boards[i][i] == 'X') {
                ++xAcount;
            } else if (boards[i][i] == 'O') {
                ++oAcount;
            }
        }
        if (isfunishX > 0) {
            return xAcount - 1 == oAcount;
        }
        if (isfunishO > 0) {
            return xAcount == oAcount;
        }
        return xAcount == oAcount || xAcount - 1 == oAcount;
    }

}
