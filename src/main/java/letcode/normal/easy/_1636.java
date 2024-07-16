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

import datastructure.utils.FormatPrintUtils;

/**
 * @author Caiyongcheng
 * @description 给你一个整数数组nums，请你将数组按照每个值的频率 升序 排序。如果有多个值的频率相同，
 * 请你按照数值本身将它们 降序 排序。  请你返回排序后的数组。
 * 来源：力扣（LeetCode） 链接：https://leetcode.cn/problems/sort-array-by-increasing-frequency
 * 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 * @since 2022/9/19 17:55
 */
public class _1636 {

    private int[] tempArr;
    private int temp;

    private int[] nums;

    private final int[] cnt = new int[202];

    public int[] frequencySort(int[] nums) {
        this.nums = nums;
        for (int i = 0; i < this.nums.length; i++) {
            this.nums[i] += 100;
            cnt[this.nums[i]]++;
        }
        tempArr = new int[nums.length];
        mergeSort(0, nums.length / 2, nums.length - 1);
        for (int i = 0; i < this.nums.length; i++) {
            this.nums[i] -= 100;
        }
        return nums;
    }


    public void mergeSort(int left, int mid, int right) {
        if (left == right) {
            return;
        }
        if (left + 1 == mid) {
            if (cnt[nums[mid]] < cnt[nums[left]] || (cnt[nums[mid]] == cnt[nums[left]] && nums[mid] > nums[left])) {
                temp = nums[mid];
                nums[mid] = nums[left];
                nums[left] = temp;
            }
        } else if (mid > left) {
            mergeSort(left, (left + mid) / 2, mid);
        }
        if (mid + 2 == right) {
            if (cnt[nums[mid + 1]] > cnt[nums[right]] || (cnt[nums[mid + 1]] == cnt[nums[right]] && nums[mid + 1] < nums[right])) {
                temp = nums[mid + 1];
                nums[mid + 1] = nums[right];
                nums[right] = temp;
            }
        } else if (right > mid + 2) {
            mergeSort(mid + 1, (right + mid + 1) / 2, right);
        }
        int i = left;
        int len = right;
        int rightleft = mid + 1;
        int index = 0;
        while (left <= mid || rightleft <= right) {
            if (left > mid) {
                tempArr[index++] = nums[rightleft++];
                continue;
            }
            if (rightleft > right) {
                tempArr[index++] = nums[left++];
                continue;
            }
            if (cnt[nums[left]] < cnt[nums[rightleft]] || (cnt[nums[left]] == cnt[nums[rightleft]] && nums[left] > nums[rightleft])) {
                tempArr[index++] = nums[left++];
            } else {
                tempArr[index++] = nums[rightleft++];
            }
        }
        for (int j = 0; i <= len; ++i, ++j) {
            nums[i] = tempArr[j];
        }
    }


    /**
     * 示例 1：
     * <p>
     * 输入：nums = [1,1,2,2,2,3]
     * 输出：[3,1,1,2,2,2]
     * 解释：'3' 频率为 1，'1' 频率为 2，'2' 频率为 3 。
     * 示例 2：
     * <p>
     * 输入：nums = [2,3,1,3,2]
     * 输出：[1,3,3,2,2]
     * 解释：'2' 和 '3' 频率都为 2 ，所以它们之间按照数值本身降序排序。
     * 示例 3：
     * <p>
     * 输入：nums = [-1,1,-6,4,5,-6,1,4,1]
     * 输出：[5,-1,4,4,-6,-6,1,1,1]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/sort-array-by-increasing-frequency
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatPrintUtils.formatArray(new _1636().frequencySort(
                new int[]{-1, 1, -6, 4, 5, -6, 1, 4, 1}
        )));
    }


}
