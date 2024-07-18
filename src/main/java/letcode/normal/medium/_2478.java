package letcode.normal.medium;

import letcode.utils.ListNode;
import letcode.utils.TestCaseInputUtils;

import java.util.Stack;

/**
 * 给你一个链表的头节点 head 。  移除每个右侧有一个更大数值的节点。  返回修改后链表的头节点 head 。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/1/5 09:03
 */
public class _2478 {

    public ListNode removeNodes1(ListNode head) {
        // 用个单调栈处理就行了
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            while (!stack.empty() && stack.peek().val < head.val) {
                stack.pop();
            }
            stack.push(head);
            head = head.next;
        }
        head = new ListNode();
        while (!stack.empty()) {
            ListNode pop = stack.pop();
            pop.next = head.next;
            head.next = pop;
        }
        return head.next;
    }

    public ListNode removeNodes(ListNode head) {
        // 递归实现
        // 因为左边的节点不会影响右边的节点
        // 所以计算左边节点和右边剩余节点 再比较是可以直接续借上去 还是只保留更大的右边节点
        if (head == null) {
            return null;
        }
        head.next = removeNodes(head.next);
        if (head.next != null && head.val < head.next.val) {
            return head.next;
        }
        return head;
    }


    /**
     * 输入：head = [5,2,13,3,8]
     * 输出：[13,8]
     * 解释：需要移除的节点是 5 ，2 和 3 。
     * - 节点 13 在节点 5 右侧。
     * - 节点 13 在节点 2 右侧。
     * - 节点 8 在节点 3 右侧。
     * 示例 2：
     *
     * 输入：head = [1,1,1,1]
     * 输出：[1,1,1,1]
     * 解释：每个节点的值都是 1 ，所以没有需要移除的节点。
     * @param args
     */
    public static void main(String[] args) {
        new _2478().removeNodes(
                new ListNode(
                        TestCaseInputUtils.getIntArr("[5,2,13,3,8]")
                )
        ).display();
    }



}
