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

import java.util.Stack;

/**
 * @program: MyLeetcode
 * @description: 给定一个以字符串表示的非负整数num，移除这个数中的 k 位数字，使得剩下的数字最小。
 * 注意:  num 的长度小于 10002 且≥ k。 num 不会包含任何前导零。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/remove-k-digits 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @date: 2021-03-29 14:21
 **/
public class _402FourHundredTwo {


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


    /**^
     * 示例 1 :
     * 输入: num = "1432219", k = 3
     * 输出: "1219"
     * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
     *
     * 示例 2 :
     * 输入: num = "10200", k = 1
     * 输出: "200"
     * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
     *
     * 示例 3 :
     * 输入: num = "10", k = 2
     * 输出: "0"
     * 解释: 从原数字移除所有的数字，剩余为空就是0。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-k-digits
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _402FourHundredTwo().removeKdigits("100100132", 3));
    }
}
