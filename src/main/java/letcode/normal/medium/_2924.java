package letcode.normal.medium;

import letcode.utils.TestCaseInputUtils;

/**
 * 一场比赛中共有 n 支队伍，按从 0 到  n - 1 编号。每支队伍也是 有向无环图（DAG） 上的一个节点。
 * 给你一个整数 n 和一个下标从 0 开始、长度为 m 的二维整数数组 edges 表示这个有向无环图，
 * 其中 edges[i] = [ui, vi] 表示图中存在一条从 ui 队到 vi 队的有向边。
 * 从 a 队到 b 队的有向边意味着 a 队比 b 队 强 ，也就是 b 队比 a 队 弱 。
 * 在这场比赛中，如果不存在某支强于 a 队的队伍，则认为 a 队将会是 冠军 。
 * 如果这场比赛存在 唯一 一个冠军，则返回将会成为冠军的队伍。否则，返回 -1 。
 * 注意  环 是形如 a1, a2, ..., an, an+1 的一个序列，
 * 且满足：节点 a1 与节点 an+1 是同一个节点；节点 a1, a2, ..., an 互不相同；对于范围 [1, n] 中的每个 i ，
 * 均存在一条从节点 ai 到节点 ai+1 的有向边。 有向无环图 是不存在任何环的有向图。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/18 17:06
 */
public class _2924 {

    public int findChampion(int n, int[][] edges) {
        int[] pointArr = new int[n];
        for (int[] edge : edges) {
            pointArr[edge[1]] = 1;
        }
        int ans = -1;
        for (int point = 0; point < pointArr.length; point++) {
            if (pointArr[point] == 0) {
                if (ans != -1) {
                    return -1;
                }
                ans = point;
            }
        }
        return ans;
    }

    /**
     * 示例 1：
     *
     *
     *
     * 输入：n = 3, edges = [[0,1],[1,2]]
     * 输出：0
     * 解释：1 队比 0 队弱。2 队比 1 队弱。所以冠军是 0 队。
     * 示例 2：
     *
     *
     *
     * 输入：n = 4, edges = [[0,2],[1,3],[1,2]]
     * 输出：-1
     * 解释：2 队比 0 队和 1 队弱。3 队比 1 队弱。但是 1 队和 0 队之间不存在强弱对比。所以答案是 -1 。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2924().findChampion(
                4,
                TestCaseInputUtils.get2DIntArr("[[0,2],[1,3],[1,2]]")
        ));
    }

}
