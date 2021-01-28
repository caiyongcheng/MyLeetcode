package letcode.normal.medium;

import letcode.utils.ListNode;

/**
 * @program: Leetcode
 * @description: 对链表进行插入排序
 * @author: 蔡永程
 * @create: 2021-01-13 10:18
 */
public class _147OneHundredFortySeven {

    /**
     * 示例 1：
     * 输入: 4->2->1->3
     * 输出: 1->2->3->4
     * 示例 2：
     * 输入: -1->5->3->4->0
     * 输出: -1->0->3->4->5
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/insertion-sort-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        final ListNode listNode = new ListNode(new int[]{4, 2, 1, 3, 5});
        listNode.display();
        new _147OneHundredFortySeven().insertionSortList(listNode).display();
    }

    private ListNode searchListNode(ListNode head, int target) {
        ListNode p = head;
        while (p.next != null) {
            if (p.next.val >= target) {
                return p;
            }
            p = p.next;
        }
        return p;
    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode front = new ListNode(Integer.MAX_VALUE);
        ListNode p = head;
        ListNode q = null;
        ListNode tmp = null;
        front.next = head;
        while (p.next != null) {
            q = searchListNode(front, p.next.val);
            tmp = p.next;
            p.next = tmp.next;
            tmp.next = q.next;
            q.next = tmp;
            if (p.next == tmp) {
                p = p.next;
            }
        }
        return front.next;
    }

}