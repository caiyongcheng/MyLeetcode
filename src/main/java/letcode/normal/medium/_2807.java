package letcode.normal.medium;

import letcode.utils.ListNode;
import letcode.utils.TestCaseInputUtils;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2024/1/7 21:31
 * description 给你一个链表的头 head ，每个结点包含一个整数值。  在相邻结点之间，请你插入一个新的结点，结点值为这两个相邻结点值的 最大公约数 。
 * 请你返回插入之后的链表。  两个数的 最大公约数 是可以被两个数字整除的最大正整数。
 */
public class _2807 {

    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode p = head;
        while (p.next != null) {
            ListNode insertNode = new ListNode(gcd(p.val, p.next.val));
            insertNode.next = p.next;
            p.next = insertNode;
            p = insertNode.next;
        }
        return head;
    }


    /**
     * get gcd x must great y
     * @param x x
     * @param y y
     * @return gcd
     */
    public int gcd(int x, int y) {
        if (x < y) {
            return gcd(y, x);
        }
        int t;
        while (x % y != 0) {
            t = y;
            y = x % y;
            x = t;
        }
        return y;
    }

}
