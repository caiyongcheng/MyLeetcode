package letcode.normal.easy;

import java.util.Arrays;

/**
 * 1967. Number of Strings That Appear as Substrings in Word
 * Difficulty: Easy
 * Link: https://leetcode.cn/problems/number-of-strings-that-appear-as-substrings-in-word/
 * Given an array of strings patterns and a string word ,
 * return the number of strings in patterns that exist as a substring in word .
 * A substring is a contiguous sequence of characters within a string.
 */
public class _1967 {

    public int numOfStrings(String[] patterns, String word) {
        return (int) Arrays.stream(patterns).filter(word::contains).count();
    }
}
