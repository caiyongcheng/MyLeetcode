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
 * @since 2022/9/23 9:21
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


}
