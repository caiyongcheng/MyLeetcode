package letcode.easy;

import letcode.utils.TreeNode;

/**
 * Leetcode
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * @author : CaiYongcheng
 * @date : 2020-06-29 15:16
 **/
public class _104OneHundredFour {

    public static int maxDepth(TreeNode root, int n) {
        return root == null ? n :
                Math.max(maxDepth(root.left, n + 1), maxDepth(root.right, n + 1));
    }

    /**
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回它的最大深度3 。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        return maxDepth(root, 0);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(maxDepth(root));
    }
}
