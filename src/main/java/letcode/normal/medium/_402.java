package letcode.normal.medium;

import java.util.Stack;

/**
 * @program: MyLeetcode
 * @description: 给定一个以字符串表示的非负整数num，移除这个数中的 k 位数字，使得剩下的数字最小。
 * 注意:  num 的长度小于 10002 且≥ k。 num 不会包含任何前导零。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/remove-k-digits 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @since: 2021-03-29 14:21
 **/
public class _402 {


    public String removeKdigits(String num, int k) {
        int size = 0;
        int limit = num.length() - k;
        char[] chars = num.toCharArray();
        int length = chars.length;
        Stack<Character> characters = new Stack<>();
        Stack<Character> temporary = new Stack<>();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (length - i + size == limit) {
                characters.push(chars[i]);
                ++size;
            } else {
                while (size > 0 && characters.peek() > chars[i] && length - i + size > limit) {
                    --size;
                    characters.pop();
                }
                if (size < limit) {
                    characters.push(chars[i]);
                    ++size;
                }
            }
        }
        if (size == 0) {
            return "0";
        }
        while (size > 0) {
            temporary.push(characters.pop());
            --size;
        }
        while (!temporary.empty() && temporary.peek() == '0') {
            temporary.pop();
        }
        while (!temporary.empty()) {
            ans.append(temporary.pop());
        }
        return ans.length() == 0 ? "0" : ans.toString();
    }


}
