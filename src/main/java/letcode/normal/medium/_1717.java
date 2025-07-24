package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * You are given a string s and two integers x and y. You can perform two types of operations any number of times.
 * Remove substring "ab" and gain x points. For example, when removing "ab" from "cabxbae" it becomes "cxbae".
 * Remove substring "ba" and gain y points. For example, when removing "ba" from "cabxbae" it becomes "cabxe".
 * Return the maximum points you can gain after applying the above operations on s.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-07-24 14:02
 */
public class _1717 {

    public int maximumGain(String s, int x, int y) {
        int highScoreChar;
        int lowScoreChar;
        int highScore;
        int lowScore;
        if (x >= y) {
            highScoreChar = 'a';
            lowScoreChar = 'b';
            highScore = x;
            lowScore = y;
        } else {
            highScoreChar = 'b';
            lowScoreChar = 'a';
            highScore = y;
            lowScore = x;
        }

        int ans = 0;
        int length = s.length();
        char curChar;
        int highScoreCharCount = 0;
        int lowScoreCharCount = 0;
        for (int i = 0; i < length; i++) {
            curChar = s.charAt(i);
            if (curChar == highScoreChar) {
                highScoreCharCount++;
            } else if (curChar == lowScoreChar)  {
                if (highScoreCharCount > 0) {
                    highScoreCharCount--;
                    ans += highScore;
                } else {
                    lowScoreCharCount++;
                }
            } else {
                ans += Math.min(highScoreCharCount, lowScoreCharCount) * lowScore;
                highScoreCharCount = 0;
                lowScoreCharCount = 0;
            }
        }
        ans += Math.min(highScoreCharCount, lowScoreCharCount) * lowScore;
        return ans;
    }


    /**
     * Example 1:
     *
     * Input: s = "cdbcbbaaabab", x = 4, y = 5
     * Output: 19
     * Explanation:
     * - Remove the "ba" underlined in "cdbcbbaaabab". Now, s = "cdbcbbaaab" and 5 points are added to the score.
     * - Remove the "ab" underlined in "cdbcbbaaab". Now, s = "cdbcbbaa" and 4 points are added to the score.
     * - Remove the "ba" underlined in "cdbcbbaa". Now, s = "cdbcba" and 5 points are added to the score.
     * - Remove the "ba" underlined in "cdbcba". Now, s = "cdbc" and 5 points are added to the score.
     * Total score = 5 + 4 + 5 + 5 = 19.
     * Example 2:
     *
     * Input: s = "aabbaaxybbaabb", x = 5, y = 4
     * Output: 20
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
