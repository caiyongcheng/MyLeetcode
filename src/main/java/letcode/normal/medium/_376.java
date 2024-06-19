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
 * @program: Leetcode
 * @description: 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。
 * 第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。
 * 例如，[1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3)是正负交替出现的。
 * 相反, [1,4,7,2,5]和[1,7,4,5,5] 不是摆动序列，
 * 第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
 * 给定一个整数序列，返回作为摆动序列的最长子序列的长度。
 * 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，
 * 剩下的元素保持其原始顺序。  来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/wiggle-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-12-11 17:34
 */
public class _376 {


    /**
     * 示例 1:
     * 输入: [1,7,4,9,2,5]
     * 输出: 6
     * 解释: 整个序列均为摆动序列。
     * 示例 2:
     * 输入: [1,17,5,10,13,15,10,5,16,8]
     * 输出: 7
     * 解释: 这个序列包含几个长度为 7 摆动序列，其中一个可为[1,17,10,13,10,16,8]。
     * 示例 3:
     * 输入: [1,2,3,4,5,6,7,8,9]
     * 输出: 2
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/wiggle-subsequence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _376().wiggleMaxLength(
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}
        ));
    }

    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        if (nums.length == 2) {
            return nums[0] == nums[1] ? 1 : 2;
        }
        int maxLength = Integer.MIN_VALUE;
        int[][] seqLength = new int[nums.length][2];
        Arrays.fill(seqLength, new int[]{1, 1});
        for (int index = nums.length - 2; index >= 0; index--) {
            if (nums[index + 1] > nums[index]) {
                seqLength[index][0] = 1 + seqLength[index + 1][1];
            } else if (nums[index + 1] < nums[index]) {
                seqLength[index][1] = 1 + seqLength[index + 1][0];
            }
        }
        for (int index = seqLength.length - 1; index >= 0; index--) {
            if (seqLength[index][0] > maxLength) {
                maxLength = seqLength[index][0];
            }
            if (seqLength[index][1] > maxLength) {
                maxLength = seqLength[index][1];
            }
        }
        return maxLength;
    }


}
