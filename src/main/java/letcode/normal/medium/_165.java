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

import letcode.utils.TestUtil;

import java.nio.charset.StandardCharsets;

/**
 * 给你两个版本号 version1 和 version2 ，请你比较它们。  
 * 版本号由一个或多个修订号组成，各修订号由一个 '.' 连接。每个修订号由 多位数字 组成，可能包含 前导零 。
 * 每个版本号至少包含一个字符。修订号从左到右编号，下标从 0 开始，最左边的修订号下标为 0 ，下一个修订号下标为 1 ，以此类推。例如，2.5.33 和 0.1 都是有效的版本号。  
 * 比较版本号时，请按从左到右的顺序依次比较它们的修订号。比较修订号时，只需比较 忽略任何前导零后的整数值 。
 * 也就是说，修订号 1 和修订号 001 相等 。如果版本号没有指定某个下标处的修订号，则该修订号视为 0 。
 * 例如，版本 1.0 小于版本 1.1 ，因为它们下标为 0 的修订号相同，而下标为 1 的修订号分别为 0 和 1 ，0 < 1 。  
 * 返回规则如下：
 * 如果version1 > version2返回1，
 * 如果version1 < version2 返回 -1，
 * 除此之外返回 0。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/compare-version-numbers 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-09-02 09:05
 **/
public class _165 {

    public int compareVersion(String version1, String version2) {
        int idx1 = 0;
        int idx2 = 0;
        int subVersion1;
        int subVersion2;
        byte[] byteArr1 = version1.getBytes(StandardCharsets.UTF_8);
        byte[] byteArr2 = version2.getBytes(StandardCharsets.UTF_8);

        while (idx1 < byteArr1.length || idx2 < byteArr2.length) {
            subVersion1 = 0;
            while (idx1 < byteArr1.length) {
                if (byteArr1[idx1] == '.') {
                    ++idx1;
                    break;
                }
                subVersion1 = subVersion1 * 10 + byteArr1[idx1++] - '0';
            }

            subVersion2 = 0;
            while (idx2 < byteArr2.length) {
                if (byteArr2[idx2] == '.') {
                    ++idx2;
                    break;
                }
                subVersion2 = subVersion2 * 10 + byteArr2[idx2++] - '0';
            }

            if (subVersion1 > subVersion2) {
                return 1;
            } else if (subVersion1 < subVersion2) {
                return -1;
            }
        }
        return 0;
    }

    /**
     * Example 1:
     *
     * Input: version1 = "1.2", version2 = "1.10"
     *
     * Output: -1
     *
     * Explanation:
     *
     * version1's second revision is "2" and version2's second revision is "10": 2 < 10, so version1 < version2.
     *
     * Example 2:
     *
     * Input: version1 = "1.01", version2 = "1.001"
     *
     * Output: 0
     *
     * Explanation:
     *
     * Ignoring leading zeroes, both "01" and "001" represent the same integer "1".
     *
     * Example 3:
     *
     * Input: version1 = "1.0", version2 = "1.0.0.0"
     *
     * Output: 0
     *
     * Explanation:
     *
     * version1 has less revisions, which means every missing revision are treated as "0".
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }







}
