package letcode.normal.medium;

import java.util.*;

/**
 * 3121. Count the Number of Special Characters II
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/count-the-number-of-special-characters-ii/
 * You are given a string word . A letter c is called special if it appears both in lowercase and uppercase in word ,
 * and every lowercase occurrence of c appears before the first uppercase occurrence of c .
 * Return the number of special letters in word .
 */
public class _3121 {

    public int numberOfSpecialChars(String word) {
        int[][] flag = new int[26][2];

        byte[] bytes = word.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] >= 'a' && bytes[i] <= 'z') {
                flag[bytes[i] - 97][0] = i + 1;
            } else if (flag[bytes[i] - 65][1] == 0) {
                flag[bytes[i] - 65][1] = i + 1;
            }
        }

        int ans = 0;
        for (int[] charFlag : flag) {
            if (charFlag[0] != 0 && charFlag[1] != 0 && charFlag[0] < charFlag[1]) {
                ans++;
            }
        }
        return ans;
    }

}
