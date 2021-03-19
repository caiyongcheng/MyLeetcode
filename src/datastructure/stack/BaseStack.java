package datastructure.stack;

import datastructure.node.Node;

/**
 * @program: MyLeetcode
 * @description: 基本栈实现
 * @packagename: datastructure.stack
 * @author: 6JSh5rC456iL
 * @date: 2021-03-19 09:12
 **/
public class BaseStack<T> {

    private final Node<T> head;

    public BaseStack() {
        head = new Node<T>();
    }

    /**
     * 入栈
     * @param data 入栈元素
     */
    public void push(T data) {
        Node<T> top = new Node<>(data);
        top.setNext(head.getNext());
        head.setNext(top);
    }

    /**
     * 出栈
     * @return 原栈顶元素
     */
    public T pop() {
        Node<T> top = head.getNext();
        if (null == top) {
            return null;
        }
        head.setNext(top.getNext());
        return top.getData();
    }

    /**
     * 判栈空
     * @return true：空栈
     */
    public boolean isEmpty() {
        return null == head.getNext();
    }

    /**
     * 获取栈顶元素
     * @return 栈顶元素
     */
    public T top() {
        Node<T> top = head.getNext();
        if (null == top) {
            return null;
        }
        return top.getData();
    }

}
