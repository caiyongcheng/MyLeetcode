package letcode.normal.difficult;

import letcode.utils.TestUtil;

/**
 * Alice and Bob are playing a game. Initially, Alice has a string word = "a".
 * You are given a positive integer k. You are also given an integer array operations, where operations[i] represents the type of the ith operation.
 * Now Bob will ask Alice to perform all operations in sequence:  If operations[i] == 0, append a copy of word to itself.
 * If operations[i] == 1, generate a new string by changing each character in word to its next character in the English alphabet,
 * and append it to the original word. For example, performing the operation on "c" generates "cd" and performing the operation on "zb"
 * generates "zbac". Return the value of the kth character in word after performing all the operations.
 * Note that the character 'z' can be changed to 'a' in the second type of operation.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-07-04 10:57
 */
public class _3307 {

    public char kthCharacter(long k, int[] operations) {
        long floor = nextPowerOfTwo(k) >> 1;
        long delta = 0;
        int len = operations.length;
        while (k > 1) {
            delta += (k > floor ? operations[--len] : 0);
            k -= (k > floor ? floor : 0);
            floor >>= 1;
        }
        return (char) ('a' + delta % 26);
    }

    private long nextPowerOfTwo(long cap) {
        long n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= Long.MAX_VALUE) ? Long.MAX_VALUE : n + 1;
    }

    /**
     * Example 1:
     * <p>
     * Input: k = 5, operations = [0,0,0]
     * <p>
     * Output: "a"
     * <p>
     * Explanation:
     * <p>
     * Initially, word == "a". Alice performs the three operations as follows:
     * <p>
     * Appends "a" to "a", word becomes "aa".
     * Appends "aa" to "aa", word becomes "aaaa".
     * Appends "aaaa" to "aaaa", word becomes "aaaaaaaa".
     * Example 2:
     * <p>
     * Input: k = 10, operations = [0,1,0,1]
     * <p>
     * Output: "b"
     * <p>
     * Explanation:
     * <p>
     * Initially, word == "a". Alice performs the four operations as follows:
     * <p>
     * Appends "a" to "a", word becomes "aa".
     * Appends "bb" to "aa", word becomes "aabb".
     * Appends "aabb" to "aabb", word becomes "aabbaabb".
     * Appends "bbccbbcc" to "aabbaabb", word becomes "aabbaabbbbccbbcc".
     *
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
