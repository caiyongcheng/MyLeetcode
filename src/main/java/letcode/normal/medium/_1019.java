/*
 * 版权所有（c）<2021><蔡永程>
 *
 * 反996许可证版本1.0
 *
 * 在符合下列条件的情况下，特此免费向任何得到本授权作品的副本（包括源代码、文件和/或相关内容，以
 * 下统称为“授权作品”）的个人和法人实体授权：被授权个人或法人实体有权以任何目的处置授权作品，包括
 * 但不限于使用、复制，修改，衍生利用、散布，发布和再许可：
 *
 * 1. 个人或法人实体必须在许可作品的每个再散布或衍生副本上包含以上版权声明和本许可证，不得自行修
 * 改。
 * 2. 个人或法人实体必须严格遵守与个人实际所在地或个人出生地或归化地、或法人实体注册地或经营地（
 * 以较严格者为准）的司法管辖区所有适用的与劳动和就业相关法律、法规、规则和标准。如果该司法管辖区
 * 没有此类法律、法规、规章和标准或其法律、法规、规章和标准不可执行，则个人或法人实体必须遵守国际
 * 劳工标准的核心公约。
 * 3. 个人或法人不得以任何方式诱导、暗示或强迫其全职或兼职员工或其独立承包人以口头或书面形式同意
 * 直接或间接限制、削弱或放弃其所拥有的，受相关与劳动和就业有关的法律、法规、规则和标准保护的权利
 * 或补救措施，无论该等书面或口头协议是否被该司法管辖区的法律所承认，该等个人或法人实体也不得以任
 * 何方法限制其雇员或独立承包人向版权持有人或监督许可证合规情况的有关当局报告或投诉上述违反许可证
 * 的行为的权利。
 *
 * 该授权作品是"按原样"提供，不做任何明示或暗示的保证，包括但不限于对适销性、特定用途适用性和非侵
 * 权性的保证。在任何情况下，无论是在合同诉讼、侵权诉讼或其他诉讼中，版权持有人均不承担因本软件或
 * 本软件的使用或其他交易而产生、引起或与之相关的任何索赔、损害或其他责任。
 */

package letcode.normal.medium;

import letcode.utils.ListNode;
import letcode.utils.TestCaseOutputUtils;

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
 * @since: 2021-03-23 15:46
 **/
public class _1019 {

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
        System.out.println(TestCaseOutputUtils.formatArray(new _1019().nextLargerNodes(listNode)));
    }

}
