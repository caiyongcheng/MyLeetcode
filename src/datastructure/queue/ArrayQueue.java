package datastructure.queue;

import datastructure.exception.QueueEmptyException;
import datastructure.node.Node;


/**
 * @program: MyLeetcode
 * @description: 底层使用数组作为节点的链表，可扩充,
 * @packagename: datastructure.queue
 * @author: 6JSh5rC456iL
 * @date: 2021-03-25 09:40
 **/
public class ArrayQueue<T> implements Queue<T>{


    private Node<T[]> head;


    private Node<T[]> tail;


    private int slotHead;


    private int slotTail;


    private final int slotSize;


    private int slotAmount;


    private int size;


    @SuppressWarnings("unchecked")
    public ArrayQueue(int slotSize) {
        if (slotSize < 0) {
            throw new IllegalArgumentException("slotSize must be more than zero");
        }
        this.slotSize = slotSize;
        head = new Node<>((T[]) new Object[slotSize]);
        tail = head;
        slotHead = 0;
        slotTail = 0;
        slotAmount = 1;
        size = 0;
    }

    /**
     * 入队操作
     *
     * @param data 入队元素
     * @return 入队结果 true：成功
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean enQueue(T data) {
        if (data == null) {
            throw new NullPointerException("element is null");
        }
        if (slotTail > slotSize - 1) {
            slotTail = 1;
            T[] slot = (T[]) new Object[slotSize];
            slot[0] = data;
            tail.setNext(new Node<>(slot));
            tail = tail.getNext();
            if (head == null) {
                head = tail;
            }
            ++slotAmount;
        } else {
            tail.getData()[slotTail++] = data;
        }
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
        if (slotHead >= slotSize - 1) {
            T frontData = head.getData()[slotHead];
            head = head.getNext();
            slotHead = 0;
            --size;
            return frontData;
        }
        --size;
        return head.getData()[slotHead++];
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
        return head.getData()[slotHead];
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

}
