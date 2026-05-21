package letcode.normal.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/10/21 12:27
 * description 给你一个整数 n ，表示一张 无向图 中有 n 个节点，编号为 0 到 n - 1 。
 * 同时给你一个二维整数数组 edges ，其中 edges[i] = [ai, bi] 表示节点 ai 和 bi 之间有一条 无向 边。
 * 请你返回 无法互相到达 的不同 点对数目 。
 */
public class _2316 {

    public long countPairs(int n, int[][] edges) {
        // 深度或者广度遍历 得到一个个的连接子图 每个连接子图的节点数量相乘即可
        // 使用类似前缀和方式 优化计算效率
        // 处理点数据，使用临接表方式保存关系
        Map<Integer, List<Integer>> edgeMap = new HashMap<>(n);
        boolean[] visitedArr = new boolean[n];
        extractedPointAndMap(edges, edgeMap);
        long visitedCnt = 0;
        long ans = 0;
        for (int point = 0; point < n; point++) {
            if (!visitedArr[point]) {
                visitedArr[point] = true;
                long subMapPointCnt = dps(point, edgeMap, visitedArr);
                ans += visitedCnt * subMapPointCnt;
                visitedCnt += subMapPointCnt;
            }
        }
        return ans;
    }

    private static void extractedPointAndMap(int[][] edges, Map<Integer, List<Integer>> edgeMap) {
        for (int[] edge : edges) {
            // 数据规定 edge[0] != edge[1]

            List<Integer> edgeList = edgeMap.get(edge[0]);
            if (null == edgeList) {
                edgeList = new ArrayList<>();
            }
            edgeList.add(edge[1]);
            edgeMap.put(edge[0], edgeList);
            edgeList = edgeMap.get(edge[1]);
            if (null == edgeList) {
                edgeList = new ArrayList<>();
            }
            edgeList.add(edge[0]);
            edgeMap.put(edge[1], edgeList);
        }
    }


    public long dps(int start, Map<Integer, List<Integer>> edgeMap, boolean[] visitedSet) {
        long cnt = 1;
        List<Integer> edgeList = edgeMap.get(start);
        if (null == edgeList || edgeList.isEmpty()) {
            return cnt;
        }
        for (Integer nextPoint : edgeList) {
            if (!visitedSet[nextPoint]) {
                visitedSet[nextPoint] = true;
                cnt += dps(nextPoint, edgeMap, visitedSet);
            }
        }
        return cnt;
    }


}
