package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 * There is a programming language with only four operations and one variable X:
 * ++X and X++ increments the value of the variable X by 1. --X and X-- decrements the value of the variable X by 1.
 * Initially, the value of X is 0.
 * Given an array of strings operations containing a list of operations,
 * return the final value of X after performing all the operations.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-10-20 21:11
 */
public class _2011 {

    public int finalValueAfterOperations(String[] operations) {
        int ans = 0;
        for (String operation : operations) {
            ans += (operation.charAt(1) == '+' ? 1 : -1);
        }
        return ans;
    }


    /**
     * Example 1:
     *
     * Input: operations = ["--X","X++","X++"]
     * Output: 1
     * Explanation: The operations are performed as follows:
     * Initially, X = 0.
     * --X: X is decremented by 1, X =  0 - 1 = -1.
     * X++: X is incremented by 1, X = -1 + 1 =  0.
     * X++: X is incremented by 1, X =  0 + 1 =  1.
     * Example 2:
     *
     * Input: operations = ["++X","++X","X++"]
     * Output: 3
     * Explanation: The operations are performed as follows:
     * Initially, X = 0.
     * ++X: X is incremented by 1, X = 0 + 1 = 1.
     * ++X: X is incremented by 1, X = 1 + 1 = 2.
     * X++: X is incremented by 1, X = 2 + 1 = 3.
     * Example 3:
     *
     * Input: operations = ["X++","++X","--X","X--"]
     * Output: 0
     * Explanation: The operations are performed as follows:
     * Initially, X = 0.
     * X++: X is incremented by 1, X = 0 + 1 = 1.
     * ++X: X is incremented by 1, X = 1 + 1 = 2.
     * --X: X is decremented by 1, X = 2 - 1 = 1.
     * X--: X is decremented by 1, X = 1 - 1 = 0.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
