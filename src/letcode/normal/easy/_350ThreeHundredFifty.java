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

package normal.easy;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @program: Leetcode
 * @description: 给定两个数组，编写一个函数来计算它们的交集。
 * @author: 蔡永程
 * @create: 2021-01-12 16:15
 */
public class _350ThreeHundredFifty {


    /**
     * 示例 1：
     * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2,2]
     * 示例 2:
     * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出：[4,9]
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                new _350ThreeHundredFifty().intersect(
                        new int[]{1, 2, 2, 1},
                        new int[]{1, 1}
                )
        ));
    }

    public void quickSortForArray(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int l = left;
        int r = right;
        int separateValue = nums[left];
        while (l < r) {
            while (l < r && nums[r] > separateValue) --r;
            if (l < r) nums[l++] = nums[r];
            while (l < r && nums[l] <= separateValue) ++l;
            if (l < r) nums[r--] = nums[l];
        }
        nums[r] = separateValue;
        quickSortForArray(nums, left, r - 1);
        quickSortForArray(nums, r + 1, right);
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        final ArrayList<Integer> uniteSet = new ArrayList<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int index1 = 0;
        int index2 = 0;
        while (index1 < nums1.length && index2 < nums2.length) {
            if (nums1[index1] < nums2[index2]) {
                ++index1;
            } else if (nums1[index1] > nums2[index2]) {
                ++index2;
            } else {
                uniteSet.add(nums1[index1]);
                ++index1;
                ++index2;
            }
        }
        final int[] resultArray = new int[uniteSet.size()];
        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = uniteSet.get(i);
        }
        return resultArray;
    }

}