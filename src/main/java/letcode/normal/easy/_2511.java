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
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/9/2 12:00
 * description 给你一个长度为 n ，下标从 0 开始的整数数组 forts ，表示一些城堡。forts[i] 可以是 -1 ，0 或者 1 ，
 * 其中：  -1 表示第 i 个位置 没有 城堡。 0 表示第 i 个位置有一个 敌人 的城堡。 1 表示第 i 个位置有一个你控制的城堡。
 * 现在，你需要决定，将你的军队从某个你控制的城堡位置 i 移动到一个空的位置 j ，
 * 满足：  0 <= i, j <= n - 1 军队经过的位置 只有 敌人的城堡。
 * 正式的，对于所有 min(i,j) < k < max(i,j) 的 k ，都满足 forts[k] == 0 。 当军队移动时，所有途中经过的敌人城堡都会被 摧毁 。
 * 请你返回 最多 可以摧毁的敌人城堡数目。如果 无法 移动你的军队，或者没有你控制的城堡，请返回 0 。
 */
public class _2511 {

    public int captureForts(int[] forts) {
        //题目简化为 找到连续的敌军堡垒 并且左边和右边一个是我方堡垒 一个是空堡垒
        int rst = 0;
        for (int i = 0; i < forts.length; i++) {
            if (forts[i] == 0) {
                int curIdx = i;
                //跳过连续的地方堡垒
                while (curIdx < forts.length && forts[curIdx] == 0) {
                    ++curIdx;
                }
                //左右两边一个是我方堡垒 一个是空堡垒
                if (i > 0 && curIdx < forts.length && forts[i - 1] * forts[curIdx] == -1) {
                    rst = Math.max(rst, curIdx - i);
                }
                i = curIdx;
            }
        }
        return rst;
    }


    /**
     * 示例 1：
     * <p>
     * 输入：forts = [1,0,0,-1,0,0,0,0,1]
     * 输出：4
     * 解释：
     * - 将军队从位置 0 移动到位置 3 ，摧毁 2 个敌人城堡，位置分别在 1 和 2 。
     * - 将军队从位置 8 移动到位置 3 ，摧毁 4 个敌人城堡。
     * 4 是最多可以摧毁的敌人城堡数目，所以我们返回 4 。
     * 示例 2：
     * <p>
     * 输入：forts = [0,0,1,-1]
     * 输出：0
     * 解释：由于无法摧毁敌人的城堡，所以返回 0 。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2511().captureForts(
                new int[]{0, 0, 1, -1}
        ));
    }

}
