package letcode.normal.medium;

import letcode.utils.TestCaseUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个下标从 0 开始的整数数组 nums ，以及整数 modulo 和整数 k 。
 * 请你找出并统计数组中 趣味子数组 的数目。
 * 如果 子数组 nums[l..r] 满足下述条件，则称其为 趣味子数组 ：  在范围 [l, r] 内，设 cnt 为满足 nums[i] % modulo == k 的索引 i 的数量。
 * 并且 cnt % modulo == k 。 以整数形式表示并返回趣味子数组的数目。  注意：子数组是数组中的一个连续非空的元素序列。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-07-11 11:20
 */
public class _2845 {

    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        /*
        找出符合条件的数组下标 对下标进行遍历
        假设 list[i...j]的数量满足(i - j + 1) % modulo == k
        那么数量就等 nums(list[i-1]...list[i]] * nums[list[j]...list[j+1])
        这时候就要不停枚举i、j位置 时间复杂度会来到O(n^2)
        但是注意到对于j，我们只关心有哪些位置可以和他满足条件，这时候可以提前缓存好这些数量即可，那么时间复杂度就会来到o(n)
         */

        int cnt = 0;
        Map<Integer, Integer> mod2CntMap = new HashMap<>();
        mod2CntMap.put(0, 1);
        long ans = 0L;
        for (Integer num : nums) {
            if (num % modulo == k) {
                cnt = (cnt + 1) % modulo;
            }
            ans += mod2CntMap.getOrDefault((cnt - k + modulo) % modulo, 0);
            mod2CntMap.merge(cnt, 1, Integer::sum);
        }
        return ans;
    }


    /**
     * 示例 1：
     *
     * 输入：nums = [3,2,4], modulo = 2, k = 1
     * 输出：3
     * 解释：在这个示例中，趣味子数组分别是：
     * 子数组 nums[0..0] ，也就是 [3] 。
     * - 在范围 [0, 0] 内，只存在 1 个下标 i = 0 满足 nums[i] % modulo == k 。
     * - 因此 cnt = 1 ，且 cnt % modulo == k 。
     * 子数组 nums[0..1] ，也就是 [3,2] 。
     * - 在范围 [0, 1] 内，只存在 1 个下标 i = 0 满足 nums[i] % modulo == k 。
     * - 因此 cnt = 1 ，且 cnt % modulo == k 。
     * 子数组 nums[0..2] ，也就是 [3,2,4] 。
     * - 在范围 [0, 2] 内，只存在 1 个下标 i = 0 满足 nums[i] % modulo == k 。
     * - 因此 cnt = 1 ，且 cnt % modulo == k 。
     * 可以证明不存在其他趣味子数组。因此，答案为 3 。
     * 示例 2：
     *
     * 输入：nums = [3,1,9,6], modulo = 3, k = 0
     * 输出：2
     * 解释：在这个示例中，趣味子数组分别是：
     * 子数组 nums[0..3] ，也就是 [3,1,9,6] 。
     * - 在范围 [0, 3] 内，只存在 3 个下标 i = 0, 2, 3 满足 nums[i] % modulo == k 。
     * - 因此 cnt = 3 ，且 cnt % modulo == k 。
     * 子数组 nums[1..1] ，也就是 [1] 。
     * - 在范围 [1, 1] 内，不存在下标满足 nums[i] % modulo == k 。
     * - 因此 cnt = 0 ，且 cnt % modulo == k 。
     * 可以证明不存在其他趣味子数组，因此答案为 2 。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2845().countInterestingSubarrays(
                TestCaseUtils.getIntegerList("[3,2,4]"),
                2,
                1
        ));
    }
}
