package letcode.normal.medium;

import letcode.utils.TestCaseOutputUtils;
import letcode.utils.TreeNode;

/**
 * 给定一棵树的前序遍历 preorder 与中序遍历  inorder。请构造二叉树并返回其根节点。
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder和inorder均无重复元素
 * inorder均出现在preorder preorder保证为二叉树的前序遍历序列
 * inorder保证为二叉树的中序遍历序列
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-09-18 09:20
 **/
public class _105 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        /*
        前序遍历是 根 左 右 也即是先根节点再子节点
        中序遍历是 左 根 右 根据前序遍历拿出根节点，根据中序遍历判断是左节点 还是右节点
         */
        int[] valToIndex = new int[6001];
        for (int index = 0; index < inorder.length; index++) {
            valToIndex[inorder[index] + 3000] = index;
        }
        TreeNode root = new TreeNode(preorder[0]);
        TreeNode child;
        TreeNode parent = root;
        for (int index = 1; index < preorder.length; index++) {
            child = new TreeNode(preorder[index]);
            //左节点
            if (valToIndex[child.val + 3000] + 1 == valToIndex[parent.val + 3000]) {
                parent.left = child;
                parent = child;
                continue;
            }
            // 表明child 不是 parent的子节点 需要从root节点开始找父节点
            parent = root;
            while (parent != child) {
                if (valToIndex[child.val + 3000] < valToIndex[parent.val + 3000]) {
                    if (parent.left == null) {
                        parent.left = child;
                        parent = child;
                    } else {
                        parent = parent.left;
                    }
                } else {
                    if (parent.right == null) {
                        parent.right = child;
                        parent = child;
                    } else {
                        parent = parent.right;
                    }
                }
            }
        }
        return root;
    }


}
