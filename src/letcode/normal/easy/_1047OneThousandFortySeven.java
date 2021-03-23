package letcode.normal.easy;

import java.util.Stack;

/**
 * @program: MyLeetcode
 * @description: 给出由小写字母组成的字符串S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。 
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。  在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.easy
 * @author: 6JSh5rC456iL
 * @date: 2021-03-23 14:47
 **/
public class _1047OneThousandFortySeven {

    public String removeDuplicates(String S) {
        char[] chars = S.toCharArray();
        Stack<Character> characters = new Stack<>();
        characters.push('-');
        for (char aChar : chars) {
            if (aChar != characters.peek()) {
                characters.push(aChar);
            } else {
                characters.pop();
            }
        }
        Stack<Character> characters1 = new Stack<>();
        while (!characters.empty()) {
            characters1.push(characters.pop());
        }
        StringBuilder ans = new StringBuilder();
        characters1.pop();
        while (!characters1.empty()) {
            ans.append(characters1.pop());
        }
        return ans.toString();
    }

    /**
     * 输入："abbaca"
     * 输出："ca"
     * 解释：
     * 例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1047OneThousandFortySeven().removeDuplicates("abbaca"));
    }
}
