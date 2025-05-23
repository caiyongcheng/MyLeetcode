package letcode.normal.unansweredquestions.medium;

import letcode.utils.TestUtil;

/**
 * You are given two strings word1 and word2.
 * A string x is called valid if x can be rearranged to have word2 as a  prefix .
 * Return the total number of valid  substrings  of word1.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-01-09 09:04
 */
public class _3297 {

    public long validSubstringCount(String word1, String word2) {
        int[] ch2CntMap = new int[26];

        int len = word2.length();
        for (int i = 0; i < len; i++) {
            ch2CntMap[word2.charAt(i) - 'a']++;
        }

        int needMatchCnt = 0;
        for (int cnt : ch2CntMap) {
            needMatchCnt += cnt > 0 ? 1 : 0;
        }

        long ans = 0;
        int length = word1.length();
        int endIdx = 0;
        int chIdx;
        for (int startIdx = 0; startIdx < length; startIdx++) {
            while (endIdx < length && needMatchCnt > 0) {
                chIdx = word1.charAt(endIdx) - 'a';
                ch2CntMap[chIdx]--;
                if (ch2CntMap[chIdx] == 0) {
                    --needMatchCnt;
                }
                endIdx++;
            }
            if (needMatchCnt > 0) {
                break;
            }
            ans += length - endIdx + 1;
            chIdx = word1.charAt(startIdx) - 'a';
            ch2CntMap[chIdx]++;
            if (ch2CntMap[chIdx] == 1) {
                ++needMatchCnt;
            }
        }
        return ans;
    }


    /**
     * Example 1:
     *
     * Input: word1 = "bcca", word2 = "abc"
     *
     * Output: 1
     *
     * Explanation:
     *
     * The only valid substring is "bcca" which can be rearranged to "abcc" having "abc" as a prefix.
     *
     * Example 2:
     *
     * Input: word1 = "abcabc", word2 = "abc"
     *
     * Output: 10
     *
     * Explanation:
     *
     * All the substrings except substrings of size 1 and size 2 are valid.
     *
     * Example 3:
     *
     * Input: word1 = "abcabc", word2 = "aaabc"
     *
     * Output: 0
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
