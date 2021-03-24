package datastructure.stack;

import datastructure.exception.StackEmptyException;
import datastructure.node.Node;

import java.util.List;

/**
 * @program: MyLeetcode
 * @description: 基本栈实现
 * @packagename: datastructure.stack
 * @author: 6JSh5rC456iL
 * @date: 2021-03-19 09:12
 **/
public class LinkedStack<T> implements Stack<T> {

    private final Node<T> head;

    private int size;

    public LinkedStack() {
        head = new Node<T>();
        size = 0;
    }


    /**
     * 元素入栈
     * @param data 入栈元素
     * @return 入栈结果
     */
    @Override
    public boolean push(T data) {
        Node<T> top = new Node<>(data);
        top.setNext(head.getNext());
        head.setNext(top);
        ++size;
        return true;
    }


    /**
     * 元素入栈
     * @param dataList 入栈列表
     */
    public void push(List<T> dataList) {
        for (T data : dataList) {
            Node<T> top = new Node<>(data);
            top.setNext(head.getNext());
            head.setNext(top);
        }
        size += dataList.size();
    }

    /**
     * 元素出栈，与top()区别在于top()不会移除栈顶元素
     * @return 栈顶元素
     */
    @Override
    public T pop() {
        if (size == 0) {
            throw new StackEmptyException("stack is empty");
        }
        Node<T> top = head.getNext();
        head.setNext(top.getNext());
        --size;
        return top.getData();
    }

    /**
     * 获取栈顶元素
     *
     * @return 栈顶元素
     */
    @Override
    public T top() {
        if (size == 0) {
            throw new StackEmptyException("stack is empty");
        }
        return head.getNext().getData();
    }

    /**
     * 返回栈是不是空的
     *
     * @return true：栈是空的
     */
    @Override
    public boolean empty() {
        return size == 0;
    }

    /**
     * 获取栈内元素数量
     *
     * @return 栈内元素数量
     */
    @Override
    public int size() {
        return size;
    }
}
