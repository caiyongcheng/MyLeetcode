package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 * You are given a string s consisting of lowercase English letters.
 * Your task is to find the maximum difference diff = a1 - a2 between the frequency of characters a1 and a2 in the string such that:
 * a1 has an odd frequency in the string. a2 has an even frequency in the string. Return this maximum difference.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-06-10 09:02
 */
public class _3422 {

    public int maxDifference(String s) {
        int[] letter2CntArr = new int[26];
        int length = s.length();
        for (int i = 0; i < length; i++) {
            letter2CntArr[s.charAt(i) - 'a']++;
        }


        int maxOdd = 0;
        int minEven = Integer.MAX_VALUE;
        for (int cnt : letter2CntArr) {
            if ((cnt & 1) == 1) {
                if (cnt > maxOdd) {
                    maxOdd = cnt;
                }
            } else if (cnt != 0 && cnt < minEven) {
                minEven = cnt;
            }
        }
        return minEven == Integer.MAX_VALUE ? 0 : maxOdd - minEven;
    }


    /**
     * Example 1:
     *
     * Input: s = "aaaaabbc"
     *
     * Output: 3
     *
     * Explanation:
     *
     * The character 'a' has an odd frequency of 5, and 'b' has an even frequency of 2.
     * The maximum difference is 5 - 2 = 3.
     * Example 2:
     *
     * Input: s = "abcabcab"
     *
     * Output: 1
     *
     * Explanation:
     *
     * The character 'a' has an odd frequency of 3, and 'c' has an even frequency of 2.
     * The maximum difference is 3 - 2 = 1.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
