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

package normal.difficult;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: Leetcode
 * @description: 你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过 *，/，+，-，(，) 的运算得到 24。
 * @author: 蔡永程
 * @create: 2020-08-22 06:17
 */
public class _679SixHundredSeventyNine {

    private List<Float> list = new ArrayList<>(10);

    public static void main(String[] args) {
        System.out.println(new _679SixHundredSeventyNine().judgePoint24(new int[]{1, 2, 1, 2}));
    }

    public boolean searching(int size) {
        if (size == 1) {
            return Math.abs(list.get(0) - 24) < 1e-4;
        }
        int i = size - 1;
        int j = 0;
        Float vi = 0f;
        Float vj = 0f;
        for (; i > 0; --i) {
            vi = list.get(i);
            for (j = i - 1; j > -1; --j) {

                vj = list.get(j);
                list.remove(i);
                list.remove(j);

                list.add(vi + vj);
                if (searching(size - 1)) {
                    return true;
                }
                list.remove(vi + vj);

                list.add(vj * vi);
                if (searching(size - 1)) {
                    return true;
                }
                list.remove(vj * vi);

                list.add(vi - vj);
                if (searching(size - 1)) {
                    return true;
                }
                list.remove(vi - vj);

                list.add(vj - vi);
                if (searching(size - 1)) {
                    return true;
                }
                list.remove(vj - vi);

                if (vi != 0) {
                    list.add(vj / vi);
                    if (searching(size - 1)) {
                        return true;
                    }
                    list.remove(vj / vi);
                }

                if (vj != 0) {
                    list.add(vi / vj);
                    if (searching(size - 1)) {
                        return true;
                    }
                    list.remove(vi / vj);
                }

                list.add(j, vj);
                list.add(i, vi);
            }
        }
        return false;
    }

    /**
     * 示例 1:
     * <p>
     * 输入: [4, 1, 8, 7]
     * 输出: True
     * 解释: (8-4) * (7-1) = 24
     * 示例 2:
     * <p>
     * 输入: [1, 2, 1, 2]
     * 输出: False
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/24-game
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean judgePoint24(int[] nums) {
        for (int num : nums) {
            list.add((float) num);
        }
        return searching(4);
    }

}