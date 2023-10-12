/*
 * 版权所有（c）<2021><蔡永程>
 *
 * 反996许可证版本1.0
 *
 * 在符合下列条件的情况下，特此免费向任何得到本授权作品的副本（包括源代码、文件和/或相关内容，以
 * 下统称为“授权作品”）的个人和法人实体授权：被授权个人或法人实体有权以任何目的处置授权作品，包括
 * 但不限于使用、复制，修改，衍生利用、散布，发布和再许可：
 *
 * 1. 个人或法人实体必须在许可作品的每个再散布或衍生副本上包含以上版权声明和本许可证，不得自行修
 * 改。
 * 2. 个人或法人实体必须严格遵守与个人实际所在地或个人出生地或归化地、或法人实体注册地或经营地（
 * 以较严格者为准）的司法管辖区所有适用的与劳动和就业相关法律、法规、规则和标准。如果该司法管辖区
 * 没有此类法律、法规、规章和标准或其法律、法规、规章和标准不可执行，则个人或法人实体必须遵守国际
 * 劳工标准的核心公约。
 * 3. 个人或法人不得以任何方式诱导、暗示或强迫其全职或兼职员工或其独立承包人以口头或书面形式同意
 * 直接或间接限制、削弱或放弃其所拥有的，受相关与劳动和就业有关的法律、法规、规则和标准保护的权利
 * 或补救措施，无论该等书面或口头协议是否被该司法管辖区的法律所承认，该等个人或法人实体也不得以任
 * 何方法限制其雇员或独立承包人向版权持有人或监督许可证合规情况的有关当局报告或投诉上述违反许可证
 * 的行为的权利。
 *
 * 该授权作品是"按原样"提供，不做任何明示或暗示的保证，包括但不限于对适销性、特定用途适用性和非侵
 * 权性的保证。在任何情况下，无论是在合同诉讼、侵权诉讼或其他诉讼中，版权持有人均不承担因本软件或
 * 本软件的使用或其他交易而产生、引起或与之相关的任何索赔、损害或其他责任。
 */

package datastructure.queue;

import datastructure.exception.QueueEmptyException;
import datastructure.exception.QueueOverFlowException;

/**
 * @program: MyLeetcode
 * @description: 创建后容量不可变的队列
 * @packagename: datastructure.queue
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

}
