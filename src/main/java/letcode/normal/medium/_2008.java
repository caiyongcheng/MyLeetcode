package letcode.normal.medium;

import letcode.utils.TestCaseUtils;

import java.util.*;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/12/12 9:24
 * description 你驾驶出租车行驶在一条有 n 个地点的路上。这 n 个地点从近到远编号为 1 到 n ，你想要从 1 开到 n ，通过接乘客订单盈利。
 * 你只能沿着编号递增的方向前进，不能改变方向。
 * 乘客信息用一个下标从 0 开始的二维数组 rides 表示，其中 rides[i] = [starti, endi, tipi] 表示第 i 位乘客需要从地点 starti 前往 endi ，愿意支付 tipi 元的小费。
 * 每一位 你选择接单的乘客 i ，你可以 盈利 endi - starti + tipi 元。你同时 最多 只能接一个订单。
 * 给你 n 和 rides ，请你返回在最优接单方案下，你能盈利 最多 多少元。  注意：你可以在一个地点放下一位乘客，并在同一个地点接上另一位乘客。
 */
public class _2008 {

    public long maxTaxiEarnings(int n, int[][] rides) {
        // 动态规划
        // profitArr[i] 表示从n-1到i为止的最大收益 i指的是乘客
        // 所以 profitArr[i] = max(profitArr[i+1], rides[i][1] - rides[i][0] + rides[i][2] + profitArr[下一个可以接单的点])
        int len = rides.length;
        Arrays.sort(rides, (a, b) -> a[0] - b[0]);
        long[] profitArr = new long[len + 1];
        profitArr[len - 1] = rides[len - 1][1] - rides[len - 1][0] + rides[len - 1][2];
        for (int i = profitArr.length - 2; i >= 0; i--) {
            int start = bSearch(rides, i, rides[i][1]);
            profitArr[i] = Long.max(profitArr[i + 1], profitArr[start] + rides[i][1] - rides[i][0] + rides[i][2]);
        }
        return profitArr[0];
    }

    public long maxTaxiEarnings2(int n, int[][] rides) {
        // 动态规划
        // dp[i] 表示 车辆到达i位置时的最大收益
        // dp[i] = max(dp[i] - 1[无乘客在i点下车] +  max(i-j+p[for(终点在i的乘客[j, i, p]]))
        Map<Integer, List<Integer>> groupByEnd = new HashMap<>();
        for (int i = 0; i < rides.length; i++) {
            List<Integer> endList = groupByEnd.computeIfAbsent(rides[i][1], k -> new ArrayList<>());
            endList.add(i);
        }
        long[] profitArr = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            profitArr[i] = profitArr[i - 1];
            List<Integer> endList = groupByEnd.get(i);
            if (endList != null) {
                for (Integer start : endList) {
                    profitArr[i] = Long.max(profitArr[i], profitArr[rides[start][0]] + rides[start][1] - rides[start][0] + rides[start][2]);
                }
            }
        }
        return profitArr[n];
    }

    public int bSearch(int[][] rides, int start, int target) {
        if (rides[start][0] >= target) {
            return start;
        }
        if (rides[rides.length - 1][0] < target) {
            return rides.length;
        }
        int l = start;
        int r = rides.length - 1;
        int mid = l + 1;
        while (true) {
            mid = (l + r) >>> 1;
            if (l == mid) {
                break;
            }
            if (rides[mid][0] >= target) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }


    /**
     * 示例 1：
     *
     * 输入：n = 5, rides = [[2,5,4],[1,5,1]]
     * 输出：7
     * 解释：我们可以接乘客 0 的订单，获得 5 - 2 + 4 = 7 元。
     * 示例 2：
     *
     * 输入：n = 20, rides = [[1,6,1],[3,10,2],[10,12,3],[11,12,2],[12,15,2],[13,18,1]]
     * 输出：20
     * 解释：我们可以接以下乘客的订单：
     * - 将乘客 1 从地点 3 送往地点 10 ，获得 10 - 3 + 2 = 9 元。
     * - 将乘客 2 从地点 10 送往地点 12 ，获得 12 - 10 + 3 = 5 元。
     * - 将乘客 5 从地点 13 送往地点 18 ，获得 18 - 13 + 1 = 6 元。
     * 我们总共获得 9 + 5 + 6 = 20 元。
     * @param args
     */
    public static void main(String[] args) {
/*        System.out.println(new _2008().maxTaxiEarnings2(
                20,
                TestCaseUtils.get2DIntArr("[[1,6,1],[3,10,2],[10,12,3],[11,12,2],[12,15,2],[13,18,1]]")
        ));*/
        int[][] dIntArr = TestCaseUtils.get2DIntArr("[[1,6,1],[3,10,2],[10,12,3],[11,12,2],[12,15,2],[13,18,1]]");
        System.out.println(dIntArr);
    }

}
