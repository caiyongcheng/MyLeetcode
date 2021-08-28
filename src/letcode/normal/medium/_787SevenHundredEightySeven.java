package letcode.normal.medium;

import java.util.Arrays;

/**
 * @program: Leetcode
 * @description: 有 n 个城市通过 m 个航班连接。每个航班都从城市 u 开始，以价格 w 抵达 v。
 * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到从 src 到 dst 最多经过 k站中转的最便宜的价格。
 * 如果没有这样的路线，则输出 -1。  来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/cheapest-flights-within-k-stops 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-11-20 23:10
 */
public class _787SevenHundredEightySeven {


    /**
     * 1 <= n <= 100
     * 0 <= flights.length <= (n * (n - 1) / 2)
     * flights[i].length == 3
     * 0 <= fromi, toi < n
     * fromi != toi
     * 1 <= pricei <= 104
     * 航班没有重复，且不存在自环
     * 0 <= src, dst, k < n
     * src != dst
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/cheapest-flights-within-k-stops
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param n
     * @param flights
     * @param src
     * @param dst
     * @param k
     * @return
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        /*
         * dp
         * dp[k][i] 表示经过k个中转到达i的最小花费 那么 dp[k][i] = Math.min(dp[k-1][j]+cost(j,i),  dp[k][i])
         */
        ++k;
        int unreachable = 1000000;
        int[][] dp = new int[k + 1][n];
        for (int[] ints : dp) {
            Arrays.fill(ints, unreachable);
        }
        dp[0][src] = 0;
        int ans = unreachable;
        for (int throughCityCount = 1; throughCityCount <= k; throughCityCount++) {
            for (int[] flight : flights) {
                dp[throughCityCount][flight[1]] = Math.min(dp[throughCityCount][flight[1]], dp[throughCityCount-1][flight[0]]+flight[2]);
            }
        }
        for (int throughCityCount = 0; throughCityCount <= k; throughCityCount++) {
            ans = Math.min(ans, dp[throughCityCount][dst]);
        }
        return ans >= unreachable ? -1 : ans;
    }

    /**
     * 示例 1：
     * 输入: 
     * n = 3, edges = {{0,1,100},{1,2,100},{0,2,500}}
     * src = 0, dst = 2, k = 1
     * 输出: 200
     * 解释: 
     * 城市航班图如下
     * 从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200，如图中红色所示。
     * 
     * 示例 2：
     * 输入: 
     * n = 3, edges = {{0,1,100},{1,2,100},{0,2,500}}
     * src = 0, dst = 2, k = 0
     * 输出: 500
     * 解释: 
     * 城市航班图如下
     * 从城市 0 到城市 2 在 0 站中转以内的最便宜价格是 500，如图中蓝色所示。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/cheapest-flights-within-k-stops
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _787SevenHundredEightySeven().findCheapestPrice(
                3,
                new int[][]{{0,1,100},{1,2,100},{0,2,500}},
                0,
                2,
                0
        ));
    }



}