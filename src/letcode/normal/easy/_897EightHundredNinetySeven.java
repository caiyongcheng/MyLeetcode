package letcode.normal.easy;
import letcode.utils.TreeNode;

import java.util.Stack;

/**
 * 给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 *
 * @author CaiYongcheng
 * @date 2021-04-25 11:15
 **/
public class _897EightHundredNinetySeven {

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

    /**
     * 输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
     * 输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/increasing-order-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        TreeNode node = new TreeNode(new Integer[]{5, 3, 6, 2, 4, null, 8, 1, null, null, null, null, null, 7, 9});
        /**
         *                     5
         *             3             6
         *         2       4       n    8
         *       1   n   n   n        7   9
         */
        new _897EightHundredNinetySeven().increasingBST(node);
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.right;
        }
    }

}
