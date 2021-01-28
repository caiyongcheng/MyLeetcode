package letcode.medium;

import letcode.utils.ListNode;

/**
 * Leetcode
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * @author : CaiYongcheng
 * @date : 2020-07-20 11:03
 **/
public class _82EightyTwo {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(new int[]{0, 1, 1, 1, 2, 2, 3, 3});
        listNode.display();
        listNode = new _82EightyTwo().deleteDuplicates(listNode);
        listNode.display();
    }

    public ListNode delete(ListNode head) {
        ListNode p = head;
        int val = head.val;
        while (p != null && p.val == val) {
            p = p.next;
        }
        if (head.next != p) {
            return p;
        }
        return head;
    }

    /**
     * 示例 1:
     * 输入: 1->2->3->3->4->4->5
     * 输出: 1->2->5
     * 示例 2:
     * 输入: 1->1->1->2->3
     * 输出: 2->3
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode p = new ListNode(0);
        ListNode h = p;
        p.next = head;
        while (h.next != null) {
            ListNode delete = delete(h.next);
            if (h.next == delete) {
                h = h.next;
            } else {
                h.next = delete;
            }
        }
        return p.next;
    }
}
