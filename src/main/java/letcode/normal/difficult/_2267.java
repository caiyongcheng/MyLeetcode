package letcode.normal.difficult;

import java.util.Arrays;

/**
 * 2267. Check if There Is a Valid Parentheses String Path
 * Difficulty: Hard
 * Link: https://leetcode.cn/problems/check-if-there-is-a-valid-parentheses-string-path/
 * <p>
 * A parentheses string is a non-empty string consisting only of '(' and ')' . It is valid if any of
 * the following conditions is true :
 * <p>
 * - It is () .
 * <p>
 * - It can be written as AB ( A concatenated with B ), where A and B are valid parentheses strings.
 * <p>
 * - It can be written as (A) , where A is a valid parentheses string.
 * <p>
 * You are given an m x n matrix of parentheses grid . A valid parentheses string path in the grid is a
 * path satisfying all of the following conditions:
 * <p>
 * - The path starts from the upper left cell (0, 0) .
 * <p>
 * - The path ends at the bottom-right cell (m - 1, n - 1) .
 * <p>
 * - The path only ever moves down or right .
 * <p>
 * - The resulting parentheses string formed by the path is valid .
 * <p>
 * Return true if there exists a valid parentheses string path in the grid. Otherwise, return false .
 * <p>
 * Example 1:
 * <p>
 * Input: grid = [["(","(","("],[")","(",")"],["(","(",")"],["(","(",")"]]
 * Output: true
 * Explanation: The above diagram shows two possible paths that form valid parentheses strings.
 * The first path shown results in the valid parentheses string "()(())".
 * The second path shown results in the valid parentheses string "((()))".
 * Note that there may be other valid parentheses string paths.
 * <p>
 * Example 2:
 * <p>
 * Input: grid = [[")",")"],["(","("]]
 * Output: false
 * Explanation: The two possible paths form the parentheses strings "))(" and ")((". Since neither of
 * them are valid parentheses strings, we return false.
 * <p>
 * Constraints:
 * <p>
 * - m == grid.length
 * <p>
 * - n == grid[i].length
 * <p>
 * - 1 <= m, n <= 100
 * <p>
 * - grid[i][j] is either '(' or ')' .
 */
public class _2267 {

    public boolean hasValidPath(char[][] grid) {
        if (grid[0][0] == ')') {
            return false;
        }

        int rowLen = grid.length;
        int colLen = grid[0].length;

        if (((rowLen + colLen - 1) & 1) == 1) {
            return false;
        }

        boolean[][] last = new boolean[colLen][colLen + rowLen];
        boolean[][] cur = new boolean[colLen][colLen + rowLen];
        cur[0][1] = true;

        int baseScore;
        int nextScore;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                baseScore = (grid[row][col] == '(' ? 1 : -1);
                if (col - 1 > -1) {
                    for (int score = 0; score < cur[col - 1].length; score++) {
                        nextScore = score + baseScore;
                        if (nextScore >= 0 && nextScore < cur[col - 1].length) {
                            cur[col][nextScore] |= cur[col - 1][score];
                        }
                    }
                }
                if (row - 1 > -1) {
                    for (int score = 0; score < last[col].length; score++) {
                        nextScore = score + baseScore;
                        if (nextScore >= 0 && nextScore < last[col].length) {
                            cur[col][nextScore] |= last[col][score];
                        }
                    }
                }
            }

            boolean[][] temp = last;
            last = cur;
            cur = temp;
            for (int i = 0; i < colLen; i++) {
                Arrays.fill(cur[i], false);
            }
        }

        return last[colLen - 1][0];
    }
}
