package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * 求解一个给定的方程，将x以字符串 "x=#value"的形式返回。该方程仅包含 '+' ， '-' 操作，变量x和其对应系数。
 * 如果方程没有解，请返回"No solution"。如果方程有无限解，则返回 “Infinite solutions” 。
 * 题目保证，如果方程中只有一个解，则 'x' 的值是一个整数。
 * 来源：力扣（LeetCode） 链接：https://leetcode.cn/problems/solve-the-equation 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-08-10 19:42
 **/
public class _640 {

    public String solveEquation(String equation) {
        // 一元一次方程 可以化成 ax=c 的形式 得出 x=-c/a（a！=0）
        // 所以题目实际要转化成上述的形式
        // 所以当 a等于0， c不等于0时方程无解；当c等于0时，a也等于0时，方程有无数解。其余情况按照题目方程有唯一解。
        // 故题目本质上求a与c的值。
        equation = equation + '+';
        int coefficient = 0;
        int constNum = 0;
        int strLen = equation.length();
        int num = 0;
        char ch;
        char preCh = 'a';
        int symbol = 1;
        int i = 0;
        for (; i < strLen; i++) {
            ch = equation.charAt(i);
            if (ch == '=') {
                constNum += (symbol == 1 ? num : -num);
                break;
            }
            if (ch == 'x') {
                if (num == 0 && preCh != '0') {
                    num = 1;
                }
                coefficient += (symbol == 1 ? num : -num);
                num = 0;
            } else if (ch == '-') {
                constNum += (symbol == 1 ? num : -num);
                num = 0;
                symbol = 0;
            } else if (ch == '+') {
                constNum += (symbol == 1 ? num : -num);
                num = 0;
                symbol = 1;
            } else {
                num = num * 10 + ch - '0';
            }
            preCh = ch;
        }
        num = 0;
        symbol = 1;
        preCh = 'a';
        for (++i; i < strLen; i++) {
            ch = equation.charAt(i);
            if (ch == 'x') {
                if (num == 0 && preCh != '0') {
                    num = 1;
                }
                coefficient += (symbol == 0 ? num : -num);
                num = 0;
            } else if (ch == '-') {
                constNum += (symbol == 0 ? num : -num);
                num = 0;
                symbol = 0;
            } else if (ch == '+') {
                constNum += (symbol == 0 ? num : -num);
                num = 0;
                symbol = 1;
            } else {
                num = num * 10 + ch - '0';
            }
            preCh = ch;
        }
        if (constNum == 0 && coefficient == 0) {
            return "Infinite solutions";
        }
        if (coefficient == 0 && constNum != 0) {
            return "No solution";
        }
        return "x=" + -constNum / coefficient + "";
    }

}
