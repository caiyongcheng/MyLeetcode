package letcode.normal.medium;

/**
 * 2904. Shortest and Lexicographically Smallest Beautiful String
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/shortest-and-lexicographically-smallest-beautiful-string/
 * <p>
 * You are given a binary string s and a positive integer k .
 * <p>
 * A substring of s is beautiful if the number of 1 's in it is exactly k .
 * <p>
 * Let len be the length of the shortest beautiful substring.
 * <p>
 * Return the lexicographically smallest beautiful substring of string s with length equal to len . If
 * s doesn't contain a beautiful substring, return an empty string .
 * <p>
 * A string a is lexicographically larger than a string b (of the same length) if in the first position
 * where a and b differ, a has a character strictly larger than the corresponding character in b .
 * <p>
 * - For example, "abcd" is lexicographically larger than "abcc" because the first position they differ
 * is at the fourth character, and d is greater than c .
 * <p>
 * Example 1:
 * <p>
 * Input: s = "100011001", k = 3
 * Output: "11001"
 * Explanation: There are 7 beautiful substrings in this example:
 * 1. The substring " 100011 001".
 * 2. The substring " 1000110 01".
 * 3. The substring " 10001100 1".
 * 4. The substring "1 00011001 ".
 * 5. The substring "10 0011001 ".
 * 6. The substring "100 011001 ".
 * 7. The substring "1000 11001 ".
 * The length of the shortest beautiful substring is 5.
 * The lexicographically smallest beautiful substring with length 5 is the substring "11001".
 * <p>
 * Example 2:
 * <p>
 * Input: s = "1011", k = 2
 * Output: "11"
 * Explanation: There are 3 beautiful substrings in this example:
 * 1. The substring " 101 1".
 * 2. The substring "1 011 ".
 * 3. The substring "10 11 ".
 * The length of the shortest beautiful substring is 2.
 * The lexicographically smallest beautiful substring with length 2 is the substring "11".
 * <p>
 * Example 3:
 * <p>
 * Input: s = "000", k = 1
 * Output: ""
 * Explanation: There are no beautiful substrings in this example.
 * <p>
 * Constraints:
 * <p>
 * - 1 <= s.length <= 100
 * <p>
 * - 1 <= k <= s.length
 */
public class _2904 {

    public String shortestBeautifulSubstring(String s, int k) {
        // 记录所有 1 的位置
        int bigCount = 0;
        int[] oneBitIdxArr = new int[s.length()];
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == '1') {
                oneBitIdxArr[bigCount++] = i;
            }
        }

        if (bigCount < k) {
            return "";
        }

        int shortestLen = Integer.MAX_VALUE;
        String answer = "";
        for (int i = 0; i + k <= bigCount; i++) {
            int start = oneBitIdxArr[i];
            int end = oneBitIdxArr[i + k - 1];
            int currentLen = end - start + 1;
            String current = s.substring(start, end + 1);

            // 连续 k 个 1 确定一个候选区间，先取最短，再取字典序最小。
            if (currentLen < shortestLen
                    || (currentLen == shortestLen && current.compareTo(answer) < 0)) {
                shortestLen = currentLen;
                answer = current;
            }
        }
        return answer;
    }
}
