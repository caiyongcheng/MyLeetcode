package letcode.normal.unansweredquestions.medium;

import letcode.utils.TestUtil;

/**
 * You are given a string word and a non-negative integer k.  Return the total number of substrings of word that
 * contain every vowel ('a', 'e', 'i', 'o', and 'u') at least once and exactly k consonants.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-03-12 09:08
 */
public class _3305 {
    public int countOfSubstrings(String word, int k) {
        // 如果当前是辅音 那么总结果等于 满足元音条件+辅音少1的数量 同时其
        return 0;
    }


    private int getIdx(char ch) {
        switch (ch) {
            case 'a':
                return 0;
            case 'e':
                return 1;
            case 'i':
                return 2;
            case 'o':
                return 3;
            case 'u':
                return 4;
            default:
                return 5;
        }
    }

    private boolean match(int[] arr, int k) {
        for (int i = 0; i < 5; i++) {
            if (arr[i] == 0) {
                return false;
            }
        }
        return arr[5] == k;
    }

    /**
     * Example 1:
     *
     * Input: word = "aeioqq", k = 1
     *
     * Output: 0
     *
     * Explanation:
     *
     * There is no substring with every vowel.
     *
     * Example 2:
     *
     * Input: word = "aeiou", k = 0
     *
     * Output: 1
     *
     * Explanation:
     *
     * The only substring with every vowel and zero consonants is word[0..4], which is "aeiou".
     *
     * Example 3:
     *
     * Input: word = "ieaouqqieaouqq", k = 1
     *
     * Output: 3
     *
     * Explanation:
     *
     * The substrings with every vowel and one consonant are:
     *
     * word[0..5], which is "ieaouq".
     * word[6..11], which is "qieaou".
     * word[7..12], which is "ieaouq".
     * @param args
     */
    public static void main(String[] args) {
        // TestUtil.test();
        TestUtil.test(_3305.class, "=\"iqeaouqi\", =2");
    }

}
