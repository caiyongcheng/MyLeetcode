package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * You are given a directed graph with n nodes labeled from 0 to n - 1,
 * where each node has exactly one outgoing edge.
 * The graph is represented by a given 0-indexed integer array edges of length n,
 * where edges[i] indicates that there is a directed edge from node i to node edges[i].
 * The edge score of a node i is defined as the sum of the labels of all the nodes that have an edge pointing to i.
 * Return the node with the highest edge score. If multiple nodes have the same edge score,
 * return the node with the smallest index.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-09-21 13:45
 */
public class _2374 {

    public int edgeScore(int[] edges) {
        long[] scopeArr = new long[edges.length];
        for (int i = 0; i < edges.length; i++) {
            scopeArr[edges[i]] += i;
        }
        int ans = edges.length;
        long maxScope = -1;
        for (int idx = scopeArr.length - 1; idx >= 0; idx--) {
            if (scopeArr[idx] > maxScope) {
                maxScope = scopeArr[idx];
                ans = idx;
            } else if (scopeArr[idx] == maxScope) {
                ans = idx;
            }
        }
        return ans;
    }

}
