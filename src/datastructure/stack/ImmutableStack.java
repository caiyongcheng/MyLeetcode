package datastructure.stack;

import datastructure.exception.StackEmptyException;
import datastructure.exception.StackOverFlowException;

/**
 * @program: MyLeetcode
 * @description: 底层使用数组的栈,栈容量固定
 * @packagename: datastructure.stack
 * @author: 6JSh5rC456iL
 * @date: 2021-03-23 16:48
 **/
public class ImmutableStack<T> implements Stack<T> {


    private final T[] data;


    private int top;


    private final int maxIndex;


    @SuppressWarnings("unchecked")
    public ImmutableStack(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity must be greater than 0");
        }
        data = (T[])new Object[capacity];
        top = -1;
        this.maxIndex = capacity - 1;
    }

    /**
     * 元素入栈
     *
     * @param data 入栈元素
     * @return 入栈结果
     */
    @Override
    public boolean push(T data) {
        if (null == data) {
            throw new NullPointerException("push data is null");
        }
        if (top == maxIndex) {
            throw new StackOverFlowException("stack is over flow");
        }
        this.data[++top] = data;
        return true;
    }

    /**
     * 元素出栈，与top()区别在于top()不会移除栈顶元素
     *
     * @return 栈顶元素
     */
    @Override
    public T pop() {
        if (top == -1) {
            throw new StackEmptyException("stack is empty");
        }
        return data[top--];
    }

    /**
     * 获取栈顶元素
     *
     * @return 栈顶元素
     */
    @Override
    public T top() {
        if (top == -1) {
            throw new StackEmptyException("stack is empty");
        }
        return data[top];
    }

    /**
     * 返回栈是不是空的
     *
     * @return true：栈是空的
     */
    @Override
    public boolean empty() {
        return top == -1;
    }


    /**
     * 获取栈内元素数量
     *
     * @return 栈内元素数量
     */
    @Override
    public int size() {
        return top+1;
    }

    /**
     * 获取栈的最大容量
     * @return 栈的最大容量
     */
    public int getStackCapacity() {
        return maxIndex + 1;
    }
}
