package letcode.normal.medium;

import letcode.normal.easy._2839;
import letcode.utils.TestUtil;

/**
 * You are given two strings s1 and s2, both of length n, consisting of lowercase English letters.
 * You can apply the following operation on any of the two strings any number of times:
 * Choose any two indices i and j such that i < j and the difference j - i is even,
 * then swap the two characters at those indices in the string.
 * Return true if you can make the strings s1 and s2 equal, and false otherwise.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2026-03-30 14:51
 */
public class _2840 {


    /**
     * Equivalent to {@link _2839}: only indices of the same parity can be permuted, so the character multisets on
     * even indices and on odd indices must match pairwise between {@code s1} and {@code s2}. Delegates to
     * {@link _2839#canBeEqual(String, String)}.
     *
     * @param s1 first string
     * @param s2 second string, same length as {@code s1}
     * @return {@code true} if the two strings can be made equal using the allowed swaps
     */
    public boolean checkStrings(String s1, String s2) {
        return new _2839().canBeEqual(s1, s2);
    }

}
