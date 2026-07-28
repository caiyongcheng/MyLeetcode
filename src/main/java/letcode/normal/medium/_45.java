package letcode.normal.medium;

import java.util.Arrays;

/**
 * 给你一个非负整数数组nums ，你最初位于数组的第一个位置。  数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 假设你总是可以到达数组的最后一个位置。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/jump-game-ii 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-09-17 11:45
 **/
public class _45 {

    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE - 100000);
        dp[dp.length - 1] = 0;
        for (int i = dp.length - 2; i >= 0; i--) {
            for (int j = i; j < dp.length && j <= i + nums[i]; ++j) {
                dp[i] = Math.min(dp[j], dp[i]);
            }
            ++dp[i];
        }
        return dp[0];
    }

}
