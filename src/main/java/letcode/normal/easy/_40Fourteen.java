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
 * @program: StudyHTTP
 * @description: 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * @author: 蔡永程
 * @create: 2020-06-17 23:05
 */
public class _40Fourteen {

    /**
     * 示例1:
     * <p>
     * 输入: ["flower","flow","flight"]
     * 输出: "fl"
     * 示例2:
     * <p>
     * 输入: ["dog","racecar","car"]
     * 输出: ""
     * 解释: 输入不存在公共前缀。
     *
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        if (strs.length == 1) return strs[0];
        StringBuilder stringBuilder = new StringBuilder();
        int x = 0, y = 0, length = 0;
        int minlength1 = Integer.MAX_VALUE;
        int minlength2 = Integer.MAX_VALUE;
        int i = 0, j = 0;
        int strlength = 0;
        char ch = 'a';
        for (i = 0; i < strs.length; ++i) {
            length = strs[i].length();
            if (length <= minlength1) {
                minlength2 = minlength1;
                minlength1 = length;
                y = x;
                x = i;
            } else if (length <= minlength2) {
                minlength2 = length;
                y = i;
            }
        }
        for (i = 0; i < minlength1; ++i) {
            ch = strs[x].charAt(i);
            if (ch == strs[y].charAt(i)) {
                stringBuilder.append(ch);
            } else {
                break;
            }
        }
        strlength = stringBuilder.length();
        for (i = 0; i < strs.length; ++i) {
            if (i == x || i == y) continue;
            if (strlength <= 0) return "";
            for (j = strlength - 1; j > -1; --j) {
                ch = stringBuilder.charAt(j);
                if (strs[i].charAt(j) != ch) {
                    strlength -= 1;
                }
            }
        }
        return stringBuilder.substring(0, strlength).toString();
    }

    public static void main(String[] args) {
        String[] strings = {"dog", "racecar", "car"};
        System.out.println(longestCommonPrefix(strings));
    }
}