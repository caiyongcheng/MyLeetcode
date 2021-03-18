package datastructure.node;

/**
 * @program: MyLeetcode
 * @description: 二叉树节点
 * @packagename: datastructure.node
 * @author: 6JSh5rC456iL
 * @date: 2021-03-15 17:21
 **/
public class BinaryTreeNode<T extends Comparable<T>> {

    T value;

    BinaryTreeNode<T> leftChild;

    BinaryTreeNode<T> rightChile;

    public BinaryTreeNode() {
    }

    public BinaryTreeNode(T value, BinaryTreeNode<T> leftChild, BinaryTreeNode<T> rightChile) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChile = rightChile;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public BinaryTreeNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryTreeNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public BinaryTreeNode<T> getRightChile() {
        return rightChile;
    }

    public void setRightChile(BinaryTreeNode<T> rightChile) {
        this.rightChile = rightChile;
    }
}
