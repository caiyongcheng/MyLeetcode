package letcode.normal.medium;

import letcode.utils.TestCaseInputUtils;

/**
 * 你需要访问 n 个房间，房间从 0 到 n - 1 编号。同时，每一天都有一个日期编号，从 0 开始，依天数递增。你每天都会访问一个房间。
 * 最开始的第 0 天，你访问 0 号房间。给你一个长度为 n 且 下标从 0 开始 的数组 nextVisit 。
 * 在接下来的几天中，你访问房间的 次序 将根据下面的 规则 决定：
 * 假设某一天，你访问 i 号房间。
 * 如果算上本次访问，访问 i 号房间的次数为 奇数 ，那么 第二天 需要访问 nextVisit[i] 所指定的房间，其中 0 <= nextVisit[i] <= i 。
 * 如果算上本次访问，访问 i 号房间的次数为 偶数 ，那么 第二天 需要访问 (i + 1) mod n 号房间。
 * 请返回你访问完所有房间的第一天的日期编号。题目数据保证总是存在这样的一天。由于答案可能很大，返回对 109 + 7 取余后的结果。
 *
 * n == nextVisit.length
 * 2 <= n <= 105
 * 0 <= nextVisit[i] <= i
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/3/28 10:08
 */
public class _1997 {

    public int firstDayBeenInAllRooms(int[] nextVisit) {
        /*
         * 根据条件3 0 <= nextVisit[i] <= i 得出 当次数为奇时候 最多呆在原地，下一次为偶数的时候，才能往前进一步。
         * 也就是说 最后到达的点一定是n-1 只有到达某个点是偶数的时候才能往前走
         * 所以 定义dp[i][1] 表示到达i为奇数的时候花的天数 dp[i][0] 表示到达i为偶数的时候花的天数
         * dp[i][1] = dp[i-1][0] + 1
         * dp[i][0] = dp[i][1](第一次到达i点) + 1(到达nextVisit[i]点,次数为奇数，因为前面已经离开过该点) + (dp[i][1] - dp[nextVisit[i]][1])(从nextVisit[i]到达i的距离)
         *          = dp[i][1] * 2 + 1 - dp[nextVisit[i]][1]
         * 联立得到 dp[i][1] =  dp[i-1][0] + 1 = dp[i-1][1] * 2 + 1 - dp[nextVisit[i-1]][1]  + 1
         * 简化得 dp[i] = dp[i-1] * 2 + 2 - dp[nextVisit[i-1]]
         */
        int mod = 10_0000_0000 + 7;
        int[] dp = new int[nextVisit.length];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = (dp[i-1] * 2 % mod + 2 + mod - dp[nextVisit[i-1]]) % mod;
        }
        return dp[dp.length - 1];
    }

    /**
     * 示例 1：
     *
     * 输入：nextVisit = [0,0]
     * 输出：2
     * 解释：
     * - 第 0 天，你访问房间 0 。访问 0 号房间的总次数为 1 ，次数为奇数。
     *   下一天你需要访问房间的编号是 nextVisit[0] = 0
     * - 第 1 天，你访问房间 0 。访问 0 号房间的总次数为 2 ，次数为偶数。
     *   下一天你需要访问房间的编号是 (0 + 1) mod 2 = 1
     * - 第 2 天，你访问房间 1 。这是你第一次完成访问所有房间的那天。
     * 示例 2：
     *
     * 输入：nextVisit = [0,0,2]
     * 输出：6
     * 解释：
     * 你每天访问房间的次序是 [0,0,1,0,0,1,2,...] 。
     * 第 6 天是你访问完所有房间的第一天。
     * 示例 3：
     *
     * 输入：nextVisit = [0,1,2,0]
     * 输出：6
     * 解释：
     * 你每天访问房间的次序是 [0,0,1,1,2,2,3,...] 。
     * 第 6 天是你访问完所有房间的第一天。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1997().firstDayBeenInAllRooms(
                TestCaseInputUtils.getIntArr("[0,1,2,0]")
        ));
    }

}
