package letcode.normal.medium;

import letcode.utils.ListNode;
import letcode.utils.TestUtil;

/**
 * 给你一个链表的头节点 head ，该链表包含由 0 分隔开的一连串整数。链表的 开端 和 末尾 的节点都满足 Node.val == 0 。
 * 对于每两个相邻的 0 ，请你将它们之间的所有节点合并成一个节点，其值是所有已合并节点的值之和。然后将所有 0 移除，修改后的链表不应该含有任何 0 。
 * 返回修改后链表的头节点 head 。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-09-09 18:25
 */
public class _2181 {

    public ListNode mergeNodes(ListNode head) {
        ListNode root = new ListNode();
        root.next = head;

        ListNode p = root.next;
        ListNode q = p;
        while (p != null) {
            if (p.val == 0) {
                q = p.next;
                while (q != null && q.val != 0) {
                    p.val += q.val;
                    p.next = q.next;
                    q = p.next;
                }
            }
            if (p.next != null && p.next.val == 0 && p.next.next == null) {
                p.next = null;
                break;
            }
            p = p.next;
        }
        return root.next;
    }

}
