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


}
