package letcode.normal.medium;

/**
 * @program: MyLeetCode
 * @description: 你准备参加一场远足活动。给你一个二维rows x columns的地图heights，其中heights[row][col]表示格子(row, col)的高度。
 * 一开始你在最左上角的格子(0, 0)，且你希望去最右下角的格子(rows-1, columns-1)（注意下标从 0 开始编号）。
 * 你每次可以往 上，下，左，右四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
 * 一条路径耗费的 体力值是路径上相邻格子之间 高度差绝对值的 最大值决定的。
 * 请你返回从左上角走到右下角的最小体力消耗值。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/path-with-minimum-effort 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2021-01-29 10:02
 */
public class N_1631OneThousandSixHundredThirtyOne {


    private Integer minCost = 0;
    private int[][] map;
    private boolean[][] isSearch;
    private int[][] cache;

    /**
     * 输入：heights = {{1,2,2},{3,8,2},{5,3,5}}
     * 输出：2
     * 解释：路径 {1,3,5,3,5} 连续格子的差值绝对值最大为 2 。
     * 这条路径比路径 {1,2,2,2,5} 更优，因为另一条路径差值最大值为 3 。
     * <p>
     * 示例 2：
     * 输入：heights = {{1,2,3},{3,8,4},{5,3,5}}
     * 输出：1
     * 解释：路径 {1,2,3,4,5} 的相邻格子差值绝对值最大为 1 ，比路径 {1,3,5,3,5} 更优。
     * <p>
     * 示例 3：
     * 输入：heights = {{1,2,1,1,1},{1,2,1,2,1},{1,2,1,2,1},{1,2,1,2,1},{1,1,1,2,1}}
     * 输出：0
     * 解释：上图所示路径不需要消耗任何体力。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/path-with-minimum-effort
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new N_1631OneThousandSixHundredThirtyOne().minimumEffortPath(new int[][]{{3}}));
    }

    public void dfs(int xindex, int yindex, int nowCost) {
        if (xindex == map[0].length - 1 && yindex == map.length - 1) {
            if (nowCost < minCost) {
                minCost = nowCost;
            }
            return;
        }
        int cost = Integer.MIN_VALUE;
        int nowMinCost = Integer.MIN_VALUE;
        if (xindex > 0 && !isSearch[yindex][xindex - 1]) {
            cost = Math.abs(map[yindex][xindex - 1] - map[yindex][xindex]);
            if (cost < nowMinCost) {

            }
            if (cost < minCost) {
                if (nowCost < cost) {
                    nowCost = cost;
                }
                isSearch[yindex][xindex - 1] = true;
                dfs(xindex - 1, yindex, nowCost);
                isSearch[yindex][xindex - 1] = false;
            }
        }
        if (yindex > 0 && !isSearch[yindex - 1][xindex]) {
            cost = Math.abs(map[yindex - 1][xindex] - map[yindex][xindex]);
            if (cost < minCost) {
                if (nowCost < cost) {
                    nowCost = cost;
                }
                isSearch[yindex - 1][xindex] = true;
                dfs(xindex, yindex - 1, nowCost);
                isSearch[yindex - 1][xindex] = false;
            }
        }
        if (xindex < map[0].length - 1 && !isSearch[yindex][xindex + 1]) {
            cost = Math.abs(map[yindex][xindex] - map[yindex][xindex + 1]);
            if (cost < minCost) {
                if (nowCost < cost) {
                    nowCost = cost;
                }
                isSearch[yindex][xindex + 1] = true;
                dfs(xindex + 1, yindex, nowCost);
                isSearch[yindex][xindex + 1] = false;
            }
        }
        if (yindex < map.length - 1 && !isSearch[yindex + 1][xindex]) {
            cost = Math.abs(map[yindex][xindex] - map[yindex + 1][xindex]);
            if (cost < minCost) {
                if (nowCost < cost) {
                    nowCost = cost;
                }
                isSearch[yindex + 1][xindex] = true;
                dfs(xindex, yindex + 1, nowCost);
                isSearch[yindex + 1][xindex] = false;
            }
        }
        cache[yindex][xindex] = nowCost;
    }

    public int minimumEffortPath(int[][] heights) {
        map = heights;
        isSearch = new boolean[heights.length][heights[0].length];
        int cost = 0;
        int tmp = 0;
        int i = 0;
        for (; i < map[0].length - 1; i++) {
            tmp = Math.abs(map[0][i] - map[0][i + 1]);
            if (tmp > cost) {
                cost = tmp;
            }
        }
        for (int j = 0; j < map.length - 1; j++) {
            tmp = Math.abs(map[j][i] - map[j + 1][i]);
            if (tmp > cost) {
                cost = tmp;
            }
        }
        minCost = cost;
        if (minCost == 0) {
            return 0;
        }
        isSearch[0][0] = true;
        dfs(0, 0, 0);
        return minCost;
    }

}