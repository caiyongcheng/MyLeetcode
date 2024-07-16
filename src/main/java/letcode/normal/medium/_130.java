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

import datastructure.utils.FormatPrintUtils;

import java.util.Arrays;
import java.util.Stack;

/**
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的'O'都不会被填充为'X'。 
 * 任何不在边界上，或不与边界上的'O'相连的'O'最终都会被填充为'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/surrounded-regions 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-04-19 15:49
 **/
public class _130 {


    private boolean[] visited;
    private Stack<Integer> stack;
    private int rowLen;
    private int colLen;

    public void solve(char[][] board) {
        //以边界上的o作为起始点 进行遍历
        //遍历结束后 未访问到的 o 处理为x
        rowLen = board.length;
        colLen = board[0].length;
        visited = new boolean[rowLen * colLen];
        stack = new Stack<>();
        for (int i = 0; i < colLen; i++) {
            if (board[0][i]  == 'O' && !visited[i]) {
                stack.push(i);
                bfs(board);
            }
            if (board[rowLen-1][i]  == 'O' && !visited[(rowLen-1)*colLen + i]) {
                stack.push((rowLen-1)*colLen + i);
                bfs(board);
            }
        }
        for (int i = 0; i < rowLen; i++) {
            if (board[i][0]  == 'O' && !visited[i * colLen]) {
                stack.push(i * colLen);
                bfs(board);
            }
            if (board[i][colLen-1]  == 'O' && !visited[i * colLen + colLen - 1]) {
                stack.push(i * colLen + colLen - 1);
                bfs(board);
            }
        }
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (board[i][j] == 'O' && !visited[i*colLen + j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void bfs(char[][] board) {
        int index;
        int row;
        int col;
        while (!stack.empty()) {
            index = stack.pop();
            row = index / colLen;
            col = index % colLen;
            visited[index] = true;
            ++row;
            if (row < rowLen && board[row][col] == 'O' && !visited[row * colLen + col]) {
                stack.push(row * colLen + col);
            }
            row -= 2;
            if (row > -1 && board[row][col] == 'O' && !visited[row * colLen + col]) {
                stack.push(row * colLen + col);
            }
            ++row;
            ++col;
            if (col < colLen && board[row][col] == 'O' && !visited[row * colLen + col]) {
                stack.push(row * colLen + col);
            }
            col -= 2;
            if (col > -1 && board[row][col] == 'O' && !visited[row * colLen + col]) {
                stack.push(row * colLen + col);
            }
        }
    }

    /**
     * 输入：board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}}
     * 输出：{{'X','X','X','X'},{'X','X','X','X'},{'X','X','X','X'},{'X','O','X','X'}}

     
     * 示例 2：
     * 输入：board = {{'X'}}
     * 输出：{{'X'}}
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/surrounded-regions
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        char[][] ints = {{'X'}};
        new _130().solve(ints);
        for (char[] anInt : ints) {
            System.out.println(Arrays.toString(anInt));
        }
    }

}
