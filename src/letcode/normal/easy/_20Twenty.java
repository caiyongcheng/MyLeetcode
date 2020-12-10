package letcode.easy;

import java.util.ArrayList;

/**
 * @program: StudyHTTP
 * @description: 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：  左括号必须用相同类型的右括号闭合。 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * @author: 蔡永程
 * @create: 2020-06-18 14:06
 */
public class _20Twenty {

    /**
     * 示例 1:
     * 输入: "()"
     * 输出: true
     *
     * 示例 2:
     * 输入: "()[]{}"
     * 输出: true
     *
     * 示例 3:
     * 输入: "(]"
     * 输出: false
     *
     * 示例 4:
     * 输入: "([)]"
     * 输出: false
     *
     * 示例 5:
     * 输入: "{[]}"
     * 输出: true
     */
    public static boolean isValid(String s) {

        ArrayList<Character> characters = new ArrayList<>();
        int n = s.length();
        char ch;
        int l;
        for (int i = 0; i < n; ++i){
            ch = s.charAt(i);
            if(ch == '(' || ch == '[' || ch == '{'){
                characters.add(ch);
            }else{
                l = characters.size();
                if (l<=0) {
                    return false;
                }else if(ch == ')'){
                    if (characters.get(l-1) != '(') {
                        return false;
                    }
                    characters.remove(l-1);
                }else if(ch == ']'){
                    if (characters.get(l-1) != '[') {
                        return false;
                    }
                    characters.remove(l-1);
                }else if(ch == '}'){
                    if (characters.get(l-1) != '{') {
                        return false;
                    }
                    characters.remove(l-1);
                }else{
                    return false;
                }
            }
        }
        return characters.size()==0;
    }

    public static void main(String[] args) {
        System.out.println(isValid("([])"));
    }
}