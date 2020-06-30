package letcode.utils;

/**
 * Leetcode
 * 链表的节点类
 *
 * @author : CaiYongcheng
 * @date : 2020-06-27 11:55
 **/
public class ListNode {

    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }

    public ListNode(int[] nums){
        ListNode head = new ListNode(1);
        ListNode p = head;
        int n = nums.length-1;
        this.val = nums[0];
        for(int i=1; i<n; ++i){
            head.next = new ListNode(nums[i]);
            head = head.next;
        }
        head.next = new ListNode(nums[n]);
        this.next = p.next;
    }

    public void display(){
        ListNode head = this;
        System.out.print("[\t");
        while (head != null){
            System.out.print(head.val+"\t");
            head = head.next;
        }
        System.out.println("]");
    }
}
