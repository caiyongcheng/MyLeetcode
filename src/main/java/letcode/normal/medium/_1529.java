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
 * @description: 房间中有 n 个灯泡，编号从 0 到 n-1 ，
 * 自左向右排成一行。最开始的时候，所有的灯泡都是 关 着的。
 * 请你设法使得灯泡的开关状态和 target 描述的状态一致，
 * 其中 target[i] 等于 1 第 i 个灯泡是开着的，等于 0 意味着第 i 个灯是关着的。
 * 有一个开关可以用于翻转灯泡的状态，翻转操作定义如下：
 * 选择当前配置下的任意一个灯泡（下标为 i ） 翻转下标从 i 到 n-1 的每个灯泡 翻转时，
 * 如果灯泡的状态为 0 就变为 1，为 1 就变为 0 。
 * 返回达成 target 描述的状态所需的 最少 翻转次数。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/bulb-switcher-iv
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-12-14 17:46
 */
public class _1529 {

    /**
     * 示例 1：
     * 输入：target = "10111"
     * 输出：3
     * 解释：初始配置 "00000".
     * 从第 3 个灯泡（下标为 2）开始翻转 "00000" -> "00111"
     * 从第 1 个灯泡（下标为 0）开始翻转 "00111" -> "11000"
     * 从第 2 个灯泡（下标为 1）开始翻转 "11000" -> "10111"
     * 至少需要翻转 3 次才能达成 target 描述的状态
     * <p>
     * 示例 2：
     * 输入：target = "101"
     * 输出：3
     * 解释："000" -> "111" -> "100" -> "101".
     * <p>
     * 示例 3：
     * 输入：target = "00000"
     * 输出：0
     * <p>
     * 示例 4：
     * 输入：target = "001011101"
     * 输出：5
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/bulb-switcher-iv
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1529().minFlips(
                "001011101"
        ));
    }

    public int minFlips(String target) {
        char ch;
        int changeQuantity = 0;
        for (int index = 0; index < target.length(); ++index) {
            ch = (changeQuantity & 1) == 1 ? '1' : '0';
            if (target.charAt(index) != ch) {
                ++changeQuantity;
            }
        }
        return changeQuantity;
    }

}
