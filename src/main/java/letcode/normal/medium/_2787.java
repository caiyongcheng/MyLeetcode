package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * Given two positive integers n and x.  Return the number of ways n can be expressed as the sum of the xth power of
 * unique positive integers, in other words, the number of sets of unique integers [n1, n2, ..., nk] where n = n1x + n2x + ... + nkx.
 * Since the result can be very large, return it modulo 109 + 7.  For example, if n = 160 and x = 3,
 * one way to express n is n = 23 + 33 + 53.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-08-12 10:40
 */
public class _2787 {

    private static final long MOD = 1000000007;

    private static final long[][] preCalculateResult = new long[6][301];

    static {
        int start;
        int end;
        for (int base = 1; base < preCalculateResult.length; base++) {
            preCalculateResult[base][0] = 1;
            for (int num = 1; num < preCalculateResult[base].length; num++) {
                end = powerRoot(num, base);
                start = powerRoot(num >> 1, base);
                for (; end > start; end--) {
                    preCalculateResult[base][num] = (preCalculateResult[base][num] + preCalculateResult[base][(int) (num - Math.pow(end, base))]) % MOD;
                }
            }
        }
    }

    public int numberOfWays(int n, int x) {
        return (int) (preCalculateResult[x][n] % MOD);
    }

    private static int powerRoot(int num, int base) {
        int ans = 0;
        while (Math.pow(ans, base) <= num) {
            ans++;
        }
        return ans - 1;
    }


    /**
     * Example 1:
     *
     * Input: n = 10, x = 2
     * Output: 1
     * Explanation: We can express n as the following: n = 32 + 12 = 10.
     * It can be shown that it is the only way to express 10 as the sum of the 2nd power of unique integers.
     * Example 2:
     *
     * Input: n = 4, x = 1
     * Output: 2
     * Explanation: We can express n in the following ways:
     * - n = 41 = 4.
     * - n = 31 + 11 = 4.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }



}
