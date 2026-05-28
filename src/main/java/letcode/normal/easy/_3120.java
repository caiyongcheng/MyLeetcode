package letcode.normal.easy;

/**
 * 3120. Count the Number of Special Characters I
 * Difficulty: Easy
 * Link: https://leetcode.cn/problems/count-the-number-of-special-characters-i/
 * You are given a string word . A letter is called special if it appears both in lowercase and uppercase in word .
 * Return the number of special letters in word .
 */
public class _3120 {

    public int numberOfSpecialChars(String word) {
        int lowerMask = 0;
        int upperMask = 0;
        for (int i = 0, n = word.length(); i < n; i++) {
            char c = word.charAt(i);
            if (c <= 'Z') {
                upperMask |= 1 << (c - 'A');
            } else {
                lowerMask |= 1 << (c - 'a');
            }
        }
        // 大小写均出现的字母对应位同时为 1
        return Integer.bitCount(lowerMask & upperMask);
    }

}
