package letcode.normal.easy;

import letcode.utils.ListNode;

/**
 * Given the head of a singly linked list,
 * return the middle node of the linked list.
 * If there are two middle nodes, return the second middle node.
 *
 * @author CaiYongcheng
 * @since 2021-12-28 14:18
 **/
public class _876 {

    public ListNode middleNode(ListNode head) {
        int count = 0;
        ListNode p = head;
        while (p != null) {
            ++count;
            p = p.next;
        }
        count = (count + 2) / 2;
        for (int i = 1; i < count; i++) {
            head = head.next;
        }
        return head;
    }

}
