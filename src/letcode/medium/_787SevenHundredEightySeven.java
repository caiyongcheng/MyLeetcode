package medium;

/**
 * @program: Leetcode
 * @description: 有 n 个城市通过 m 个航班连接。每个航班都从城市 u 开始，以价格 w 抵达 v。 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到从 src 到 dst 最多经过 k 站中转的最便宜的价格。 如果没有这样的路线，则输出 -1。  来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/cheapest-flights-within-k-stops 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-11-20 23:10
 */
public class _787SevenHundredEightySeven {

    private int[][] edges;
    private int cityNum;
    private int throughCityNum;
    private int endCity;
    private int minFares = Integer.MAX_VALUE;
    private boolean[] isThroughCityList;

    public void search(int src, int nowThroughCityNum, int nowFares){
        if (nowThroughCityNum > throughCityNum) {
            return;
        }
        if (src == endCity) {
            if (nowFares < minFares) {
                minFares = nowFares;
            }
            return;
        }
        if (nowThroughCityNum < throughCityNum) {
            for (int nextCity = 0; nextCity < cityNum; nextCity++) {
                if (edges[src][nextCity] > 0 && !isThroughCityList[nextCity]) {
                    isThroughCityList[nextCity] = true;
                    search(nextCity, nowThroughCityNum+1, nowFares+ edges[src][nextCity]);
                    isThroughCityList[nextCity] = false;
                }
            }
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        cityNum = n;
        throughCityNum = K+1;
        endCity = dst;
        edges = new int[n][n];
        for (int i = 0; i < flights.length; i++) {
            edges[flights[i][0]][flights[i][1]] = flights[i][2];
        }
        isThroughCityList = new boolean[cityNum];
        search(src, 0, 0);
        return minFares == Integer.MAX_VALUE ? -1 : minFares;
    }

    /**
     * 示例 1：
     * 输入:
     * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
     * src = 0, dst = 2, k = 1
     * 输出: 200
     *
     * 示例 2：
     * 输入:
     * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
     * src = 0, dst = 2, k = 0
     * 输出: 500
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/cheapest-flights-within-k-stops
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/cheapest-flights-within-k-stops
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        int[][] edges = new int[][]{{0,1,100}, {1,2,100}, {0,2,500}};
        System.out.println(new _787SevenHundredEightySeven().findCheapestPrice(3, edges, 0, 2, 0));
    }
}