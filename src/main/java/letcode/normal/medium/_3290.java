package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.Arrays;

/**
 * 3290. Maximum Multiplication Score
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/maximum-multiplication-score/
 * <p>
 * You are given an integer array a of size 4 and another integer array b of size at least 4.
 * <p>
 * You need to choose 4 indices i 0 , i 1 , i 2 , and i 3 from the array b such that i 0 < i 1 < i 2 <
 * i 3 . Your score will be equal to the value a[0] * b[i 0 ] + a[1] * b[i 1 ] + a[2] * b[i 2 ] + a[3]
 * * b[i 3 ] .
 * <p>
 * Return the maximum score you can achieve.
 * <p>
 * Example 1:
 * <p>
 * Input: a = [3,2,5,6], b = [2,-6,4,-5,-3,2,-7]
 * <p>
 * Output: 26
 * <p>
 * Explanation:
 * <p>
 * We can choose the indices 0, 1, 2, and 5. The score will be 3 * 2 + 2 * (-6) + 5 * 4 + 6 * 2 = 26 .
 * <p>
 * Example 2:
 * <p>
 * Input: a = [-1,4,5,-2], b = [-5,-1,-3,-2,-4]
 * <p>
 * Output: -1
 * <p>
 * Explanation:
 * <p>
 * We can choose the indices 0, 1, 3, and 4. The score will be (-1) * (-5) + 4 * (-1) + 5 * (-2) + (-2)
 * * (-4) = -1 .
 * <p>
 * Constraints:
 * <p>
 * - a.length == 4
 * <p>
 * - 4 <= b.length <= 10 5
 * <p>
 * - -10 5 <= a[i], b[i] <= 10 5
 */
public class _3290 {

    public long maxScore(int[] a, int[] b) {
        long[] dp = new long[5];
        Arrays.fill(dp, Long.MIN_VALUE >> 1);
        dp[0] = 0;

        for (long num : b) {
            for (int i = dp.length - 1; i >= 1; i--) {
                dp[i] = Long.max(dp[i], dp[i - 1] + a[i - 1] * num);
            }
        }

        return dp[4];
    }

    public static void main(String[] args) {
        TestUtil.test("=[100000,100000,100000,100000],=[-100000,-100000,-100000,-100000]");
    }
}
