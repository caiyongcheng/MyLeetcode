package letcode.normal.medium;

/**
 * 2095. Delete the Middle Node of a Linked List
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/delete-the-middle-node-of-a-linked-list/
 * You are given the head of a linked list. Delete the middle node , and return the head of the modified linked list .
 * The middle node of a linked list of size n is the &lfloor;n / 2&rfloor; th node from the start using 0-based indexing ,
 * where &lfloor;x&rfloor; denotes the largest integer less than or equal to x . - For n = 1 , 2 , 3 , 4 , and 5 ,
 * the middle nodes are 0 , 1 , 1 , 2 , and 2 , respect...
 */

import letcode.utils.ListNode;

/**
* Definition for singly-linked list.
* public class ListNode {

    *     int val;
    *     ListNode next;
    *     ListNode() {
    }
    *     ListNode(int val) {
        this.val = val;
    }
    *     ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
    *
}
*/
public class _2095 {

    public ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        int len = 0;
        ListNode tempHead = head;
        while (tempHead != null) {
            ++len;
            tempHead = tempHead.next;
        }

        ListNode removeNodeLastNode = head;
        int removeNodeLastNodeIdx = len / 2 - 1;
        for (int i = 0; i < removeNodeLastNodeIdx; i++) {
            removeNodeLastNode = removeNodeLastNode.next;
        }
        removeNodeLastNode.next = removeNodeLastNode.next.next;

        return head;
    }
}
