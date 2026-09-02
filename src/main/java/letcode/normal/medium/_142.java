package letcode.normal.medium;

/**
 * 142. Linked List Cycle II
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/linked-list-cycle-ii/
 * <p>
 * Given the head of a linked list, return the node where the cycle begins. If there is no cycle,
 * return null .
 * <p>
 * There is a cycle in a linked list if there is some node in the list that can be reached again by
 * continuously following the next pointer. Internally, pos is used to denote the index of the node
 * that tail's next pointer is connected to ( 0-indexed ). It is -1 if there is no cycle. Note that pos
 * is not passed as a parameter .
 * <p>
 * Do not modify the linked list.
 * <p>
 * Example 1:
 * <p>
 * Input: head = [3,2,0,-4], pos = 1
 * Output: tail connects to node index 1
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 * <p>
 * Example 2:
 * <p>
 * Input: head = [1,2], pos = 0
 * Output: tail connects to node index 0
 * Explanation: There is a cycle in the linked list, where tail connects to the first node.
 * <p>
 * Example 3:
 * <p>
 * Input: head = [1], pos = -1
 * Output: no cycle
 * Explanation: There is no cycle in the linked list.
 * <p>
 * Constraints:
 * <p>
 * - The number of the nodes in the list is in the range [0, 10 4 ] .
 * <p>
 * - -10 5 <= Node.val <= 10 5
 * <p>
 * - pos is -1 or a valid index in the linked-list.
 * <p>
 * Follow up: Can you solve it using O(1) (i.e. constant) memory?
 */

import letcode.utils.ListNode;

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *
 }
 *
 }
 */
public class _142 {

    public ListNode detectCycle(ListNode head) {
        /*
        假设存在环，慢指针每次走一步，快指针每次走两步，最终一定会在环内相遇。
        因为进入环后，两指针的相对位置每次改变一个节点，经过若干步后必然重合。

        假设：
        s：头结点到环入口的距离
        c：环的长度
        p：环入口到相遇点的距离

        相遇时，慢指针走了 s + nc + p，快指针走了 s + mc + p。
        由于快指针速度是慢指针的两倍：
        2(s + nc + p) = s + mc + p
        整理得：
        s + p = (m - 2n)c
        s = (m - 2n)c - p
          = (m - 2n - 1)c + (c - p)

        其中 c - p 是相遇点沿环到达入口的距离。
        因此让一个指针从头结点出发，另一个从相遇点出发，
        两者同时每次走一步，最终会在环入口相遇。
         */
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode slow2 = head;
                while (slow2 != slow) {
                    slow2 = slow2.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }
}
