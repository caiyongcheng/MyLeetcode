package letcode.medium;

import letcode.utils.ListNode;

/**
 * Leetcode
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * @author : CaiYongcheng
 * @date : 2020-06-29 22:15
 **/
public class _24TwentyFour {


    /**
     * 示例:
     * 给定 1->2->3->4, 你应该返回 2->1->4->3
     *
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        ListNode phead = new ListNode(0);
        ListNode p = phead;
        ListNode tmp;
        phead.next = head;
        while (p != null && p.next != null && p.next.next != null) {
            tmp = p.next.next;
            p.next.next = p.next.next.next;
            tmp.next = p.next;
            p.next = tmp;
            p = p.next.next;
        }
        return phead.next;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(new int[]{1, 2, 3, 4});
        listNode.display();
        swapPairs(listNode).display();
    }


}
