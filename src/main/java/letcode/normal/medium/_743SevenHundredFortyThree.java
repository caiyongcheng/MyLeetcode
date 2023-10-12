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
import java.util.HashMap;
import java.util.Stack;

/**
 * @program: MyLeetcode
 * @description: 有 n 个网络节点，标记为1到 n。  给你一个列表times，表示信号经过 有向 边的传递时间。
 * times[i] = (ui, vi, wi)，其中ui是源节点，vi是目标节点， wi是一个信号从源节点传递到目标节点的时间。
 * 现在，从某个节点K发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回-1 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/network-delay-time 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @date: 2021-04-08 10:24
 **/
public class _743SevenHundredFortyThree {

    /**
     * 问题可以划分为两部分
     * 1、能否传播到所有节点
     * 2、如果能传播到所有节点 那么传播到每个节点的最短时间都是多少
     * @param times
     * @param n
     * @param k
     * @return
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        //使用邻接矩阵表示图
        int[][] matrix = new int[n][n];
        for (int[] ints : matrix) {
            Arrays.fill(ints, -1);
        }
        int ans = Integer.MIN_VALUE;
        for (int[] time : times) {
            matrix[time[0]-1][time[1]-1] = time[2];
        }
        k -= 1;
        Stack<Integer> bfs = new Stack<>();
        //初始节点能连接到的点
        for (int i = 0; i < matrix[k].length; i++) {
            if (matrix[k][i] != -1) {
                bfs.push(i);
            }
        }
        while (!bfs.empty()) {
            //表示当前处理的点
            Integer currentNode = bfs.pop();
            for (int i = 0; i < matrix[currentNode].length; i++) {
                if (i == currentNode || matrix[currentNode][i] == -1) {
                    continue;
                }
                //更新根节点到当前处理的点可到达点的最短距离
                if (matrix[k][currentNode] + matrix[currentNode][i] < matrix[k][i] || matrix[k][i] == -1) {
                    matrix[k][i] = matrix[k][currentNode] + matrix[currentNode][i];
                    bfs.push(i);
                }
            }
        }
        for (int i = 0; i < matrix[k].length; i++) {
            if (i == k) {
                continue;
            }
            if (matrix[k][i] == -1) {
                return -1;
            }
            if (matrix[k][i] > ans) {
                ans = matrix[k][i];
            }
        }
        return ans;
    }


    /**
     * 示例 1：
     * 输入：times = {{2,1,1},{2,3,1},{3,4,1}}, n = 4, k = 2
     * 输出：2
     * 
     * 示例 2：
     * 输入：times = {{1,2,1}}, n = 2, k = 1
     * 输出：1
     * 
     * 示例 3：
     * 输入：times = {{1,2,1}}, n = 2, k = 2
     * 输出：-1
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/network-delay-time
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _743SevenHundredFortyThree().networkDelayTime(
                new int[][]{{3,5,78},{2,1,1},{1,3,0},{4,3,59},{5,3,85},{5,2,22},{2,4,23},{1,4,43},{4,5,75},{5,1,15},{1,5,91},{4,1,16},{3,2,98},{3,4,22},{5,4,31},{1,2,0},{2,5,4},{4,2,51},{3,1,36},{2,3,59}},
                5,
                5
        ));
    }

}
