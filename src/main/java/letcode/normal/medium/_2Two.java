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

package letcode.medium;

import letcode.utils.ListNode;

/**
 * @program: StudyHTTP
 * @description: 给出两个非空 的链表用来表示两个非负的整数。
 * 其中，它们各自的位数是按照逆序的方式存储的，并且它们的每个节点只能存储一位数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0开头。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/add-two-numbers 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-06-15 16:51
 */


public class _2Two {
    /**
     * 示例：
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        /*
        0 m             0-m 0-n
        0 n
         */


        ListNode res = new ListNode(0);
        ListNode p = res;
        int i = 0;
        int j = 0;
        /*
        计算公共部分
         */
        while (l1 != null && l2 != null) {
            i = (l1.val + l2.val + j) % 10;
            p.next = new ListNode(i);
            j = (l1.val + l2.val + j) / 10;
            l1 = l1.next;
            l2 = l2.next;
            p = p.next;
        }
        while (l2 != null) {
            i = (l2.val + j) % 10;
            p.next = new ListNode(i);
            j = (l2.val + j) / 10;
            l2 = l2.next;
            p = p.next;
        }
        while (l1 != null) {
            i = (l1.val + j) % 10;
            p.next = new ListNode(i);
            j = (l1.val + j) / 10;
            l1 = l1.next;
            p = p.next;
        }
        if (j == 1) {
            p.next = new ListNode(1);
        }
        return res.next;
    }

    public static void printListNode(ListNode a) {
        if (a == null) {
            System.out.println();
        } else {
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