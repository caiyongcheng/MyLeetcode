package letcode.normal.easy;

import letcode.utils.TestUtil;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * You are given a 0-indexed string array words, where words[i] consists of lowercase English letters.
 * In one operation, select any index i such that 0 < i < words.length and words[i - 1] and words[i] are anagrams,
 * and delete words[i] from words.
 * Keep performing this operation as long as you can select an index that satisfies the conditions.
 * Return words after performing all operations.
 * It can be shown that selecting the indices for each operation in any arbitrary order will lead to the same result.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase using
 * all the original letters exactly once. For example, "dacb" is an anagram of "abdc".
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-10-19 08:32
 */
public class _2273 {

    public List<String> removeAnagrams(String[] words) {
        List<String> ans = new ArrayList<>();
        int lastWordLength = 0;
        int[] lastComposition = new int[26];
        int[] curComposition;
        for (String word : words) {
            curComposition = getComposition(word);
            if (lastWordLength != word.length() || isDifferenceComposition(lastComposition, curComposition)) {
                ans.add(word);
                lastComposition = curComposition;
                lastWordLength = word.length();
            }
        }
        return ans;
    }

    private int[] getComposition(String word) {
        int[] composition = new int[26];
        for (byte ch : word.getBytes(StandardCharsets.UTF_8)) {
            composition[ch - 'a']++;
        }
        return composition;
    }

    private boolean isDifferenceComposition(int[] c1, int[] c2) {
        for (int i = 0; i < 26; i++) {
            if (c1[i] != c2[i]) {
                return true;
            }
        }
        return false;
    }


    /**
     * Example 1:
     *
     * Input: words = ["abba","baba","bbaa","cd","cd"]
     * Output: ["abba","cd"]
     * Explanation:
     * One of the ways we can obtain the resultant array is by using the following operations:
     * - Since words[2] = "bbaa" and words[1] = "baba" are anagrams, we choose index 2 and delete words[2].
     *   Now words = ["abba","baba","cd","cd"].
     * - Since words[1] = "baba" and words[0] = "abba" are anagrams, we choose index 1 and delete words[1].
     *   Now words = ["abba","cd","cd"].
     * - Since words[2] = "cd" and words[1] = "cd" are anagrams, we choose index 2 and delete words[2].
     *   Now words = ["abba","cd"].
     * We can no longer perform any operations, so ["abba","cd"] is the final answer.
     * Example 2:
     *
     * Input: words = ["a","b","c","d","e"]
     * Output: ["a","b","c","d","e"]
     * Explanation:
     * No two adjacent strings in words are anagrams of each other, so no operations are performed.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
