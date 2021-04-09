package datastructure.heap;

/**
 * @program: MyLeetcode
 * @description: 基础堆接口
 * @packagename: datastructure.heap
 * @author: 6JSh5rC456iL
 * @date: 2021-04-08 14:40
 **/
public interface Heap<T extends Comparable<T>> {


    /**
     * 堆类型 大根堆
     */
    static final int HEAP_TYPE_MAX = 1;

    /**
     * 堆类型 小根堆
     */
    static final int HEAP_TYPE_MIN = -1;


    /**
     * 向堆中添加元素
     * @param data 需要向堆中添加的元素
     */
    void add(T data);

    /**
     * 移除并返回堆顶元素
     * @return 堆中的最大元素
     */
    T remove();


    /**
     * 获取堆顶元素
     * @return 堆顶元素
     */
    T top();


    /**
     * 获取堆中元素数量
     * @return 堆中元素数量
     */
    int size();


    /**
     * 堆是不是空的
     * @return true:堆是空的
     */
    boolean empty();


}
