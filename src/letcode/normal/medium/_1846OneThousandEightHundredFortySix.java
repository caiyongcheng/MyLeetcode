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
 * 给你一个正整数数组arr。请你对 arr执行一些操作（也可以不进行任何操作），使得数组满足以下条件：  arr中 第一个元素必须为1。
 * 任意相邻两个元素的差的绝对值 小于等于1，也就是说，对于任意的 1 <= i < arr.length（数组下标从 0 开始），都满足abs(arr[i] - arr[i - 1]) <= 1。abs(x)为x的绝对值。
 * 你可以执行以下 2 种操作任意次：  减小 arr中任意元素的值，使其变为一个 更小的正整数。 重新排列arr中的元素，你可以以任意顺序重新排列。
 * 请你返回执行以上操作后，在满足前文所述的条件下，arr中可能的 最大值。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/maximum-element-after-decreasing-and-rearranging 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-07-15 15:29
 **/
public class _1846OneThousandEightHundredFortySix {

    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        /**
         * 按照题目要求 arr 尽可能大 且可以任意重排序 也就意味着
         * 按升序排列的情况下，改数列是非递减数列。那么最大值=Math.min(数列长度，数列最小值)
         * 可以用计数排序
         */
        int max = 0;
        Arrays.sort(arr);
        for (int item : arr) {
            if (item - max <= 1) {
                max = item;
            } else {
                ++max;
            }
        }
        return max;
    }

    /**
     * 示例 1：
     * 输入：arr = [2,2,1,2,1]
     * 输出：2
     * 解释：
     * 我们可以重新排列 arr 得到 [1,2,2,2,1] ，该数组满足所有条件。
     * arr 中最大元素为 2 。
     *
     * 示例 2：
     * 输入：arr = [100,1,1000]
     * 输出：3
     * 解释：
     * 一个可行的方案如下：
     * 1. 重新排列 arr 得到 [1,100,1000] 。
     * 2. 将第二个元素减小为 2 。
     * 3. 将第三个元素减小为 3 。
     * 现在 arr = [1,2,3] ，满足所有条件。
     * arr 中最大元素为 3 。
     *
     * 示例 3：
     * 输入：arr = [1,2,3,4,5]
     * 输出：5
     * 解释：数组已经满足所有条件，最大元素为 5 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-element-after-decreasing-and-rearranging
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1846OneThousandEightHundredFortySix().maximumElementAfterDecrementingAndRearranging(
                new int[]{1,2,3,4,5}
        ));
    }


}
