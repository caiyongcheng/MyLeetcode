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

package interview.easy;

import java.util.Arrays;

/**
 * @program: Leetcode
 * @description: 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。
 * 编写一个方法，将 B 合并入 A 并排序。
 * 初始化A 和 B 的元素数量分别为m 和 n。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/sorted-merge-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-12-10 15:16
 */
public class _10_01_Ten_One {

    /**
     * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
     * 初始化A 和 B 的元素数量分别为m 和 n。
     * 示例:
     * 输入:
     * A = [1,2,3,0,0,0], m = 3
     * B = [2,5,6],       n = 3
     * 输出:[1,2,2,3,5,6]
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sorted-merge-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] intsA = {4, 5, 6, 0, 0, 0};
        int[] intB = {1, 2, 3};
        new _10_01_Ten_One().merge(intsA, 3, intB, 3);
        System.out.println(Arrays.toString(intsA));
    }

    public void merge(int[] A, int m, int[] B, int n) {
        int[] result = new int[m + n];
        int indexA = 0;
        int indexB = 0;
        int indexC = 0;
        while (indexA < m && indexB < n) {
            if (A[indexA] <= B[indexB]) {
                result[indexC] = A[indexA];
                ++indexA;
            } else {
                result[indexC] = B[indexB];
                ++indexB;
            }
            ++indexC;
        }
        if (indexA >= m) {
            while (indexB < n) {
                result[indexC] = B[indexB];
                ++indexB;
                ++indexC;
            }
        } else {
            while (indexA < m) {
                result[indexC] = A[indexA];
                ++indexA;
                ++indexC;
            }
        }
        for (int index = 0; index < m + n; index++) {
            A[index] = result[index];
        }
    }

}