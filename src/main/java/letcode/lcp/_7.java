package letcode.lcp;


/**
 * 小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：  有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0 每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。
 * 传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。
 * 每轮信息必须需要传递给另一个人，且信息可重复经过同一个人 给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。
 * 返回信息从小 A (编号 0 ) 经过 k 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，返回 0。
 * 限制：
 * 2 <= n <= 10
 * 1 <= k <= 5
 * 1 <= relation.length <= 90, 且 relation[i].length == 2
 * 0 <= relation[i][0],relation[i][1] < n 且 relation[i][0] != relation[i][1]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/chuan-di-xin-xi
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/chuan-di-xin-xi 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * @author CaiYongcheng
 * @since 2021-07-01 09:18
 **/
public class _7 {


    /**
     * 分析：从数据规模上来看，使用暴力即可
     * @param n
     * @param relation
     * @param k
     * @return
     */
    public int numWays(int n, int[][] relation, int k) {
        int[][] dp = new int[k + 1][n];
        dp[0][0] = 1;
        for (int i = 0; i < k; i++) {
            for (int[] edge : relation) {
                int src = edge[0], dst = edge[1];
                dp[i + 1][dst] += dp[i][src];
            }
        }
        return dp[k][n - 1];
    }

}


















