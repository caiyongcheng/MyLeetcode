package letcode.normal.medium;

import java.util.Arrays;

/**
 * 3723. Maximize Sum of Squares of Digits
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/maximize-sum-of-squares-of-digits/
 * <p>
 * You are given two positive integers num and sum .
 * <p>
 * A positive integer n is good if it satisfies both of the following:
 * <p>
 * - The number of digits in n is exactly num .
 * <p>
 * - The sum of digits in n is exactly sum .
 * <p>
 * The score of a good integer n is the sum of the squares of digits in n .
 * <p>
 * Return a string denoting the good integer n that achieves the maximum score . If there are multiple
 * possible integers, return the maximum ​​​​​​​one. If no such integer exists, return an empty string.
 * <p>
 * Example 1:
 * <p>
 * Input: num = 2, sum = 3
 * <p>
 * Output: "30"
 * <p>
 * Explanation:
 * <p>
 * There are 3 good integers: 12, 21, and 30.
 * <p>
 * - The score of 12 is 1 2 + 2 2 = 5 .
 * <p>
 * - The score of 21 is 2 2 + 1 2 = 5 .
 * <p>
 * - The score of 30 is 3 2 + 0 2 = 9 .
 * <p>
 * The maximum score is 9, which is achieved by the good integer 30. Therefore, the answer is "30" .
 * <p>
 * Example 2:
 * <p>
 * Input: num = 2, sum = 17
 * <p>
 * Output: "98"
 * <p>
 * Explanation:
 * <p>
 * There are 2 good integers: 89 and 98.
 * <p>
 * - The score of 89 is 8 2 + 9 2 = 145 .
 * <p>
 * - The score of 98 is 9 2 + 8 2 = 145 .
 * <p>
 * The maximum score is 145. The maximum good integer that achieves this score is 98. Therefore, the
 * answer is "98" .
 * <p>
 * Example 3:
 * <p>
 * Input: num = 1, sum = 10
 * <p>
 * Output: ""
 * <p>
 * Explanation:
 * <p>
 * There are no integers that have exactly 1 digit and whose digits sum to 10. Therefore, the answer is
 * "" .
 * <p>
 * Constraints:
 * <p>
 * - 1 <= num <= 2 * 10 5
 * <p>
 * - 1 <= sum <= 2 * 10 6
 */
public class _3723 {

    public String maxSumOfSquares(int num, int sum) {
        /*
         假设 x + y = z
         如果 z 固定 那么 (x + y) ^ 2 是固定
         那么 x^2 + y^2 + 2xy 也是固定的，想要 x^2 + y^2 越大，那么 2xy就必须越小
         因为 x + y 是固定的 且 x^2 - (x-b)(x+b) = b^2 也就是说 x+y固定 |x-y|越大，2xy越小
         */
        if (num * 9 < sum) {
            return "";
        }
        char[] ans = new char[num];
        Arrays.fill(ans, '0');
        if (sum > 9) {
            Arrays.fill(ans, 0, sum / 9, '9');
            if (sum % 9 != 0) {
                ans[sum / 9] = (char) (sum % 9 + '0');
            }
        } else {
            ans[0] = (char) (sum + '0');
        }
        return new String(ans);

    }


}
