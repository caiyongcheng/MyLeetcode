package datastructure.Queue;

import datastructure.exception.QueueEmptyException;
import datastructure.exception.QueueOverFlowException;

/**
 * @program: MyLeetcode
 * @description: 创建后容量不可变的队列
 * @packagename: datastructure.Queue
 * @author: 6JSh5rC456iL
 * @date: 2021-03-24 10:57
 **/
public class ImmutableQueue<T> implements Queue<T>{

    private T[] data;

    private int front;

    private int tail;

    private int capacity;

    private int size;


    @SuppressWarnings("unchecked")
    public ImmutableQueue(int capacity) {
        this.capacity = capacity;
        data = (T[]) new Object[capacity];
        front = 0;
        tail = -1;
        size = 0;
    }

    /**
     * 入队操作
     *
     * @param data 入队元素
     * @return 入队结果 true：成功
     */
    @Override
    public boolean enQueue(T data) {
        if (null == data) {
            throw new NullPointerException("enQueue data is null");
        }
        if (size >= capacity) {
            throw QueueOverFlowException.QUEUE_OVER_FLOW_EXCEPTION;
        }
        tail = (tail+1) % capacity;
        this.data[tail] = data;
        ++size;
        return true;
    }

    /**
     * 出队操作
     *
     * @return 原队头元素
     */
    @Override
    public T deQueue() {
        if (size == 0) {
            throw QueueEmptyException.QUEUE_EMPTY_EXCEPTION;
        }
        T frontData = data[front];
        front = (front + capacity + 1) % capacity;
        --size;
        return frontData;
    }

    /**
     * 获取队头元素，该方法不会导致出队
     *
     * @return 队头元素
     */
    @Override
    public T frontData() {
        if (size == 0) {
            throw QueueEmptyException.QUEUE_EMPTY_EXCEPTION;
        }
        return data[front];
    }

    /**
     * 队空判断
     *
     * @return true:空队
     */
    @Override
    public boolean empty() {
        return size == 0;
    }

    /**
     * 获取队内元素数量
     *
     * @return 队内元素数量
     */
    @Override
    public int size() {
        return size;
    }


    public static void main(String[] args) {

    }
}
