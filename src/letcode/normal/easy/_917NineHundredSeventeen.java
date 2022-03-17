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

package letcode.normal.easy;

/**
 * 给你一个字符串 s ，根据下述规则反转字符串：  所有非英文字母保留在原有位置。 所有英文字母（小写或大写）位置反转。 返回反转后的 s 。
 *
 * @author CaiYongcheng
 * @date 2022-02-23 09:27
 **/
public class _917NineHundredSeventeen {

    public String reverseOnlyLetters(String s) {
        int left = 0;
        int right = s.length() - 1;
        char lch = 'a';
        char rch = 'a';
        StringBuilder sb = new StringBuilder(s);
        while (left < right) {
            while (left < right) {
                lch = s.charAt(left);
                if ((lch >= 'a' && lch <= 'z') || (lch >= 'A' && lch <= 'Z')) {
                    break;
                }
                ++left;
            }
            while (left < right) {
                rch = s.charAt(right);
                if ((rch >= 'a' && rch <= 'z') || (rch >= 'A' && rch <= 'Z')) {
                    break;
                }
                --right;
            }
            if (left < right) {
                sb.deleteCharAt(left);
                sb.insert(left, rch);
                sb.deleteCharAt(right);
                sb.insert(right, lch);
            }
            ++left;
            --right;
        }
        return sb.toString();
    }


    /**
     * 示例 1：
     * <p>
     * 输入：s = "ab-cd"
     * 输出："dc-ba"
     * 示例 2：
     * <p>
     * 输入：s = "a-bC-dEf-ghIj"
     * 输出："j-Ih-gfE-dCba"
     * 示例 3：
     * <p>
     * 输入：s = "Test1ng-Leet=code-Q!"
     * 输出："Qedo1ct-eeLg=ntse-T!"
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-only-letters
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _917NineHundredSeventeen().reverseOnlyLetters(
                "Test1ng-Leet=code-Q!"
        ).equals("Qedo1ct-eeLg=ntse-T!"));
    }

}
