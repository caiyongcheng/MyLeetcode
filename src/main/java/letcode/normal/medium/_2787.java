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


}
