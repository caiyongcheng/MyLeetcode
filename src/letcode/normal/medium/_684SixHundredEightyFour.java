package letcode.normal.medium;

import java.util.Arrays;
import java.util.Stack;

/**
 * @program: MyLeetcode
 * @description: 在本问题中, 树指的是一个连通且无环的无向图。  
 * 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。  
 * 结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v]，满足u < v，表示连接顶点u和v的无向图的边。 
 * 返回一条可以删去的边，使得结果图是一个有着N个节点的树。
 * 如果有多个答案，则返回二维数组中最后出现的边。答案边[u, v] 应满足相同的格式u < v。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/redundant-connection 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @date: 2021-04-13 11:13
 **/
public class _684SixHundredEightyFour {


    public int[] findRedundantConnection(int[][] edges) {
        // 输入图是由 一个连通且无环的无向图 一条附加的边构成
        // 这表明如果 两个节点之间仅仅有一条边 这该边一定属于原无向图 不可删除
        int[][] matrix = new int[edges.length+1][edges.length+1];
        int count = 0;
        //抓换成邻接矩阵
        for (int[] edge : edges) {
            matrix[edge[0]-1][edge[1]-1] = 1;
            matrix[edge[1]-1][edge[0]-1] = 1;
        }
        for (int i = edges.length - 1; i >= 0; i--) {
            count = 0;
            for (int point : matrix[edges[i][0]-1]) {
                count += point;
            }
            if (count < 2) {
                continue;
            }
            count = 0;
            for (int point : matrix[edges[i][1]-1]) {
                count += point;
            }
            if (count > 1) {
                matrix[edges[i][1]-1][edges[i][0]-1] = 0;
                matrix[edges[i][0]-1][edges[i][1]-1] = 0;
                if (check(edges[i][0]-1, edges[i][1]-1, matrix, matrix.length)) {
                    return edges[i];
                }
                matrix[edges[i][1]-1][edges[i][0]-1] = 1;
                matrix[edges[i][0]-1][edges[i][1]-1] = 1;
            }
        }
        return null;
    }


    public boolean check(int start, int end, int[][] matrix, int N) {
        boolean[] visited = new boolean[N+1];
        Stack<Integer> bfs = new Stack<>();
        visited[start] = true;
        bfs.push(start);
        int top;
        while (!bfs.empty()) {
            top = bfs.pop();
            for (int i = 0; i < matrix[top].length; i++) {
                if (!visited[i] && matrix[top][i] != 0) {
                    if (i == end) {
                        return true;
                    }
                    bfs.push(i);
                    visited[i] = true;
                }
            }
        }
        return false;
    }


    /**
     * 示例 1：
     * 输入: {{1,2}, {1,3}, {2,3}}
     * 输出: {2,3}
     * 解释: 给定的无向图为:
     *   1
     *  / \
     * 2 - 3
     * 
     * 示例 2：
     * 输入: {{1,2}, {2,3}, {3,4}, {1,4}, {1,5}}
     * 输出: {1,4}
     * 解释: 给定的无向图为:
     * 5 - 1 - 2
     *     |   |
     *     4 - 3
     *
     *
     * {{9,10},{5,8},{2,6},{1,5},{3,8},{4,9},{8,10},{4,10},{6,8},{7,9}}
     * {{3,4},{1,2},{2,4},{3,5},{2,5}}
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/redundant-connection
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new _684SixHundredEightyFour().findRedundantConnection(
                new int[][]{{3,4},{1,2},{2,4},{3,5},{2,5}}
        )));
    }

}
