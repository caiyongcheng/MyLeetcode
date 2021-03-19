package datastructure.node;

import java.util.StringJoiner;

/**
 * @program: MyLeetcode
 * @description: 二叉平衡树节点
 * @packagename: datastructure.node
 * @author: 6JSh5rC456iL
 * @date: 2021-03-19 09:47
 **/
public class AVLBTreeNode<T extends Comparable<T>> extends BTreeNode<T> {

    private int height;

    public AVLBTreeNode(T value) {
        super(value);
        this.height = 1;
    }

    public AVLBTreeNode(T value, BTreeNode<T> leftChild, BTreeNode<T> rightChile) {
        super(value, leftChild, rightChile);
        this.height = 1;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AVLBTreeNode.class.getSimpleName() + "[", "]")
                .add("height=" + height)
                .add("value=" + value)
                .toString();
    }
}
