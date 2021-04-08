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
