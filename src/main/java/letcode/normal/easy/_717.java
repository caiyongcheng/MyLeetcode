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


}
