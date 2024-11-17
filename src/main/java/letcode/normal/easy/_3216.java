package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 * Given a string s containing only digits, return the  lexicographically smallest string
 * that can be obtained after swapping adjacent digits in s with the same parity at most once.
 * Digits have the same parity if both are odd or both are even. For example, 5 and 9,
 * as well as 2 and 4, have the same parity, while 6 and 9 do not.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-10-30 09:27
 */
public class _3216 {

    public String getSmallestString(String s) {
        byte[] bytes = s.getBytes();
        for (int i = 0; i < bytes.length - 1; i++) {
            if ((bytes[i] & 1) == (bytes[i + 1] & 1) && bytes[i] > bytes[i + 1]) {
                byte temp = bytes[i];
                bytes[i] = bytes[i + 1];
                bytes[i + 1] = temp;
                return new String(bytes);
            }
        }
        return s;
    }


    /**
     * Example 1:
     *
     * Input: s = "45320"
     *
     * Output: "43520"
     *
     * Explanation:
     *
     * s[1] == '5' and s[2] == '3' both have the same parity, and swapping them results in the lexicographically smallest string.
     *
     * Example 2:
     *
     * Input: s = "001"
     *
     * Output: "001"
     *
     * Explanation:
     *
     * There is no need to perform a swap because s is already the lexicographically smallest.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(_3216.class);
    }

}
