package letcode.normal.medium;

import letcode.utils.SolutionTestMethod;

/**
 * 2523. Closest Prime Numbers in Range
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/closest-prime-numbers-in-range/
 * <p>
 * Given two positive integers left and right , find the two integers num1 and num2 such that:
 * <p>
 * - left <= num1 < num2 <= right .
 * <p>
 * - Both num1 and num2 are prime numbers .
 * <p>
 * - num2 - num1 is the minimum amongst all other pairs satisfying the above conditions.
 * <p>
 * Return the positive integer array ans = [num1, num2] . If there are multiple pairs satisfying these
 * conditions, return the one with the smallest num1 value. If no such numbers exist, return [-1, -1] .
 * <p>
 * Example 1:
 * <p>
 * Input: left = 10, right = 19
 * Output: [11,13]
 * Explanation: The prime numbers between 10 and 19 are 11, 13, 17, and 19.
 * The closest gap between any pair is 2, which can be achieved by [11,13] or [17,19].
 * Since 11 is smaller than 17, we return the first pair.
 * <p>
 * Example 2:
 * <p>
 * Input: left = 4, right = 6
 * Output: [-1,-1]
 * Explanation: There exists only one prime number in the given range, so the conditions cannot be
 * satisfied.
 * <p>
 * Constraints:
 * <p>
 * - 1 <= left <= right <= 10 6
 */

public class _2523 {

    public static final int[] screening = new int[1_000_000 + 1];
    public static int processLen = 2;

    static {
        screening[0] = 1;
        screening[1] = 1;
    }

    @SolutionTestMethod
    public int[] closestPrimes(int left, int right) {
        int[] ans = new int[]{0, 0};

        processScreening(right);

        for (int i = left; i <= right; i++) {
            if (screening[i] == 0) {
                if (ans[0] != 0) {
                    ans[1] = i;
                    break;
                }
                ans[0] = i;
            }
        }

        if (ans[1] == 0) {
            return new int[]{-1, -1};
        }

        int curPrime = ans[1];
        for (int i = ans[1] + 1; i <= right; i++) {
            if (screening[i] == 0) {
                if ((i - curPrime) < (ans[1] - ans[0])) {
                    ans[0] = curPrime;
                    ans[1] = i;
                }
                curPrime = i;
            }
        }

        return ans;


    }

    public void processScreening(int limit) {
        if (limit < processLen) {
            return ;
        }
        processLen = limit;
        for (int i = 2; i <= processLen; i++) {
            if (screening[i] != 0) {
                continue;
            }
            if (i << 1 > processLen) {
                break;
            }
            for (int j = 2; i * j <= processLen; j++) {
                screening[i * j] = 1;
            }
        }

    }

    public static void main(String[] args) {

    }
}
