package letcode.normal.easy;

/**
 * 1189. Maximum Number of Balloons
 * Difficulty: Easy
 * Link: https://leetcode.cn/problems/maximum-number-of-balloons/
 * Given a string text , you want to use the characters of text to form as many instances of the word "balloon" as possible.
 * You can use each character in text at most once . Return the maximum number of instances that can be formed.
 */
public class _1189 {

    public int maxNumberOfBalloons(String text) {
        int[] charCntArr = new int[26];
        char[] charArray = text.toCharArray();
        for (char c : charArray) {
            charCntArr[c - 'a']++;
        }
        int maxCnt = text.length();

        if (maxCnt > charCntArr[0]) {
            maxCnt = charCntArr[0];
        }
        if (maxCnt > charCntArr[1]) {
            maxCnt = charCntArr[1];
        }
        if (maxCnt > (charCntArr[11] >>= 1)) {
            maxCnt = charCntArr[11];
        }
        if (maxCnt > charCntArr[13]) {
            maxCnt = charCntArr[13];
        }
        if (maxCnt > (charCntArr[14] >>= 1)) {
            maxCnt = charCntArr[14];
        }
        return maxCnt;
    }
}
