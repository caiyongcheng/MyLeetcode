package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 * Alice and Bob are playing a game. Initially, Alice has a string word = "a".  You are given a positive integer k.
 * Now Bob will ask Alice to perform the following operation forever:  Generate a new string by changing each character in word
 * to its next character in the English alphabet, and append it to the original word. For example, performing the operation on
 * "c" generates "cd" and performing the operation on "zb" generates "zbac".  Return the value of the kth character in word,
 * after enough operations have been done for word to have at least k characters.
 * Note that the character 'z' can be changed to 'a' in the operation.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-07-03 14:06
 */
public class _3304 {

    public char kthCharacter(int k) {
/*        int floor = nextPowerOfTwo(k) >> 1;
        int delta = 0;
        while (k > 1) {
            delta += (k > floor ? 1 : 0);
            k -= (k > floor ? floor : 0);
            floor >>= 1;
        }
        return (char) ('a' + delta % 26);*/
        return (char) ('a' + Integer.bitCount(k - 1) % 26);
    }

    private int nextPowerOfTwo(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= Integer.MAX_VALUE) ? Integer.MAX_VALUE : n + 1;
    }

    /**
     * Example 1:
     *
     * Input: k = 5
     *
     * Output: "b"
     *
     * Explanation:
     *
     * Initially, word = "a". We need to do the operation three times:
     *
     * Generated string is "b", word becomes "ab".
     * Generated string is "bc", word becomes "abbc".
     * Generated string is "bccd", word becomes "abbcbccd".
     * Example 2:
     *
     * Input: k = 10
     *
     * Output: "c"
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }



}
