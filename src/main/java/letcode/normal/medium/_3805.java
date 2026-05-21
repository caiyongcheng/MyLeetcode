package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * You are given a string word and an integer k.
 * We consider word to be k-special if |freq(word[i]) - freq(word[j])| <= k for all indices i and j in the string.
 * Here, freq(x) denotes the frequency of the character x in word, and |y| denotes the absolute value of y.
 * Return the minimum number of characters you need to delete to make word k-special.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-06-21 23:37
 */
public class _3805 {

    public int minimumDeletions(String word, int k) {
        int[] char2NoZeroCntArr = getChar2CntArr(word);
        int ans = 100001;
        int deleteCnt;
        for (int minRemainingCnt : char2NoZeroCntArr) {
            if (minRemainingCnt == 0) {
                continue;
            }
            deleteCnt = 0;
            for (int charCnt : char2NoZeroCntArr) {
                if (charCnt < minRemainingCnt) {
                    deleteCnt += charCnt;
                } else if (charCnt > minRemainingCnt + k) {
                    deleteCnt += charCnt - minRemainingCnt - k;
                }
            }
            ans = Math.min(ans, deleteCnt);
        }
        return ans;
    }

    private static int[] getChar2CntArr(String word) {
        int[] char2CntArr = new int[26];
        int length = word.length();
        for (int i = 0; i < length; i++) {
            char2CntArr[word.charAt(i) - 'a']++;
        }
        return char2CntArr;
    }


}
