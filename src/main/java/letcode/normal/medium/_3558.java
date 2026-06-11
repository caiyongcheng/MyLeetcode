package letcode.normal.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 3558. Number of Ways to Assign Edge Weights I
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/number-of-ways-to-assign-edge-weights-i/
 * There is an undirected tree with n nodes labeled from 1 to n ,
 * rooted at node 1. The tree is represented by a 2D integer array edges of length n - 1 ,
 * where edges[i] = [u i , v i ] indicates that there is an edge between nodes u i and v i .
 * Initially, all edges have a weight of 0. You must assign each edge a weight of either 1 or 2 .
 * The cost of a path between any two nodes u and v is the total we...
 */
public class _3558 {

    private static int MOD = 1_000_000_000 + 7;

    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int[] edge : edges) {
            if (graph[edge[0]] == null) {
                graph[edge[0]] = new ArrayList<>();
            }
            if (graph[edge[1]] == null) {
                graph[edge[1]] = new ArrayList<>();
            }
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int[] queue = new int[n];
        int[] depth = new int[n + 1];
        int left = 0;
        int right = 0;
        int maxDepth = 0;
        queue[right++] = 1;
        depth[1] = 1;

        while (left < right) {
            int currentPoint = queue[left++];
            maxDepth = Math.max(maxDepth, depth[currentPoint] - 1);
            if (graph[currentPoint] == null) {
                continue;
            }
            for (Integer nextPoint : graph[currentPoint]) {
                if (depth[nextPoint] != 0) {
                    continue;
                }
                // 树是无向输入，depth 兼作访问标记，避免走回父节点。
                depth[nextPoint] = depth[currentPoint] + 1;
                queue[right++] = nextPoint;
            }
        }

        if (maxDepth == 0) {
            return 0;
        }
        return pow(2, maxDepth - 1);

    }

    private int pow(long num, int power) {
        long ans = 1;
        while (power > 0) {
            if ((power & 1) == 1) {
                ans = ans * num % MOD;
            }
            num = num * num % MOD;
            power >>= 1;
        }
        return (int) ans;
    }
}
