package letcode.offer.easy;

import letcode.utils.ListNode;

import java.util.List;

/**
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
 *
 * @author CaiYongcheng
 * @date 2021-09-02 08:55
 **/
public class _Offer_TwentyTwo22 {

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode target = head;
        while (k > 1 && head != null) {
            head = head.next;
            --k;
        }
        while (head.next != null) {
            head = head.next;
            target = target.next;
        }
        return target;
    }

    public static void main(String[] args) {

        ListNode listNode = new ListNode(new int[]{1,2,3,4,5});
        ListNode kthFromEnd = new _Offer_TwentyTwo22().getKthFromEnd(listNode, 2);
        System.out.println(kthFromEnd == listNode.next.next.next);
    }

}
