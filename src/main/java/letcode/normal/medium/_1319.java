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
 * @since: 2021-04-13 14:13
 **/
public class _1319 {


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
        System.out.println(new _1319().makeConnected(
                6,
                new int[][]{{0,1},{0,2},{0,3},{1,2},{1,3}}
        ));
    }


}
