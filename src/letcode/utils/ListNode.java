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
    public String prefix = "[";
    public String suffix = "\t]";
    public String separator = "\t";

    public ListNode(int[] nums){

        this.val = nums[0];
        for (int i = nums.length - 1; i >= 1; i--) {
            ListNode listNode = new ListNode(nums[i]);
            listNode.next = this.next;
            this.next = listNode;
        }
    }

    public void display(){
        display(prefix, suffix, separator);
    }

    public void display(String prefix, String suffix, String separator){
        ListNode head = this;
        System.out.print(prefix);
        while (head != null){
            System.out.print(separator + head.getVal());
            head = head.next;
        }
        System.out.println(suffix);
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
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
}
