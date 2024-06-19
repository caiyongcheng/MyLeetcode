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

import java.util.*;

/**
 * @author Caiyongcheng
 * @description 给你一个整数 n ，表示有 n 节课，课程编号从 1 到 n 。同时给你一个二维整数数组 relations ，
 * 其中 relations[j] = [prevCoursej, nextCoursej] ，表示课程 prevCoursej 必须在课程 nextCoursej 之前 完成（先修课的关系）。
 * 同时给你一个下标从 0 开始的整数数组 time ，其中 time[i] 表示完成第 (i+1) 门课程需要花费的 月份 数。
 * 请你根据以下规则算出完成所有课程所需要的 最少 月份数：  如果一门课的所有先修课都已经完成，你可以在 任意 时间开始这门课程。
 * 你可以 同时 上 任意门课程 。 请你返回完成所有课程所需要的 最少 月份数。  注意：测试数据保证一定可以完成所有课程（也就是先修课的关系构成一个有向无环图）。
 * @since 2023/7/28 14:10
 */
public class _2050 {

    public int minimumTime(int n, int[][] relations, int[] time) {
        /*
        课程d的前序课程是 a b c， 那么完成d的最少时间是完成由完成a b c的至少时间决定 也就是 a b c的最大时间
        更具上诉分析 可以使用 动态规划 递归的形式+记忆化即可 好写好实现好理解
        因为题目保证无环 也可以使用bfs bfs 有性能缺陷 如果更新了某个已遍历节点 会导致该节点后续节点的更新 存在不必需要的重复性计算
        所以 该节点更新后续节点的时候 要保证该节点不会再被更新了 可以避免重复计算
         */

        List<Integer>[] map = new List[n];
        Map<Integer, Integer> enterCntMap = new HashMap<>(n);
        // 使用队列进行bfs
        Queue<Integer> queue = new LinkedList();
        for (int point = 0; point < n; point++) {
            queue.add(point);
        }
        for (int point = 0; point < map.length; point++) {
            map[point] = new ArrayList<>();
        }
        // 构造图的临接矩阵表示 fix 修改为邻接表 可以直接获取可访问下一节点 避免稀疏矩阵的n^2复杂度
        for (int[] relation : relations) {
            int currentPoint = relation[0] - 1;
            int nextPoint = relation[1] - 1;
            map[currentPoint].add(nextPoint);
            //移除由入边的点
            queue.remove(nextPoint);
            enterCntMap.put(nextPoint, enterCntMap.getOrDefault(nextPoint, 0) + 1);
        }
        // 构造数组cost cost[i]表示到达该节点所需的最大时间
        int[] costArr = new int[n];
        //开始bsf
        bfs(map, costArr, queue, time, enterCntMap);
        //bsf结束后 计算最大结果 也可以在bsf的时候 就一起计算出来
        //表示学完课程0的至少时间
        int max = costArr[0] + time[0];
        for (int i = 1; i < n; i++) {
            max = Math.max(max, costArr[i] + time[i]);
        }
        return max;
    }

    private void bfs(List<Integer>[] map, int[] costArr, Queue<Integer> queue, int[] time, Map<Integer, Integer> enterCntMap) {
        int currentCost;
        while (!queue.isEmpty()) {
            // 获取当前节点
            Integer currentPoint = queue.remove();
            // 遍历可到达的下一节点
            currentCost = costArr[currentPoint] + time[currentPoint];
            for (Integer nextPoint : map[currentPoint]) {
                // 更新当前节点到达下一节点的cost 如果小于下一节点原cost 那么不会产生任何影响
                // 否则要将下一节点放入队列中，后续遍历更新下一节点的后续节点
                if (currentCost > costArr[nextPoint]) {
                    costArr[nextPoint] = currentCost;
                }
                Integer enterEdgeCnt = enterCntMap.get(nextPoint);
                --enterEdgeCnt;
                enterCntMap.put(nextPoint, enterEdgeCnt);
                //当前节点是下一节点的前序节点中 最后被遍历的节点 表明 下一节点的所有前序节点已经计算完毕 需要放入队列中
                if (enterEdgeCnt <= 0) {
                    queue.add(nextPoint);
                }
            }
        }
    }

    private void dp(Set<Integer>[] map, int[] costArr, Queue<Integer> queue, int[] time) {

    }

    /**
     * 输入：n = 3, relations = {{1,3},{2,3}}, time = {3,2,5}
     * 输出：8
     * 解释：上图展示了输入数据所表示的先修关系图，以及完成每门课程需要花费的时间。
     * 你可以在月份 0 同时开始课程 1 和 2 。
     * 课程 1 花费 3 个月，课程 2 花费 2 个月。
     * 所以，最早开始课程 3 的时间是月份 3 ，完成所有课程所需时间为 3 + 5 = 8 个月。
     * <p>
     * 输入：n = 5, relations = {{1,5},{2,5},{3,5},{3,4},{4,5}}, time = {1,2,3,4,5}
     * 输出：12
     * 解释：上图展示了输入数据所表示的先修关系图，以及完成每门课程需要花费的时间。
     * 你可以在月份 0 同时开始课程 1 ，2 和 3 。
     * 在月份 1，2 和 3 分别完成这三门课程。
     * 课程 4 需在课程 3 之后开始，也就是 3 个月后。课程 4 在 3 + 4 = 7 月完成。
     * 课程 5 需在课程 1，2，3 和 4 之后开始，也就是在 max(1,2,3,7) = 7 月开始。
     * 所以完成所有课程所需的最少时间为 7 + 5 = 12 个月。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2050().minimumTime(
                5,
                new int[][]{{1, 5}, {2, 5}, {3, 5}, {3, 4}, {4, 5}},
                new int[]{1, 2, 3, 4, 5}
        ));
    }


}
