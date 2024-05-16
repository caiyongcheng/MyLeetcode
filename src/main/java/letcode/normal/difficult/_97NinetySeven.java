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

package letcode.normal.difficult;

/**
 * Leetcode
 * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
 *
 * @author : CaiYongcheng
 * @date : 2020-08-01 09:09
 **/
public class _97NinetySeven {

    private char[] one;
    private char[] two;
    private char[] three;
    private boolean[][] cache;

    public static void main(String[] args) {
        boolean interleave = new _97NinetySeven().isInterleave("aabcc", "dbbca", "aadbbcbcac");
        System.out.println(interleave);
    }

    public boolean check(int index1, int index2, int index3) {
        if (index1 == one.length) {
            while (index2 < two.length) {
                if (two[index2] != three[index3]) {
                    return false;
                }
                ++index2;
                ++index3;
            }
            return true;
        }
        if (index2 == two.length) {
            while (index1 < one.length) {
                if (one[index1] != three[index3]) {
                    return false;
                }
                ++index1;
                ++index3;
            }
            return true;
        }
        if (!cache[index1][index2]) {
            cache[index1][index2] = true;
            if (one[index1] == three[index3]) {
                if (check(index1 + 1, index2, index3 + 1)) {
                    return true;
                }
            }
            if (two[index2] == three[index3]) {
                if (check(index1, index2 + 1, index3 + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 示例 1：
     * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
     * 输出：true
     * <p>
     * 示例2：
     * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
     * 输出：false
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s1.length() == 0) {
            return (s2 == null || s2.length() == 0) && (s3 == null || s3.length() == 0) || s2.equals(s3);
        }
        if (s2 == null || s2.length() == 0) {
            return (s1 == null || s1.length() == 0) && (s3 == null || s3.length() == 0) || s1.equals(s3);
        }
        one = s1.toCharArray();
        two = s2.toCharArray();
        three = s3.toCharArray();
        cache = new boolean[one.length][two.length];
        return s1.length() + s2.length() == s3.length() && check(0, 0, 0);
    }

}
