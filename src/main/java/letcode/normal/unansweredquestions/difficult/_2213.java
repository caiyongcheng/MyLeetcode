package letcode.normal.unansweredquestions.difficult;

/**
 * 2213. Longest Substring of One Repeating Character
 * Difficulty: Hard
 * Link: https://leetcode.cn/problems/longest-substring-of-one-repeating-character/
 * <p>
 * You are given a 0-indexed string s . You are also given a 0-indexed string queryCharacters of length
 * k and a 0-indexed array of integer indices queryIndices of length k , both of which are used to
 * describe k queries.
 * <p>
 * The i th query updates the character in s at index queryIndices[i] to the character
 * queryCharacters[i] .
 * <p>
 * Return an array lengths of length k where lengths[i] is the length of the longest substring of s
 * consisting of only one repeating character after the i th query is performed.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "babacc", queryCharacters = "bcb", queryIndices = [1,3,3]
 * Output: [3,3,4]
 * Explanation:
 * - 1 st query updates s = " b b b acc". The longest substring consisting of one repeating character
 * is "bbb" with length 3.
 * - 2 nd query updates s = "bbb c cc ".
 * The longest substring consisting of one repeating character can be "bbb" or "ccc" with length 3.
 * - 3 rd query updates s = " bbb b cc". The longest substring consisting of one repeating character is
 * "bbbb" with length 4.
 * Thus, we return [3,3,4].
 * <p>
 * Example 2:
 * <p>
 * Input: s = "abyzz", queryCharacters = "aa", queryIndices = [2,1]
 * Output: [2,3]
 * Explanation:
 * - 1 st query updates s = "ab a zz ". The longest substring consisting of one repeating character is
 * "zz" with length 2.
 * - 2 nd query updates s = " a a a zz". The longest substring consisting of one repeating character is
 * "aaa" with length 3.
 * Thus, we return [2,3].
 * <p>
 * Constraints:
 * <p>
 * - 1 <= s.length <= 10 5
 * <p>
 * - s consists of lowercase English letters.
 * <p>
 * - k == queryCharacters.length == queryIndices.length
 * <p>
 * - 1 <= k <= 10 5
 * <p>
 * - queryCharacters consists of lowercase English letters.
 * <p>
 * - 0 <= queryIndices[i] < s.length
 */
public class _2213 {

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        return null;
    }
}
