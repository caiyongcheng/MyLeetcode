package letcode.normal.medium;

import letcode.utils.TreeNode;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/12/4 10:17
 * description 给定一个二叉搜索树 root (BST)，请将它的每个节点的值替换成树中大于或者等于该节点值的所有节点值之和。
 * 提醒一下， 二叉搜索树 满足下列约束条件：  节点的左子树仅包含键 小于 节点键的节点。
 * 节点的右子树仅包含键 大于 节点键的节点。 左右子树也必须是二叉搜索树。
 */
public class _1038OneThousandThirtyEight {

    int sum;

    public TreeNode bstToGst(TreeNode root) {
        /*
        右中左的顺序遍历
         */
        sum = 0;
        search(root);
        return root;
    }


    public void search(TreeNode treeNode) {
        if (treeNode.right != null) {
            search(treeNode.right);
        }
        treeNode.val += sum;
        sum = treeNode.val;
        if (treeNode.left != null) {
            search(treeNode.left);
        }
    }


    /**
     * 输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
     * 输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
     * 示例 2：
     *
     * 输入：root = [0,null,1]
     * 输出：[1,null,1]
     * @param args
     */
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(new Integer[]{4, 1, 6, 0, 2, 5, 7, null, null, null, 3, null, null, null, 8});
        System.out.println(new _1038OneThousandThirtyEight().bstToGst(
                treeNode
        ));
    }

}
