package letcode.normal.difficult;

/**
 * 3699. Number of ZigZag Arrays I
 * Difficulty: Hard
 * Link: https://leetcode.cn/problems/number-of-zigzag-arrays-i/
 * You are given three integers n , l , and r . A ZigZag array of length n is defined as follows:
 * - Each element lies in the range [l, r] .
 * - No two adjacent elements are equal.
 * - No three consecutive elements form a strictly increasing or strictly decreasing sequence.
 * Return the total number of valid ZigZag arrays. Since the answer may be large, return it modulo 10 9 + 7 .
 * A sequence is said to be s...
 */
public class _3699 {

    public int zigZagArrays(int n, int l, int r) {
        int len = r - l + 1;
        int[][] curSelectCnt = new int[len][2];
        int[][] preSum = new int[2][len];

        for (int i = 0; i < len; i++) {
            preSum[0][i] = i + 1;
            preSum[1][i] = i + 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < len - 1; j++) {
                curSelectCnt[j][0] = preSum[1][j - 1];
                curSelectCnt[j][1] = preSum[0][j + 1] - preSum[0][j];
                preSum[0][j] = preSum[0][j - 1] + curSelectCnt[j][0];
                preSum[1][j] = preSum[1][j - 1] + curSelectCnt[j][1];
            }
        }

        return preSum[0][len - 1] + preSum[1][len - 1];

    }
}
