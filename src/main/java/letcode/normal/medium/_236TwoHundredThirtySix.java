package letcode.normal.medium;

import letcode.utils.TestCaseUtils;
import letcode.utils.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2024/2/12 17:04
 * description 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。  百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，
 * 最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 */
public class _236TwoHundredThirtySix {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        /*
        优化手段 增加TreeNode的equal和hashcode实现 使用set替代两层循环的实现、
        第一次遍历获取set 第二次边遍历边判断set是否存在
         */
        List<TreeNode> pPath = new ArrayList<>();
        List<TreeNode> qPath = new ArrayList<>();
        getPath(root, p.val, pPath);
        getPath(root, q.val, qPath);
        for (TreeNode treeNode : pPath) {
            for (TreeNode node : qPath) {
                if (treeNode == node) {
                    return treeNode;
                }
            }
        }
        return root;
    }


    public boolean getPath(TreeNode curRoot, int val, List<TreeNode> path) {
        if (curRoot.val == val) {
            path.add(curRoot);
            return true;
        }
        if (Objects.nonNull(curRoot.left) && getPath(curRoot.left, val, path)) {
            path.add(curRoot);
            return true;
        }
        if (Objects.nonNull(curRoot.right) && getPath(curRoot.right, val, path)) {
            path.add(curRoot);
            return true;
        }
        return false;
    }

    /**
     * 示例 1：
     *
     *
     * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
     * 输出：3
     * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
     * 示例 2：
     *
     *
     * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
     * 输出：5
     * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
     * 示例 3：
     *
     * 输入：root = [1,2], p = 1, q = 2
     * 输出：1
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _236TwoHundredThirtySix().lowestCommonAncestor(
                TreeNode.createUseLeetCode(TestCaseUtils.getIntegerArr("[1,2]")),
                new TreeNode(1),
                new TreeNode(2)
        ).val);
    }




}
