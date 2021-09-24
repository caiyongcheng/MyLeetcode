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

package letcode.interview.medium;

import letcode.utils.FormatUtils;

import java.util.Arrays;

/**
 * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
 *
 * @author CaiYongcheng
 * @date 2021-09-03 09:08
 **/
public class _17_14_Seventeen_Fourteen {


    public int[] smallestK(int[] arr, int k) {
        useQuickSort(arr, 0, arr.length - 1, k - 1);
        return Arrays.copyOf(arr, k);
    }


    public void useQuickSort(int[] arr, int start, int end, int target) {
        if (start >= arr.length || end < 0) {
            return;
        }
        int separator = arr[start];
        int left = start;
        int right = end;
        while (left < right) {
            while (left < right && arr[right] > separator) {
                --right;
            }
            if (right > left) {
                arr[left++] = arr[right];
            }
            while (left < right && arr[left] <= separator) {
                ++left;
            }
            if (right > left) {
                arr[right--] = arr[left];
            }
        }
        arr[right] = separator;
        if (right > target) {
            useQuickSort(arr, start, right - 1, target);
        } else if (right < target) {
            useQuickSort(arr, right+1, end, target);
        }
    }


    /**
     * 示例：
     * 输入： arr = [1,3,5,7,2,4,6,8], k = 4
     * 输出： [1,2,3,4]
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatUtils.formatArray(new _17_14_Seventeen_Fourteen().smallestK(
                new int[]{1, 3, 5, 7, 2, 4, 6, 8, 0, -1},
                4
        )));
    }

}
