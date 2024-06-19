package letcode.normal.easy;

import letcode.utils.TestCaseUtils;
import letcode.utils.TreeNode;

import java.util.Objects;

/**
 * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/2/26 17:01
 */
public class _938 {

    int low;
    int high;

    int total = 0;


    public int rangeSumBST(TreeNode root, int low, int high) {
        if (Objects.isNull(root)) {
            return total;
        }
        this.low = low;
        this.high = high;
        traversal(root);
        return total;
    }

    public void traversal(TreeNode root) {
        if (root.val >= low && root.val <= high) {
            total += root.val;
        }
        if (Objects.nonNull(root.left)) {
            traversal(root.left);
        }
        if (Objects.nonNull(root.right)) {
            traversal(root.right);
        }
    }

    /**
     * 示例 1：
     *
     *
     * 输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
     * 输出：32
     * 示例 2：
     *
     *
     * 输入：root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
     * 输出：23
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _938().rangeSumBST(
                TreeNode.createUseLeetCode(TestCaseUtils.getIntegerArr("[10,5,15,3,7,13,18,1,null,6]")),
                6,
                10
        ));
    }

}
