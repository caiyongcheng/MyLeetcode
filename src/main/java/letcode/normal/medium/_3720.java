package letcode.normal.medium;


/**
 * 3720. Lexicographically Smallest Permutation Greater Than Target
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/lexicographically-smallest-permutation-greater-than-target/
 * <p>
 * You are given two strings s and target , both having length n , consisting of lowercase English
 * letters.
 * <p>
 * Return the lexicographically smallest permutation of s that is strictly greater than target . If no
 * permutation of s is lexicographically strictly greater than target , return an empty string.
 * <p>
 * A string a is lexicographically strictly greater than a string b (of the same length) if in the
 * first position where a and b differ, string a has a letter that appears later in the alphabet than
 * the corresponding letter in b .
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abc", target = "bba"
 * <p>
 * Output: "bca"
 * <p>
 * Explanation:
 * <p>
 * - The permutations of s (in lexicographical order) are "abc" , "acb" , "bac" , "bca" , "cab" , and
 * "cba" .
 * <p>
 * - The lexicographically smallest permutation that is strictly greater than target is "bca" .
 * <p>
 * Example 2:
 * <p>
 * Input: s = "leet", target = "code"
 * <p>
 * Output: "eelt"
 * <p>
 * Explanation:
 * <p>
 * - The permutations of s (in lexicographical order) are "eelt" , "eetl" , "elet" , "elte" , "etel" ,
 * "etle" , "leet" , "lete" , "ltee" , "teel" , "tele" , and "tlee" .
 * <p>
 * - The lexicographically smallest permutation that is strictly greater than target is "eelt" .
 * <p>
 * Example 3:
 * <p>
 * Input: s = "baba", target = "bbaa"
 * <p>
 * Output: ""
 * <p>
 * Explanation:
 * <p>
 * - The permutations of s (in lexicographical order) are "aabb" , "abab" , "abba" , "baab" , "baba" ,
 * and "bbaa" .
 * <p>
 * - None of them is lexicographically strictly greater than target . Therefore, the answer is "" .
 * <p>
 * Constraints:
 * <p>
 * - 1 <= s.length == target.length <= 300
 * <p>
 * - s and target consist of only lowercase English letters.
 */
public class _3720 {

    public String lexGreaterPermutation(String s, String target) {
        // 首先找到小于等于target的排列t
        // 找到第一个大于等于t的排列即可
        // 从后往前第一个位置 a，再找到把a更靠前，且字母更小的b 交换位置

        int[] charCountArr = new int[26];
        char[] sCharArr = s.toCharArray();
        for (char ch : sCharArr) {
            charCountArr[ch - 'a']++;
        }

        char[] ans = new char[sCharArr.length];
        char[] tCharArr = target.toCharArray();
        int matchIdx = 0;
        for (; matchIdx < tCharArr.length; matchIdx++) {
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

        // 尝试组合出严格大于target的排序
        while (matchIdx >= 0) {
            // 在matchIdx 能找到匹配的结果
            for (int i = tCharArr[matchIdx] - 'a' + 1; i < charCountArr.length; i++) {
                if (charCountArr[i] <= 0) {
                    continue;
                }
                // 能够组合出大于的组合 且是最先匹配到的 是字典序最小的
                ans[matchIdx] = (char) (i + 'a');
                charCountArr[i]--;
                // match位上已经满足严格大于条件 尾部组合需要取最小的满足条件
                ++matchIdx;
                for (int j = 0; j < charCountArr.length; j++) {
                    while (charCountArr[j] > 0) {
                        ans[matchIdx] = (char) (j + 'a');
                        ++matchIdx;
                        --charCountArr[j];
                    }
                }
                return new String(ans);
            }
            // 尝试上一个matchIdx
            if (matchIdx > 0) {
                charCountArr[ans[matchIdx - 1] - 'a']++;
            }
            --matchIdx;
        }
        return "";
    }



}
