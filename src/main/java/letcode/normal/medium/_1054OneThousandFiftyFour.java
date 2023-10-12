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

import letcode.utils.FormatUtils;

import java.util.ArrayList;

/**
 * 在一个仓库里，有一排条形码，其中第 i 个条形码为barcodes[i]。  
 * 请你重新排列这些条形码，使其中两个相邻的条形码 不能 相等。 你可以返回任何满足该要求的答案，此题保证存在答案。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/distant-barcodes 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 1 <= barcodes.length <= 10000
 * 1 <= barcodes[i] <= 10000
 * @author CaiYongcheng
 * @date 2021-04-28 10:45
 **/
public class _1054OneThousandFiftyFour {


    public int[] rearrangeBarcodes(int[] barcodes) {
        int[] ans = new int[barcodes.length];
        int[] count = new int[10001];
        for (int barcode : barcodes) {
            count[barcode]++;
        }
        ArrayList<Integer[]> list = new ArrayList<>();
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                list.add(new Integer[]{i, count[i]});
            }
        }
        list.sort((o1, o2) -> o2[1].compareTo(o1[1]));
        int index = 0;
        for (Integer[] arr : list) {
            for (int i = 0; i < arr[1]; i++) {
                ans[index] = arr[0];
                index += 2;
                if (index >= barcodes.length) {
                    index = 1;
                }
            }
        }
        return ans;
    }

    /**
     * 示例 1：
     * 输入：[1,1,1,2,2,2]
     * 输出：[2,1,2,1,2,1]
     *
     * 示例 2：
     * 输入：[1,1,1,1,2,2,3,3]
     * 输出：[1,3,1,3,2,1,2,1]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/distant-barcodes
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatUtils.formatArray(new _1054OneThousandFiftyFour().rearrangeBarcodes(new int[]{2, 2, 1, 3})));
    }

}
