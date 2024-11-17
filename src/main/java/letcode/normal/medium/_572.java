package letcode.normal.medium;

import letcode.utils.TestUtil;
import letcode.utils.TreeNode;

/**
 * 给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。如果存在，返回 true ；否则，返回 false 。
 * 二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-08-06 09:24
 */
public class _572 {

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        /*
         * 其实可以把两棵树 都转化成 字符串 比较 a字符串是否包含b字符串即可
         * 或者在递归的时候使用类似kmp的方法也可以加速
         *
         * 下面的方法三用的是 计算子树代表的hash值 通过hash的方式做的 但是要注意hash碰撞
         * https://leetcode.cn/problems/subtree-of-another-tree/solutions/233896/ling-yi-ge-shu-de-zi-shu-by-leetcode-solution/?envType=daily-question&envId=2024-08-04
         *
         */
        return match(root, subRoot)
                || (root.left != null && isSubtree(root.left, subRoot))
                || (root.right != null && isSubtree(root.right, subRoot));
    }

    private boolean match(TreeNode root, TreeNode subRoot) {
        return root == subRoot
                || (root != null && subRoot != null
                    && root.val == subRoot.val
                    && match(root.left, subRoot.left)
                    && match(root.right, subRoot.right)
        );
    }

    /**
     * 示例 1：
     *
     *
     * 输入：root = [3,4,5,1,2], subRoot = [4,1,2]
     * 输出：true
     * 示例 2：
     *
     *
     * 输入：root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
     * 输出：false
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(_572.class);
    }

}
