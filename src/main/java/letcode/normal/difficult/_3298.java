package letcode.normal.difficult;

import letcode.normal.unansweredquestions.medium._3297;
import letcode.utils.TestUtil;

/**
 * You are given two strings word1 and word2.
 * A string x is called valid if x can be rearranged to have word2 as a prefix .
 * Return the total number of valid  substrings  of word1.
 * Note that the memory limits in this problem are smaller than usual,
 * so you must implement a solution with a linear runtime complexity.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-01-10 17:32
 */
public class _3298 {

    public long validSubstringCount(String word1, String word2) {
        // This question is the same as _3297, but the time complexity requirement is O(n).
        return new _3297().validSubstringCount(word1, word2);
    }

}
