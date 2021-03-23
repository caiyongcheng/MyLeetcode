package letcode.normal.easy;

import java.util.Stack;

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
 * @date: 2021-03-23 14:12
 **/
public class _1021OneThousandTwentyOne {

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


    /**
     * 示例 1：
     * 输入："(()())(())"
     * 输出："()()()"
     * 解释：
     * 输入字符串为 "(()())(())"，原语化分解得到 "(()())" + "(())"，
     * 删除每个部分中的最外层括号后得到 "()()" + "()" = "()()()"。
     *
     * 示例 2：
     * 输入："(()())(())(()(()))"
     * 输出："()()()()(())"
     * 解释：
     * 输入字符串为 "(()())(())(()(()))"，原语化分解得到 "(()())" + "(())" + "(()(()))"，
     * 删除每个部分中的最外层括号后得到 "()()" + "()" + "()(())" = "()()()()(())"。
     *
     * 示例 3：
     * 输入："()()"
     * 输出：""
     * 解释：
     * 输入字符串为 "()()"，原语化分解得到 "()" + "()"，
     * 删除每个部分中的最外层括号后得到 "" + "" = ""。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-outermost-parentheses
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1021OneThousandTwentyOne().removeOuterParentheses("()()"));
    }
}
