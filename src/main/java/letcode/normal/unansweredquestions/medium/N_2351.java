package letcode.normal.unansweredquestions.medium;

import letcode.utils.TestUtil;

/**
 * You are given two 0-indexed strings word1 and word2.
 * A move consists of choosing two indices i and j such
 * that 0 <= i < word1.length and 0 <= j < word2.length and swapping word1[i] with word2[j].
 * Return true if it is possible to get the number of distinct characters in word1 and word2 to be equal with exactly
 * one move. Return false otherwise.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-11-18 10:44
 */
public class N_2351 {

    public boolean isItPossible(String word1, String word2) {
        int[] char2CntMap1 = getChar2CntMap(word1);
        int[] char2CntMap2 = getChar2CntMap(word2);
        int charTypeCnt1 = getCharTypeCnt(char2CntMap1);
        int charTypeCnt2 = getCharTypeCnt(char2CntMap2);

        boolean hasOnlyChar1 = false;
        boolean hasOnlyChar2 = false;
        boolean hasOnlyOneChar1 = false;
        boolean hasOnlyOneChar2 = false;
        for (int i = 0; i < char2CntMap1.length; i++) {
            if (char2CntMap1[i] > 1 && char2CntMap2[i] == 0) {
                hasOnlyChar1 = true;
            } else if (char2CntMap2[i] > 1 && char2CntMap1[i] == 0) {
                hasOnlyChar2 = true;
            }
            if (char2CntMap1[i] == 1 && char2CntMap2[i] == 0) {
                hasOnlyOneChar1 = true;
            } else if (char2CntMap2[i] == 1 && char2CntMap1[i] == 0) {
                hasOnlyOneChar2 = true;
            }
        }

        // 如果相等 交换相同字符 或者 交换两边只存在一个的字符 或者两边都互相不存在的字符，且数量大于1
        if (charTypeCnt1 == charTypeCnt2) {
            return (hasOnlyChar1 && hasOnlyChar2)
                    || (hasOnlyOneChar1 && hasOnlyOneChar2)
                    || (hasSameCharacterAndCountGe1(char2CntMap1, char2CntMap2));
        }

        // 如果相差两个字符 那么多的 要把多的那边要把两边唯一的一个字符 交换给少的
        if (Math.abs(charTypeCnt1 - charTypeCnt2) == 2) {
            return charTypeCnt1 > charTypeCnt2 ? hasOnlyOneChar1 : hasOnlyOneChar2;
        }

        // 如果两边相差一个字符 那么多的那边 要把仅有的一个，且少的那边有的字符交换过去, 而少的那边也必须存在多的那边有的字符
        if (Math.abs(charTypeCnt1 - charTypeCnt2) == 1) {
            return  charTypeCnt1 > charTypeCnt2
                    ? hasCountOneCharacterAndOtherCountMore(char2CntMap1, char2CntMap2)
                    : hasCountOneCharacterAndOtherCountMore(char2CntMap2, char2CntMap1);
        }

        return false;
    }

    private static boolean hasCountOneCharacterAndOtherCountMore(int[] char2CntMap1, int[] char2CntMap2) {
        for (int i = 0; i < char2CntMap1.length; i++) {
            if (char2CntMap1[i] == 1 && char2CntMap2[i] > 0) {
                for (int j = 0; j < char2CntMap2.length; j++) {
                    if (char2CntMap2[j] > 1 && char2CntMap1[j] > 0 && i != j) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean hasSameCharacterAndCountGe1(int[] char2CntMap1, int[] char2CntMap2) {
        for (int i = 0; i < char2CntMap1.length; i++) {
            if (char2CntMap1[i] == char2CntMap2[i] && char2CntMap1[i] > 0) {
                return true;
            }
        }
        return false;
    }


    private int[] getChar2CntMap(String s) {
        int[] map = new int[26];
        for (char c : s.toCharArray()) {
            map[c - 'a']++;
        }
        return map;
    }

    private int getCharTypeCnt(int[] char2CntMap) {
        int cnt = 0;
        for (int i = 0; i < 26; i++) {
            if (char2CntMap[i] > 0) {
                cnt++;
            }
        }
        return cnt;
    }


    /**
     * Example 1:
     *
     * Input: word1 = "ac", word2 = "b"
     * Output: false
     * Explanation: Any pair of swaps would yield two distinct characters in the first string, and one in the second string.
     * Example 2:
     *
     * Input: word1 = "abcc", word2 = "aab"
     * Output: true
     * Explanation: We swap index 2 of the first string with index 0 of the second string. The resulting strings are word1 = "abac" and word2 = "cab", which both have 3 distinct characters.
     * Example 3:
     *
     * Input: word1 = "abcde", word2 = "fghij"
     * Output: true
     * Explanation: Both resulting strings will have 5 distinct characters, regardless of which indices we swap.
     * @param args
     */
    public static void main(String[] args) {
        //TestUtil.test(_2351.class);
        TestUtil.test(N_2351.class, "=aa, =bcd");
    }

}
