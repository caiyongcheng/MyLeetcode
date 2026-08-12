package letcode.normal.medium;

/**
 * 2606. Find the Substring With Maximum Cost
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/find-the-substring-with-maximum-cost/
 * <p>
 * You are given a string s , a string chars of distinct characters and an integer array vals of the
 * same length as chars .
 * <p>
 * The cost of the substring is the sum of the values of each character in the substring. The cost of
 * an empty string is considered 0 .
 * <p>
 * The value of the character is defined in the following way:
 * <p>
 * - If the character is not in the string chars , then its value is its corresponding position
 * (1-indexed) in the alphabet.
 * <p>
 * - For example, the value of 'a' is 1 , the value of 'b' is 2 , and so on. The value of 'z' is 26 .
 * <p>
 * - Otherwise, assuming i is the index where the character occurs in the string chars , then its value
 * is vals[i] .
 * <p>
 * Return the maximum cost among all substrings of the string s .
 * <p>
 * Example 1:
 * <p>
 * Input: s = "adaa", chars = "d", vals = [-1000]
 * Output: 2
 * Explanation: The value of the characters "a" and "d" is 1 and -1000 respectively.
 * The substring with the maximum cost is "aa" and its cost is 1 + 1 = 2.
 * It can be proven that 2 is the maximum cost.
 * <p>
 * Example 2:
 * <p>
 * Input: s = "abc", chars = "abc", vals = [-1,-1,-1]
 * Output: 0
 * Explanation: The value of the characters "a", "b" and "c" is -1, -1, and -1 respectively.
 * The substring with the maximum cost is the empty substring "" and its cost is 0.
 * It can be proven that 0 is the maximum cost.
 * <p>
 * Constraints:
 * <p>
 * - 1 <= s.length <= 10 5
 * <p>
 * - s consist of lowercase English letters.
 * <p>
 * - 1 <= chars.length <= 26
 * <p>
 * - chars consist of distinct lowercase English letters.
 * <p>
 * - vals.length == chars.length
 * <p>
 * - -1000 <= vals[i] <= 1000
 */
public class _2606 {

    public int maximumCostSubstring(String s, String chars, int[] vals) {
        int[] char2CostMap = new int[26];
        char[] charArray = chars.toCharArray();

        for (int i = 0; i < char2CostMap.length; i++) {
            char2CostMap[i] = i + 1;
        }

        for (int i = 0; i < charArray.length; i++) {
            char2CostMap[charArray[i] - 'a'] = vals[i];
        }

        int maxCost = 0;
        int curCost = 0;
        char[] sCharArr = s.toCharArray();
        for (char ch : sCharArr) {
            curCost += char2CostMap[ch - 'a'];
            if (curCost < 0) {
                curCost = 0;
            } else if (curCost > maxCost) {
                maxCost = curCost;
            }
        }
        return maxCost;
    }
}
