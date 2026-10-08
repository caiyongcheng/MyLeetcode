package letcode.normal.easy;

/*
 * 1021. Remove Outermost Parentheses
 * Difficulty: Easy
 * Link: https://leetcode.cn/problems/remove-outermost-parentheses/
 *
 * A valid parentheses string is either empty "" , "(" + A + ")" , or A + B , where A and B are valid
 * parentheses strings, and + represents string concatenation.
 *
 * - For example, "" , "()" , "(())()" , and "(()(()))" are all valid parentheses strings.
 *
 * A valid parentheses string s is primitive if it is nonempty, and there does not exist a way to split
 * it into s = A + B , with A and B nonempty valid parentheses strings.
 *
 * Given a valid parentheses string s , consider its primitive decomposition: s = P 1 + P 2 + ... + P k
 * , where P i are primitive valid parentheses strings.
 *
 * Return s after removing the outermost parentheses of every primitive string in the primitive
 * decomposition of s .
 *
 * Example 1:
 *
 * Input: s = "(()())(())"
 * Output: "()()()"
 * Explanation:
 * The input string is "(()())(())", with primitive decomposition "(()())" + "(())".
 * After removing outer parentheses of each part, this is "()()" + "()" = "()()()".
 *
 * Example 2:
 *
 * Input: s = "(()())(())(()(()))"
 * Output: "()()()()(())"
 * Explanation:
 * The input string is "(()())(())(()(()))", with primitive decomposition "(()())" + "(())" +
 * "(()(()))".
 * After removing outer parentheses of each part, this is "()()" + "()" + "()(())" = "()()()()(())".
 *
 * Example 3:
 *
 * Input: s = "()()"
 * Output: ""
 * Explanation:
 * The input string is "()()", with primitive decomposition "()" + "()".
 * After removing outer parentheses of each part, this is "" + "" = "".
 *
 * Constraints:
 *
 * - 1 <= s.length <= 10 5
 *
 * - s[i] is either '(' or ')' .
 *
 * - s is a valid parentheses string.
 */
/**
 * @program: MyLeetcode
 * @description: 有效括号字符串为空("")、"(" + A + ")"或A + B，其中A 和B都是有效的括号字符串，+代表字符串的连接。
 * 例如，""，"()"，"(())()"和"(()(()))"都是有效的括号字符串。  如果有效字符串S非空，且不存在将其拆分为S = A+B的方法，
 * 我们称其为原语（primitive），其中A 和B都是非空有效括号字符串。 
 * 给出一个非空有效字符串S，考虑将其进行原语化分解，使得：S = P_1 + P_2 + ... + P_k，其中P_i是有效括号字符串原语。 
 * 对S进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 S。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/remove-outermost-parentheses 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.easy
 * @author: 6JSh5rC456iL
 * @since: 2021-03-23 14:12
 **/
public class _1021 {

    public String removeOuterParentheses(String S) {
        int count = 0;
        int start = 0;
        char[] chars = S.toCharArray();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                ++count;
                start = count == 1 ? i : start;
            } else if (--count == 0) {
                for (int j = start + 1; j < i; ++j) {
                    ans.append(chars[j]);
                }
                start = i + 1;
            }
        }
        return ans.toString();
    }


}
