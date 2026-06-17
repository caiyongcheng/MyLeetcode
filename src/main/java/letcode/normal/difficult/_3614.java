package letcode.normal.difficult;

/**
 * 3614. Process String with Special Operations II
 * Difficulty: Hard
 * Link: https://leetcode.cn/problems/process-string-with-special-operations-ii/
 * You are given a string s consisting of lowercase English letters and the special characters: '*' , '#' , and '%' .
 * You are also given an integer k .
 * Build a new string result by processing s according to the following rules from left to right:
 * - If the letter is a lowercase English letter append it to result . - A '*' removes the last character from result ,
 * if it exists. - A '#' duplicates the current result and appends it to itself. - A '%' reverses the current result.
 * Return the k-th character of the final string result. If k is out of the bounds of result, return '.'.
 */
public class _3614 {

    public char processStr(String s, long k) {
        long len = 0L;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '*') {
                len = Math.max(0L, len - 1);
            } else if (ch == '#') {
                len <<= 1;
            } else if (ch != '%') {
                len++;
            }
        }
        if (k >= len) {
            return '.';
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (ch == '*') {
                len++;
            } else if (ch == '#') {
                len >>= 1;
                if (k >= len) {
                    k -= len;
                }
            } else if (ch == '%') {
                k = len - 1 - k;
            } else {
                len--;
                if (k == len) {
                    return ch;
                }
            }
        }
        return '.';
    }
}
