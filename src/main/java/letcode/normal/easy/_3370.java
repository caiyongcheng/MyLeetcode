package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 * You are given a positive number n.  Return the smallest number x greater than or equal to n,
 * such that the binary representation of x contains only set bits
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-10-29 08:38
 */
public class _3370 {

    public int smallestNumber(int n) {
        // return -1 >>> Integer.numberOfLeadingZeros(n);
        int ans = 2;
        while (n >= ans) {
            ans = ans << 1;
        }
        return ans - 1;
    }


    /**
     * Example 1:
     *
     * Input: n = 5
     *
     * Output: 7
     *
     * Explanation:
     *
     * The binary representation of 7 is "111".
     *
     * Example 2:
     *
     * Input: n = 10
     *
     * Output: 15
     *
     * Explanation:
     *
     * The binary representation of 15 is "1111".
     *
     * Example 3:
     *
     * Input: n = 3
     *
     * Output: 3
     *
     * Explanation:
     *
     * The binary representation of 3 is "11".
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
