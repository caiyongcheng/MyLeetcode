package letcode.normal.medium;

import java.util.Stack;

/**
 * 给出一个字符串s（仅含有小写英文字母和括号）。  请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。  
 * 注意，您的结果中 不应 包含任何括号。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/reverse-substrings-between-each-pair-of-parentheses 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-06-28 10:07
 **/
public class _1190 {

    public String reverseParentheses(String s) {
        int index = 0;
        char[] chars = s.toCharArray();
        char[] ansChars = new char[chars.length];
        Character tepChr;
        StringBuilder ansStr = new StringBuilder();
        Stack<Character> ansStack = new Stack<>();
        for (char aChar : chars) {
            if (aChar == ')') {
                //翻转
                index = 0;
                while (!ansStack.empty() && ansStack.peek() != '(') {
                    ansChars[index++] = ansStack.pop();
                }
                if (!ansStack.empty()) {
                    ansStack.pop();
                }
                for (int i = 0; i < index; i++) {
                    ansStack.push(ansChars[i]);
                }
            } else {
                ansStack.push(aChar);
            }
        }
        index = chars.length;
        while (!ansStack.empty()) {
            if (ansStack.peek() == '(') {
                continue;
            }
            ansChars[--index] = ansStack.pop();
        }

        while (index < chars.length) {
            ansStr.append(ansChars[index++]);
        }
        return ansStr.toString();
    }

}
