package letcode.normal.medium;

import letcode.utils.SolutionTestMethod;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 3970. Shortest Path With At Most K Consecutive Identical Characters
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/shortest-path-with-at-most-k-consecutive-identical-characters/
 * <p>
 * You are given an integer n representing the number of nodes in a directed weighted graph, numbered
 * from 0 to n - 1 . This is represented by a 2D integer array edges , where edges[i] = [u i , v i , w
 * i ] represents a directed edge from node u i to node v i with weight w i .
 * <p>
 * You are also given a string labels of length n , where labels[i] is the character assigned to node i
 * , and an integer k .
 * <p>
 * Return the minimum total edge weight of a path from node 0 to node n - 1 such that the concatenation
 * of the labels of the nodes along the path contains at most k consecutive identical characters. If no
 * valid path exists, return -1.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3, edges = [[0,1,1],[1,2,1],[0,2,3]], labels = "aab", k = 1
 * <p>
 * Output: 3
 * <p>
 * Explanation:
 * <p>
 * The optimal valid path from node 0 to node 2 is as follows:
 * <p>
 * - Use edges[2] = [0, 2, 3] to reach node 2 with a weight w i = 3 .
 * <p>
 * The corresponding concatenation of labels is "ab" , which satisfies at most k = 1 consecutive
 * identical characters. Thus, the answer is 3.
 * <p>
 * Example 2:
 * <p>
 * Input: n = 3, edges = [[0,1,1],[1,2,1],[0,2,3]], labels = "aab", k = 2
 * <p>
 * Output: 2
 * <p>
 * Explanation:
 * <p>
 * The optimal valid path from node 0 to node 2 is as follows:
 * <p>
 * - Use edges[0] = [0, 1, 1] to reach node 1 with weight w i = 1 .
 * <p>
 * - Use edges[1] = [1, 2, 1] to reach node 2 with weight w i = 1 .
 * <p>
 * The corresponding concatenation of labels is "aab" , which satisfies at most k = 2 consecutive
 * identical characters. Thus, the answer is 2.
 * <p>
 * Example 3:
 * <p>
 * Input: n = 3, edges = [[0,1,1],[1,2,1]], labels = "aaa", k = 2
 * <p>
 * Output: -1
 * <p>
 * Explanation:
 * <p>
 * There is no valid path from node 0 to node 2 that satisfies at most k = 2 consecutive identical
 * characters. Thus, the answer is -1.
 * <p>
 * Constraints:
 * <p>
 * - 1 <= n == labels.length <= 5 * 10 4
 * <p>
 * - 0 <= edges.length <= 5 * 10 4
 * <p>
 * - edges[i] == [u i , v i , w i ]
 * <p>
 * - 0 <= u i , v i <= n - 1
 * <p>
 * - u i != v i
 * <p>
 * - 1 <= w i <= 10 4
 * <p>
 * - labels consists of lowercase English letters
 * <p>
 * - 1 <= k <= 50
 */
@SolutionTestMethod(method = "shortestPath")
public class _3970 {

    int minCost;
    int endNo;
    boolean[] visited;
    int sameCharLenLimit;

    class Node {
        int no;
        char ch;
        List<Integer> adjNodeList;
        LinkedList<Integer> adjWeightNode;

        public Node(int no, char ch) {
            this.no = no;
            this.ch = ch;
            adjNodeList = new LinkedList<>();
            adjWeightNode = new LinkedList<>();
        }
    }

    public int shortestPath(int n, int[][] edges, String labels, int k) {
        minCost = Integer.MAX_VALUE;
        endNo = n - 1;
        visited = new boolean[n];
        sameCharLenLimit = k;

        Map<Integer, Node> no2NodeMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            no2NodeMap.put(i, new Node(i, labels.charAt(i)));
        }

        for (int[] edge : edges) {
            Node from = no2NodeMap.get(edge[0]);
            from.adjNodeList.add(edge[1]);
            from.adjWeightNode.add(edge[2]);
        }

        Node startNode = no2NodeMap.get(0);
        dfs(startNode, 0, startNode.ch, 1, no2NodeMap);
        return minCost ==  Integer.MAX_VALUE ? -1 : minCost;
    }

    public void dfs(Node curNode, int pathCost, char lastCh, int sameCharLen, Map<Integer, Node> no2NodeMap) {
        if (curNode.no == endNo) {
            minCost = Math.min(minCost, pathCost);
            return;
        }
        for (int i = 0; i < curNode.adjNodeList.size(); i++) {
            Integer next = curNode.adjNodeList.get(i);
            if (visited[next]) {
                continue;
            }
            int nextPathCost = pathCost + curNode.adjWeightNode.get(i);
            if (nextPathCost >= minCost) {
                continue;
            }
            Node nextNode = no2NodeMap.get(next);
            if (lastCh == nextNode.ch && sameCharLen + 1 > sameCharLenLimit ) {
                continue;
            }
            visited[next] = true;
            dfs(nextNode, nextPathCost, nextNode.ch, nextNode.ch == curNode.ch ? sameCharLen + 1 : 1, no2NodeMap);
            visited[next] = false;
        }
    }


}
