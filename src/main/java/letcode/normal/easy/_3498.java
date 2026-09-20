package letcode.normal.easy;

/**
 * 3498. Reverse Degree of a String
 * Difficulty: Easy
 * Link: https://leetcode.cn/problems/reverse-degree-of-a-string/
 * <p>
 * Given a string s , calculate its reverse degree .
 * <p>
 * The reverse degree is calculated as follows:
 * <p>
 * - For each character, multiply its position in the reversed alphabet ( 'a' = 26, 'b' = 25, ..., 'z'
 * = 1) with its position in the string (1-indexed) .
 * <p>
 * - Sum these products for all characters in the string.
 * <p>
 * Return the reverse degree of s .
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abc"
 * <p>
 * Output: 148
 * <p>
 * Explanation:
 * <p>
 * Letter
 * Index in Reversed Alphabet
 * Index in String
 * Product
 * <p>
 * 'a'
 * 26
 * 1
 * 26
 * <p>
 * 'b'
 * 25
 * 2
 * 50
 * <p>
 * 'c'
 * 24
 * 3
 * 72
 * <p>
 * The reversed degree is 26 + 50 + 72 = 148 .
 * <p>
 * Example 2:
 * <p>
 * Input: s = "zaza"
 * <p>
 * Output: 160
 * <p>
 * Explanation:
 * <p>
 * Letter
 * Index in Reversed Alphabet
 * Index in String
 * Product
 * <p>
 * 'z'
 * 1
 * 1
 * 1
 * <p>
 * 'a'
 * 26
 * 2
 * 52
 * <p>
 * 'z'
 * 1
 * 3
 * 3
 * <p>
 * 'a'
 * 26
 * 4
 * 104
 * <p>
 * The reverse degree is 1 + 52 + 3 + 104 = 160 .
 * <p>
 * Constraints:
 * <p>
 * - 1 <= s.length <= 1000
 * <p>
 * - s contains only lowercase English letters.
 */
public class _3498 {

    public int reverseDegree(String s) {
        int reverseDegree = 0;
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            reverseDegree += (i + 1) * ('z' - s.charAt(i) + 1);
        }
        return reverseDegree;
    }
}
