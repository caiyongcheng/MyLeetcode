package letcode.normal.medium;

import letcode.utils.TreeNode;

/**
 * 给你二叉树的根结点root，此外树的每个结点的值要么是 0 ，要么是 1 。
 * 返回移除了所有不包含 1 的子树的原二叉树。
 * 节点 node 的子树为 node 本身加上所有 node 的后代。
 * 来源：力扣（LeetCode） 链接：https://leetcode.cn/problems/binary-tree-pruning 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-07-21 09:00
 **/
public class _814 {

    public TreeNode pruneTree(TreeNode root) {
        /*
        递归遍历 判断移除即可
         */
        return pruning(root);
    }


    public TreeNode pruning(TreeNode root) {
        if (root.left != null) {
            root.left = pruning(root.left);
        }
        if (root.right != null) {
            root.right = pruning(root.right);
        }
        return root.left == null && root.right == null && root.val == 0 ? null : root;
    }


}
