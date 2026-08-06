package letcode.normal.easy;

/**
 * 3345. Smallest Divisible Digit Product I
 * Difficulty: Easy
 * Link: https://leetcode.cn/problems/smallest-divisible-digit-product-i/
 * <p>
 * You are given two integers n and t . Return the smallest number greater than or equal to n such that
 * the product of its digits is divisible by t .
 * <p>
 * Example 1:
 * <p>
 * Input: n = 10, t = 2
 * <p>
 * Output: 10
 * <p>
 * Explanation:
 * <p>
 * The digit product of 10 is 0, which is divisible by 2, making it the smallest number greater than or
 * equal to 10 that satisfies the condition.
 * <p>
 * Example 2:
 * <p>
 * Input: n = 15, t = 3
 * <p>
 * Output: 16
 * <p>
 * Explanation:
 * <p>
 * The digit product of 16 is 6, which is divisible by 3, making it the smallest number greater than or
 * equal to 15 that satisfies the condition.
 * <p>
 * Constraints:
 * <p>
 * - 1 <= n <= 100
 * <p>
 * - 1 <= t <= 10
 */
public class _3345 {

    public int smallestNumber(int n, int t) {
        if (n <= t) {
            return t;
        }
        while (n <= 100) {
            boolean test0 = n < 10 && n % t == 0;
            boolean test1 = n >= 10 && (n % 10) * (n / 10) % t == 0;
            if (test0 || test1) {
                return n;
            }
            ++n;
        }
        return 100;
    }
}
