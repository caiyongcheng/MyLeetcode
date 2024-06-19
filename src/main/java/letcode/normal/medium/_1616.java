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
 * @program: Leetcode
 * @description: 给你两个字符串a 和b，它们长度相同。请你选择一个下标，将两个字符串都在相同的下标 分割开。
 * 由a可以得到两个字符串：aprefix和asuffix，
 * 满足a = aprefix + asuffix，同理，由b 可以得到两个字符串bprefix 和bsuffix，
 * 满足b = bprefix + bsuffix。请你判断aprefix + bsuffix 或者bprefix + asuffix能否构成回文串。
 * 当你将一个字符串s分割成sprefix 和ssuffix时，ssuffix 或者sprefix 可以为空。
 * 比方说，s = "abc"那么"" + "abc"，"a" + "bc"，"ab" + "c"和"abc" + ""都是合法分割。
 * 如果 能构成回文字符串 ，那么请返回true，否则返回false。
 * 请注意，x + y表示连接字符串x 和y。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/split-two-strings-to-make-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-12-31 09:21
 */
public class _1616 {

    private static boolean isPlalindromeStr(String str) {
        if (null == str || str.length() < 2) {
            return true;
        }
        int lindex = 0;
        int rindex = str.length() - 1;
        while (lindex <= rindex) {
            if (str.charAt(lindex) != str.charAt(rindex)) {
                return false;
            }
            ++lindex;
            --rindex;
        }
        return true;
    }

    private static boolean isPlalindromeMergeStr(String preStr, String lastStr) {
        int lindex = 0;
        int rindex = preStr.length() - 1;
        while (lindex <= rindex) ;
        return true;
    }


}
