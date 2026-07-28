package letcode.utils;

import java.util.List;

/**
 * 通用Node
 *
 * @author CaiYongcheng
 * @since 2021-09-24 09:07
 **/
public class Node {

    public int val;
    public Node prev;
    public Node next;
    public Node random;
    public Node child;

    public Node left;
    public Node right;

    public List<Node> children;

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node prev) {
        this.val = val;
        this.prev = prev;
    }

    public Node(int[] datas) {
        this.val = datas[0];
        Node now = this;
        for (int i = 1; i < datas.length; i++) {
            now.next = new Node(datas[i], now);
            now = now.next;
        }
    }

    public Node after(int index) {
        Node start = this;
        while (index > 0) {
            --index;
            start = start.next;
        }
        return start;
    }

    public Node before(int index) {
        Node start = this;
        while (index > 0) {
            --index;
            start = start.prev;
        }
        return start;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getRandom() {
        return random;
    }

    public void setRandom(Node random) {
        this.random = random;
    }

    public Node getChild() {
        return child;
    }

    public void setChild(Node child) {
        this.child = child;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        Node now = this;
        StringBuilder toString = new StringBuilder("[");
        while (now != null) {
            toString.append(now.val);
            if (now.child != null) {
                toString.append("->").append(now.child);
            }
            toString.append(",");
            now = now.next;
        }
        if (toString.charAt(toString.length() - 1) == ',') {
            toString.deleteCharAt(toString.length() - 1);
        }
        return toString.append("]").toString();
    }
}
