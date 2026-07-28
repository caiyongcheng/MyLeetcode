package letcode.normal.medium;

import letcode.utils.TreeNode;

import java.util.PriorityQueue;

/**
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 *
 * @author CaiYongcheng
 * @since 2021-10-17 23:42
 **/
public class _230 {

    PriorityQueue<Integer> priorityQueue;

    public int kthSmallest(TreeNode root, int k) {
        priorityQueue = new PriorityQueue<>();
        dfs(root);
        for (int i = 1; i < k; i++) {
            priorityQueue.poll();
        }
        return priorityQueue.poll();
    }


    public void dfs(TreeNode root) {
        priorityQueue.add(root.val);
        if (root.left != null) {
            dfs(root.left);
        }
        if (root.right != null) {
            dfs(root.right);
        }
    }

}
