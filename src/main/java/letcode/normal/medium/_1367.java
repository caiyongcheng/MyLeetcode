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

}
