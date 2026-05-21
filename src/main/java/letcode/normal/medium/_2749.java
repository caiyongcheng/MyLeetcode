package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * You are given two integers num1 and num2.  In one operation, you can choose integer i in the range [0, 60]
 * and subtract 2i + num2 from num1.  Return the integer denoting the minimum number of operations needed to make
 * num1 equal to 0.  If it is impossible to make num1 equal to 0, return -1.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-09-05 10:06
 */
public class _2749 {

    public int makeTheIntegerZero(int num1, int num2) {
        long num = num1;
        for (int i = 0; i < 60; i++) {
            // 进行了i次操作 那么最少都要减去 i*(1 + nums2)
            if (num < i) {
                return -1;
            }
            // 小于是因为 当 num = (num1 - i*num2)的1有j个时，如果j<i,那么 可以通过把一个2^x变成2^(x-1)*2来变更操作数
            if (Long.bitCount(num) <= i) {
                return i;
            }
            num -= num2;
        }
        return -1;
    }


}
