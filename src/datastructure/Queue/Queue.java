package datastructure.Queue;

/**
 * @program: MyLeetcode
 * @description: 基本队列接口
 * @packagename: datastructure.Queue
 * @author: 6JSh5rC456iL
 * @date: 2021-03-24 10:52
 **/
public interface Queue<T> {


    /**
     * 入队操作
     * @param data 入队元素
     * @return 入队结果 true：成功
     */
    boolean enQueue(T data);


    /**
     * 出队操作
     * @return 原队头元素
     */
    T deQueue();


    /**
     * 获取队头元素，该方法不会导致出队
     * @return 队头元素
     */
    T frontData();


    /**
     * 队空判断
     * @return true:空队
     */
    boolean empty();


    /**
     * 获取队内元素数量
     * @return 队内元素数量
     */
    int size();


}
