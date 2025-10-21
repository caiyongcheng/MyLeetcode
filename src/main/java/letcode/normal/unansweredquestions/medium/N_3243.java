package letcode.normal.unansweredquestions.medium;

import letcode.utils.TestUtil;

/**
 * You are given an integer n and a 2D integer array queries.  There are n cities numbered from 0 to n - 1. Initially,
 * there is a unidirectional road from city i to city i + 1 for all 0 <= i < n - 1.  queries[i] = [ui, vi]
 * represents the addition of a new unidirectional road from city ui to city vi. After each query, you need
 * to find the length of the shortest path from city 0 to city n - 1.  Return an array answer where for each i
 * in the range [0, queries.length - 1], answer[i] is the length of the shortest path from city 0 to city n - 1
 * after processing the first i + 1 queries.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-11-20 23:35
 */
public class N_3243 {

    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        /*
        保存每个点到起点和终点的距离
        那么 每次新建路径u->v
        可以更新u到终点的距离为原本距离与v的终点距离+1的最小值
        同理也可也更新v到起点的距离
        然后判断起点到u再到v再到终点的距离是否小于当前距离 不停更新即可
         */
        int minDistance = n - 1;
        int[] startDistanceArr = new int[n];
        int[] endDistanceArr = new int[n];
        int[] ans = new int[queries.length];

        // 初始化
        for (int i = 0; i < startDistanceArr.length; i++) {
            startDistanceArr[i] = i;
        }
        for (int i = 0; i < endDistanceArr.length; i++) {
            endDistanceArr[i] = n - 1 - i;
        }

        for (int i = 0; i < ans.length; i++) {
            // 更新到起点的最短距离
            startDistanceArr[queries[i][1]] = Math.min(startDistanceArr[queries[i][1]], startDistanceArr[queries[i][0]] + 1);
            // 更新到终点的最短距离
            endDistanceArr[queries[i][0]] = Math.min(endDistanceArr[queries[i][0]], endDistanceArr[queries[i][1]] + 1);
            // 更新最短距离
            minDistance = Math.min(
                    minDistance,
                    Math.min(
                            startDistanceArr[queries[i][0]] + 1 + endDistanceArr[queries[i][1]],
                            Math.min(
                                    startDistanceArr[queries[i][1]] + endDistanceArr[queries[i][1]],
                                    startDistanceArr[queries[i][0]] + endDistanceArr[queries[i][0]]
                            )
                    )
            );
            ans[i] = minDistance;
        }

        return ans;

    }


    /**
     * Example 1:
     * Input: n = 5, queries = [[2,4],[0,2],[0,4]]
     * Output: [3,2,1]
     * Explanation:
     * After the addition of the road from 2 to 4, the length of the shortest path from 0 to 4 is 3.
     * After the addition of the road from 0 to 2, the length of the shortest path from 0 to 4 is 2.
     * After the addition of the road from 0 to 4, the length of the shortest path from 0 to 4 is 1.
     * Example 2:
     * Input: n = 4, queries = [[0,3],[0,2]]
     * Output: [1,1]
     * Explanation:
     * After the addition of the road from 0 to 3, the length of the shortest path from 0 to 3 is 1.
     * After the addition of the road from 0 to 2, the length of the shortest path remains 1.
     *
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(N_3243.class);
    }

}
