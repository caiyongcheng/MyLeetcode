package letcode.normal.medium;

import java.util.*;

/**
 * @program: MyLeetcode
 * @description: n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。
 * 如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。
 * 给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置，返回 可以移除的石子 的最大数量。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/most-stones-removed-with-same-row-or-column 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @date: 2021-04-14 14:14
 **/
public class _947NineHundredFortySeven {


    public int removeStones(int[][] stones) {
        // 首先理解题意
        // 由示例2可知
        // 若:  石头x只与与石头y在同一行，不与其他石头在同一列。
        //      则x可以移除，若后面将y移除后，x位置不存在同行或同列的石头。
        //      但x位置的石头不可再放置上去，因为x已经在y之前移除了。
        // 所以题目可以转化为求图上的石头可以划分为几个集合
        int[] parent = new int[stones.length];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        HashMap<Integer, List<Integer>> rows = new HashMap<>();
        HashMap<Integer, List<Integer>> cols = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < stones.length; i++) {
            if (cols.containsKey(stones[i][0])) {
                cols.get(stones[i][0]).add(i);
                union(cols.get(stones[i][0]).get(0), i, parent);
            } else {
                ArrayList<Integer> col = new ArrayList<>();
                col.add(i);
                cols.put(stones[i][0], col);
            }
            if (rows.containsKey(stones[i][1])) {
                rows.get(stones[i][1]).add(i);
                union(rows.get(stones[i][1]).get(0), i, parent);
            } else {
                ArrayList<Integer> row = new ArrayList<>();
                row.add(i);
                rows.put(stones[i][1], row);
            }
        }
        for (int i = 0; i < parent.length; i++) {
            set.add(find(i, parent));
        }
        return stones.length - set.size();
    }


    public void union(int one, int tow, int[] parent) {
        parent[find(tow, parent)] = find(one, parent);
    }

    public int find(int index, int[] parent) {
        Stack<Integer> stack = new Stack<>();
        while (index != parent[index]) {
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
     * 输入：stones = {{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}}
     * 输出：5
     * 解释：一种移除 5 块石头的方法如下所示：
     * 1. 移除石头 {2,2} ，因为它和 {2,1} 同行。
     * 2. 移除石头 {2,1} ，因为它和 {0,1} 同列。
     * 3. 移除石头 {1,2} ，因为它和 {1,0} 同行。
     * 4. 移除石头 {1,0} ，因为它和 {0,0} 同列。
     * 5. 移除石头 {0,1} ，因为它和 {0,0} 同行。
     * 石头 {0,0} 不能移除，因为它没有与另一块石头同行/列。
     *
     * 示例 2：
     * 输入：stones = {{0,0},{0,2},{1,1},{2,0},{2,2}}
     * 输出：3
     * 解释：一种移除 3 块石头的方法如下所示：
     * 1. 移除石头 {2,2} ，因为它和 {2,0} 同行。
     * 2. 移除石头 {2,0} ，因为它和 {0,0} 同列。
     * 3. 移除石头 {0,2} ，因为它和 {0,0} 同行。
     * 石头 {0,0} 和 {1,1} 不能移除，因为它们没有与另一块石头同行/列。
     *
     * 示例 3：
     * 输入：stones = {{0,0}}
     * 输出：0
     * 解释：{0,0} 是平面上唯一一块石头，所以不可以移除它。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/most-stones-removed-with-same-row-or-column
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _947NineHundredFortySeven().removeStones(
                new int[][]{{0,1}, {1, 0}}
        ));
    }


}
