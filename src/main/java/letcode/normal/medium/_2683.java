package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * A 0-indexed array derived with length n is derived
 * by computing the bitwise XOR (⊕) of adjacent values in a binary array original of length n.
 * Specifically, for each index i in the range [0, n - 1]:  If i = n - 1, then derived[i] = original[i] ⊕ original[0].
 * Otherwise, derived[i] = original[i] ⊕ original[i + 1]. Given an array derived,
 * your task is to determine whether there exists a valid binary array original that could have formed derived.
 * Return true if such an array exists or false otherwise.
 * A binary array is an array containing only 0's and 1's
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-07-31 11:00
 */
public class _2683 {

    public boolean doesValidArrayExist(int[] derived) {
        int flag = 0;
        for (int i = 1; i < derived.length; i++) {
            flag = flag ^ derived[i];
        }
        return flag == derived[0];
    }


}
