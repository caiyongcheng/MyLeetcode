package datastructure.queue;

import datastructure.exception.QueueEmptyException;
import datastructure.node.Node;
import datastructure.validate.ValidateQueue;

/**
 * @program: MyLeetcode
 * @description: 底层使用链表，可扩充的队列
 * @packagename: datastructure.queue
 * @author: 6JSh5rC456iL
 * @date: 2021-03-25 09:09
 **/
public class LinkedQueue<T> implements Queue<T>{



    private Node<T> head;


    private Node<T> tail;


    private int size;


    public LinkedQueue() {
        head = new Node<>();
        tail = head;
    }

    /**
     * 入队操作
     *
     * @param data 入队元素
     * @return 入队结果 true：成功
     */
    @Override
    public boolean enQueue(T data) {
        if (data == null) {
            throw new NullPointerException("enqueue data is null");
        }
        tail.setNext(new Node<>(data));
        tail = tail.getNext();
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
        if (head.getNext() == null) {
            throw QueueEmptyException.QUEUE_EMPTY_EXCEPTION;
        }
        T frontData = head.getNext().getData();
        head = head.getNext();
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
        if (head.getNext() == null) {
            throw QueueEmptyException.QUEUE_EMPTY_EXCEPTION;
        }
        return head.getNext().getData();
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
        System.out.println(ValidateQueue.validateQueue(new LinkedQueue<>(), new ImmutableQueue<>(100), 99));
    }


}
