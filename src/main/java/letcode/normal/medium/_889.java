package letcode.normal.medium;

import letcode.utils.TestCaseInputUtils;
import letcode.utils.TreeNode;

/**
 * 给定两个整数数组，preorder 和 postorder ，其中 preorder 是一个具有 无重复 值的二叉树的前序遍历，postorder 是同一棵树的后序遍历，重构并返回二叉树。
 * 如果存在多个答案，您可以返回其中 任何 一个。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/2/22 14:50
 */
public class _889 {


    private int[] postorderIdxMap;
    
    private int[] preOrder;


    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        postorderIdxMap = new int[31];
        for (int i = 0; i < postorder.length; i++) {
            postorderIdxMap[postorder[i]] = i;
        }
        this.preOrder = preorder;
        return constructFromPrePost(0, preorder.length - 1, 0, postorder.length - 1);
    }

    public TreeNode constructFromPrePost(int preStartIdx, int preEndIdx, int postStartIdx, int postEndIdx) {
        if (preStartIdx > preEndIdx || preEndIdx > this.preOrder.length) {
            return null;
        }
        TreeNode root = new TreeNode(this.preOrder[preStartIdx]);
        if (preStartIdx == preEndIdx) {
            return root;
        }
        int leftChildNodeCount = postorderIdxMap[this.preOrder[preStartIdx + 1]] - postStartIdx + 1;
        root.left = constructFromPrePost(preStartIdx + 1, preStartIdx + leftChildNodeCount, postStartIdx, postStartIdx + leftChildNodeCount - 1);
        root.right = constructFromPrePost(preStartIdx + leftChildNodeCount + 1, preEndIdx, postStartIdx + leftChildNodeCount, postEndIdx - 1);
        return root;
    }

}
