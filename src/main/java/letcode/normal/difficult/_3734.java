package letcode.normal.difficult;

/**
 * 3734. Lexicographically Smallest Palindromic Permutation Greater Than Target
 * Difficulty: Hard
 * Link: https://leetcode.cn/problems/lexicographically-smallest-palindromic-permutation-greater-than-target/
 * <p>
 * You are given two strings s and target , each of length n , consisting of lowercase English letters.
 * <p>
 * Return the lexicographically smallest string that is both a palindromic permutation of s and
 * strictly greater than target . If no such permutation exists, return an empty string.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "baba", target = "abba"
 * <p>
 * Output: "baab"
 * <p>
 * Explanation:
 * <p>
 * - The palindromic permutations of s (in lexicographical order) are "abba" and "baab" .
 * <p>
 * - The lexicographically smallest permutation that is strictly greater than target is "baab" .
 * <p>
 * Example 2:
 * <p>
 * Input: s = "baba", target = "bbaa"
 * <p>
 * Output: ""
 * <p>
 * Explanation:
 * <p>
 * - The palindromic permutations of s (in lexicographical order) are "abba" and "baab" .
 * <p>
 * - None of them is lexicographically strictly greater than target . Therefore, the answer is "" .
 * <p>
 * Example 3:
 * <p>
 * Input: s = "abc", target = "abb"
 * <p>
 * Output: ""
 * <p>
 * Explanation:
 * <p>
 * s has no palindromic permutations. Therefore, the answer is "" .
 * <p>
 * Example 4:
 * <p>
 * Input: s = "aac", target = "abb"
 * <p>
 * Output: "aca"
 * <p>
 * Explanation:
 * <p>
 * - The only palindromic permutation of s is "aca" .
 * <p>
 * - "aca" is strictly greater than target . Therefore, the answer is "aca" .
 * <p>
 * Constraints:
 * <p>
 * - 1 <= n == s.length == target.length <= 300
 * <p>
 * - s and target consist of only lowercase English letters.
 */
public class _3734 {

    public String lexPalindromicPermutation(String s, String target) {
        // 长度为 1 时没有前半段，直接比较唯一字符
        if (s.length() == 1) {
            return s.charAt(0) > target.charAt(0) ? s : "";
        }

        // 假设结果是p
        // 那么有p的前半部分>=target的前半部分
        // 如果存在相等的情况，那么验证后半部分能否满足条件，满足的话就是结果
        // 否则按_3720的方式找出满足的前半部分 即是结果

        char[] sCharArray = s.toCharArray();
        int length = sCharArray.length;
        int preLen = length >> 1;
        int[] counts = getCounts(sCharArray, length);

        // 如果s无法构成回文串 那么直接返回
        if (notPalindromic(counts)) {
            return "";
        }

        // 处理长度是奇数的情况
        if ((length & 1) == 1) {
            for (int i = 0; i < counts.length; i++) {
                if ((counts[i] & 1) == 1) {
                    --counts[i];
                    sCharArray[preLen] = (char) (i + 'a');
                    break;
                }
            }
        }

        // 判断前半部分是否可能相等
        boolean preMatch = true;
        boolean suffixMatch = false;
        char[] tCharArray = target.toCharArray();
        int[] preTargetCount = getCounts(tCharArray, preLen);
        for (int i = 0; i < preTargetCount.length; i++) {
            if (preTargetCount[i] != counts[i] >> 1) {
                preMatch = false;
                break;
            }
        }
        // 如果前缀相等 验证后缀是否完全大于
        if (preMatch) {
            if ((length & 1) == 0 || sCharArray[preLen] == tCharArray[preLen]) {
                for (int i = preLen; i < length; i++) {
                    if (tCharArray[length - 1 - i] > tCharArray[i]) {
                        suffixMatch = true;
                        break;
                    } else if (tCharArray[length - 1 - i] < tCharArray[i]) {
                        break;
                    }
                }
            } else {
                suffixMatch = sCharArray[preLen] > tCharArray[preLen];
            }
        }

        // 前后缀都满足
        if (preMatch && suffixMatch) {
            return build(preLen, sCharArray, tCharArray, length);
        }

        // 否则的话 前缀走3720的逻辑
        for (int i = 0; i < counts.length; i++) {
            counts[i] = counts[i] >> 1;
        }
        char[] preCharArr = lexPalindromicPermutationFrom3734(counts, tCharArray, preLen);
        if (preCharArr == null) {
            return "";
        }
        return  build(preLen, sCharArray, preCharArr, length);

    }


    private String build(int preLen, char[] sCharArray, char[] tCharArray, int length) {
        for (int i = 0; i < preLen; i++) {
            sCharArray[i] = tCharArray[i];
            sCharArray[length - i - 1] = tCharArray[i];
        }
        return new String(sCharArray);
    }


    private int[] getCounts(char[] charArray, int len) {
        int[] counts = new int[26];
        for (int i = 0; i < len; i++) {
            counts[charArray[i] - 'a']++;
        }
        return counts;
    }

    private boolean notPalindromic(int[] counts) {
        int oddCount = 0;
        for (int count : counts) {
            if ((count & 1) == 1) {
                oddCount++;
            }
        }
        return oddCount > 1;
    }

    private char[] lexPalindromicPermutationFrom3734(int[] charCountArr, char[] tCharArr, int len) {
        char[] ans = new char[len];
        int matchIdx = 0;
        for (; matchIdx < len; matchIdx++) {
            if (charCountArr[tCharArr[matchIdx] - 'a'] <= 0) {
                break;
            }
            --charCountArr[tCharArr[matchIdx] - 'a'];
            ans[matchIdx] = tCharArr[matchIdx];
        }

        // 完全相等的情况 回退到倒数第二位 开始尝试
        if (matchIdx >= ans.length) {
            charCountArr[ans[matchIdx - 1] - 'a']++;
            --matchIdx;
        }

        while (matchIdx >= 0) {
            for (int i = tCharArr[matchIdx] - 'a' + 1; i < charCountArr.length; i++) {
                if (charCountArr[i] <= 0) {
                    continue;
                }
                ans[matchIdx] = (char) (i + 'a');
                charCountArr[i]--;
                ++matchIdx;
                for (int j = 0; j < charCountArr.length; j++) {
                    while (charCountArr[j] > 0) {
                        ans[matchIdx] = (char) (j + 'a');
                        ++matchIdx;
                        --charCountArr[j];
                    }
                }
                return ans;
            }
            if (matchIdx > 0) {
                charCountArr[ans[matchIdx - 1] - 'a']++;
            }
            --matchIdx;
        }
        return null;
    }
}
