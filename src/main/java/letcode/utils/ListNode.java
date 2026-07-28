package letcode.utils;

/**
 * Leetcode
 * 链表的节点类
 *
 * @author : CaiYongcheng
 * @since : 2020-06-27 11:55
 **/
public class ListNode {

    public int val;
    public ListNode next;
    static private final String PREFIX = "[";
    static private final String SUFFIX = "]";
    static private final String SEPARATOR = "\t";

    public ListNode() {
    }

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int[] nums) {

        this.val = nums[0];
        for (int i = nums.length - 1; i >= 1; i--) {
            ListNode listNode = new ListNode(nums[i]);
            listNode.next = this.next;
            this.next = listNode;
        }
    }

    public void display() {
        display(PREFIX, SUFFIX, SEPARATOR);
    }

    public void display(String prefix, String suffix, String separator) {
        ListNode head = this;
        System.out.print(prefix);
        while (head != null) {
            System.out.print(separator + head.getVal());
            head = head.next;
        }
        System.out.println(suffix);
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(PREFIX);
        ListNode head = this;
        while (head != null) {
            stringBuilder.append(head.val).append(SEPARATOR);
            head = head.next;
        }
        stringBuilder.append(SUFFIX);
        return stringBuilder.toString();
    }
}
