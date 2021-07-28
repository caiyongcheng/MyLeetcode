package letcode.normal.medium;
import letcode.utils.FormatPrintUtils;
import letcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树（具有根结点root），一个目标结点target，和一个整数值 K 。
 * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/all-nodes-distance-k-in-binary-tree 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-07-28 10:30
 **/
public class _863EightHundredSixtyThree {

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        /**
         * 先找到以target为根节点的，距离为k的节点。
         * 再找target的父节点下，另一棵距离子树为k-1的节点。
         * 依次向上直到根结点完成。
         */
        ArrayList<Integer> ans = new ArrayList<>();
        ArrayList<TreeNode> parent = new ArrayList<>();
        dfs(root, target, parent);
        distanceForRoot(target, ans, k);
        for (int i = 1; i < parent.size() && k > 0; i++) {
            --k;
            //父节点
            if (0 == k) {
                ans.add(parent.get(i).val);
                break;
            }
            if (parent.get(i).left == parent.get(i-1) && null != parent.get(i).right) {
                distanceForRoot(parent.get(i).right, ans, k-1);
            } else if (parent.get(i).right == parent.get(i-1) && null != parent.get(i).left) {
                distanceForRoot(parent.get(i).left, ans, k-1);
            }
        }
        return ans;
    }

    public void distanceForRoot(TreeNode root, List<Integer> list, int k) {
        if (0 == k) {
            list.add(root.val);
            return ;
        }
        if (root.left != null) {
            distanceForRoot(root.left, list, k-1);
        }
        if (root.right != null) {
            distanceForRoot(root.right, list, k-1);
        }
    }

    public boolean dfs(TreeNode root, TreeNode target, List<TreeNode> list) {
        if (root == target) {
            list.add(root);
            return true;
        }
        if (root.left != null && dfs(root.left, target, list)) {
            list.add(root);
            return true;
        }
        if (root.right != null && dfs(root.right, target, list)) {
            list.add(root);
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(5);
        treeNode.left.left = new TreeNode(6);
        treeNode.left.right = new TreeNode(2);
        treeNode.left.right.left = new TreeNode(7);
        treeNode.left.right.right = new TreeNode(4);
        treeNode.right = new TreeNode(1);
        treeNode.right.left = new TreeNode(0);
        treeNode.right.right = new TreeNode(8);
        System.out.println(FormatPrintUtils.formatList(new _863EightHundredSixtyThree().distanceK(treeNode, treeNode.right.left, 3)));
    }


}
