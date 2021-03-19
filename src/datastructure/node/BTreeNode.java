package datastructure.node;

/**
 * @program: MyLeetcode
 * @description: 二叉树节点
 * @packagename: datastructure.node
 * @author: 6JSh5rC456iL
 * @date: 2021-03-15 17:21
 **/
public class BTreeNode<T extends Comparable<T>> {

    T value;

    BTreeNode<T> leftChild;

    BTreeNode<T> rightChile;

    public BTreeNode() {
    }

    public BTreeNode(T value) {
        this.value = value;
    }

    public BTreeNode(T value, BTreeNode<T> leftChild, BTreeNode<T> rightChile) {
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

    public BTreeNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BTreeNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public BTreeNode<T> getRightChile() {
        return rightChile;
    }

    public void setRightChile(BTreeNode<T> rightChile) {
        this.rightChile = rightChile;
    }
}
