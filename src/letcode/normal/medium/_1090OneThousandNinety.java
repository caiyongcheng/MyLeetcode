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
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Caiyongcheng
 * @description 我们有一个n项的集合。
 * 给出两个整数数组values和 labels，第 i 个元素的值和标签分别是values[i]和labels[i]。
 * 还会给出两个整数numWanted和 useLimit 。
 * 从 n 个元素中选择一个子集 s :  子集 s 的大小小于或等于 numWanted 。
 * s 中 最多 有相同标签的 useLimit 项。 一个子集的分数是该子集的值之和。
 * 返回子集s 的最大 分数 。
 * 来源：力扣（LeetCode） 链接：<a href="https://leetcode.cn/problems/largest-values-from-labels">...</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @since 2023/5/23 9:10
 */
public class _1090OneThousandNinety {

    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        int[][] conbination = new int[values.length][2];
        for (int i = 0; i < conbination.length; i++) {
            conbination[i][0] = values[i];
            conbination[i][1] = labels[i];
        }
        Arrays.sort(conbination, Comparator.comparingInt(ar -> ar[0]));
        Map<Integer, Integer> labelCntMap = new HashMap<>(10000);
        int maxScope = 0;
        for (int i = conbination.length - 1; i >= 0; i--) {
            if (numWanted == 0) {
                return maxScope;
            }
            Integer labelCnt = labelCntMap.getOrDefault(conbination[i][1], 0);
            if (labelCnt >= useLimit) {
                continue;
            }
            maxScope += conbination[i][0];
            labelCntMap.put(conbination[i][1], labelCnt + 1);
            --numWanted;
        }
        return maxScope;
    }


    /**
     * 示例 1：
     * <p>
     * 输入：values = new int[]{5,4,3,2,1}, labels = new int[]{1,1,2,2,3}, numWanted = 3, useLimit = 1
     * 输出：9
     * 解释：选出的子集是第一项，第三项和第五项。
     * 示例 2：
     * <p>
     * 输入：values = new int[]{5,4,3,2,1}, labels = new int[]{1,3,3,3,2}, numWanted = 3, useLimit = 2
     * 输出：12
     * 解释：选出的子集是第一项，第二项和第三项。
     * 示例 3：
     * <p>
     * 输入：values = new int[]{9,8,8,7,6}, labels = new int[]{0,0,0,1,1}, numWanted = 3, useLimit = 1
     * 输出：16
     * 解释：选出的子集是第一项和第四项。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/largest-values-from-labels
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1090OneThousandNinety().largestValsFromLabels(
                new int[]{9, 8, 8, 7, 6},
                new int[]{0, 0, 0, 1, 1},
                3,
                1
        ));
    }

}
