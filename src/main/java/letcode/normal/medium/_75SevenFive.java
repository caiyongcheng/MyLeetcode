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

/**
 * Leetcode
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，
 * 使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 注意: 不能使用代码库中的排序函数来解决这道题。
 * 进阶：
 * <p>
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-colors
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : CaiYongcheng
 * @date : 2020-07-16 09:23
 **/
public class _75SevenFive {

    /**
     * 示例:
     * 输入: [2,0,2,1,1,0]
     * 输出: [0,0,1,1,2,2]
     *
     * @param nums
     */
    public static void sortColors(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int l1 = -1;
        int r1 = -1;
        while (left <= right) {
            while (left <= right && nums[left] != 2) {
                if (nums[left] == 1 && l1 != -1) {
                    l1 = left;
                }
                ++left;
            }
            if (left > right) {
                break;
            }
            while (left <= right && nums[right] != 0) {
                if (nums[right] == 1 && r1 != -1) {
                    r1 = right;
                }
                --right;
            }
            if (left > right) {
                break;
            }
            if (r1 != -1 && l1 != -1) {
                nums[r1] = 2;
                nums[l1] = 0;
                nums[left] = nums[right] = 1;
                r1 = l1 = -1;
            } else if (r1 != -1) {
                nums[r1] = 2;
                nums[left] = 0;
                nums[right] = 1;
                r1 = right;
            } else if (l1 != -1) {
                nums[l1] = 0;
                nums[right] = 2;
                nums[left] = 1;
                l1 = left;
            } else {
                nums[right] = 2;
                nums[left] = 0;
            }
        }
    }

    public static void main(String[] args) {
        int[] ints = new int[]{2, 0, 2, 1, 1, 0};
        sortColors(ints);
        System.out.println(Arrays.toString(ints));
    }
}
