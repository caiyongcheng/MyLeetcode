package letcode.normal.medium;

import letcode.utils.TestCaseInputUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。
 * 如果子数组中所有元素都相等，则认为子数组是一个 等值子数组 。注意，空数组是 等值子数组 。
 * 从 nums 中删除最多 k 个元素后，返回可能的最长等值子数组的长度。
 * 子数组 是数组中一个连续且可能为空的元素序列。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/5/23 09:02
 */
public class _2831 {

    public int longestEqualSubarray(List<Integer> nums, int k) {
        /**
         * 666 12 666 771777
         * 以上面数据为例 如果k=1,那么77777是最佳结果 如果k=2 666666是最佳结果
         * 说明k值决定了要删除哪一块数据
         * 也就是说对于 nums[a..b]而言，众数数量为p 如果 b-a+1-p <= k 那么p就是范围a-b内的最佳结果
         * 如果是nums[a-1]不是众数 那么加入nums[a-1]只可能会导致p值不出现 nums[b+1]
         * 所以只考虑 nums[a] == nums[b]，且nums[a]是众数的情况
         * 但是区间众数的情况无疑是繁琐的 所以只需要计算各种情况 取最大值即可 众数一定是最优结果
         *
         *
         */

        int size = nums.size();
        Map<Integer, List<Integer>> idxGroupByVal = new HashMap<>();
        for (int i = 0; i < size; i++) {
            idxGroupByVal.computeIfAbsent(nums.get(i), x -> new ArrayList<>()).add(i);
        }

        int ans = 0;
        for (List<Integer> idxList : idxGroupByVal.values()) {
            int idxSize = idxList.size();
            if (idxSize < ans) {
                continue;
            }
            for (int i = 0, j = 0; i < idxSize && j < idxSize; i++) {
                while (j < idxSize && idxList.get(j) - idxList.get(i) <= j - i + k) {
                    ++j;
                }
                if (j - i > ans) {
                    ans = j - i;
                }
            }
        }
        return ans;
    }



    /**
     * 示例 1：
     *
     * 输入：nums = [1,3,2,3,1,3], k = 3
     * 输出：3
     * 解释：最优的方案是删除下标 2 和下标 4 的元素。
     * 删除后，nums 等于 [1, 3, 3, 3] 。
     * 最长等值子数组从 i = 1 开始到 j = 3 结束，长度等于 3 。
     * 可以证明无法创建更长的等值子数组。
     * 示例 2：
     *
     * 输入：nums = [1,1,2,2,1,1], k = 2
     * 输出：4
     * 解释：最优的方案是删除下标 2 和下标 3 的元素。
     * 删除后，nums 等于 [1, 1, 1, 1] 。
     * 数组自身就是等值子数组，长度等于 4 。
     * 可以证明无法创建更长的等值子数组。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2831().longestEqualSubarray(
                TestCaseInputUtils.getIntegerList("[2, 1]"),
                0
        ));
    }

}
