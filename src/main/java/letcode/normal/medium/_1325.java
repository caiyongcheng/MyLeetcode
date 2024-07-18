package letcode.normal.medium;

import letcode.utils.TestCaseInputUtils;
import letcode.utils.TreeNode;

/**
 * 给你一棵以 root 为根的二叉树和一个整数 target ，请你删除所有值为 target 的 叶子节点 。
 * 注意，一旦删除值为 target 的叶子节点，它的父节点就可能变成叶子节点；
 * 如果新叶子节点的值恰好也是 target ，那么这个节点也应该被删除。  也就是说，你需要重复此过程直到不能继续删除。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-05-28 15:06
 */
public class _1325 {

    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) {
            return null;
        }
        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);
        if (root.val == target && root.left == null && root.right == null) {
            return null;
        }
        return root;
    }



    /**
     * 示例 1：
     * 输入：root = [1,2,3,2,null,2,4], target = 2
     * 输出：[1,null,3,null,4]
     * 解释：
     * 上面左边的图中，绿色节点为叶子节点，且它们的值与 target 相同（同为 2 ），它们会被删除，得到中间的图。
     * 有一个新的节点变成了叶子节点且它的值与 target 相同，所以将再次进行删除，从而得到最右边的图。
     * 示例 2：
     * 输入：root = [1,3,3,3,2], target = 3
     * 输出：[1,3,null,null,2]
     * 示例 3：
     * 输入：root = [1,2,null,2,null,2], target = 2
     * 输出：[1]
     * 解释：每一步都删除一个绿色的叶子节点（值为 2）。
     * 示例 4：
     * 输入：root = [1,1,1], target = 1
     * 输出：[]
     * 示例 5：
     *
     * 输入：root = [1,2,3], target = 1
     * 输出：[1,2,3]
     * @param args
     */
    public static void main(String[] args) {
        TreeNode.display(new _1325().removeLeafNodes(
                TreeNode.createUseLeetCode(TestCaseInputUtils.getIntegerArr(
                        "[1,1,1]"
                )),
                1
        ));
    }

}
