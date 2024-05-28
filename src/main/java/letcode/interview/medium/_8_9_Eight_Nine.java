package letcode.interview.medium;

import letcode.utils.FormatUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号。设计一种算法，打印n对括号的所有合法的（例如，开闭一一对应）组合。  说明：解集不能包含重复的子集。
 * 例如，给出 n = 3，生成结果为：  [   "((()))",   "(()())",   "(())()",   "()(())",   "()()()" ]
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-05-28 14:28
 */
public class _8_9_Eight_Nine {

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        search(0, 0, n, new StringBuilder(), ans);
        return ans;
    }

    public void search(int leftBracketCnt, int rightBracketCnt, int n, StringBuilder sb, List<String> ans) {
        if (rightBracketCnt == n) {
            ans.add(sb.toString());
            return;
        }
        if (leftBracketCnt < n) {
            sb.append("(");
            search(leftBracketCnt + 1, rightBracketCnt, n, sb, ans);
            sb.deleteCharAt(leftBracketCnt + rightBracketCnt);
        }
        if (rightBracketCnt < leftBracketCnt) {
            sb.append(")");
            search(leftBracketCnt, rightBracketCnt + 1, n, sb, ans);
            sb.deleteCharAt(leftBracketCnt + rightBracketCnt);
        }
    }

    public static void main(String[] args) {
        System.out.println(FormatUtils.formatList(new _8_9_Eight_Nine().generateParenthesis(
                3
        )));
    }

}
