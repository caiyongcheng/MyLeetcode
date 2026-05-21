package letcode.normal.easy;

import letcode.utils.TestCaseInputUtils;
import letcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2024/2/12 16:42
 * description 给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。
 */
public class _145 {

    public List<Integer> postorderTraversal(TreeNode root) {
        if (Objects.isNull(root)) {
            return new ArrayList<>(0);
        }
        List<Integer> ans = new ArrayList<>();
        postorderTraversal(root, ans);
        return ans;
    }

    public void postorderTraversal(TreeNode root, List<Integer> ans) {
        if (Objects.nonNull(root.left)) {
            postorderTraversal(root.left, ans);
        }
        if (Objects.nonNull(root.right)) {
            postorderTraversal(root.right, ans);
        }
        ans.add(root.val);
    }

}
