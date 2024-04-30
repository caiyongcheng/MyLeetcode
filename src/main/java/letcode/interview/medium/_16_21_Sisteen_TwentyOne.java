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

package interview.medium;

import java.util.Arrays;

/**
 * @program: Leetcode
 * @description: 给定两个整数数组，请交换一对数值（每个数组中取一个数值），使得两个数组所有元素的和相等。
 * 返回一个数组，第一个元素是第一个数组中要交换的元素，第二个元素是第二个数组中要交换的元素。
 * 若有多个答案，返回任意一个均可。若无满足条件的数值，返回空数组。
 * 示例:  来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/sum-swap-lcci 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-12-05 19:55
 */
public class _16_21_Sisteen_TwentyOne {


    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {4, 5, 6};
        int[] swapValues = new _16_21_Sisteen_TwentyOne().findSwapValues(arr1, arr2);
        System.out.println(Arrays.toString(swapValues));
    }

    public int getArrayElementSum(int[] array) {
        int elementSum = 0;
        for (int element : array) {
            elementSum += element;
        }
        return elementSum;
    }

    /**
     * 示例:
     * 输入: array1 = [4, 1, 2, 1, 1, 2], array2 = [3, 6, 3, 3]
     * 输出: [1, 3]
     * 示例:
     * 输入: array1 = [1, 2, 3], array2 = [4, 5, 6]
     * 输出: []
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sum-swap-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param array1
     * @param array2
     * @return
     */
    public int[] findSwapValues(int[] array1, int[] array2) {
        int[] resultArray = new int[2];
        Arrays.sort(array1);
        Arrays.sort(array2);
        int sum1 = getArrayElementSum(array1);
        int sum2 = getArrayElementSum(array2);
        int sumGap = sum1 - sum2;
        if (Math.abs(sumGap) % 2 == 1) {
            return new int[0];
        }
        sumGap /= 2;
        if (array2.length < array1.length) {
            sumGap = -sumGap;
            for (int element2 : array2) {
                int index = Arrays.binarySearch(array1, element2 - sumGap);
                if (index > 0) {
                    resultArray[1] = element2;
                    resultArray[0] = array1[index];
                    return resultArray;
                }
            }
        } else {
            for (int element1 : array1) {
                int index = Arrays.binarySearch(array2, element1 - sumGap);
                if (index > 0) {
                    resultArray[0] = element1;
                    resultArray[1] = array2[index];
                    return resultArray;
                }
            }
        }
        return new int[0];
    }

}