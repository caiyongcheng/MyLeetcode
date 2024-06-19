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

import letcode.utils.Node;

/**
 * 多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。
 * 这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。
 * 给你位于列表第一级的头节点，请你扁平化列表，使所有结点出现在单级双链表中。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-a-multilevel-doubly-linked-list 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-09-24 09:02
 **/
public class _430 {

    public Node flatten(Node head) {
        Node now = head;
        while (now != null) {
            if (now.child != null) {
                Node flatten = flatten(now.child);
                flatten.prev = now;
                while (flatten.next != null) {
                    flatten = flatten.next;
                }
                flatten.next = now.next;
                if (flatten.next != null) {
                    flatten.next.prev = flatten;
                }
                now.next = now.child;
                now.child = null;
                now = flatten.next;
            } else {
                now = now.next;
            }
        }
        return head;
    }

    /**
     * 输入：head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
     * 输出：[1,2,3,7,8,11,12,9,10,4,5,6]
     * 解释：
     * <p>
     * 输入的多级列表如下图所示：
     * 扁平化后的链表如下图：
     * <p>
     * 示例 2：
     * 输入：head = [1,2,null,3]
     * 输出：[1,3,2]
     * 解释：
     * 输入的多级列表如下图所示：
     * 1---2---NULL
     * |
     * 3---NULL
     * <p>
     * 示例 3：
     * 输入：head = []
     * 输出：[]
     * <p>
     * 如何表示测试用例中的多级链表？
     * <p>
     * 以 示例 1 为例：
     * 1---2---3---4---5---6--NULL
     * |
     * 7---8---9---10--NULL
     * |
     * 11--12--NULL
     * 序列化其中的每一级之后：
     * [1,2,3,4,5,6,null]
     * [7,8,9,10,null]
     * [11,12,null]
     * 为了将每一级都序列化到一起，我们需要每一级中添加值为 null 的元素，以表示没有节点连接到上一级的上级节点。
     * [1,2,3,4,5,6,null]
     * [null,null,7,8,9,10,null]
     * [null,11,12,null]
     * 合并所有序列化结果，并去除末尾的 null 。
     * [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/flatten-a-multilevel-doubly-linked-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        Node node = new Node(new int[]{1, 2, 3, 4, 5, 6});
        Node three = node.after(2);
        three.child = new Node(new int[]{7, 8, 9, 10});
        three.child.after(1).child = new Node(new int[]{11, 12});
        System.out.println(node);
        System.out.println(new _430().flatten(node).toString());
        node = null;
        System.out.println(node == null);
        System.out.println(new _430().flatten(node) == null);
        node = new Node(new int[]{1, 2});
        node.child = new Node(3);
        System.out.println(node);
        System.out.println(new _430().flatten(node));
        node = new Node(1);
        node.child = new Node(2);
        node.child.child = new Node(3);
        System.out.println(node);
        System.out.println(new _430().flatten(node));
    }


}
