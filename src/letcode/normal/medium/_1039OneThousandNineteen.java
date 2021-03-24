package letcode.normal.medium;
import letcode.utils.FormatPrintUtils;
import letcode.utils.ListNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @program: MyLeetcode
 * @description: 给出一个以头节点head作为第一个节点的链表。链表中的节点分别编号为：node_1, node_2, node_3, ... 。  
 * 每个节点都可能有下一个更大值（next larger value）：对于node_i，如果其next_larger(node_i)是node_j.val，
 * 那么就有j > i且node_j.val > node_i.val，而j是可能的选项中最小的那个。如果不存在这样的j，那么下一个更大值为0。  
 * 返回整数答案数组answer，其中answer[i] = next_larger(node_{i+1})。  注意：在下面的示例中，诸如 [2,1,5] 这样的输入（不是输出）是链表的序列化表示，其头节点的值为2，
 * 第二个节点值为 1，第三个节点值为5 。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/next-greater-node-in-linked-list 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @date: 2021-03-23 15:46
 **/
public class _1039OneThousandNineteen {

    public int[] nextLargerNodes(ListNode head) {
        ArrayList<Integer> list = new ArrayList<>(32);
        Stack<Integer> stack = new Stack<>();
        int[] ans;
        Integer integer;
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            integer = list.get(i);
            while (!stack.empty() && integer > list.get(stack.peek())) {
                ans[stack.pop()] = integer;
            }
            stack.push(i);
        }
        return ans;
    }

    /**
     * 示例 1：
     * 输入：[2,1,5]
     * 输出：[5,5,0]
     *
     * 示例 2：
     * 输入：[2,7,4,3,5]
     * 输出：[7,0,5,5,0]
     *
     * 示例 3：
     * 输入：[1,7,5,1,9,2,5,1]
     * 输出：[7,9,9,9,0,5,0,0]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/next-greater-node-in-linked-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        ListNode listNode = new ListNode(new int[]{1,7,5,1,9,2,5,1});
        System.out.println(FormatPrintUtils.formatArray(new _1039OneThousandNineteen().nextLargerNodes(listNode)));
    }

}
