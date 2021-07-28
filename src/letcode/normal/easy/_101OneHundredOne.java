package letcode.easy;

import letcode.utils.TreeNode;

/**
 * Leetcode
 * 给定一个二叉树，检查它是否是镜像对称的。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : CaiYongcheng
 * @date : 2020-06-28 20:58
 **/
public class _101OneHundredOne {

    /**
     * 例如，二叉树[1,2,2,3,4,4,3] 是对称的。
     * 1
     * / \
     * 2   2
     * / \ / \
     * 3  4 4  3
     * <p>
     * 但是下面这个[1,2,2,null,3,null,3] 则不是镜像对称的:
     * 1
     * / \
     * 2   2
     * \   \
     * 3    3
     *
     * @param root
     * @return
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetricTwo(root.left, root.right);
    }

    public static boolean isSymmetricTwo(TreeNode left, TreeNode right) {
        return left == null || right == null ? left == right :
                left.val == right.val
                        && isSymmetricTwo(left.left, right.right)
                        && isSymmetricTwo(left.right, right.left);
    }


    public static void main(String[] args) {
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = new TreeNode(2);
        p.left.right = new TreeNode(3);
        p.right.right = new TreeNode(3);
        System.out.println(isSymmetric(p));
    }
}
