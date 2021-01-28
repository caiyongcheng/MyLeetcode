package letcode.easy;

import letcode.utils.ListNode;

/**
 * @program: StudyHTTP
 * @description: 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * @author: 蔡永程
 * @create: 2020-06-18 16:11
 */


public class _21TwentyOne {

    /**
     * 示例：
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode res = new ListNode(1);
        ListNode p = res;
        int v = 0;

        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                p.next = new ListNode(l2.val);
                p = p.next;
                l2 = l2.next;
            } else {
                p.next = new ListNode(l1.val);
                p = p.next;
                l1 = l1.next;
            }
        }

        while (l1 != null) {
            p.next = new ListNode(l1.val);
            l1 = l1.next;
            p = p.next;
        }
        while (l2 != null) {
            p.next = new ListNode(l2.val);
            l2 = l2.next;
            p = p.next;
        }

        return res.next;
    }

    public static void printListNode(ListNode l) {
        while (l.next != null) {
            System.out.print(l.val + "->");
            l = l.next;
        }
        if (l != null) {
            System.out.println(l.val);
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode listNode = mergeTwoLists(l1, l2);
        printListNode(l1);
        printListNode(l2);
        printListNode(listNode);


    }


}