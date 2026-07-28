package letcode.normal.medium;

import letcode.utils.TestCaseOutputUtils;
import letcode.utils.TreeNode;

import java.util.HashMap;

/**
 * 根据一棵树的中序遍历与后序遍历构造二叉树。  注意: 你可以假设树中没有重复的元素。
 *
 * @author CaiYongcheng
 * @since 2021-09-23 09:04
 **/
public class _106 {

    private int[] inorder;
    private int[] postorder;
    private HashMap<Integer, Integer> valToInx;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        /*
         * 同105题
         * 后续遍历中后取出的是根 根据中序遍历判断是根的左节点还是右节点
         * 所以中序遍历要转化成 值->下标的形式
         */
        valToInx = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            valToInx.put(inorder[i], i);
        }
        this.inorder = inorder;
        this.postorder = postorder;
        return createTree(0, inorder.length - 1, 0, postorder.length - 1);
    }


    public TreeNode createTree(int il, int ir, int pl, int pr) {
        if (ir == il) {
            return new TreeNode(inorder[ir]);
        }
        if (ir < il) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[pr]);
        //后序遍历中 最后一个是根节点 根据根节点 在 中序遍历中 划分左右子树
        Integer inx = valToInx.get(postorder[pr]);
        root.left = createTree(il, inx - 1, pl, inx + pl - il - 1);
        root.right = createTree(inx + 1, ir, inx + pl - il, pr - 1);
        return root;
    }

}
