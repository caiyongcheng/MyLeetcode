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
 * 给你一个整数数组 nums 和一个整数 k 。
 * 区间 [left, right]（left <= right）的 异或结果 是对下标位于left 和 right（包括 left 和 right ）之间所有元素进行 XOR 运算的结果：
 * nums[left] XOR nums[left+1] XOR ... XOR nums[right] 。  返回数组中 要更改的最小元素数 ，以使所有长度为 k 的区间异或结果等于零。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/make-the-xor-of-all-segments-equal-to-zero 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 提示：
 * 1 <= k <= nums.length <= 2000
 * 0 <= nums[i] < 2^10
 *
 * @author CaiYongcheng
 * @since 2021-05-25 09:07
 **/
public class _1787 {

    // x 的范围为 [0, 2^10)
    static final int MAXX = 1 << 10;
    // 极大值，为了防止整数溢出选择 INT_MAX / 2
    static final int INFTY = Integer.MAX_VALUE / 2;


    /**
     * 示例 1：
     * 输入：nums = [1,2,0,3,0], k = 1
     * 输出：3
     * 解释：将数组 [1,2,0,3,0] 修改为 [0,0,0,0,0]
     *
     * 示例 2：
     * 输入：nums = [3,4,5,2,1,7,3,4,7], k = 3
     * 输出：3
     * 解释：将数组 [3,4,5,2,1,7,3,4,7] 修改为 [3,4,7,3,4,7,3,4,7]
     *
     * 示例 3：
     * 输入：nums = [1,2,4,1,2,5,1,2,6], k = 3
     * 输出：3
     * 解释：将数组[1,2,4,1,2,5,1,2,6] 修改为 [1,2,3,1,2,3,1,2,3]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/make-the-xor-of-all-segments-equal-to-zero
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @param k
     * @return
     */
    public int minChanges(int[] nums, int k) {
        /**
         *
         * 满足条件的数组 应有 ans[i]^ans[i+1]^...ans[i+k-1] = ans[i+1]^.....ans[i+k] == 0
         * => (ans[i]^ans[i+1]^...ans[i+k-1]) ^ (ans[i+1]^.....ans[i+k]) == 0
         * => ans[i]^ans[i+k] == 0
         * ==> ans[i] == ans[i+k]
         * 所以可以按照 i%n=j 将数组分组，每个数组中的元素相等。
         * ---------------------------------------------------------------------------------
         * dp:
         * 假设 dp[i, target] 表示 第i组，x[0]^...x[i]值为mask 需要的最小交换次数
         * size[i] 表示第i组元素数量
         * count[i, x] = 第i组中x元素的数量
         * dp[i, target] = min（dp[i-1, target^x] + size[i] - count[i, x]）
         * = size[i] + min（dp[i-1, target^x] - count[i, x]）
         * 一共有k组， 每组有target=1024个dp[i, target]要计算， 每个确定target对应的min（dp[i-1, target^x] - count[i, x]）要计算1024次
         * 也就是最后的时间复杂度=O（k2^20）
         * 但是 x取值范围[0, 1024], target取值范围[0, 1024]故异或后最大值不超过1024
         * 也就是min（dp[i-1, target^x] - count[i, x]）是固定的，只需求一次
         *
         * ----------------------------------------------------------------------------------
         */
        int len = nums.length;
        int[] cache = new int[1024];
        Arrays.fill(cache, Integer.MAX_VALUE/2);
        cache[0] = 0;
        for (int i = 0; i < k; i++) {
            HashMap<Integer, Integer> map = new HashMap<>();
            int size = 0;
            //第i组的count
            for (int j = i; j < len; j+=k) {
                ++size;
                map.put(nums[j], map.getOrDefault(nums[j], 0)+1);
            }
            //dp[i-1, target^x]的最小值
            int asInt = Arrays.stream(cache).min().getAsInt();
            //nowCache就是第i组的dp[i, target]
            //dp[i, target]要么等于不在map中的dp[i-1, ？]+size，那么等于在map中的dp[i-1, target^x]+size-count[i, x]
            int[] nowCache = new int[1024];
            Arrays.fill(nowCache, asInt);
            //计算nowCache
            for (int target = 0; target < 1024; target++) {
                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    nowCache[target] = Math.min(nowCache[target], cache[target^entry.getKey()] - entry.getValue());
                }
                nowCache[target] += size;
            }
            cache = nowCache;
        }
        return cache[0];
    }

    /**
     * 示例 1：
     * 输入：nums = [1,2,0,3,0], k = 1
     * 输出：3
     * 解释：将数组 [1,2,0,3,0] 修改为 [0,0,0,0,0]
     *
     * 示例 2：
     * 输入：nums = [3,4,5,2,1,7,3,4,7], k = 3
     * 输出：3
     * 解释：将数组 [3,4,5,2,1,7,3,4,7] 修改为 [3,4,7,3,4,7,3,4,7]
     *
     * 示例 3：
     * 输入：nums = [1,2,4,1,2,5,1,2,6], k = 3
     * 输出：3
     * 解释：将数组[1,2,4,1,2,5,1,2,6] 修改为 [1,2,3,1,2,3,1,2,3]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/make-the-xor-of-all-segments-equal-to-zero
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1787().minChanges(
                new int[]{1,2,4,1,2,5,1,2,6},
                3
        ));
    }

}
