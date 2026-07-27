package letcode.normal.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 131. Palindrome Partitioning
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/palindrome-partitioning/
 * <p>
 * Given a string s , partition s such that every substring of the partition is a palindrome . Return
 * all possible palindrome partitioning of s .
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 * <p>
 * Example 2:
 * <p>
 * Input: s = "a"
 * Output: [["a"]]
 * <p>
 * Constraints:
 * <p>
 * - 1 <= s.length <= 16
 * <p>
 * - s contains only lowercase English letters.
 */
public class _131 {

    public List<List<String>> partition(String s) {
        int length = s.length();
        List<List<String>>[] dp = new ArrayList[length + 1];

        for (int i = 0; i < dp.length; i++) {
            dp[i] = new ArrayList<>();
        }

        dp[length].add(new ArrayList<>());
        List<String> partition = new ArrayList<>();
        partition.add(s.charAt(length - 1) + "");
        dp[length - 1].add(partition);

        for (int left = length - 2; left > -1; --left) {
            for (int right = left; right < length; right++) {
                if (isPalindrome(s, left, right)) {
                    String palindrome = s.substring(left, right + 1);
                    for (int i = 0; i < dp[right + 1].size(); i++) {
                        dp[left].add(copy(palindrome, dp[right + 1].get(i)));
                    }
                }
            }
        }

        return dp[0];

    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            ++left;
            --right;
        }
        return true;
    }

    private List<String> copy(String s, List<String> partition) {
        List<String> copyResult = new ArrayList<>();
        copyResult.add(s);
        copyResult.addAll(partition);
        return copyResult;
    }


}
