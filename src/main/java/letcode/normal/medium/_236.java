package letcode.normal.medium;

import letcode.utils.TestCaseInputUtils;
import letcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2024/2/12 17:04
 * description 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。  百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，
 * 最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 */
public class _236 {

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

}
