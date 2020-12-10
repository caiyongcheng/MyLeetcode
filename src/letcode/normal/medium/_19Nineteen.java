package letcode.medium;

import letcode.utils.ListNode;

/**
 * Leetcode
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * @author : CaiYongcheng
 * @date : 2020-06-27 15:07
 **/
public class _19Nineteen {

    /**
     * 示例：
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if(n<1 || head == null) return head;
        ListNode first = new ListNode(1);
        first.next = head;
        ListNode tail = first;
        head = first;
        int i = 0;
        while (i++ < n){
            tail = tail.next;
            if(tail==null) return null;
        }

        while (tail.next != null){
            tail = tail.next;
            first = first.next;
        }
        first.next = first.next == null?null:first.next.next;
        return head.next;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(new int[]{1});
        listNode.display();
        //ListNode listNode1 = removeNthFromEnd(listNode, 5);
        //listNode1.display();
    }

}
