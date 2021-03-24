package datastructure.stack;

/**
 * @program: MyLeetcode
 * @description: 基础栈接口
 * @packagename: datastructure.stack
 * @author: 6JSh5rC456iL
 * @date: 2021-03-23 16:43
 **/
public interface Stack<T> {

    /**
     * 元素入栈
     * @param data 入栈元素
     * @return 入栈结果
     */
    boolean push(T data);

    /**
     * 元素出栈，与top()区别在于top()不会移除栈顶元素
     * @return 栈顶元素
     */
    T pop();

    /**
     * 获取栈顶元素
     * @return 栈顶元素
     */
    T top();

    /**
     * 返回栈是不是空的
     * @return true：栈是空的
     */
    boolean empty();


    /**
     * 获取栈内元素数量
     * @return 栈内元素数量
     */
    int size();

}
