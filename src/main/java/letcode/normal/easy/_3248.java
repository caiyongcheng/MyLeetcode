package letcode.normal.easy;

import letcode.utils.TestUtil;

import java.util.List;

/**
 * There is a snake in an n x n matrix grid and can move in four possible directions. Each cell in the grid is
 * identified by the position: grid[i][j] = (i * n) + j.  The snake starts at cell 0 and follows a sequence of commands.
 * You are given an integer n representing the size of the grid and an array of strings commands where
 * each command[i] is either "UP", "RIGHT", "DOWN", and "LEFT". It's guaranteed that the snake will remain within
 * the grid boundaries throughout its movement.  Return the position of the final cell where the snake ends up after
 * executing commands.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-11-21 23:31
 */
public class _3248 {

    public int finalPositionOfSnake(int n, List<String> commands) {
        int index = 0;
        for (String command : commands) {
            switch (command.charAt(0)) {
                case 'U':
                    index -= n;
                    break;
                case 'D':
                    index += n;
                    break;
                case 'L':
                    index -= 1;
                    break;
                case 'R':
                    index += 1;
                    break;
            }
        }
        return index;
    }


    /**
     * Example 1:
     * Input: n = 2, commands = ["RIGHT","DOWN"]
     * Output: 3
     * Explanation:
     * 0	1
     * 2	3
     * 0	1
     * 2	3
     * 0	1
     * 2	3
     * Example 2:
     * Input: n = 3, commands = ["DOWN","RIGHT","UP"]
     * Output: 1
     * Explanation:
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(_3248.class);
    }

}
