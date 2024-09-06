package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.*;

/**
 * 给你一个整数数组 nums 和一个 非负 整数 k 。如果一个整数序列 seq 满足在范围下标范围 [0, seq.length - 2] 中存在
 * 不超过 k 个下标 i 满足 seq[i] != seq[i + 1] ，那么我们称这个整数序列为 好 序列。
 * 请你返回 nums 中 好  子序列  的最长长度
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-09-06 14:51
 */
public class _3176 {

    public int maximumLength(int[] nums, int k) {
        List<int[]> consecutiveLengthList = new ArrayList<>();
        for (int i = 0, j = 0; i < nums.length; ) {
            for (j = i + 1; j < nums.length && nums[j] == nums[i]; j++);
            consecutiveLengthList.add(new int[]{j - i, nums[i]});
            i = j;
        }

        Map<Integer, Integer> num2IdxMap = new HashMap<>();
        int[] num2NextIdxArr = new int[consecutiveLengthList.size()];
        for (int i = consecutiveLengthList.size() - 1; i >= 0; i--) {
            num2NextIdxArr[i] = num2IdxMap.getOrDefault(consecutiveLengthList.get(i)[1], -1);
            num2IdxMap.put(consecutiveLengthList.get(i)[1], i);
        }


        if (k >= consecutiveLengthList.size() + 1) {
            return nums.length;
        }
        int[][][] dp = new int[consecutiveLengthList.size()][k + 1][2];
        Arrays.fill(dp[dp.length - 1][0], -1000);
        dp[dp.length - 1][0][1] = consecutiveLengthList.get(dp.length - 1)[0];
        dp[dp.length - 1][0][0] = dp[dp.length - 1][0][1];
        for (int i = dp.length - 2; i >= 0; i--) {
            dp[i][0][1] = consecutiveLengthList.get(i)[0];
            dp[i][0][0] = Math.max(dp[i+1][0][0], dp[i][0][1]);
            for (int diff = 1; diff < dp[i].length; diff++) {
                dp[i][diff][1] = dp[i + 1][diff - 1][0] + dp[i][0][1];
                dp[i][diff][0] = Math.max(dp[i + 1][diff - 1][0] + dp[i][0][1], dp[i + 1][diff][0]);
            }
            if (num2NextIdxArr[i] != -1) {
                for (int diff = dp[i].length - 1; diff >= 0; diff--) {
                    dp[i][diff][1] = Math.max(dp[i][diff][1], dp[num2NextIdxArr[i]][diff][1] + dp[i][0][1]);
                    dp[i][diff][0] = Math.max(dp[i][diff][0], dp[i][diff][1]);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < dp[0].length; i++) {
            ans = Math.max(ans, dp[0][i][0]);
        }
        return ans;
    }

    private int maximumLength1(int[] nums, int k) {
        // 压缩数组 将其表示为相同数字数量的数组
        // 那么数组中的每个元素肯定与其相邻元素不一致 此时计算结果即可

        // 理解错题意 是子序列 而不是子数组
        List<Integer> consecutiveLengthList = new ArrayList<>();
        for (int i = 0, j = 0; i < nums.length; ) {
            for (j = i + 1; j < nums.length && nums[j] == nums[i]; j++);
            consecutiveLengthList.add(j - i);
            i = j;
        }
        if (k >= consecutiveLengthList.size() + 1) {
            return nums.length;
        }
        int ans = 0;
        for (int i = 0; i < k + 1; ++i) {
            ans += consecutiveLengthList.get(i);
        }

        int preSum = ans;
        for (int i = k + 1; i < consecutiveLengthList.size(); ++i) {
            preSum = preSum - consecutiveLengthList.get(i - k - 1) + consecutiveLengthList.get(i);
            ans = Math.max(ans, preSum);
        }
        return ans;
    }

    /**
     * Example 1:
     *
     * Input: nums = [1,2,1,1,3], k = 2
     *
     * Output: 4
     *
     * Explanation:
     *
     * The maximum length subsequence is [1,2,1,1,3].
     *
     * Example 2:
     *
     * Input: nums = [1,2,3,4,5,1], k = 0
     *
     * Output: 2
     *
     * Explanation:
     *
     * The maximum length subsequence is [1,2,3,4,5,1].
     * @param args
     */
    public static void main(String[] args) {
        //TestUtil.test(_3176.class);
        //TestUtil.test(_3176.class, "=[29,30,30],=0");
        TestUtil.test(_3176.class, "=[60,58,60,59,58,58],=2");
    }

}
