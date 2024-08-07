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

package letcode.normal.difficult;

/**
 * @program: MyLeetcode
 * @description: 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。
 * 例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到： 若旋转 4 次，则可以得到 [4,5,6,7,0,1,4] 若旋转 7 次，则可以得到 [0,1,4,4,5,6,7] 注意，
 * 数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 * 给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.difficult
 * @author: 6JSh5rC456iL
 * @since: 2021-04-09 10:46
 **/
public class _154 {


    /**
     * 原数组是 a[0]....a[j-1], a[j]....a[n]
     * 满足 a[0] <= a[j-1] <= a[j] <= a[n]
     * 变形后
     * a[j]...a[n],a[0]...a[j-1]
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        // 此时数组为 a[j]...a[n],a[0]...a[j-1] 的形式，其中a[0]...a[j-1]是递增序列，a[j]...a[n]是递增序列，且a[n]>=a[0]
        // 要寻找的答案是 a[j]
        int left = 0;
        int right = nums.length - 1;
        int middle;
        while (left < right) {
            middle = (left + right) / 2;
            //表明mid一定在[j]...a[n]
            if (nums[middle] > nums[right]) {
                left = middle + 1;
            }
            //表明mid一定在a[0]...a[j-1]
            //如果写作right = middle-1 当a[right] = a[0]时，将找不出答案
            else if(nums[middle] < nums[right]) {
                right = middle;
            }
            //只知道right可以舍去
            else{
                --right;
            }
        }
        return nums[left];
    }


    /**
     * 示例 1：
     * 输入：nums = [1,3,5]
     * 输出：1
     *
     * 示例 2：
     * 输入：nums = [2,2,2,0,1]
     * 输出：0


     * 提示：
     * n == nums.length
     * 1 <= n <= 5000
     * -5000 <= nums[i] <= 5000
     * nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
     *
     * 进阶：
     * 这道题是寻找旋转排序数组中的最小值的延伸题目。
     * 允许重复会影响算法的时间复杂度吗？会如何影响，为什么？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _154().findMin(new int[]{2,2,2,0,1}));
    }

}
