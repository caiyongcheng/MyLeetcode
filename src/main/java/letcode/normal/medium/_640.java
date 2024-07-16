/*
 * 版权所有（c）<2021><蔡永程>
 *
 * 反996许可证版本1.0
 *
 * 在符合下列条件的情况下，特此免费向任何得到本授权作品的副本（包括源代码、文件和/或相关内容，以
 * 下统称为“授权作品”）的个人和法人实体授权：被授权个人或法人实体有权以任何目的处置授权作品，包括
 * 但不限于使用、复制，修改，衍生利用、散布，发布和再许可：
 *
 * 1. 个人或法人实体必须在许可作品的每个再散布或衍生副本上包含以上版权声明和本许可证，不得自行修
 * 改。
 * 2. 个人或法人实体必须严格遵守与个人实际所在地或个人出生地或归化地、或法人实体注册地或经营地（
 * 以较严格者为准）的司法管辖区所有适用的与劳动和就业相关法律、法规、规则和标准。如果该司法管辖区
 * 没有此类法律、法规、规章和标准或其法律、法规、规章和标准不可执行，则个人或法人实体必须遵守国际
 * 劳工标准的核心公约。
 * 3. 个人或法人不得以任何方式诱导、暗示或强迫其全职或兼职员工或其独立承包人以口头或书面形式同意
 * 直接或间接限制、削弱或放弃其所拥有的，受相关与劳动和就业有关的法律、法规、规则和标准保护的权利
 * 或补救措施，无论该等书面或口头协议是否被该司法管辖区的法律所承认，该等个人或法人实体也不得以任
 * 何方法限制其雇员或独立承包人向版权持有人或监督许可证合规情况的有关当局报告或投诉上述违反许可证
 * 的行为的权利。
 *
 * 该授权作品是"按原样"提供，不做任何明示或暗示的保证，包括但不限于对适销性、特定用途适用性和非侵
 * 权性的保证。在任何情况下，无论是在合同诉讼、侵权诉讼或其他诉讼中，版权持有人均不承担因本软件或
 * 本软件的使用或其他交易而产生、引起或与之相关的任何索赔、损害或其他责任。
 */

package letcode.normal.medium;

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

    /**
     * 示例 1：
     * <p>
     * 输入: equation = "x+5-3+x=6+x-2"
     * 输出: "x=2"
     * 示例 2:
     * <p>
     * 输入: equation = "x=x"
     * 输出: "Infinite solutions"
     * 示例 3:
     * <p>
     * 输入: equation = "2x=x"
     * 输出: "x=0"
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/solve-the-equation
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _640().solveEquation("0=x-x"));
    }


}
