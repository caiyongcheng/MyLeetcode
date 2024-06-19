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
 * 峰值元素是指其值严格大于左右相邻值的元素。  给你一个整数数组nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * 你可以假设nums[-1] = nums[n] = -∞ 。  你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/find-peak-element 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * nums[i] != nums[i + 1]
 *
 * @author CaiYongcheng
 * @date 2021-09-15 10:40
 **/
public class _162 {

    private int[] arr;

    public int findPeakElement(int[] nums) {
        /*
         * 分析：
         * nums[-1] = nums[n] = -∞ 意味着 nums[0] > nums[1]  nums[n-1] > nums[n]
         * nums[i] != nums[i + 1] 意味着相邻元素不会相等
         * O(log n) 的算法 要求 每次迭代都要减少 查询范围
         * 如果 nums[index] 大于 nums[index-1] 说明 [index, end]区域一定存在峰值
         * 如果 nums[index] 小于 nums[index-1] 说明 [0, index-1]区域一定有峰值
         * 不断压缩区域即可
         */
        arr = nums;
        int left = 0;
        int right = nums.length;
        int mid;
        while (right - left >= 2) {
            mid = (left + right) >> 1;
            if (greater(mid, mid - 1)) {
                if (greater(mid, mid + 1)) {
                    return mid;
                }
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return greater(left, right) ? left : right;
    }

    public boolean greater(int i, int j) {
        if (i == -1 || i == arr.length) {
            return false;
        }
        if (j == -1 || j == arr.length) {
            return true;
        }
        return arr[i] > arr[j];
    }

    /**
     * 示例 1：
     * 输入：nums = [1,2,3,1]
     * 输出：2
     * 解释：3 是峰值元素，你的函数应该返回其索引 2。
     * <p>
     * 示例2：
     * 输入：nums = [1,2,1,3,5,6,4]
     * 输出：1 或 5
     * 解释：你的函数可以返回索引 1，其峰值元素为 2；
     * 或者返回索引 5， 其峰值元素为 6。
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 1000
     * -231 <= nums[i] <= 231 - 1
     * 对于所有有效的 i 都有 nums[i] != nums[i + 1]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-peak-element
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _162().findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4}));
    }


}
