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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        List<Long> collect1 = Arrays.stream(version1.split("\\.")).map(Long::valueOf).collect(Collectors.toList());
        List<Long> collect2 = Arrays.stream(version2.split("\\.")).map(Long::valueOf).collect(Collectors.toList());
        int i1 = 0;
        int i2 = 0;
        long c1 = 0L;
        long c2 = 0L;
        while (i1 < collect1.size() || i2 < collect2.size()) {
            c1 = i1 >= collect1.size() ? 0L : collect1.get(i1);
            c2 = i2 >= collect2.size() ? 0L : collect2.get(i2);
            if (c1 != c2) {
                return c1 > c2 ? 1 : -1;
            }
            ++i1;
            ++i2;
        }
        return 0;
    }

    /**
     * 示例 1：
     * 输入：version1 = "1.01", version2 = "1.001"
     * 输出：0
     * 解释：忽略前导零，"01" 和 "001" 都表示相同的整数 "1"
     *
     * 示例 2：
     * 输入：version1 = "1.0", version2 = "1.0.0"
     * 输出：0
     * 解释：version1 没有指定下标为 2 的修订号，即视为 "0"
     *
     * 示例 3：
     * 输入：version1 = "0.1", version2 = "1.1"
     * 输出：-1
     * 解释：version1 中下标为 0 的修订号是 "0"，version2 中下标为 0 的修订号是 "1" 。0 < 1，所以 version1 < version2
     *
     * 示例 4：
     * 输入：version1 = "1.0.1", version2 = "1"
     * 输出：1
     *
     * 示例 5:
     * 输入：version1 = "7.5.2.4", version2 = "7.5.3"
     * 输出：-1
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/compare-version-numbers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _165().compareVersion(
                "7.5.2.4",  "7.5.3"
        ));
    }







}
