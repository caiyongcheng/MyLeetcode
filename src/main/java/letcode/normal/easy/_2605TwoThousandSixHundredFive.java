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
 * @since 2023/9/5 8:40
 * description 给你两个只包含 1 到 9 之间数字的数组 nums1 和 nums2 ，每个数组中的元素 互不相同 ，请你返回 最小 的数字，两个数组都 至少 包含这个数字的某个数位。
 */
public class _2605TwoThousandSixHundredFive {


    int[] cntSort;

    public int searchMin(int[] num) {
        int minItem = 10;
        for (int i : num) {
            cntSort[i]++;
            if (i < minItem) {
                minItem = i;
            }
        }
        return minItem;
    }

    public int minNumber(int[] nums1, int[] nums2) {
        cntSort = new int[10];
        int min1 = searchMin(nums1);
        int min2 = searchMin(nums2);
        if (min1 == min2) {
            return min1;
        }
        for (int i = 0; i < cntSort.length; i++) {
            if (cntSort[i] > 1) {
                return i;
            }
        }
        return min1 > min2 ? min2 * 10 + min1 : min1 * 10 + min2;
    }


    /**
     * 示例 1：
     * <p>
     * 输入：nums1 = [4,1,3], nums2 = [5,7]
     * 输出：15
     * 解释：数字 15 的数位 1 在 nums1 中出现，数位 5 在 nums2 中出现。15 是我们能得到的最小数字。
     * 示例 2：
     * <p>
     * 输入：nums1 = [3,5,2,6], nums2 = [3,1,7]
     * 输出：3
     * 解释：数字 3 的数位 3 在两个数组中都出现了。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2605TwoThousandSixHundredFive().minNumber(
                new int[]{3, 5, 2, 6},
                new int[]{3, 1, 7}
        ));
    }


}
