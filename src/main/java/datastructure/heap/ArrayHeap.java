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

package datastructure.heap;

import datastructure.exception.HeapEmptyException;
import datastructure.exception.HeapOverFlowException;

/**
 * @program: MyLeetcode
 * @description: 采用数组实现的堆
 * @packagename: datastructure.heap
 * @author: 6JSh5rC456iL
 * @since: 2021-04-08 14:45
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
