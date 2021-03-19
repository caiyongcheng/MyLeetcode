package datastructure.node;

/**
 * @program: MyLeetcode
 * @description: 基本节点类
 * @packagename: datastructure.node
 * @author: 6JSh5rC456iL
 * @date: 2021-03-19 09:13
 **/
public class Node<T> {


    private T data;

    private Node<T> next;

    public Node() {
    }

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    public Node(Node<T> next) {
        this.next = next;
    }

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
