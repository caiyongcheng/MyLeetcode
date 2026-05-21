package letcode.normal.medium;

import letcode.utils.TestCaseInputUtils;
import letcode.utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/2/20 14:12
 */
public class __105_New {

    private int[] preorder;

    private Map<Integer, Integer> inVal2Idx = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        /*
        根据先序遍历得到根节点 再通过中序遍历得到根节点的左右左右子树部分 在进行递归迭代 构建左右子树 最后拼接好
         */
        this.preorder = preorder;
        for (int idx = 0; idx < inorder.length; idx++) {
            inVal2Idx.put(inorder[idx], idx);
        }
        return buildTree(0, preorder.length - 1, 0, inorder.length - 1);
    }

    public TreeNode buildTree(int preStart, int preEnd, int inStart, int inEnd) {
        if (inStart > inEnd || preEnd >= preorder.length) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        if (inStart == inEnd) {
            return root;
        }
        int rootIdx = inVal2Idx.get(preorder[preStart]);
        int leftChildNodeLen = rootIdx - inStart;
        root.left = buildTree(preStart + 1, preStart + leftChildNodeLen, inStart, rootIdx - 1);
        root.right = buildTree(preStart + leftChildNodeLen + 1, preEnd, rootIdx + 1, inEnd);
        return root;
    }

}
