package letcode.normal.easy;




import letcode.utils.TreeNode;

import java.util.Stack;

/**
 * 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。  如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。
 * 我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。  只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/cousins-in-binary-tree 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-05-17 09:22
 **/
public class _993NineHundredNinetyThree {




    public boolean isCousins(TreeNode root, int x, int y) {
        TreeNode xParent = getHeight(root, x);
        TreeNode yParent = getHeight(root, y);
        return xParent != null && yParent != null && xParent.val == yParent.val && xParent.left != yParent.left;
    }


    public TreeNode getHeight(TreeNode treeNode, int x) {
        Stack<TreeNode> treeNodeStack = new Stack<>();
        Stack<Integer> height = new Stack<>();
        TreeNode temporaryNode;
        int temporaryInt;
        treeNodeStack.add(treeNode);
        height.add(0);
        while (!treeNodeStack.empty()) {
            temporaryNode = treeNodeStack.pop();
            temporaryInt = height.pop();

            if (temporaryNode.right != null) {
                if (temporaryNode.right.val == x) {
                    return new TreeNode(temporaryInt + 1, temporaryNode, null);
                }
                treeNodeStack.add(temporaryNode.right);
                height.add(temporaryInt + 1);
            }
            if (temporaryNode.left != null) {
                if (temporaryNode.left.val == x) {
                    return new TreeNode(temporaryInt + 1, temporaryNode, null);
                }
                treeNodeStack.add(temporaryNode.left);
                height.add(temporaryInt + 1);
            }
        }
        return null;
    }

}
