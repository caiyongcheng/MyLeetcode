package letcode.offer.easy;
import letcode.utils.ListNode;

/**
 * 输入两个链表，找出它们的第一个公共节点。
 *
 * @author CaiYongcheng
 * @date 2021-07-21 23:51
 **/
public class _Offer_52FiftyTwo {


    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        /**
         * 求 两个链表的公共节点 其实是两个列表的长度差
         */
        int lengthA = getListLength(headA);
        int lengthB = getListLength(headB);
        if (lengthA == 0 || lengthB == 0) {
            return null;
        }
        while (lengthA > lengthB) {
            headA = headA.next;
            --lengthA;
        }
        while (lengthB > lengthA) {
            headB = headB.next;
            --lengthB;
        }
        while (headA != null && headB != null && headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA == headB ? headA : null;
    }

    public int getListLength(ListNode listNode) {
        if (listNode == null) {
            return 0;
        }
        int length = 0;
        while (listNode != null) {
            listNode = listNode.next;
            ++length;
        }
        return length;
    }

}
