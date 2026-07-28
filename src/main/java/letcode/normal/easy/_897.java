package letcode.normal.easy;
import letcode.utils.TreeNode;

import java.util.Stack;

/**
 * 给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 *
 * @author CaiYongcheng
 * @since 2021-04-25 11:15
 **/
public class _897 {

    public TreeNode increasingBST(TreeNode root) {
        Stack<Integer> stack = new Stack<>();
        if (root != null) {
            increasingBST(root, stack);
            TreeNode p = root;
            while (!stack.empty()) {
                p.val = stack.pop();
                p.left = null;
                if (p.right == null && !stack.empty()) {
                    p.right = new TreeNode();
                }
                p = p.right;
            }
        }
        return root;
    }


    public void increasingBST(TreeNode root, Stack<Integer> stack) {
        if (null != root.right) {
            increasingBST(root.right, stack);
        }
        stack.push(root.val);
        if (null != root.left) {
            increasingBST(root.left, stack);
        }
    }

}
