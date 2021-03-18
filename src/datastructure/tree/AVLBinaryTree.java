package datastructure.tree;

import datastructure.node.AVLBinaryTreeNode;
import datastructure.node.BinaryTreeNode;

/**
 * @program: MyLeetcode
 * @description: AVL平衡树
 * @packagename: datastructure.node
 * @author: 6JSh5rC456iL
 * @date: 2021-03-15 17:26
 **/
public class AVLBinaryTree<T extends Comparable<T>>{

    private AVLBinaryTreeNode<T> root;

    public AVLBinaryTreeNode<T> insert(T data) {
        return null;
    }

    /**
     * RR旋转
     * @param unBlanceNode 不平衡节点
     * @param <T> 数据类型 需要实现comparable接口
     * @return 根节点
     */
    private static<T extends Comparable<T>> BinaryTreeNode<T> RRSpin(BinaryTreeNode<T> unBlanceNode) {
        BinaryTreeNode<T> leftChild = unBlanceNode.getLeftChild();
        unBlanceNode.setLeftChild(leftChild.getRightChile());
        leftChild.setRightChile(unBlanceNode);
        return leftChild;
    }


    /**
     * LL旋转
     * @param unBlanceNode 不平衡节点
     * @param <T> 数据类型 需要实现comparable接口
     * @return 根节点
     */
    private static<T extends Comparable<T>> BinaryTreeNode<T> LLSpin(BinaryTreeNode<T> unBlanceNode) {
        BinaryTreeNode<T> rightChile = unBlanceNode.getRightChile();
        unBlanceNode.setLeftChild(rightChile.getLeftChild());
        rightChile.setLeftChild(unBlanceNode);
        return rightChile;
    }

    /**
     * RL旋转
     * @param unBlanceNode 不平衡节点
     * @param <T> 数据类型 需要实现comparable接口
     * @return 根节点
     */
    private static<T extends Comparable<T>> BinaryTreeNode<T> RLSpin(AVLBinaryTreeNode<T> unBlanceNode) {
        BinaryTreeNode<T> rightTree = (BinaryTreeNode<T>) RRSpin(unBlanceNode.getRightChile());
        return null;
    }


    /**
     * LR旋转
     * @param unBlanceNode 不平衡节点
     * @param <T> 数据类型 需要实现comparable接口
     * @return 根节点
     */
    private static<T extends Comparable<T>> BinaryTreeNode<T> LRSpin(AVLBinaryTreeNode<T> unBlanceNode) {
        //BinaryTreeNode<T> rightTree = (BinaryTreeNode<T>) LL(unBlanceNode.getRightChile());
        return null;
    }



}
