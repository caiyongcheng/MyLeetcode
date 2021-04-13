package letcode.normal.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

/**
 * @program: MyLeetcode
 * @description: 用以太网线缆将n台计算机连接成一个网络，计算机的编号从0到n-1。
 * 线缆用connections表示，其中connections[i] = [a, b]连接了计算机a和b。  
 * 网络中的任何一台计算机都可以通过网络直接或者间接访问同一个网络中其他任意一台计算机。  
 * 给你这个计算机网络的初始布线connections，你可以拔开任意两台直连计算机之间的线缆，并用它连接一对未直连的计算机。
 * 请你计算并返回使所有计算机都连通所需的最少操作次数。如果不可能，则返回-1 。  来源：力扣（LeetCode） 
 * 链接：https://leetcode-cn.com/problems/number-of-operations-to-make-network-connected 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @date: 2021-04-13 14:13
 **/
public class _1319OneThousandThreeHundredNineteen {


    public int makeConnected(int n, int[][] connections) {
        // 如果 connections.length < n-1 则线缆一定不足
        if (connections.length + 1 < n) {
            return -1;
        }
        //查看网络划分出几个子网络
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        for (int[] connection : connections) {
            union(arr, connection[0], connection[1]);
        }
        HashSet<Integer> set = new HashSet<>();
        for (int i : arr) {
            set.add(find(arr, i));
        }
        return set.size()-1;
    }



    public void union(int[] arr, int one, int two) {
        arr[find(arr, two)] = arr[find(arr, one)];
    }

    public int find(int[] arr, int index) {
        while (arr[index] != index) {
            index = arr[index];
        }
        return index;
    }


    /**
     * 示例 1：
     *
     * 输入：n = 4, connections = {{0,1},{0,2},{1,2}}
     * 输出：1
     * 解释：拔下计算机 1 和 2 之间的线缆，并将它插到计算机 1 和 3 上。
     *
     * 示例 2：
     *
     * 输入：n = 6, connections = {{0,1},{0,2},{0,3},{1,2},{1,3}}
     * 输出：2
     *
     * 示例 3：
     * 输入：n = 6, connections = {{0,1},{0,2},{0,3},{1,2}}
     * 输出：-1
     * 解释：线缆数量不足。
     *
     * 示例 4：
     * 输入：n = 5, connections = {{0,1},{0,2},{3,4},{2,3}}
     * 输出：0
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/number-of-operations-to-make-network-connected
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1319OneThousandThreeHundredNineteen().makeConnected(
                6,
                new int[][]{{0,1},{0,2},{0,3},{1,2},{1,3}}
        ));
    }


}
