package letcode.normal.difficult;

/**
 * 3518. Smallest Palindromic Rearrangement II
 * Difficulty: Hard
 * Link: https://leetcode.cn/problems/smallest-palindromic-rearrangement-ii/
 * <p>
 * You are given a palindromic string s and an integer k .
 * <p>
 * Return the k-th lexicographically smallest palindromic permutation of s . If there are fewer than k
 * distinct palindromic permutations, return an empty string.
 * <p>
 * Note: Different rearrangements that yield the same palindromic string are considered identical and
 * are counted once.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abba", k = 2
 * <p>
 * Output: "baab"
 * <p>
 * Explanation:
 * <p>
 * - The two distinct palindromic rearrangements of "abba" are "abba" and "baab" .
 * <p>
 * - Lexicographically, "abba" comes before "baab" . Since k = 2 , the output is "baab" .
 * <p>
 * Example 2:
 * <p>
 * Input: s = "aa", k = 2
 * <p>
 * Output: ""
 * <p>
 * Explanation:
 * <p>
 * - There is only one palindromic rearrangement: "aa" .
 * <p>
 * - The output is an empty string since k = 2 exceeds the number of possible rearrangements.
 * <p>
 * Example 3:
 * <p>
 * Input: s = "bacab", k = 1
 * <p>
 * Output: "abcba"
 * <p>
 * Explanation:
 * <p>
 * - The two distinct palindromic rearrangements of "bacab" are "abcba" and "bacab" .
 * <p>
 * - Lexicographically, "abcba" comes before "bacab" . Since k = 1 , the output is "abcba" .
 * <p>
 * Constraints:
 * <p>
 * - 1 <= s.length <= 10 4
 * <p>
 * - s consists of lowercase English letters.
 * <p>
 * - s is guaranteed to be palindromic.
 * <p>
 * - 1 <= k <= 10 6
 */
public class _3518 {

    public String smallestPalindrome(String s, int k) {
        char[] charArray = s.toCharArray();
        int[] char2Cnt = new int[26];

        for (char ch : charArray) {
            char2Cnt[ch - 'a']++;
        }

        for (int i = 0; i < char2Cnt.length; i++) {
            if ((char2Cnt[i] & 1) == 1) {
                charArray[charArray.length >> 1] = (char) ('a' + i);
                --char2Cnt[i];
                break;
            }
        }

        /*
        因为回文是对称的 所以只考虑 前半部分即可。
        那么问题转化为求前半部分的第k大排列
         */
        return buildKth(charArray, char2Cnt, k);

    }


    private String buildKth(char[] charArray, int[] char2Cnt, int k) {
        // 计算有多少种可能
        int totalCnt = 0;
        for (int cnt : char2Cnt) {
            totalCnt += cnt;
        }

        int remainCnt = totalCnt;
        for (int i = 0; i < totalCnt; i++) {

            // 计算选择当前位的可能性
            int selectCnt = 1;
            for (int p = 1; p < remainCnt; p++) {
                selectCnt *= p;
                if (selectCnt > k) {
                    break;
                }
            }

            // 选择哪个字母
            int th = k / selectCnt + 1;
            for (int x = 0; x < char2Cnt.length; x++) {
                if (char2Cnt[x] != 0) {
                    --th;
                    if (th == 0) {
                        char2Cnt[x]--;
                        charArray[i] = (char) (x + 'a');
                        charArray[charArray.length - i - 1] = (char) (x + 'a');
                        k %= selectCnt;
                        remainCnt -= 2;
                    }
                }
            }

        }

        for (int i = 0; i < charArray.length >> 1; i++) {
            charArray[charArray.length - i - 1] = charArray[i];
        }

        return new String(charArray);
    }
}
