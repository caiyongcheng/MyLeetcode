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

/**
 * @author Caiyongcheng
 * @description 给定一个单链表 L 的头节点 head ，
 * 单链表 L 表示为：  L0 → L1 → … → Ln - 1 → Ln 请将其重新排列后变为：  L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * @since 2023/7/31 8:59
 */
public class _143 {

    public void reorderList(ListNode head) {
        /*
        按照规则取链表后半部分取出来 然后按规则插入即可
         */

        /*
         pre每次跳一个节点 lastNode跳两个节点
         假设节点从1开始编号 最后一个节点为n
         当n为偶数时 中间节点为 n/2、 n/2+1 preNode跳(n/2)步到达n/2+1 此时lastNode位于 1 + (n/2) * 2 = n + 1
         当n为奇数时 中间节点为 (n+1)/2 preNode跳(n+1)/2-1步到达(n+1)/2 此时lastNode位于 1 + ((n+1)/2-1) * 2 = n
         总上所述
         当lastNode==null（位于n+1处）时，preNode位于后半部分的开始 endNode为preNode的前序节点
         */
        ListNode preNode = head;
        ListNode lastNode = head;
        ListNode endNode = head;
        while (lastNode != null) {
            endNode = preNode;
            preNode = preNode.next;
            lastNode = lastNode.next;
            if (lastNode != null) {
                lastNode = lastNode.next;
            }
        }
        endNode.next = null;

        /*
        将后半部分反转
         */
        ListNode tmpNode = new ListNode(0);
        ListNode tmpNode1;
        while (preNode != null) {
            tmpNode1 = preNode.next;
            preNode.next = tmpNode.next;
            tmpNode.next = preNode;
            preNode = tmpNode1;
        }
        preNode = tmpNode.next;

        /*
        开始插入
         */
        ListNode startNode = head;
        while (preNode != null) {
            tmpNode = preNode.next;
            preNode.next = startNode.next;
            startNode.next = preNode;
            startNode = preNode.next;
            preNode = tmpNode;
        }


    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(new int[]{1, 2, 3, 4});
        new _143().reorderList(listNode);
        listNode.display();
    }

}
