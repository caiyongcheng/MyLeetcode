package letcode.normal.medium;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/11/14 9:08
 * description 有 n 个城市，按从 0 到 n-1 编号。给你一个边数组 edges，其中 edges[i] = [fromi, toi, weighti] 代表 fromi 和 toi 两个城市之间的双向加权边，
 * 距离阈值是一个整数 distanceThreshold。  返回能通过某些路径到达其他城市数目最少、且路径距离 最大 为 distanceThreshold 的城市。如果有多个这样的城市，则返回编号最大的城市。
 * 注意，连接城市 i 和 j 的路径的距离等于沿该路径的所有边的权重之和。
 */
public class _1334 {

    int[][] map;

    int distanceThreshold;

    int curPoint;


    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        /*
        暴力
         */
        map = new int[n][n];
        this.distanceThreshold = distanceThreshold;
        for (int[] edge : edges) {
            if (edge[2] > distanceThreshold) {
                continue;
            }
            map[edge[0]][edge[1]] = edge[2];
            map[edge[1]][edge[0]] = edge[2];
        }
        int ans = -1;
        int minCityCnt = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            dps(i, i, new int[n], 0);
            int cityCnt = 0;
            for (int cityDistance : map[i]) {
                if (cityDistance != 0 && cityDistance <= distanceThreshold) {
                    ++cityCnt;
                }
            }
            if (cityCnt <= minCityCnt) {
                minCityCnt = cityCnt;
                ans = i;
            }
        }
        return ans;
    }


    public void dps(int startPoint, int point, int[] visit, int curDistance) {
        int nextDistance;
        visit[point] = 1;
        for (int nextPoint = 0; nextPoint < map[point].length; nextPoint++) {
            nextDistance = curDistance + map[point][nextPoint];
            if (map[point][nextPoint] == 0) {
                continue;
            }
            if (nextDistance >= this.distanceThreshold) {
                visit[nextPoint] = 1;
                map[startPoint][nextPoint] = Math.min(nextDistance, map[startPoint][nextPoint]);
                continue;
            }
            if (visit[nextPoint] == 0 || nextDistance <  map[startPoint][nextPoint]) {
                visit[nextPoint] = 1;
                map[startPoint][nextPoint] = Math.min(nextDistance, map[startPoint][nextPoint]);
                dps(startPoint, nextPoint, visit, nextDistance);
            }
        }
    }


}
