package letcode.normal.medium;

import letcode.utils.ListNode;
import letcode.utils.TreeNode;

/**
 * 给你一棵以 root 为根的二叉树和一个 head 为第一个节点的链表。  如果在二叉树中，存在一条一直向下的路径，
 * 且每个点的数值恰好一一对应以 head 为首的链表中每个节点的值，那么请你返回 True ，否则返回 False 。
 * 一直向下的路径的意思是：从树中某个节点开始，一直连续向下的路径。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-12-30 10:07
 */
public class _1367 {

    public boolean isSubPath(ListNode head, TreeNode root) {
        if (root == null) {
            return false;
        }
        return dfs(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }


    private boolean dfs(ListNode cur, TreeNode root) {
        if (cur == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        if (cur.val != root.val) {
            return false;
        }
        return dfs(cur.next, root.left) || dfs(cur.next, root.right);
    }

    /**
     * Example 1:
     *
     *
     *
     * Input: head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
     * Output: true
     * Explanation: Nodes in blue form a subpath in the binary Tree.
     * Example 2:
     *
     *
     *
     * Input: head = [1,4,2,6], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
     * Output: true
     * Example 3:
     *
     * Input: head = [1,4,2,6,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
     * Output: false
     * Explanation: There is no path in the binary tree that contains all the elements of the linked list from head.
     * @param args
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(1);

        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        root.right.left.left = new TreeNode(6);
        root.right.left.right = new TreeNode(8);
        root.right.left.right.left = new TreeNode(1);
        root.right.left.right.right = new TreeNode(3);

        ListNode head = new ListNode(new int[]{4,2,8});

        System.out.println(new _1367().isSubPath(head, root));
    }

}
