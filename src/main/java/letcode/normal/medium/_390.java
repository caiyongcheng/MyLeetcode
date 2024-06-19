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
 * 列表 arr 由在范围 [1, n] 中的所有整数组成，并按严格递增排序。请你对 arr 应用下述算法：
 * 从左到右，删除第一个数字，然后每隔一个数字删除一个，直到到达列表末尾。
 * 重复上面的步骤，但这次是从右到左。也就是，删除最右侧的数字，然后剩下的数字每隔一个删除一个。
 * 不断重复这两步，从左到右和从右到左交替进行，直到只剩下一个数字。 给你整数 n ，返回 arr 最后剩下的数字。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/elimination-game 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2022-01-04 15:28
 **/
public class _390 {

    public int lastRemaining(int n) {
        //显然开始的数列是等差数列 隔一个删除后 还是等差数列 一直删除到只剩下最后一个即可
        //对于一个等差数列 我们只需要 直到 首项、末项、项数中的任意两项 + 公差即可确定一个等差数列
        //所以选择维护首项与项数 项数为1时 就表示数列只剩下一个数了
        int first = 1;
        int numCount = n;
        int diff = 1;
        int count = 1;
        while (numCount > 1) {
            //项数为奇数，或者是从左往右删除 首项会被删除
            if ((numCount & 1) == 1 || (count & 1) == 1) {
                first += diff;
            }
            diff = diff << 1;
            numCount = numCount >> 1;
            ++count;
        }
        return first;
    }


    /**
     * 示例 1：
     * <p>
     * 输入：n = 9
     * 输出：6
     * 解释：
     * arr = [1, 2, 3, 4, 5, 6, 7, 8, 9]
     * arr = [2, 4, 6, 8]
     * arr = [2, 6]
     * arr = [6]
     * 示例 2：
     * <p>
     * 输入：n = 1
     * 输出：1
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/elimination-game
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _390().lastRemaining(
                1
        ));
    }


}