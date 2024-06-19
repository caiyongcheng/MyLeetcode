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

package lrc.easy;

import java.util.Arrays;

/**
 * @program: Leetcode
 * @description: 秋日市集上，魔术师邀请小扣与他互动。魔术师的道具为分别写有数字 1~N 的 N 张卡牌，
 * 然后请小扣思考一个 N 张卡牌的排列 target。
 * 魔术师的目标是找到一个数字 k（k >= 1），
 * 使得初始排列顺序为 1~N 的卡牌经过特殊的洗牌方式最终变成小扣所想的排列 target，
 * 特殊的洗牌方式为：
 * 第一步，魔术师将当前位于 偶数位置 的卡牌（下标自 1 开始），保持 当前排列顺序 放在位于 奇数位置 的卡牌之前。
 * 例如：将当前排列 [1,2,3,4,5] 位于偶数位置的 [2,4] 置于奇数位置的 [1,3,5] 前，排列变为 [2,4,1,3,5]；
 * 第二步，若当前卡牌数量小于等于 k，则魔术师按排列顺序取走全部卡牌；若当前卡牌数量大于 k，则取走前 k 张卡牌，
 * 剩余卡牌继续重复这两个步骤，直至所有卡牌全部被取走；
 * 卡牌按照魔术师取走顺序构成的新排列为「魔术取数排列」
 * ，请返回是否存在这个数字 k 使得「魔术取数排列」恰好就是 target，
 * 从而让小扣感到大吃一惊。  来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er94lq
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-11-19 15:50
 */
public class _LRC_23 {

    private int[] unevenArray;
    private int[] evenArray;
    private int[] target;

    public static void main(String[] args) {
        int[] ints = {2, 4, 3, 1, 5};
        _LRC_23 lrc_23 = new _LRC_23();
        System.out.println(lrc_23.isMagic(ints));
    }

    /**
     * 下标奇数的数据放在偶数下标之前
     */
    private void cover(int low, int hight, int[] array) {
        int unevenIndex = 0;
        int evenIndex = 0;
        for (int i = low; i < hight; i += 2) {
            unevenArray[unevenIndex++] = array[i];
        }
        for (int i = low + 1; i < hight; i += 2) {
            evenArray[evenIndex++] = array[i];
        }
        for (int i = 0; i < evenIndex; i++) {
            array[i + low] = evenArray[i];
        }
        for (int i = 0; i < unevenIndex; i++) {
            array[i + evenIndex + low] = unevenArray[i];
        }
    }

    public boolean magic(int k, int low, int[] array) {
        int hight = array.length;
        while (low < hight) {
            cover(low, hight, array);
            int limit = Math.min(hight, low + k);
            for (int i = low; i < limit; i++) {
                if (target[i] != array[i]) {
                    return false;
                }
            }
            low += k;
        }
        return true;
    }

    public boolean isMagic(int[] target) {
        this.target = target;
        int[] ints = new int[target.length];
        unevenArray = new int[target.length / 2 + 1];
        evenArray = new int[target.length / 2 + 1];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = i + 1;
        }
        int[] array = Arrays.copyOf(ints, ints.length);
        cover(0, target.length, array);
        int kLimit = 0;
        while (array[kLimit] == target[kLimit]) {
            ++kLimit;
            ints = Arrays.copyOf(array, ints.length);
            if (magic(kLimit, kLimit, ints)) {
                return true;
            }
        }
        return false;
    }


}
