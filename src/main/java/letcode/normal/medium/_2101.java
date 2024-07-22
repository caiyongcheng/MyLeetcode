package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.Arrays;

/**
 * 给你一个炸弹列表。一个炸弹的 爆炸范围 定义为以炸弹为圆心的一个圆。
 * 炸弹用一个下标从 0 开始的二维整数数组 bombs 表示，其中 bombs[i] = [xi, yi, ri] 。xi 和 yi 表示第 i 个炸弹的 X 和 Y 坐标，ri 表示爆炸范围的 半径 。
 * 你需要选择引爆 一个 炸弹。当这个炸弹被引爆时，所有 在它爆炸范围内的炸弹都会被引爆，这些炸弹会进一步将它们爆炸范围内的其他炸弹引爆。
 * 给你数组 bombs ，请你返回在引爆 一个 炸弹的前提下，最多 能引爆的炸弹数目。
 *
 * You are given a list of bombs. The range of a bomb is defined as the area where its effect can be felt.
 * This area is in the shape of a circle with the center as the location of the bomb.
 * The bombs are represented by a 0-indexed 2D integer array bombs where bombs[i] = [xi, yi, ri].
 * xi and yi denote the X-coordinate and Y-coordinate of the location of the ith bomb, whereas ri denotes the radius of its range.
 * You may choose to detonate a single bomb. When a bomb is detonated, it will detonate all bombs that lie in its range.
 * These bombs will further detonate the bombs that lie in their ranges.
 * Given the list of bombs, return the maximum number of bombs that can be detonated if you are allowed to detonate only one bomb.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-07-22 09:58
 */
public class _2101 {

    public int maximumDetonation(int[][] bombs) {
        /*
        将每个炸弹看成图的一个点 判断图中的单一连通分量中哪个包含的点最多即可
         */
        int[][] graph = new int[bombs.length][bombs.length];
        long dist = 0;
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (i == j) {
                    continue;
                }
                dist = (long) (bombs[i][0] - bombs[j][0]) * (bombs[i][0] - bombs[j][0])
                        + (long) (bombs[i][1] - bombs[j][1]) * (bombs[i][1] - bombs[j][1]);
                if (dist <= (long) bombs[i][2] * bombs[i][2]) {
                    graph[i][j] = 1;
                }
                if (dist <= (long) bombs[j][2] * bombs[j][2]) {
                    graph[j][i] = 1;
                }
            }
        }

        int ans = 1;
        int[] visited = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            Arrays.fill(visited, 0);
            ans = Math.max(ans, dfs(graph, visited, i, 1));
        }

        return ans;
    }


    private int dfs(int[][] graph, int[] visited, int index, int cnt) {
        visited[index] = 1;
        for (int i = 0; i < graph.length; i++) {
            if (graph[index][i] == 1 && visited[i] == 0) {
                cnt = dfs(graph, visited, i, cnt + 1);
            }
        }
        return cnt;
    }


    /**
     * Example 1:
     *
     *
     * Input: bombs = [[2,1,3],[6,1,4]]
     * Output: 2
     * Explanation:
     * The above figure shows the positions and ranges of the 2 bombs.
     * If we detonate the left bomb, the right bomb will not be affected.
     * But if we detonate the right bomb, both bombs will be detonated.
     * So the maximum bombs that can be detonated is max(1, 2) = 2.
     * Example 2:
     *
     *
     * Input: bombs = [[1,1,5],[10,10,5]]
     * Output: 1
     * Explanation:
     * Detonating either bomb will not detonate the other bomb, so the maximum number of bombs that can be detonated is 1.
     * Example 3:
     *
     *
     * Input: bombs = [[1,2,3],[2,3,1],[3,4,2],[4,5,3],[5,6,4]]
     * Output: 5
     * Explanation:
     * The best bomb to detonate is bomb 0 because:
     * - Bomb 0 detonates bombs 1 and 2. The red circle denotes the range of bomb 0.
     * - Bomb 2 detonates bomb 3. The blue circle denotes the range of bomb 2.
     * - Bomb 3 detonates bomb 4. The green circle denotes the range of bomb 3.
     * Thus all 5 bombs are detonated.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(_2101.class, "Example 1: Input: bombs = [[2,1,3],[6,1,4]] Output: 2 Explanation: The above figure shows the positions and ranges of the 2 bombs. If we detonate the left bomb, the right bomb will not be affected. But if we detonate the right bomb, both bombs will be detonated. So the maximum bombs that can be detonated is max(1, 2) = 2. Example 2: Input: bombs = [[1,1,5],[10,10,5]] Output: 1 Explanation: Detonating either bomb will not detonate the other bomb, so the maximum number of bombs that can be detonated is 1. Example 3: Input: bombs = [[1,2,3],[2,3,1],[3,4,2],[4,5,3],[5,6,4]] Output: 5 Explanation: The best bomb to detonate is bomb 0 because: - Bomb 0 detonates bombs 1 and 2. The red circle denotes the range of bomb 0. - Bomb 2 detonates bomb 3. The blue circle denotes the range of bomb 2. - Bomb 3 detonates bomb 4. The green circle denotes the range of bomb 3. Thus all 5 bombs are detonated.");
    }

}
