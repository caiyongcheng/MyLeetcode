package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * A decimal number is called deci-binary if each of its digits is either 0 or 1 without any leading zeros.
 * For example, 101 and 1100 are deci-binary, while 112 and 3001 are not.  Given a string n that represents a
 * positive decimal integer, return the minimum number of positive deci-binary numbers needed so that they sum up to n.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-08-30 14:43
 */
public class _1689 {

    public int minPartitions(String n) {
        char max = '0';
        int length = n.length();
        for (int i = 0; i < length; i++) {
            char c = n.charAt(i);
            if (c > max) {
                max = c;
            }
        }
        return max - '0';
    }


    /**
     * Example 1:
     *
     * Input: n = "32"
     * Output: 3
     * Explanation: 10 + 11 + 11 = 32
     * Example 2:
     *
     * Input: n = "82734"
     * Output: 8
     * Example 3:
     *
     * Input: n = "27346209830709182346"
     * Output: 9
     *
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(_1689.class);
    }

}
