package datastructure.heap;

import datastructure.exception.HeapEmptyException;
import datastructure.exception.HeapOverFlowException;

import java.util.Arrays;

/**
 * @program: MyLeetcode
 * @description: 采用数组实现的堆
 * @packagename: datastructure.heap
 * @author: 6JSh5rC456iL
 * @date: 2021-04-08 14:45
 **/
public class ArrayHeap<T extends Comparable<T>> implements Heap<T>{


    /**
     * 堆中元素数量
     */
    private int size;


    /**
     * 堆元素存储的实现
     */
    private final T[] values;


    /**
     * 堆类型
     */
    private final int headType;


    @SuppressWarnings("unchecked")
    public ArrayHeap() {
        this.size = 0;
        this.values = (T[]) new Comparable[16];
        headType = HEAP_TYPE_MAX;
    }


    @SuppressWarnings("unchecked")
    public ArrayHeap(int headType) {
        this.size = 0;
        this.values = (T[]) new Comparable[16];
        this.headType = headType == -1 ? HEAP_TYPE_MIN : HEAP_TYPE_MAX;
    }

    /**
     * 向堆中添加元素
     *
     * @param data 需要向堆中添加的元素
     */
    @Override
    public void add(T data) {
        if (size >= this.values.length) {
            throw HeapOverFlowException.HEAP_OVER_FLOW_EXCEPTION;
        }
        values[size] = data;
        int child = size;
        int parent;
        T temporary;
        while (true) {
            parent = (child - 1) >> 1;
            if (parent < 0 || parent == child) {
                break;
            }
            if (values[parent].compareTo(values[child]) * headType == -1) {
                temporary = values[parent];
                values[parent] = values[child];
                values[child] = temporary;
            }
            child = parent;
        }
        ++size;
    }

    /**
     * 移除并返回堆顶元素
     *
     * @return 堆中的最大元素
     */
    @Override
    public T remove() {
        if (size <= 0) {
            throw HeapEmptyException.HEAP_EMPTY_EXCEPTION;
        }
        T heapTopValue = values[0];
        values[0] = values[--size];
        int parent = 0;
        int rightChild;
        int leftChild;
        int pl, pr, lr;
        T temporary;
        int temporaryIndex;
        while (true) {
            leftChild = (parent << 1) + 1;
            if (leftChild >= size) {
                break;
            }
            rightChild = leftChild + 1;
            pl = values[parent].compareTo(values[leftChild]) * headType > -1 ? 1 : -1;
            pr = rightChild >= size ? 1 : values[parent].compareTo(values[rightChild]) * headType > - 1 ? 1 : -1;
            if (pl + pr == 2) {
                break;
            } else if (pl == -1
                    && (rightChild >= size || values[leftChild].compareTo(values[rightChild]) * headType > -1)) {
                temporaryIndex = leftChild;
            } else {
                temporaryIndex = rightChild;
            }
            temporary = values[temporaryIndex];
            values[temporaryIndex] = values[parent];
            values[parent] = temporary;
            parent = temporaryIndex;
        }
        return heapTopValue;
    }

    /**
     * 获取堆顶元素
     *
     * @return 堆顶元素
     */
    @Override
    public T top() {
        if (size <= 0) {
            throw HeapEmptyException.HEAP_EMPTY_EXCEPTION;
        }
        return values[0];
    }

    /**
     * 获取堆中元素数量
     *
     * @return 堆中元素数量
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 堆是不是空的
     *
     * @return true:堆是空的
     */
    @Override
    public boolean empty() {
        return size == 0;
    }


    public static void main(String[] args) {

    }
}
