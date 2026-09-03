package letcode.normal.medium;

/**
 * 1541. Minimum Insertions to Balance a Parentheses String
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/minimum-insertions-to-balance-a-parentheses-string/
 * <p>
 * Given a parentheses string s containing only the characters '(' and ')' . A parentheses string is
 * balanced if:
 * <p>
 * - Any left parenthesis '(' must have a corresponding two consecutive right parenthesis '))' .
 * <p>
 * - Left parenthesis '(' must go before the corresponding two consecutive right parenthesis '))' .
 * <p>
 * In other words, we treat '(' as an opening parenthesis and '))' as a closing parenthesis.
 * <p>
 * - For example, "())" , "())(())))" and "(())())))" are balanced, ")()" , "()))" and "(()))" are not
 * balanced.
 * <p>
 * You can insert the characters '(' and ')' at any position of the string to balance it if needed.
 * <p>
 * Return the minimum number of insertions needed to make s balanced.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "(()))"
 * Output: 1
 * Explanation: The second '(' has two matching '))', but the first '(' has only ')' matching. We need
 * to add one more ')' at the end of the string to be "(())))" which is balanced.
 * <p>
 * Example 2:
 * <p>
 * Input: s = "())"
 * Output: 0
 * Explanation: The string is already balanced.
 * <p>
 * Example 3:
 * <p>
 * Input: s = "))())("
 * Output: 3
 * Explanation: Add '(' to match the first '))', Add '))' to match the last '('.
 * <p>
 * Constraints:
 * <p>
 * - 1 <= s.length <= 10 5
 * <p>
 * - s consists of '(' and ')' only.
 */
public class _1541 {

    public int minInsertions(String s) {
        int needRight = 0;
        int insertCnt = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                // 新的左括号前若缺一个右括号，先补齐前一个右括号对。
                if ((needRight & 1) == 1) {
                    ++insertCnt;
                    --needRight;
                }
                needRight += 2;
            } else {
                --needRight;
                // 当前右括号没有可匹配的左括号，补一个左括号。
                if (needRight < 0) {
                    ++insertCnt;
                    needRight += 2;
                }
            }
        }

        // 扫描结束后，剩余缺口直接补齐。
        insertCnt += needRight;
        return insertCnt;
    }
}
