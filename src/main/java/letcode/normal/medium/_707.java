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

/**
 * @author Caiyongcheng
 * @description 设计链表的实现。您可以选择使用单链表或双链表。
 * 单链表中的节点应该具有两个属性：val和next。val是当前节点的值，next是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性prev以指示链表中的上一个节点。
 * 假设链表中的所有节点都是 0-index 的。  在链表类中实现这些功能：  get(index)：获取链表中第index个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为val的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为val 的节点追加到链表的最后一个元素。 addAtIndex(index,val)：在链表中的第index个节点之前添加值为val 的节点。
 * 如果index等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 * deleteAtIndex(index)：如果索引index 有效，则删除链表中的第index 个节点。
 * 来源：力扣（LeetCode） 链接：https://leetcode.cn/problems/design-linked-list 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/9/23 9:21
 */
public class _707 {

    class Node {
        int val;
        Node pre;
        Node next;

        public Node() {
        }

        public Node(int val, Node pre, Node next) {
            this.val = val;
            this.pre = pre;
            this.next = next;
        }

    }

    private final Node head;
    private final Node tail;
    private int len;

    public _707() {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
        len = 0;
    }

    public int get(int index) {
        return index >= len ? -1 : getNode(index).val;
    }

    public void addAtHead(int val) {
        Node newFirst = new Node(val, head, head.next);
        head.next.pre = newFirst;
        head.next = newFirst;
        ++len;
    }

    public void addAtTail(int val) {
        Node newTail = new Node(val, tail.pre, tail);
        tail.pre.next = newTail;
        tail.pre = newTail;
        ++len;
    }

    public void addAtIndex(int index, int val) {
        if (index < 0) {
            addAtHead(val);
            return;
        }
        if (index == len) {
            addAtTail(val);
            return;
        }
        if (index < len) {
            Node node = getNode(index);
            Node newNode = new Node(val, node.pre, node);
            node.pre.next = newNode;
            node.pre = newNode;
            ++len;
        }
    }

    public void deleteAtIndex(int index) {
        if (index >= 0 && index < len) {
            Node node = getNode(index);
            node.pre.next = node.next;
            node.next.pre = node.pre;
            node.pre = null;
            node.next = null;
            --len;
        }
    }

    private Node getNode(int index) {
        Node current = head;
        if (index > len >> 1) {
            int tLen = len;
            while (tLen > index) {
                current = current.pre;
                --tLen;
            }
            current = tail;
            return current;
        }
        for (int i = 0; i <= index; i++) {
            current = current.next;
        }
        return current;
    }


    /**
     * 示例：
     * <p>
     * ["MyLinkedList","addAtHead","addAtHead","addAtHead","addAtIndex","deleteAtIndex","addAtHead","addAtTail","get","addAtHead","addAtIndex","addAtHead"]
     * [[],[7],[2],[1],[3,0],[2],[6],[4],[4],[4],[5,0],[6]]
     * <p>
     * ["MyLinkedList","addAtHead","addAtTail","deleteAtIndex","addAtTail","addAtIndex","addAtIndex","deleteAtIndex","deleteAtIndex","addAtTail","addAtIndex","addAtTail"]
     * [[],[7],[0],[1],[5],[1,1],[2,6],[2],[1],[7],[1,7],[6]]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/design-linked-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {

    }


}
