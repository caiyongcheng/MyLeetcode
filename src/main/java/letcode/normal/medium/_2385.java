package letcode.normal.medium;

import letcode.utils.TestCaseInputUtils;
import letcode.utils.TreeNode;

import java.util.*;

/**
 * 给你一棵二叉树的根节点 root ，二叉树中节点的值 互不相同 。
 * 另给你一个整数 start 。在第 0 分钟，感染 将会从值为 start 的节点开始爆发。
 * 每分钟，如果节点满足以下全部条件，就会被感染：  节点此前还没有感染。 节点与一个已感染节点相邻。
 * 返回感染整棵树需要的分钟数
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/25 15:14
 */
public class _2385 {

    public int amountOfTime(TreeNode root, int start) {
        /*
        树转图 稀疏图 使用邻接表
        广度优先遍历
         */

        // 转成领接表
        Map<Integer, int[]> map = new HashMap<>();
        dfs(map, root);

        // 迭代
        int size = map.size();
        int minute = 0;
        Set<Integer> passInfectSet = new HashSet<>(size);
        List<Integer> nextInfectList = new ArrayList<>(size);
        List<Integer> curInfectList = new ArrayList<>(size);
        curInfectList.add(start);
        int curSize = 1;
        int nextSize;
        // 初始化
        for (int i = 0; i < size; i++) {
            nextInfectList.add(0);
            curInfectList.add(0);
        }
        while (curSize > 0) {
            nextSize = 0;
            for (int i = 0; i < curSize; i++) {
                Integer curPoint = curInfectList.get(i);
                passInfectSet.add(curPoint);
                int[] edgeArr = map.get(curPoint);
                for (int nextPoint : edgeArr) {
                    if (nextPoint != 0 && !passInfectSet.contains(nextPoint)) {
                        nextInfectList.set(nextSize, nextPoint);
                        ++nextSize;
                    }
                }
            }
            for (int i = 0; i < nextSize; i++) {
                curInfectList.set(i, nextInfectList.get(i));
            }
            curSize = nextSize;
            ++minute;
        }
        return minute - 1;
    }

    public void dfs(Map<Integer, int[]> map, TreeNode point) {
        int[] edge = map.get(point.val);
        if (edge == null) {
            edge = new int[3];
        }
        if (null != point.left) {
            edge[1] = point.left.val;
            int[] leftEdge = map.get(point.left.val);
            if (leftEdge == null) {
                leftEdge = new int[3];
                leftEdge[0] = point.val;
            }
            map.put(point.left.val, leftEdge);
            dfs(map, point.left);
        }
        if (null != point.right) {
            edge[2] = point.right.val;
            int[] rightEdge = map.get(point.right.val);
            if (rightEdge == null) {
                rightEdge = new int[3];
                rightEdge[0] = point.val;
            }
            map.put(point.right.val, rightEdge);
            dfs(map, point.right);
        }
        map.put(point.val, edge);
    }

    /**
     * 输入：root = [1,5,3,null,4,10,6,9,2], start = 3
     * 输出：4
     * 解释：节点按以下过程被感染：
     * - 第 0 分钟：节点 3
     * - 第 1 分钟：节点 1、10、6
     * - 第 2 分钟：节点5
     * - 第 3 分钟：节点 4
     * - 第 4 分钟：节点 9 和 2
     * 感染整棵树需要 4 分钟，所以返回 4 。
     * 示例 2：
     *
     *
     * 输入：root = [1], start = 1
     * 输出：0
     * 解释：第 0 分钟，树中唯一一个节点处于感染状态，返回 0 。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2385().amountOfTime(
                TreeNode.createUseLeetCode(
                        TestCaseInputUtils.getIntegerArr("[1,5,3,null,4,10,6,9,2]")
                ),
                3
        ));
    }

}
