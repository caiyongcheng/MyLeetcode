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

import java.util.Arrays;

/**
 * @author Caiyongcheng
 * @description 给你一个 m x n 大小的矩阵 grid ，
 * 由若干正整数组成。  执行下述操作，直到 grid 变为空矩阵：  从每一行删除值最大的元素。如果存在多个这样的值，删除其中任何一个。
 * 将删除元素中的最大值与答案相加。 注意 每执行一次操作，矩阵中列的数据就会减 1 。  返回执行上述操作后的答案。
 * @since 2023/7/27 14:39
 */
public class _2500 {

    /**
     * 输入：grid = {{1,2,4},{3,3,1}}
     * 输出：8
     * 解释：上图展示在每一步中需要移除的值。
     * - 在第一步操作中，从第一行删除 4 ，从第二行删除 3（注意，有两个单元格中的值为 3 ，我们可以删除任一）。在答案上加 4 。
     * - 在第二步操作中，从第一行删除 2 ，从第二行删除 3 。在答案上加 3 。
     * - 在第三步操作中，从第一行删除 1 ，从第二行删除 1 。在答案上加 1 。
     * 最终，答案 = 4 + 3 + 1 = 8 。
     *
     * @param grid
     * @return
     */
    public int deleteGreatestValue(int[][] grid) {
        int res = 0;
        for (int[] ints : grid) {
            Arrays.sort(ints);
        }
        for (int i = 0; i < grid[0].length; i++) {
            int max = 0;
            for (int[] ints : grid) {
                max = Math.max(max, ints[i]);
            }
            res += max;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new _2500().deleteGreatestValue(new int[][]{{1, 2, 4}, {3, 3, 1}}));
    }
}