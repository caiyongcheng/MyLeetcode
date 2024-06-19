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
public class _787 {


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
        System.out.println(new _787().findCheapestPrice(
                3,
                new int[][]{{0,1,100},{1,2,100},{0,2,500}},
                0,
                2,
                0
        ));
    }



}
