package letcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * @author : CaiYongcheng
 * @date : 2020-06-28 21:10
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class TwentyTwo {

    static int limit;
    static List<String> list;
    static StringBuilder str;

    public static void generateParenthesis(int left, int right) {
        if(right > left) return;
        if(left > limit || right > limit) return;
        if(left == limit){
            int r = right;
            while (r++ < left){
                str.append(')');
            }
            list.add(str.toString());
            str.delete(left+right,str.length());
            return;
        }
        str.append('(');
        generateParenthesis(left+1,right);
        str.deleteCharAt(str.length()-1);
        if(left > right){
            str.append(')');
            generateParenthesis(left,right+1);
            str.deleteCharAt(str.length()-1);
        }
    }

    /**
     * 输入：n = 3
     * 输出：[
     *        "((()))",
     *        "(()())",
     *        "(())()",
     *        "()(())",
     *        "()()()"
     *      ]
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        limit = n;
        list = new ArrayList<String>();
        str = new StringBuilder();
        generateParenthesis(0,0);
        return list;
    }

    public static void main(String[] args) {
        generateParenthesis(3);
        for (String s : list) {
            System.out.println(s);
        }
    }
}
