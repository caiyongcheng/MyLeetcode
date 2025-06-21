package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 * You are given a positive integer n.  Let even denote the number of even indices
 * in the binary representation of n with value 1.  Let odd denote the number of odd
 * indices in the binary representation of n with value 1.  Note that bits are indexed from right
 * to left in the binary representation of a number.  Return the array [even, odd].
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-02-20 10:44
 */
public class _2595 {

    public int[] evenOddBit(int n) {
        // return new int[]{ Integer.bitCount(n & 0x55555555), Integer.bitCount(n & 0xAAAAAAAA) };
        int oddCnt = 0;
        int evenCnt = 0;
        int time = 0;
        while (n > 0) {
            if ((n & 1) == 1) {
                if (time == 1) {
                    oddCnt++;
                } else {
                    evenCnt++;
                }
            }
            n >>= 1;
            time ^= 1;
        }
        return new int[]{evenCnt, oddCnt};
    }

    /**
     * Example 1:
     *
     * Input: n = 50
     *
     * Output: [1,2]
     *
     * Explanation:
     *
     * The binary representation of 50 is 110010.
     *
     * It contains 1 on indices 1, 4, and 5.
     *
     * Example 2:
     *
     * Input: n = 2
     *
     * Output: [0,1]
     *
     * Explanation:
     *
     * The binary representation of 2 is 10.
     *
     * It contains 1 only on index 1.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
