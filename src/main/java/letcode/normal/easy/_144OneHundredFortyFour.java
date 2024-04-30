package letcode.normal.easy;

import letcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2024/2/12 16:57
 * description 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 */
public class _144OneHundredFortyFour {

    public List<Integer> preorderTraversal(TreeNode root) {
        if (Objects.isNull(root)) {
            return new ArrayList<>(0);
        }
        List<Integer> ans = new ArrayList<>();
        preorderTraversal(root, ans);
        return ans;
    }



    public void preorderTraversal(TreeNode root, List<Integer> ans) {
        ans.add(root.val);
        if (Objects.nonNull(root.left)) {
            preorderTraversal(root.left, ans);
        }
        if (Objects.nonNull(root.right)) {
            preorderTraversal(root.right, ans);
        }
    }

    /**
     * 示例 1：
     *
     *
     * 输入：root = [1,null,2,3]
     * 输出：[1,2,3]
     * 示例 2：
     *
     * 输入：root = []
     * 输出：[]
     * 示例 3：
     *
     * 输入：root = [1]
     * 输出：[1]
     * 示例 4：
     *
     *
     * 输入：root = [1,2]
     * 输出：[1,2]
     * 示例 5：
     *
     *
     * 输入：root = [1,null,2]
     * 输出：[1,2]
     * @param args
     */
    public static void main(String[] args) {

    }

}
