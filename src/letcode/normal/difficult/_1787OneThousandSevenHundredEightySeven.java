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
 * @date 2021-05-25 09:07
 **/
public class _1787OneThousandSevenHundredEightySeven {

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
        System.out.println(new _1787OneThousandSevenHundredEightySeven().minChanges(
                new int[]{1,2,4,1,2,5,1,2,6},
                3
        ));
    }

}
