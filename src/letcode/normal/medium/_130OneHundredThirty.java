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
 * @date 2021-04-19 15:49
 **/
public class _130OneHundredThirty {


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
        new _130OneHundredThirty().solve(ints);
        for (char[] anInt : ints) {
            System.out.println(Arrays.toString(anInt));
        }
    }

}
