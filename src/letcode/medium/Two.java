package letcode.medium;

import letcode.utils.ListNode;

/**
 * @program: StudyHTTP
 * @description: 给出两个 非空 的链表用来表示两个非负的整数。
 * 其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/add-two-numbers 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-06-15 16:51
 */





public class Two {
    /**
     * 示例：
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode p = res;
        int i = 0;
        int j = 0;
        /*
        计算公共部分
         */
        while (l1 != null && l2 !=null){
            i =  (l1.val+l2.val+j)%10;
            p.next = new ListNode(i);
            j = (l1.val+l2.val+j)/10;
            l1 = l1.next;
            l2 = l2.next;
            p = p.next;
        }
        while (l2 != null){
            i = (l2.val+j)%10;
            p.next =  new ListNode(i);
            j = (l2.val+j)/10;
            l2 = l2.next;
            p = p.next;
        }
        while (l1 != null){
            i = (l1.val+j)%10;
            p.next =  new ListNode(i);
            j = (l1.val+j)/10;
            l1 = l1.next;
            p = p.next;
        }
        if(j==1){
            p.next = new ListNode(1);
        }
        return res.next;
    }

    public static void printListNode(ListNode a){
        if (a==null) {
            System.out.println();
        }else{
            System.out.print(a.val);
            printListNode(a.next);
        }
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(3);
        a.next = new ListNode(7);
        //a.next.next = new ListNode(6);

        ListNode b = new ListNode(9);
        b.next = new ListNode(2);
        //b.next = new ListNode(9);


        ListNode c = addTwoNumbers(a, b);
        printListNode(c);
        
        printListNode(a);
        printListNode(b);
    }
}