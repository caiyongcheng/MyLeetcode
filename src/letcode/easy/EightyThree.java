package letcode.easy;

import letcode.utils.ListNode;

/**
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 *
 * @author : CaiYongcheng
 * @date : 2020-06-27 11:53
 **/


public class EightyThree {

    /**
     * 示例 1:
     * 输入: 1->1->2
     * 输出: 1->2
     *
     * 示例 2:
     * 输入: 1->1->2->3->3
     * 输出: 1->2->3
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode p = head;
        ListNode pnext = null;
        while (p!=null && p.next!=null){
            if(p.val == p.next.val){
                p.next = p.next.next;
            }else {
                p = p.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(new int[]{1, 1, 2,3,3});
        listNode.display();
        deleteDuplicates(listNode).display();
    }
}
