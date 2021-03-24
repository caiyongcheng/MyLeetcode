package datastructure.stack;

import datastructure.exception.StackEmptyException;
import datastructure.node.Node;


/**
 * @program: MyLeetcode
 * @description: 底层使用数组的可扩充栈,准确来说每个数组是一个插槽，插槽使用链表形式扩充
 * @packagename: datastructure.stack
 * @author: 6JSh5rC456iL
 * @date: 2021-03-23 17:41
 **/
public class ArrayStack<T> implements Stack<T>{


    private Node<T[]> head;


    private Node<T[]> actualHead;


    private int top;


    private int slotSize;


    private int slotAmount;


    @SuppressWarnings("unchecked")
    public ArrayStack(int slotSize) {
        if (slotSize < 0) {
            throw new IllegalArgumentException("slotSize must be more than zero");
        }
        this.slotSize = slotSize;
        head = new Node<>();
        actualHead = new Node<>();
        actualHead.setData((T[]) new Comparable[slotSize]);
        head.setNext(actualHead);
        top = slotSize;
        slotAmount = 1;
    }

    /**
     * 元素入栈
     * @param data 入栈元素
     * @return 入栈结果
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean push(T data) {
        if (data == null) {
            throw new NullPointerException("element is null");
        }
        if (top <= 0) {
            T[] tArray = (T[]) new Comparable[slotSize];
            tArray[slotSize - 1] = data;
            Node<T[]> node = new Node<>(tArray, actualHead);
            head.setNext(node);
            actualHead = node;
            top = slotSize - 1;
            ++slotAmount;
        } else {
            actualHead.getData()[--top] = data;
        }
        return true;
    }

    /**
     * 元素出栈，与top()区别在于top()不会移除栈顶元素
     *
     * @return 栈顶元素
     */
    @Override
    public T pop() {
        if (top == slotSize) {
            throw new StackEmptyException("stack is empty");
        }
        T topData = actualHead.getData()[top];
        if (top == slotSize - 1) {
            if (actualHead.getNext() != null) {
                actualHead = actualHead.getNext();
                head.setNext(actualHead);
                top = 0;
                --slotAmount;
            } else {
                top = slotSize;
            }
        } else {
            ++top;
        }
        return topData;
    }

    /**
     * 获取栈顶元素
     *
     * @return 栈顶元素
     */
    @Override
    public T top() {
        if (top == slotSize) {
            throw new StackEmptyException("stack is empty");
        }
        return actualHead.getData()[top];
    }

    /**
     * 返回栈是不是空的
     *
     * @return true：栈是空的
     */
    @Override
    public boolean empty() {
        return top == slotSize;
    }

    /**
     * 获取栈内元素数量
     * @return 栈内元素数量
     */
    @Override
    public int size() {
        return slotAmount * slotSize - top;
    }

}
