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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums ，返回 nums 中所有 等差子序列 的数目。  如果一个序列中 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该序列为等差序列。
 * 例如，[1, 3, 5, 7, 9]、[7, 7, 7, 7] 和 [3, -1, -5, -9] 都是等差序列。 再例如，[1, 1, 2, 5, 7] 不是等差序列。
 * 数组中的子序列是从数组中删除一些元素（也可能不删除）得到的一个序列。  例如，[2,5,10] 是 [1,2,1,2,4,1,5,10] 的一个子序列。
 * 题目数据保证答案是一个 32-bit 整数。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/arithmetic-slices-ii-subsequence 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-08-11 09:05
 **/
public class _446 {

    public int numberOfArithmeticSlices(int[] nums) {
        /*
         * maps[i][diff] 表示 以nums[i]作为结尾，diff为差的长度最少是2的序列数量
         * 那么 maps[i][diff] =  maps[i][diff] + maps[j][diff] + 1; diff == nums[i]-nums[j]
         */
        Map<Long, Integer>[] maps = new HashMap[nums.length];
        for (int i = 0; i < maps.length; i++) {
            maps[i] = new HashMap<>();
        }
        long diff;
        int ans = 0;
        int lenJ;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; ++j) {
                diff = (long) nums[i] - nums[j];
                lenJ = maps[j].getOrDefault(diff, 0);
                maps[i].put(diff, maps[i].getOrDefault(diff, 0) + lenJ + 1);
                ans += lenJ;
            }
        }
        return ans;
    }

    /**
     * 示例 1：
     * 输入：nums = [2,4,6,8,10]
     * 输出：7
     * 解释：所有的等差子序列为：
     * [2,4,6]
     * [4,6,8]
     * [6,8,10]
     * [2,4,6,8]
     * [4,6,8,10]
     * [2,4,6,8,10]
     * [2,6,10]
     *
     * 示例 2：
     * 输入：nums = [7,7,7,7,7]
     * 输出：16
     * 解释：数组中的任意子序列都是等差子序列。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/arithmetic-slices-ii-subsequence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _446().numberOfArithmeticSlices(
                new int[]{7,7,7,7,7}
        ));
    }

}
