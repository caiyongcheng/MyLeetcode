package letcode.normal.medium;

import letcode.utils.TreeNode;

/**
 * @author 蔡永程
 * @since 2022/8/17 15:57
 */
public class _1302 {

    int maxLen = 0;
    int sum = 0;

    /**
     * 给你一棵二叉树的根节点 root ，请你返回 层数最深的叶子节点的和 。
     */
    public int deepestLeavesSum(TreeNode root) {
        dfs(root, 0);
        return sum;
    }


    public void dfs(TreeNode currentNode, int currentLen) {
        if (currentLen > maxLen) {
            maxLen = currentLen;
            sum = currentNode.val;
        } else if (currentLen == maxLen) {
            sum += currentNode.val;
        }
        if (currentNode.left != null) {
            dfs(currentNode.left, currentLen + 1);
        }
        if (currentNode.right != null) {
            dfs(currentNode.right, currentLen + 1);
        }
    }

}
