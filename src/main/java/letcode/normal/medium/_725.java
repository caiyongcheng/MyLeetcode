package letcode.normal.medium;

import letcode.utils.ListNode;
import letcode.utils.TestCaseOutputUtils;

/**
 * 给你一个头结点为 head 的单链表和一个整数 k ，请你设计一个算法将链表分隔为 k 个连续的部分。
 * 每部分的长度应该尽可能的相等：任意两部分的长度差距不能超过 1 。这可能会导致有些部分为 null 。
 * 这 k 个部分应该按照在链表中出现的顺序排列，并且排在前面的部分的长度应该大于或等于排在后面的长度。  返回一个由上述 k 部分组成的数组。
 *
 * @author CaiYongcheng
 * @since 2021-09-22 09:06
 **/
public class _725 {

    public ListNode[] splitListToParts(ListNode head, int k) {
        /*
         * 快慢指针 确定 有无环 长度
         * 按长度k分割
         */
        if (head == null) {
            return new ListNode[k];
        }
        ListNode fast = head;
        ListNode slow = head;
        int len = 0;
        if (fast.next != null) {
            while (fast != null && fast.next != null) {
                len += 2;
                fast = fast.next.next;
                slow = slow.next;
                if (fast == slow) {
                    break;
                }
            }
            if (fast != slow) {
                len += (fast == null ? 0 : 1);
            } else {
                len = 0;
                fast = head;
                while (fast != slow) {
                    ++len;
                    fast = fast.next;
                    slow = slow.next;
                }
                slow = slow.next;
                ++len;
                while (fast != slow) {
                    ++len;
                }
            }
        } else {
            len = 1;
        }
        int mod = len % k;
        len = len / k;
        ListNode[] ans = new ListNode[k];
        for (int i = 0; i < k; i++) {
            ans[i] = head;
            if (len == 0) {
                --mod;
            } else {
                for (int j = 1; j < len; j++) {
                    head = head.next;
                }
                if (head != null && --mod > -1) {
                    head = head.next;
                }
            }
            if (head == null) {
                break;
            }
            ListNode tmp = head.next;
            head.next = null;
            head = tmp;
        }
        return ans;
    }

}
