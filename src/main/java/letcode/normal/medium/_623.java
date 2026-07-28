package letcode.normal.medium;

import letcode.utils.TreeNode;

/**
 * 给定一个二叉树的根root和两个整数 val 和depth，在给定的深度depth处添加一个值为 val 的节点行。
 * 注意，根节点root位于深度1。  加法规则如下:  给定整数depth，对于深度为depth - 1 的每个非空树节点 cur ，
 * 创建两个值为 val 的树节点作为 cur 的左子树根和右子树根。 cur 原来的左子树应该是新的左子树根的左子树。
 * cur 原来的右子树应该是新的右子树根的右子树。 如果 depth == 1 意味着depth - 1根本没有深度，那么创建一个树节点，
 * 值 val 作为整个原始树的新根，而原始树就是新根的左子树。
 * 来源：力扣（LeetCode） 链接：https://leetcode.cn/problems/add-one-row-to-tree 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-08-05 08:59
 **/
public class _623 {

    private int targetDepth;

    private int targetVal;

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode newRoot = new TreeNode();
            newRoot.val = val;
            newRoot.left = root;
            return newRoot;
        }
        targetDepth = depth - 1;
        targetVal = val;
        recur(root, 1);
        return root;
    }


    public void recur(TreeNode curNode, int curDep) {
        if (curDep == targetDepth) {
            TreeNode newLeftSubNode = new TreeNode();
            newLeftSubNode.val = targetVal;
            newLeftSubNode.left = curNode.left;
            curNode.left = newLeftSubNode;
            TreeNode newRightSubNode = new TreeNode();
            newRightSubNode.val = targetVal;
            newRightSubNode.right = curNode.right;
            curNode.right = newRightSubNode;
            return;
        }
        if (null != curNode.left) {
            recur(curNode.left, curDep + 1);
        }
        if (null != curNode.right) {
            recur(curNode.right, curDep + 1);
        }
    }


}
