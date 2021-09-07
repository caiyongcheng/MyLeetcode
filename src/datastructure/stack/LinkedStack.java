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

package datastructure.stack;

import datastructure.exception.StackEmptyException;
import datastructure.node.Node;

import java.util.List;

/**
 * @program: MyLeetcode
 * @description: 基本栈实现
 * @packagename: datastructure.stack
 * @author: 6JSh5rC456iL
 * @date: 2021-03-19 09:12
 **/
public class LinkedStack<T> implements Stack<T> {

    private final Node<T> head;

    private int size;

    public LinkedStack() {
        head = new Node<T>();
        size = 0;
    }


    /**
     * 元素入栈
     * @param data 入栈元素
     * @return 入栈结果
     */
    @Override
    public boolean push(T data) {
        Node<T> top = new Node<>(data);
        top.setNext(head.getNext());
        head.setNext(top);
        ++size;
        return true;
    }


    /**
     * 元素入栈
     * @param dataList 入栈列表
     */
    public void push(List<T> dataList) {
        for (T data : dataList) {
            Node<T> top = new Node<>(data);
            top.setNext(head.getNext());
            head.setNext(top);
        }
        size += dataList.size();
    }

    /**
     * 元素出栈，与top()区别在于top()不会移除栈顶元素
     * @return 栈顶元素
     */
    @Override
    public T pop() {
        if (size == 0) {
            throw new StackEmptyException("stack is empty");
        }
        Node<T> top = head.getNext();
        head.setNext(top.getNext());
        --size;
        return top.getData();
    }

    /**
     * 获取栈顶元素
     *
     * @return 栈顶元素
     */
    @Override
    public T top() {
        if (size == 0) {
            throw new StackEmptyException("stack is empty");
        }
        return head.getNext().getData();
    }

    /**
     * 返回栈是不是空的
     *
     * @return true：栈是空的
     */
    @Override
    public boolean empty() {
        return size == 0;
    }

    /**
     * 获取栈内元素数量
     *
     * @return 栈内元素数量
     */
    @Override
    public int size() {
        return size;
    }
}
