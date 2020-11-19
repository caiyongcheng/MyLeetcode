package easy;

import java.util.Stack;

/**
 * @program: Leetcode
 * @description: 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。
 * # 代表退格字符。  注意：如果对空文本输入退格字符，文本继续为空。  来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/backspace-string-compare 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-10-19 23:18
 */
public class _844EightHundredFortyFour {   

    /**
     * 计算输入到空白的文本编辑器后的结果
     * @param str
     * @return
     */
    public String parseStr(String str) {

        Stack<Character> characters = new Stack<>();
        char[] chars = str.toCharArray();
        for (char aChar : chars) {
            if (aChar != '#') {
                characters.push(aChar);
            } else {
                if (!characters.empty()) {
                    characters.pop();
                }
            }
        }
        StringBuilder resultStr = new StringBuilder();
        while (!characters.empty()) {
            resultStr.append(characters.pop());
        }
        return resultStr.toString();
    }

    /**
     * 示例 1：
     * 输入：S = "ab#c", T = "ad#c"
     * 输出：true
     * 解释：S 和 T 都会变成 “ac”。
     *
     * 示例 2：
     * 输入：S = "ab##", T = "c#d#"
     * 输出：true
     * 解释：S 和 T 都会变成 “”。
     *
     * 示例 3：
     * 输入：S = "a##c", T = "#a#c"
     * 输出：true
     * 解释：S 和 T 都会变成 “c”。
     *
     * 示例 4：
     * 输入：S = "a#c", T = "b"
     * 输出：false
     * 解释：S 会变成 “c”，但 T 仍然是 “b”。
     * @param S
     * @param T
     * @return
     */
    public boolean backspaceCompare(String S, String T) {
        String strS = parseStr(S);
        String strT = parseStr(T);
        if (strS == null) {
            return strT == null;
        }
        if (strT == null) {
            return false;
        }
        return strS.equals(strT);
    }

    public static void main(String[] args) {
        System.out.println(new _844EightHundredFortyFour().backspaceCompare(
                "ab#c", "ad#c"
        ));
    }
}