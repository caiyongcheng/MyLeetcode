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
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/8/2 10:37
 * description 在桌子上有 N 张卡片，每张卡片的正面和背面都写着一个正数（正面与背面上的数有可能不一样）。
 * 我们可以先翻转任意张卡片，然后选择其中一张卡片。  如果选中的那张卡片背面的数字 X 与任意一张卡片的正面的数字都不同，那么这个数字是我们想要的数字。
 * 哪个数是这些想要的数字中最小的数（找到这些数中的最小值）呢？如果没有一个数字符合要求的，输出 0。
 * 其中, fronts[i] 和 backs[i] 分别代表第 i 张卡片的正面和背面的数字。
 * 如果我们通过翻转卡片来交换正面与背面上的数，那么当初在正面的数就变成背面的数，背面的数就变成正面的数。
 */
public class _822EightHundredTwentyTwo {


    public int flipgame(int[] fronts, int[] backs) {
        /*
        对于正反面一样数字n的牌 不管怎么反转 一定会重复
        至于其他情况 基本与正面一致 只需要反转一致的正面牌即可 因为不是正反面一样数字 所以一定不会重复
         */

        int rst = Integer.MAX_VALUE;

        //获取正反面相同的牌数值
        int[] sameNumSet = new int[2001];
        //当前反面统计
        for (int i = 0; i < fronts.length; i++) {
            if (fronts[i] == backs[i]) {
                sameNumSet[fronts[i]] = 1;
            }
        }
        //遍历
        for (int i = 0; i < fronts.length; i++) {
            //不反转当前牌
            if (backs[i] < rst && sameNumSet[backs[i]] != 1) {
                rst = backs[i];
            }
            //反转当前牌
            if (fronts[i] < rst && sameNumSet[fronts[i]] != 1) {
                rst = fronts[i];
            }
        }

        return rst == Integer.MAX_VALUE ? 0 : rst;

    }


    /**
     * 输入：fronts = [1,2,4,4,7], backs = [1,3,4,1,3]
     * 输出：2
     * 解释：假设我们翻转第二张卡片，那么在正面的数变成了 [1,3,4,4,7] ， 背面的数变成了 [1,2,4,1,3]。
     * 接着我们选择第二张卡片，因为现在该卡片的背面的数是 2，2 与任意卡片上正面的数都不同，所以 2 就是我们想要的数字。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _822EightHundredTwentyTwo().flipgame(
                new int[]{1, 1},
                new int[]{1, 2}
        ));
    }

}
