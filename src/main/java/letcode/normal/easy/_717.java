package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 * We have two special characters:  The first character can be represented by one bit 0. The second character can be represented by two bits (10 or 11). Given a binary array bits that ends with 0, return true if the last character must be a one-bit character.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-11-18 17:07
 */
public class _717 {


    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length, i = 0;
        while (i < n - 1) {
            i += bits[i] + 1;
        }
        return i == n - 1;
    }


    /**
     * Example 1:
     *
     * Input: bits = [1,0,0]
     * Output: true
     * Explanation: The only way to decode it is two-bit character and one-bit character.
     * So the last character is one-bit character.
     * Example 2:
     *
     * Input: bits = [1,1,1,0]
     * Output: false
     * Explanation: The only way to decode it is two-bit character and two-bit character.
     * So the last character is not one-bit character.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
