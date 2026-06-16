package letcode.normal.medium;

/**
 * 3612. Process String with Special Operations I
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/process-string-with-special-operations-i/
 * You are given a string s consisting of lowercase English letters and the special characters: * , # ,
 * and % . Build a new string result by processing s according to the following rules from left to right:
 * - If the letter is a lowercase English letter append it to result . - A '*' removes the last character from result ,
 * if it exists. - A '#' duplicates the current result and appends it to itself. -...
 */
public class _3612 {

    public String processStr(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] charArray = s.toCharArray();
        for (char c : charArray) {
            if (c == '*') {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            } else if (c == '#') {
                stringBuilder.append(stringBuilder);
            } else if (c == '%') {
                stringBuilder.reverse();
            } else {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }
}
