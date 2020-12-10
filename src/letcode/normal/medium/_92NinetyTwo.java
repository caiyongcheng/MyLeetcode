package letcode.medium;

import letcode.utils.ListNode;

/**
 * Leetcode
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。  说明: 1 ≤ m ≤ n ≤ 链表长度。
 *
 * @author : CaiYongcheng
 * @date : 2020-07-24 16:21
 **/
public class _92NinetyTwo {

    /**
     * 示例:
     * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
     * 输出: 1->4->3->2->5->NULL
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || m == n){
            return head;
        }
        int index = 1;
        int t = m;
        // 找到第一个待交换节点的前节点
        ListNode pre = new ListNode(1);
        // 用于交换时临时保存的节点
        ListNode tmp;
        // 第一个要交换的节点
        ListNode tail;
        pre.next = head;

        // 找到第一个待交换节点的前节点
        while (index < m){
            ++index;
            pre = pre.next;
        }
        tail = pre.next;
        for (; m < n; ++m) {
            if (tail != null && tail.next != null) {
                tmp = tail.next;
                tail.next = tmp.next;
                tmp.next = pre.next;
                pre.next = tmp;
            }
        }
        return t == 1 ? pre.next : head;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(new int[]{1, 2});
        listNode.display();
        ListNode listNode1 = new _92NinetyTwo().reverseBetween(listNode, 1, 2);
        listNode1.display();
    }



}
