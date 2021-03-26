package letcode.normal.medium;

import java.util.Queue;
import java.util.Stack;

/**
 * @program: MyLeetcode
 * @description: 给定一个经过编码的字符串，返回它解码后的字符串。  编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。 
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。  
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像3a或2[4]的输入。 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/decode-string 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @date: 2021-03-25 15:45
 **/
public class _394ThreeHundredNinetyFour {

    public String decodeString(String s) {
        Stack<Character> characters = new Stack<>();
        Stack<Character> temporary = new Stack<>();
        char[] chars = s.toCharArray();
        StringBuilder ans = new StringBuilder();
        StringBuilder temporaryStr;
        int degree = 1;
        int size = 0;
        String s1;
        char[] temporaryArr;
        for (int i = 0; i < chars.length; ++i) {
            if (chars[i] == ']') {
                size = 0;
                degree = 1;
                while (!characters.empty() && characters.peek() != '[') {
                    temporary.push(characters.pop());
                }
                if (!characters.empty() && characters.peek() == '[') {
                    characters.pop();
                }
                while (!characters.empty() && characters.peek() >= '0' && characters.peek() <= '9') {
                    size = (characters.pop() - '0') * degree + size;
                    degree *= 10;
                }
                temporaryStr = new StringBuilder();
                while (!temporary.empty()) {
                    temporaryStr.append(temporary.pop());
                }
                s1 = temporaryStr.toString();
                for (int j = 1; j < size; ++j) {
                    temporaryStr.append(s1);
                }
                temporaryArr = temporaryStr.toString().toCharArray();
                for (char c : temporaryArr) {
                    characters.push(c);
                }
            } else {
                characters.push(chars[i]);
            }
        }
        while (!temporary.empty()) {
            temporary.pop();
        }
        while (!characters.empty()) {
            temporary.push(characters.pop());
        }
        while (!temporary.empty()) {
            ans.append(temporary.pop());
        }
        return ans.toString();
    }


    /**
     * 示例 1：
     *
     * 输入：s = "3[a]2[bc]"
     * 输出："aaabcbc"
     * 示例 2：
     *
     * 输入：s = "3[a2[c]]"
     * 输出："accaccacc"
     * 示例 3：
     *
     * 输入：s = "2[abc]3[cd]ef"
     * 输出："abcabccdcdcdef"
     * 示例 4：
     *
     * 输入：s = "abc3[cd]xyz"
     * 输出："abccdcdcdxyz"
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/decode-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _394ThreeHundredNinetyFour().decodeString("100[leetcode]"));
    }

}
