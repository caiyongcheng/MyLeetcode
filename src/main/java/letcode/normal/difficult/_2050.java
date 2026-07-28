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

}
