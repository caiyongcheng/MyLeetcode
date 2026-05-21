package letcode.normal.medium;

import letcode.utils.TreeNode;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/12/4 10:17
 * description 给定一个二叉搜索树 root (BST)，请将它的每个节点的值替换成树中大于或者等于该节点值的所有节点值之和。
 * 提醒一下， 二叉搜索树 满足下列约束条件：  节点的左子树仅包含键 小于 节点键的节点。
 * 节点的右子树仅包含键 大于 节点键的节点。 左右子树也必须是二叉搜索树。
 */
public class _1038 {

    int sum;

    public TreeNode bstToGst(TreeNode root) {
        /*
        右中左的顺序遍历
         */
        sum = 0;
        search(root);
        return root;
    }


    public void search(TreeNode treeNode) {
        if (treeNode.right != null) {
            search(treeNode.right);
        }
        treeNode.val += sum;
        sum = treeNode.val;
        if (treeNode.left != null) {
            search(treeNode.left);
        }
    }


}
