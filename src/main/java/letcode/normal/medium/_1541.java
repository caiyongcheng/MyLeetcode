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

        char[] charArray = s.toCharArray();
        int leftScore = 0;
        int insertCnt = 0;
        for (char c : charArray) {
            // 每个左括号前 必须是平衡结果 或者只能有n个(
            if (c == '(') {
                // 每个(前不能有多余的)，如果有需要添加(进行平衡
                if (leftScore < 0) {
                    leftScore = -leftScore;
                    insertCnt += (leftScore >> 1);
                    leftScore = leftScore & 1;
                    // 多了一个右括号, 需要加一个左括号，一个右括号
                    if (leftScore == 1) {
                        insertCnt += 2;
                        leftScore = 0;
                    }
                } else if ((leftScore & 1) == 1) {
                    // 少了一个右括号
                    ++insertCnt;
                    --leftScore;
                }
                leftScore += 2;
            } else if (c == ')') {
                leftScore--;
            }
        }

        // 最后再进行一次验证
        if (leftScore > 0) {
            // 少了一个右括号 先补充一个右括号
            if ((leftScore & 1) == 1) {
                ++insertCnt;
                --leftScore;
            }
            insertCnt += leftScore;;
        } else if (leftScore < 0) {
            leftScore = -leftScore;
            insertCnt += (leftScore >> 1);
            // 多出一个右括号 需要添加一个左括号和一个右括号
            if ((leftScore & 1) == 1) {
                insertCnt += 2;
            }
        }
        return insertCnt;


    }
}
