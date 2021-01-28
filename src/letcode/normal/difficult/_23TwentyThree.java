package letcode.difficult;

/**
 * StudyHTTP
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * @author : CaiYongcheng
 * @date : 2020-06-26 14:59
 **/

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    ListNode(int[] nums) {
        ListNode head = new ListNode(1);
        ListNode p = head;
        int n = nums.length - 1;
        this.val = nums[0];
        for (int i = 1; i < n; ++i) {
            head.next = new ListNode(nums[i]);
            head = head.next;
        }
        head.next = new ListNode(nums[n]);
        this.next = p.next;
    }

    void display() {
        ListNode head = this;
        System.out.print("[\t");
        while (head != null) {
            System.out.print(head.val + "\t");
            head = head.next;
        }
        System.out.println("]");
    }
}


public class _23TwentyThree {


    static ListNode[] slists;

    public static ListNode mergeTwoLists(ListNode left, ListNode right) {
        ListNode head = new ListNode(1);
        ListNode p = head;
        while (left != null && right != null) {
            if (left.val < right.val) {
                head.next = left;
                left = left.next;
            } else {
                head.next = right;
                right = right.next;
            }
            head = head.next;
            head.next = null;
        }
        if (left != null) {
            head.next = left;
        } else {
            head.next = right;
        }
        return p.next;
    }

    public static ListNode divideAndRule(int left, int right) {
        if (left == right) {
            return slists[left];
        }
        if (left >= right) {
            return null;
        }
        int mid = (left + right) / 2;
        return mergeTwoLists(divideAndRule(left, mid), divideAndRule(mid + 1, right));
    }

    /**
     * 示例:
     * 输入:
     * [
     * 1->4->5,
     * 1->3->4,
     * 2->6
     * ]
     * 输出: 1->1->2->3->4->4->5->6
     *
     * @param lists
     * @return
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        slists = lists;
        return divideAndRule(0, lists.length - 1);
    }

    public static void main(String[] args) {
        ListNode[] listNodes = new ListNode[]{
                new ListNode(new int[]{1, 4, 5}),
                new ListNode(new int[]{1, 3, 4}),
                new ListNode(new int[]{2, 6})
        };
        for (ListNode listNode : listNodes) {
            listNode.display();
        }
        ListNode listNode = mergeKLists(listNodes);
        listNode.display();
    }

}
