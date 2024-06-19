package letcode.normal.medium;

import letcode.utils.TestCaseUtils;
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

    /**
     * 示例 1:
     *
     *
     * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
     * 输出: [3,9,20,null,null,15,7]
     * 示例 2:
     *
     * 输入: preorder = [-1], inorder = [-1]
     * 输出: [-1]
     * @param args
     */
    public static void main(String[] args) {
        TreeNode treeNode = new __105_New().buildTree(
                TestCaseUtils.getIntArr("[3,9,20,15,7]"),
                TestCaseUtils.getIntArr("[9,3,15,20,7]")
        );
        System.out.println(treeNode.toString());
/*        TreeNode treeNode = new __105_New().buildTree(
                TestCaseUtils.getIntArr("[1,2,3]"),
                TestCaseUtils.getIntArr("[3,2,1]")
        );
        System.out.println(treeNode.toString());*/
    }

}
