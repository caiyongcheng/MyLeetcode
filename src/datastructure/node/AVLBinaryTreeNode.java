package datastructure.node;

/**
 * @program: MyLeetcode
 * @description: 二叉平衡树节点
 * @packagename: datastructure.node
 * @author: 6JSh5rC456iL
 * @date: 2021-03-15 17:23
 **/
public class AVLBinaryTreeNode<T extends Comparable<T>> extends BinaryTreeNode<T>{

    private int balance;

    public AVLBinaryTreeNode(int balance) {
        this.balance = balance;
    }

    public AVLBinaryTreeNode(T value, BinaryTreeNode<T> leftChild, BinaryTreeNode<T> rightChile, int balance) {
        super(value, leftChild, rightChile);
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
