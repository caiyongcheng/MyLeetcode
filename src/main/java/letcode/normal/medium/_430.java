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
 * @since 2021-09-24 09:02
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

}
