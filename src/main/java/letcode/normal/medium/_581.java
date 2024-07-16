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


/**
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。  请你找出符合题意的 最短 子数组，并输出它的长度。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-08-03 10:05
 **/
public class _581 {

    public int findUnsortedSubarray(int[] nums) {
        /**
         * 将 nums 划分为 3 部分 na nb nc
         * 其中na nc是符合要求的 nb是需要求的
         * 解法1 排序 逐个位置比较
         * 解法2 na nc 都满足 num[0..i-1] <= num[i] <= num[i+1, n-1]
         * 而nb的左边界，右边界是第一个和最后一个不满足条件的
         * 求出na nb的左右边界即可
         */
        int len = nums.length - 1;
        int nowMaxVal = Integer.MIN_VALUE;
        int nowMinVal = Integer.MAX_VALUE;
        int left = -1;
        int right = -1;
        for (int i = 0; i < nums.length; i++) {
            //右边界
            if (nums[i] < nowMaxVal) {
                right = i;
            } else {
                nowMaxVal = nums[i];
            }
            //左边界
            if (nums[len-i] > nowMinVal) {
                left = len - i;
            } else {
                nowMinVal = nums[len - i];
            }
        }
        return right == -1 ? 0 : right - left + 1;
    }


    /**
     * 示例 1：
     * 输入：nums = [2,6,4,8,10,9,15]
     * 输出：5
     * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
     *
     * 示例 2：
     * 输入：nums = [1,2,3,4]
     * 输出：0
     *
     * 示例 3：
     * 输入：nums = [1]
     * 输出：0
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _581().findUnsortedSubarray(
                new int[]{1,2,3,3,3}
        ));
    }

}
