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
