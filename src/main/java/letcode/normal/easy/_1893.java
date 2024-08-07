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

import java.util.ArrayList;

/**
 * 给你一个二维整数数组ranges和两个整数left和right。每个ranges[i] = [starti, endi]表示一个从starti到endi的闭区间。 
 * 如果闭区间[left, right]内每个整数都被ranges中至少一个区间覆盖，那么请你返回true，否则返回false。 
 * 已知区间 ranges[i] = [starti, endi] ，如果整数 x 满足 starti <= x <= endi，那么我们称整数x被覆盖了。  
 *   来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/check-if-all-the-integers-in-a-range-are-covered 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-07-23 09:25
 **/
public class _1893 {

    public boolean isCovered(int[][] ranges, int left, int right) {
        ArrayList<int[]> ints = new ArrayList<>();
        int uniteLeft;
        int uniteRight;
        int index;
        int length;
        ints.add(new int[]{left, right});
        for (int[] range : ranges) {
            length = ints.size();
            for (index = 0; index < length;) {
                int[] targetRange = ints.get(index);
                //判断有无交集
                if (range[1] < targetRange[0] || targetRange[1] < range[0]) {
                    ++index;
                    continue;
                }
                //求交集左右端点
                uniteLeft = Math.max(range[0], targetRange[0]);
                uniteRight = Math.min(range[1], targetRange[1]);
                //计算剩余左部分
                if (targetRange[0] <= uniteLeft - 1) {
                    ints.add(new int[]{targetRange[0], uniteLeft - 1});
                }
                //计算剩余右部分
                if (targetRange[1] >= uniteRight + 1) {
                    ints.add(new int[]{uniteRight + 1, targetRange[1]});
                }
                //移除被处理部分
                ints.remove(index);
                --length;
            }
            if (ints.isEmpty()) {
                return true;
            }
        }
        return ints.isEmpty();
    }

    /**
     * 示例 1：
     * 输入：ranges = {{1,2},{3,4},{5,6}}, left = 2, right = 5
     * 输出：true
     * 解释：2 到 5 的每个整数都被覆盖了：
     * - 2 被第一个区间覆盖。
     * - 3 和 4 被第二个区间覆盖。
     * - 5 被第三个区间覆盖。
     * 
     * 示例 2：
     * 输入：ranges = {{1,10},{10,20}}, left = 21, right = 21
     * 输出：false
     * 解释：21 没有被任何一个区间覆盖。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/check-if-all-the-integers-in-a-range-are-covered
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1893().isCovered(
                new int[][]{{1,10},{10,20}}, 21, 21
        ));
    }




}
