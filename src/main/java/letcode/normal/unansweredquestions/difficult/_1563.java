package letcode.normal.difficult;

/**
 * 1563. Stone Game V
 * Difficulty: Hard
 * Link: https://leetcode.cn/problems/stone-game-v/
 * <p>
 * There are several stones arranged in a row , and each stone has an associated value which is an
 * integer given in the array stoneValue .
 * <p>
 * In each round of the game, Alice divides the row into two non-empty rows (i.e. left row and right
 * row), then Bob calculates the value of each row which is the sum of the values of all the stones in
 * this row. Bob throws away the row which has the maximum value, and Alice's score increases by the
 * value of the remaining row. If the value of the two rows are equal, Bob lets Alice decide which row
 * will be thrown away. The next round starts with the remaining row.
 * <p>
 * The game ends when there is only one stone remaining . Alice's score is initially zero .
 * <p>
 * Return the maximum score that Alice can obtain .
 * <p>
 * Example 1:
 * <p>
 * Input: stoneValue = [6,2,3,4,5,5]
 * Output: 18
 * Explanation: In the first round, Alice divides the row to [6,2,3], [4,5,5]. The left row has the
 * value 11 and the right row has value 14. Bob throws away the right row and Alice's score is now 11.
 * In the second round Alice divides the row to [6], [2,3]. This time Bob throws away the left row and
 * Alice's score becomes 16 (11 + 5).
 * The last round Alice has only one choice to divide the row which is [2], [3]. Bob throws away the
 * right row and Alice's score is now 18 (16 + 2). The game ends because only one stone is remaining in
 * the row.
 * <p>
 * Example 2:
 * <p>
 * Input: stoneValue = [7,7,7,7,7,7,7]
 * Output: 28
 * <p>
 * Example 3:
 * <p>
 * Input: stoneValue = [4]
 * Output: 0
 * <p>
 * Constraints:
 * <p>
 * - 1 <= stoneValue.length <= 500
 * <p>
 * - 1 <= stoneValue[i] <= 10 6
 */
public class _1563 {

    public int stoneGameV(int[] stoneValue) {
        return 0;
    }
}
