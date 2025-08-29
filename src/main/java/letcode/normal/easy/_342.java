package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 * Given an integer n, return true if it is a power of four. Otherwise, return false.
 * An integer n is a power of four, if there exists an integer x such that n == 4x.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-08-15 09:11
 */
public class _342 {

    public boolean isPowerOfFour(int n) {
        while (n > 1) {
            if (n % 4 != 0) {
                return false;
            }
            n /= 4;
        }
        return n == 1;
    }

    /**
     * Example 1:
     *
     * Input: n = 16
     * Output: true
     * Example 2:
     *
     * Input: n = 5
     * Output: false
     * Example 3:
     *
     * Input: n = 1
     * Output: true
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }


}
