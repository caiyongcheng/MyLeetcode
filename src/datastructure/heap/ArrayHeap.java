package datastructure.heap;

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
    private T[] data;


    /**
     * 堆类型
     */
    private int headType;


    @SuppressWarnings("unchecked")
    public ArrayHeap() {
        this.size = 0;
        this.data = (T[]) new Comparable[16];
        headType = HEAP_TYPE_MAX;
    }


    @SuppressWarnings("unchecked")
    public ArrayHeap(int headType) {
        this.size = 0;
        this.data = (T[]) new Comparable[16];
        this.headType = headType == -1 ? HEAP_TYPE_MIN : HEAP_TYPE_MAX;
    }

    /**
     * 向堆中添加元素
     *
     * @param data 需要向堆中添加的元素
     */
    @Override
    public void add(T data) {
        if (size >= this.data.length) {

        }
    }

    /**
     * 移除并返回堆顶元素
     *
     * @return 堆中的最大元素
     */
    @Override
    public T remove() {
        return null;
    }

    /**
     * 获取堆中元素数量
     *
     * @return 堆中元素数量
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * 堆是不是空的
     *
     * @return true:堆是空的
     */
    @Override
    public boolean empty() {
        return false;
    }
}
