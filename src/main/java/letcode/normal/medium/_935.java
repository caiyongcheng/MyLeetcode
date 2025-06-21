package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.Arrays;

/**
 * The chess knight has a unique movement, it may move two squares vertically and one square horizontally,
 * or two squares horizontally and one square vertically (with both forming the shape of an L).
 * The possible movements of chess knight are shown in this diagram:  A chess knight can move as indicated in the chess
 * diagram below:\nWe have a chess knight and a phone pad as shown below, the knight can only stand on a numeric cell
 * (i.e. blue cell).\nGiven an integer n, return how many distinct phone numbers of length n we can dial.
 * You are allowed to place the knight on any numeric cell initially and then you should perform n - 1 jumps to dial
 * a number of length n. All jumps should be valid knight jumps.  As the answer may be very large,
 * return the answer modulo 109 + 7.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-12-10 09:45
 */
public class _935 {

    static int[][] MOVE_ARR = new int[][]{{1,2}, {1,-2}, {-1,2}, {-1,-2}, {2,1}, {2,-1}, {-2,1}, {-2,-1}};
    static int[] INVALID_KEY_1 = new int[]{3, 0};
    static int[] INVALID_KEY_2 = new int[]{3, 2};
    static int MOD = 1_000_000_000 + 7;


    public int knightDialer(int n) {
        int[][] mobileKeyboard = new int[4][3];
        int[][] nextKeyboard = new int[4][3];

        // 初始化
        for (int row = 0; row < mobileKeyboard.length; row++) {
            for (int col = 0; col < mobileKeyboard[row].length; col++) {
                if ((row == INVALID_KEY_1[0] && col == INVALID_KEY_1[1])
                        || (row == INVALID_KEY_2[0] && col == INVALID_KEY_2[1])) {
                    continue;
                }
                mobileKeyboard[row][col] = 1;
            }
        }

        // 从长度i到i+1做dp
        int nextRow = 0;
        int nextCol = 0;
        for (int i = 1; i < n; i++) {
            for (int[] arr : nextKeyboard) {
                Arrays.fill(arr, 0);
            }
            for (int row = 0; row < nextKeyboard.length; row++) {
                for (int col = 0; col < nextKeyboard[row].length; col++) {
                    if ((row == INVALID_KEY_1[0] && col == INVALID_KEY_1[1])
                            || (row == INVALID_KEY_2[0] && col == INVALID_KEY_2[1])) {
                        continue;
                    }
                    for (int[] move : MOVE_ARR) {
                        nextRow = row + move[0];
                        nextCol = col + move[1];
                        if (nextRow > -1 && nextRow < nextKeyboard.length
                                && nextCol > -1 && nextCol < nextKeyboard[nextRow].length) {
                            nextKeyboard[nextRow][nextCol] = (mobileKeyboard[row][col] + nextKeyboard[nextRow][nextCol]) % MOD;
                        }
                    }
                }
            }
            for (int iRow = 0; iRow < mobileKeyboard.length; iRow++) {
                for (int jCol = 0; jCol < mobileKeyboard[jCol].length; jCol++) {
                    if ((iRow == INVALID_KEY_1[0] && jCol == INVALID_KEY_1[1])
                            || (iRow == INVALID_KEY_2[0] && jCol == INVALID_KEY_2[1])) {
                        continue;
                    }
                    mobileKeyboard[iRow][jCol] = nextKeyboard[iRow][jCol];
                }
            }
        }

        // 计算结果
        int ans = 0;
        for (int row = 0; row < mobileKeyboard.length; row++) {
            for (int col = 0; col < mobileKeyboard[row].length; col++) {
                if ((row == INVALID_KEY_1[0] && col == INVALID_KEY_1[1])
                        || (row == INVALID_KEY_2[0] && col == INVALID_KEY_2[1])) {
                    continue;
                }
                ans += mobileKeyboard[row][col] %= MOD;
                ans %= MOD;
            }
        }
        return ans;
    }


    /**
     * Example 1:
     *
     * Input: n = 1
     * Output: 10
     * Explanation: We need to dial a number of length 1, so placing the knight over any numeric cell of the 10 cells is sufficient.
     * Example 2:
     *
     * Input: n = 2
     * Output: 20
     * Explanation: All the valid number we can dial are [04, 06, 16, 18, 27, 29, 34, 38, 40, 43, 49, 60, 61, 67, 72, 76, 81, 83, 92, 94]
     * Example 3:
     *
     * Input: n = 3131
     * Output: 136006598
     * Explanation: Please take care of the mod.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(_935.class);
    }


}
