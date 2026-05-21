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

}
