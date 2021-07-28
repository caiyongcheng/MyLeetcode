package letcode.medium;

import letcode.utils.ListNode;

/**
 * Leetcode
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数
 *
 * @author : CaiYongcheng
 * @date : 2020-07-12 14:46
 **/
public class _61SixtyOne {

    /**
     * 示例1:
     * 输入: 1->2->3->4->5->NULL, k = 2
     * 输出: 4->5->1->2->3->NULL
     * 解释:
     * 向右旋转 1 步: 5->1->2->3->4->NULL
     * 向右旋转 2 步: 4->5->1->2->3->NULL
     * <p>
     * 示例2:
     * 输入: 0->1->2->NULL, k = 4
     * 输出: 2->0->1->NULL
     * 解释:
     * 向右旋转 1 步: 2->0->1->NULL
     * 向右旋转 2 步: 1->2->0->NULL
     * 向右旋转 3 步:0->1->2->NULL
     * 向右旋转 4 步:2->0->1->NULL
     *
     * @param head
     * @param k
     * @return
     */
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }
        k %= length;
        tail.next = head;
        k = length - k;
        for (int i = 1; i <= k; ++i) {
            tail = tail.next;
        }
        head = tail.next;
        tail.next = null;
        return head;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(new int[]{1, 2, 3, 4, 5});
        listNode.display();
        ListNode listNode1 = rotateRight(listNode, 2);
        listNode1.display();
    }
}
