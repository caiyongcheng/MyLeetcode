package letcode.normal.medium;


import java.util.HashSet;
import java.util.Stack;

/**
 * @program: MyLeetcode
 * @description: 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * 返回矩阵中 省份 的数量。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/number-of-provinces 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @date: 2021-04-14 10:49
 **/
public class _574FiveHundredSeventyFour {


    public int findCircleNum(int[][] isConnected) {
        int[] parent = new int[isConnected.length];
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = i+1; j < isConnected.length; ++j) {
                if (isConnected[i][j] > 0 && find(i, parent) != find(j, parent)) {
                    union(i, j, parent);
                }
            }
        }
        for (int i : parent) {
            set.add(find(i, parent));
        }
        return set.size();
    }


    public void union(int one, int two, int[] parent) {
        int newParent = find(one, parent);
        int oldParent = two;
        while (parent[two] != two) {
            oldParent = parent[two];
            parent[two] = newParent;
            two = oldParent;
        }
        parent[two] = newParent;
    }


    public int find(int index, int[] parent) {
        Stack<Integer> stack = new Stack<>();
        while (parent[index] != index) {
            stack.push(index);
            index = parent[index];
        }
        while (!stack.empty()) {
            parent[stack.pop()] = index;
        }
        return index;
    }

    /**
     * 示例 1：
     * 输入：isConnected = {{1,1,0},{1,1,0},{0,0,1}}
     * 输出：2
     *
     * 示例 2：
     * 输入：isConnected = {{1,0,0},{0,1,0},{0,0,1}}
     * 输出：3
     * 
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/number-of-provinces
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     *
     * //[[1,1,1],[1,1,1],[1,1,1]]
     *
     *
     * {{1,0,0,0,1,0,0,0,0,0,0,0,0,0,0},{0,1,0,0,0,1,0,0,0,0,0,0,0,0,0},{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},{1,0,0,0,1,0,0,0,0,0,0,0,1,0,0},{0,1,0,0,0,1,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,0,1,0,0,0,0,1,0,0,0},{0,0,0,0,0,0,0,1,0,0,0,1,1,0,0},{0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,1,1,0,0,0},{0,0,0,0,0,0,1,1,0,0,1,1,0,0,1},{0,0,0,0,1,1,0,1,0,0,0,0,1,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},{0,0,0,0,0,0,0,0,0,0,0,1,0,0,1}}
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _574FiveHundredSeventyFour().findCircleNum(
                new int[][]{{1,0,0},{0,1,0},{0,0,1}}
        ));
    }

}
