package letcode.normal.difficult;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 存在一个由 n 个节点组成的无向连通图，图中的节点按从 0 到 n - 1 编号。  给你一个数组 graph 表示这个图。其中，graph[i] 是一个列表，由所有与节点 i 直接相连的节点组成。
 * 返回能够访问所有节点的最短路径的长度。你可以在任一节点开始和停止，也可以多次重访节点，并且可以重用边。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/shortest-path-visiting-all-nodes 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-08-06 09:01
 **/
public class _847EightHundredFortySeven {

    public int shortestPathLength(int[][] graph) {
        /**
         * 使用bfs搜索。（如果边的的权值都是1，使用bfs可以很方便的求出点到点的最短路径）
         * 因为bfs需要指定起点，根据题意要求所以每个点都用一次。
         * 首先，对于无向边uv，在遍历过程中，从u-》v与从v-》u明显只能出现一次，否则就是重复搜索。
         * 所以记录访问过的边，访问过了就无需再访问。
         * 同时需要记录遍历到某节点时的当前路径长度。
         * 还需要判断已经访问过哪些节点
         * 使用二进制掩码表示已经访问过哪些点
         * 所以对于每次遍历 需要 {当前遍历节点， 已遍历节点的掩码， 已遍历长度}
         */
        int pointCount = graph.length;
        int finishTroughMask = (1 << pointCount) - 1;
        Queue<int[]> queue = new LinkedList();
        //isThrough[a][b] 表示下一个访问节点是a,当前已访问节点的掩码是b的情况是否已经处理过。
        //对于不同的初始节点而言，isThrough[a][b]是公用的。因为bfs的特性决定了先访问到的结果是路径最短的。
        //故可以对于不同的起始节点，到达isThrough[a][b]的状态，其结果可以是共用的。
        boolean[][] isThrough = new boolean[pointCount][1 << pointCount];
        //表示正在遍历的节点数据
        int [] currentPointData;
        int currentPoint;
        int currentTroughMask;
        int currentTroughDistance;
        //需要遍历所有节点作为起始节点 所以初始化：队列放入所有节点
        for (int startPoint = 0; startPoint < pointCount; startPoint++) {
            queue.offer(new int[]{startPoint, 1 << startPoint, 0});
            isThrough[startPoint][1 << startPoint] = true;
        }
        //bfs
        while (!queue.isEmpty()) {
            currentPointData = queue.poll();
            currentPoint = currentPointData[0];
            currentTroughMask = currentPointData[1];
            currentTroughDistance = currentPointData[2];
            //对于长度均为1的图而言，bfs最早搜索完的就是最短路径。
            if (currentTroughMask == finishTroughMask) {
                return currentTroughDistance;
            }
            for (int nextPoint : graph[currentPoint]) {
                if (!isThrough[nextPoint][currentTroughMask]) {
                    isThrough[nextPoint][currentTroughMask] = true;
                    queue.offer(new int[]{nextPoint, currentTroughMask | (1 << nextPoint), currentTroughDistance + 1});
                }
            }
        }
        return 0;
    }


    /**
     * 示例 1：
     *
     *
     * 输入：graph = {{1,2,3},{0},{0},{0}}
     * 输出：4
     * 解释：一种可能的路径为 {1,0,2,0,3}
     * 
     * 示例 2：
     * 输入：graph = {{1},{0,2,4},{1,3,4},{2},{1,2}}
     * 输出：4
     * 解释：一种可能的路径为 {0,1,4,2,3}
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/shortest-path-visiting-all-nodes
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _847EightHundredFortySeven().shortestPathLength(
                new int[][]{{1},{0,2,4},{1,3,4},{2},{1,2}}
        ));
    }


}
