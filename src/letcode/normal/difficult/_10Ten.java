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

package letcode.difficult;

/**
 * StudyHTTP
 * 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。
 * 说明:  s可能为空，且只包含从a-z的小写字母。
 * p可能为空，且只包含从a-z的小写字母，以及字符.和*。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/regular-expression-matching 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : CaiYongcheng
 * @date : 2020-06-22 15:29
 **/
public class _10Ten {


    /**
     * 示例 1:
     * 输入:
     * s = "aa"
     * p = "a"
     * 输出: false
     * 解释: "a" 无法匹配 "aa" 整个字符串。
     * <p>
     * 示例 2:
     * 输入:
     * s = "aa"
     * p = "a*"
     * 输出: true
     * 解释:因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
     * <p>
     * 示例3:
     * 输入:
     * s = "ab"
     * p = ".*"
     * 输出: true
     * 解释:".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
     * <p>
     * 示例 4:
     * 输入:
     * s = "aab"
     * p = "c*a*b"
     * 输出: true
     * 解释:因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
     * <p>
     * 示例 5:
     * 输入:
     * s = "mississippi"
     * p = "mis*is*p*."
     * 输出: false
     *
     * @param s
     * @param p
     * @return
     */

    static short[][] ref;

    public static short isMatch(String s, int si, String p, int pi) {


        if (ref[si][pi] != 0) return ref[si][pi];

        int i = si;
        int j = pi;
        int sn = s.length();
        int pn = p.length();
        char ch1 = '-';
        char ch2 = '-';

        while (i < sn && j < pn) {
            ch1 = s.charAt(i);
            ch2 = p.charAt(j);
            if (j + 1 < pn && p.charAt(j + 1) == '*') {
                break;
            } else if (ch1 == ch2 || ch2 == '.') {
                ++i;
                ++j;
            } else {
                ref[si][pi] = 1;
                return ref[si][pi];
            }
        }
        if (i == sn) {
            if (j == pn) {
                ref[si][pi] = 2;
            } else if (j == pn - 2 && p.charAt(pn - 1) == '*') {
                ref[si][pi] = 2;
            } else {
                ref[si][pi] = 1;
            }
        } else if (j == pn) {
            ref[si][pi] = 1;
        } else {
            int zero = isMatch(s, i, p, j + 2);
            int one = 0;
            if (ch1 == ch2 || ch2 == '.') {
                one = isMatch(s, i + 1, p, j);
            }
            if (zero == 2 || one == 2) {
                ref[si][pi] = 2;
            }
        }
        return ref[si][pi];
    }


    public static boolean isMatch(String s, String p) {
        if (s.length() == 0 && p.length() == 0) return true;
        if (p.length() == 0) return false;
        if (s.length() == 0) {
            int n = p.length();
            if (n % 2 == 1) return false;
            for (int i = 1; i < n; i += 2) {
                if (p.charAt(i) != '*') {
                    return false;
                }
            }
            return true;
        }
        ref = new short[s.length() + 1][p.length() + 1];
        isMatch(s, 0, p, 0);
        return ref[0][0] == 2;
    }

    //"aabcbcbcaccbcaabc"
    //".*a*aa*.*b*.c*.*a*"
    public static void main(String[] args) {
        System.out.println(isMatch("aabcbcbcaccbcaabc", ".*a*aa*.*b*.c*.*a*"));
    }
}
