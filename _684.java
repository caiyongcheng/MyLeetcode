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
public class _684 {


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
        System.out.println(Arrays.toString(new _684().findRedundantConnection(
                new int[][]{{3,4},{1,2},{2,4},{3,5},{2,5}}
        )));
    }

}
