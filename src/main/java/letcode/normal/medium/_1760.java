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
 * @program: MyLeetcode
 * @description: 给你一个整数数组nums，其中nums[i]表示第i个袋子里球的数目。同时给你一个整数maxOperations。 
 * 你可以进行如下操作至多maxOperations次：  选择任意一个袋子，并将袋子里的球分到2 个新的袋子中，每个袋子里都有 正整数个球。 
 * 比方说，一个袋子里有5个球，你可以把它们分到两个新袋子里，分别有 1个和 4个球，或者分别有 2个和 3个球。 
 * 你的开销是单个袋子里球数目的 最大值，你想要 最小化开销。  请你返回进行上述操作后的最小开销。 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/minimum-limit-of-balls-in-a-bag 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @since: 2021-03-17 16:46
 **/
public class _1760 {

    public int minimumSize(int[] nums, int maxOperations) {
        int hi = nums[0];
        int lo = 1;
        int ans;
        int mid;
        int count;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > hi) {
                hi = nums[i];
            }
        }
        ans = hi;
        while (lo <= hi) {
            mid = (lo + hi) >> 1;
            count = 0;
            for (int num : nums) {
                count += (num-1) / mid;
            }
            if (count <= maxOperations) {
                hi = mid - 1;
                if (mid < ans) {
                    ans = mid;
                }
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 示例 1：
     * 输入：nums = [9], maxOperations = 2
     * 输出：3
     * 解释：
     * - 将装有 9 个球的袋子分成装有 6 个和 3 个球的袋子。[9] -> [6,3] 。
     * - 将装有 6 个球的袋子分成装有 3 个和 3 个球的袋子。[6,3] -> [3,3,3] 。
     * 装有最多球的袋子里装有 3 个球，所以开销为 3 并返回 3 。
     *
     * 示例 2：
     * 输入：nums = [2,4,8,2], maxOperations = 4
     * 输出：2
     * 解释：
     * - 将装有 8 个球的袋子分成装有 4 个和 4 个球的袋子。[2,4,8,2] -> [2,4,4,4,2] 。
     * - 将装有 4 个球的袋子分成装有 2 个和 2 个球的袋子。[2,4,4,4,2] -> [2,2,2,4,4,2] 。
     * - 将装有 4 个球的袋子分成装有 2 个和 2 个球的袋子。[2,2,2,4,4,2] -> [2,2,2,2,2,4,2] 。
     * - 将装有 4 个球的袋子分成装有 2 个和 2 个球的袋子。[2,2,2,2,2,4,2] -> [2,2,2,2,2,2,2,2] 。
     * 装有最多球的袋子里装有 2 个球，所以开销为 2 并返回 2 。
     *
     * 示例 3：
     * 输入：nums = [7,17], maxOperations = 2
     * 输出：7
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-limit-of-balls-in-a-bag
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1760().minimumSize(
                new int[]{431,922,158,60,192,14,788,146,788,775,772,792,68,143,376,375,877,516,595,82,56,704,160,403,713,504,67,332,26},
        80
        ));
    }
}
