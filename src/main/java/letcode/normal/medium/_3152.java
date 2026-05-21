package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * 如果数组的每一对相邻元素都是两个奇偶性不同的数字，则该数组被认为是一个 特殊数组 。
 * 周洋哥有一个整数数组 nums 和一个二维整数矩阵 queries，对于 queries[i] = [fromi, toi]，请你帮助周洋哥检查子数组 nums[fromi..toi] 是不是一个 特殊数组 。
 * 返回布尔数组 answer，如果 nums[fromi..toi] 是特殊数组，则 answer[i] 为 true ，否则，answer[i] 为 false 。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-08-14 09:02
 */
public class _3152 {

    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int[] dp = new int[nums.length];
        for (int i = 1; i < dp.length; i++) {
            if (((nums[i] ^ nums[i - 1]) & 1) == 0) {
                dp[i] = i;
            } else {
                dp[i] = dp[i - 1];
            }
        }

        boolean[] ans = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            if (dp[queries[i][1]] <= queries[i][0]) {
                ans[i] = true;
            }
        }
        return ans;
    }


}
