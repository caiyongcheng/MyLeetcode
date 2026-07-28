package letcode.normal.medium;

import letcode.utils.ListNode;

/**
 * @author Caiyongcheng
 * @description 给定一个单链表 L 的头节点 head ，
 * 单链表 L 表示为：  L0 → L1 → … → Ln - 1 → Ln 请将其重新排列后变为：  L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * @since 2023/7/31 8:59
 */
public class _143 {

    public void reorderList(ListNode head) {
        /*
        按照规则取链表后半部分取出来 然后按规则插入即可
         */

        /*
         pre每次跳一个节点 lastNode跳两个节点
         假设节点从1开始编号 最后一个节点为n
         当n为偶数时 中间节点为 n/2、 n/2+1 preNode跳(n/2)步到达n/2+1 此时lastNode位于 1 + (n/2) * 2 = n + 1
         当n为奇数时 中间节点为 (n+1)/2 preNode跳(n+1)/2-1步到达(n+1)/2 此时lastNode位于 1 + ((n+1)/2-1) * 2 = n
         总上所述
         当lastNode==null（位于n+1处）时，preNode位于后半部分的开始 endNode为preNode的前序节点
         */
        ListNode preNode = head;
        ListNode lastNode = head;
        ListNode endNode = head;
        while (lastNode != null) {
            endNode = preNode;
            preNode = preNode.next;
            lastNode = lastNode.next;
            if (lastNode != null) {
                lastNode = lastNode.next;
            }
        }
        endNode.next = null;

        /*
        将后半部分反转
         */
        ListNode tmpNode = new ListNode(0);
        ListNode tmpNode1;
        while (preNode != null) {
            tmpNode1 = preNode.next;
            preNode.next = tmpNode.next;
            tmpNode.next = preNode;
            preNode = tmpNode1;
        }
        preNode = tmpNode.next;

        /*
        开始插入
         */
        ListNode startNode = head;
        while (preNode != null) {
            tmpNode = preNode.next;
            preNode.next = startNode.next;
            startNode.next = preNode;
            startNode = preNode.next;
            preNode = tmpNode;
        }


    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(new int[]{1, 2, 3, 4});
        new _143().reorderList(listNode);
        listNode.display();
    }

}
