package letcode.normal.medium;

import java.util.PriorityQueue;

/**
 * 3275. K-th Nearest Obstacle Queries
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/k-th-nearest-obstacle-queries/
 * <p>
 * There is an infinite 2D plane.
 * <p>
 * You are given a positive integer k . You are also given a 2D array queries , which contains the
 * following queries:
 * <p>
 * - queries[i] = [x, y] : Build an obstacle at coordinate (x, y) in the plane. It is guaranteed that
 * there is no obstacle at this coordinate when this query is made.
 * <p>
 * After each query, you need to find the distance of the k th nearest obstacle from the  origin.
 * <p>
 * Return an integer array results where results[i] denotes the k th nearest obstacle after query i ,
 * or results[i] == -1 if there are less than k obstacles.
 * <p>
 * Note that initially there are no obstacles anywhere.
 * <p>
 * The distance of an obstacle at coordinate (x, y) from the origin is given by |x| + |y| .
 * <p>
 * Example 1:
 * <p>
 * Input: queries = [[1,2],[3,4],[2,3],[-3,0]], k = 2
 * <p>
 * Output: [-1,7,5,3]
 * <p>
 * Explanation:
 * <p>
 * - Initially, there are 0 obstacles.
 * <p>
 * - After queries[0] , there are less than 2 obstacles.
 * <p>
 * - After queries[1] , there are obstacles at distances 3 and 7.
 * <p>
 * - After queries[2] , there are obstacles at distances 3, 5, and 7.
 * <p>
 * - After queries[3] , there are obstacles at distances 3, 3, 5, and 7.
 * <p>
 * Example 2:
 * <p>
 * Input: queries = [[5,5],[4,4],[3,3]], k = 1
 * <p>
 * Output: [10,8,6]
 * <p>
 * Explanation:
 * <p>
 * - After queries[0] , there is an obstacle at distance 10.
 * <p>
 * - After queries[1] , there are obstacles at distances 8 and 10.
 * <p>
 * - After queries[2] , there are obstacles at distances 6, 8, and 10.
 * <p>
 * Constraints:
 * <p>
 * - 1 <= queries.length <= 2 * 10 5
 * <p>
 * - All queries[i] are unique.
 * <p>
 * - -10 9 <= queries[i][0], queries[i][1] <= 10 9
 * <p>
 * - 1 <= k <= 10 5
 */
public class _3275 {


    public int[] resultsArray(int[][] queries, int k) {

        int[] ans = new int[queries.length];
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        for (int i = 0; i < queries.length; i++) {
            int dist = Math.abs(queries[i][0]) + Math.abs(queries[i][1]);
            if (pq.size() < k) {
                pq.offer(dist);
            } else if (pq.peek() > dist) {
                pq.poll();
                pq.offer(dist);
            }
            ans[i] = pq.size() == k ? pq.peek() : -1;
        }
        return ans;
    }


}
