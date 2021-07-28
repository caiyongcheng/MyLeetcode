package letcode.easy;

import letcode.utils.TreeNode;

/**
 * Leetcode
 * 给定两个二叉树，编写一个函数来检验它们是否相同。  如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/same-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : CaiYongcheng
 * @date : 2020-06-28 20:47
 **/
public class _100OneHundred {

    /**
     * 示例1:
     * 输入:       1         1
     * / \       / \
     * 2   3     2   3
     * <p>
     * [1,2,3],   [1,2,3]
     * <p>
     * 输出: true
     * <p>
     * 示例 2:
     * 输入:      1          1
     * /           \
     * 2             2
     * <p>
     * [1,2],     [1,null,2]
     * <p>
     * 输出: false
     * <p>
     * 示例3:
     * 输入:       1         1
     * / \       / \
     * 2   1     1   2
     * <p>
     * [1,2,1],   [1,1,2]
     * 输出: false
     *
     * @param p
     * @param q
     * @return
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        return p == null || q == null ? p == q : p.val == q.val &&
                isSameTree(p.left, q.left) &&
                isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        TreeNode q = new TreeNode(1);
        q.right = new TreeNode(2);
        System.out.println(isSameTree(p, q));
    }

}
