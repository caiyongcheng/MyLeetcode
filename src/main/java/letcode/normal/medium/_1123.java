package letcode.normal.medium;

import letcode.utils.TreeNode;

import java.util.*;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/9/6 9:50
 * description 给你一个有根节点 root 的二叉树，返回它 最深的叶节点的最近公共祖先 。
 * 回想一下：  叶节点 是二叉树中没有子节点的节点 树的根节点的 深度 为 0，如果某一节点的深度为 d，
 * 那它的子节点的深度就是 d+1 如果我们假定 A 是一组节点 S 的 最近公共祖先，S 中的每个节点都在以 A 为根节点的子树中，
 * 且 A 的深度达到此条件下可能的最大值。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中的节点数将在 [1, 1000] 的范围内。
 * 0 <= Node.val <= 1000
 * 每个节点的值都是 独一无二 的。
 */
public class _1123 {

    Map<Integer, TreeNode> id2TreeNode;
    Map<Integer, Integer> id2TreeHigh;
    Map<Integer, Integer> id2ParentId;

    int[] empty = new int[]{-1, -1};


    int maxHigh = 0;

    public TreeNode lcaDeepestLeaves2(TreeNode root) {
        /**
         * 如果根节点x 的左子树l高和右子树r高一致 那么加上x构成的树即满足条件 也就是答案是x
         * 如果两边字数不一样高 那么高的字数就满足答案 不停的递归分解子问题 即可得到最终答案
         */
        id2TreeNode = new HashMap<>(128);
        return id2TreeNode.get(search2(root, 0)[0]);
    }


    public int[] search2(TreeNode root, int high) {
        id2TreeNode.put(root.val, root);
        if (Objects.isNull(root.right) && Objects.isNull(root.left)) {
            return new int[]{root.val, high};
        }
        int[] leftAns = Objects.isNull(root.left) ? empty : search2(root.left, high + 1);
        int[] rightAns = Objects.isNull(root.right) ? empty : search2(root.right, high + 1);
        return leftAns[1] == rightAns[1]
                ? new int[]{root.val, leftAns[1]}
                : (leftAns[1] > rightAns[1] ? leftAns : rightAns);
    }


    public TreeNode lcaDeepestLeaves(TreeNode root) {
        /*
        根据题目提示 可以使用节点值作为节点id
        遍历树 保存子节点树高 以及父节点id 依次向上回溯即可
         */
        id2TreeNode = new HashMap<>(128);
        id2TreeHigh = new HashMap<>(128);
        id2ParentId = new HashMap<>(128);
        search(root, 0);

        //找出最深叶子节点
        return findDeepNode();
    }

    private void search(TreeNode treeNode, int high) {
        id2TreeNode.put(treeNode.val, treeNode);
        if (Objects.nonNull(treeNode.left)) {
            id2ParentId.put(treeNode.left.val, treeNode.val);
            search(treeNode.left, high + 1);
        }
        if (Objects.nonNull(treeNode.right)) {
            id2ParentId.put(treeNode.right.val, treeNode.val);
            search(treeNode.right, high + 1);
        }
        if (Objects.isNull(treeNode.left) && Objects.isNull(treeNode.right) && high >= maxHigh) {
            if (high > maxHigh) {
                id2TreeHigh.clear();
                maxHigh = high;
            }
            id2TreeHigh.put(treeNode.val, high);
        }
    }


    private TreeNode findDeepNode() {
        List<Integer> deepNodeIdList = new ArrayList<>(id2TreeHigh.keySet());
        //只有一个最深节点
        if (deepNodeIdList.size() == 1) {
            return id2TreeNode.get(deepNodeIdList.get(0));
        }
        //所有节点依次迭代 直到父节点相等为止
        while (true) {
            //哨兵 减少判断
            boolean same = true;
            int preNodeParentId = 0;
            for (int i = 0; i < deepNodeIdList.size(); i++) {
                Integer parentId = id2ParentId.get(deepNodeIdList.get(i));
                same = same && (preNodeParentId == 0 || parentId == preNodeParentId);
                deepNodeIdList.set(i, parentId);
                preNodeParentId = parentId;
            }
            if (same) {
                return id2TreeNode.get(preNodeParentId);
            }
        }
    }


}
