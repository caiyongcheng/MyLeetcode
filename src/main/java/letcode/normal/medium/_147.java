package letcode.normal.medium;

import letcode.utils.ListNode;

/**
 * @program: Leetcode
 * @description: 对链表进行插入排序
 * @author: 蔡永程
 * @create: 2021-01-13 10:18
 */
public class _147 {

    private ListNode searchListNode(ListNode head, int target) {
        ListNode p = head;
        while (p.next != null) {
            if (p.next.val >= target) {
                return p;
            }
            p = p.next;
        }
        return p;
    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode front = new ListNode(Integer.MAX_VALUE);
        ListNode p = head;
        ListNode q = null;
        ListNode tmp = null;
        front.next = head;
        while (p.next != null) {
            q = searchListNode(front, p.next.val);
            tmp = p.next;
            p.next = tmp.next;
            tmp.next = q.next;
            q.next = tmp;
            if (p.next == tmp) {
                p = p.next;
            }
        }
        return front.next;
    }

}
