package letcode.normal.medium;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 139. Word Break
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/word-break/
 * <p>
 * Given a string s and a dictionary of strings wordDict , return true if s can be segmented into a
 * space-separated sequence of one or more dictionary words.
 * <p>
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * <p>
 * Example 2:
 * <p>
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * <p>
 * Example 3:
 * <p>
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 * <p>
 * Constraints:
 * <p>
 * - 1 <= s.length <= 300
 * <p>
 * - 1 <= wordDict.length <= 1000
 * <p>
 * - 1 <= wordDict[i].length <= 20
 * <p>
 * - s and wordDict[i] consist of only lowercase English letters.
 * <p>
 * - All the strings of wordDict are unique .
 */
public class _139 {

    public boolean wordBreak(String s, List<String> wordDict) {
        Map<Character, List<String>> groupByFirstCharacter =
                wordDict.stream().collect(Collectors.groupingBy(word -> word.charAt(0)));
        return search(s, groupByFirstCharacter, new int[s.length()], 0) == 1;
    }

    private int search(String s, Map<Character, List<String>> groupByFirstCharacter, int[] cache, int index) {
        if (cache[index] != 0) {
            return cache[index];
        }

        List<String> candidateWordDict = groupByFirstCharacter.get(s.charAt(0));
        if (candidateWordDict == null || candidateWordDict.isEmpty()) {
            cache[index] = -1;
            return cache[index];
        }

        cache[index] = -1;
        int nextIndex;
        for (String word : candidateWordDict) {
            if (s.startsWith(word) && cache[index] != 1) {
                nextIndex = index + word.length();
                if (nextIndex >= cache.length || cache[nextIndex] == 1) {
                    cache[index] = 1;
                } else if (cache[nextIndex] == 0) {
                    cache[index] = search(s.substring(word.length()), groupByFirstCharacter, cache, index + word.length());
                }
            }
        }
        return cache[index];
    }

}
