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
        char highScoreChar;
        char lowScoreChar;
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
        int highScoreCharCount = 0;
        int lowScoreCharCount = 0;
        char[] charArray = s.toCharArray();
        for (char curChar : charArray) {
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


}
