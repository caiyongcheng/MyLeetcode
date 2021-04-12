package datastructure.tree;

import datastructure.node.BTreeNode;
import datastructure.node.RBTreeNode;
import datastructure.stack.ArrayStack;
import datastructure.stack.Stack;

/**
 * @program: MyLeetcode
 * @description: 红黑树实现
 * @packagename: datastructure.tree
 * @author: 6JSh5rC456iL
 * @date: 2021-04-12 14:45
 **/
public class RBTree<T extends Comparable<T>> {

    private RBTreeNode<T> root;

    private static final RBTreeNode NIL = new RBTreeNode<>(null, RBTreeNode.RBTREE_NOTE_COLOR_BLACK);


    public RBTree() {
    }

    public RBTree(T data) {
        this.root = new RBTreeNode<>(data);
        root.setColor(RBTreeNode.RBTREE_NOTE_COLOR_BLACK);
    }



    /**
     * 向当前二叉树新增数据
     * @param value 需要新增的数据
     * @return 暂定
     */
    public boolean addData(T value) {

        return true;
    }


    /**
     * 从树中删除给定值
     * @param value 需要删除的给定值
     * @return true 删除成功 false 树为空或删除值不存在与树中
     */
    public boolean removeData(T value) {

        return true;
    }

    /**
     * 获取当前二叉树最小值
     * @return 当前二叉树最小值
     */
    public T minValue() {
        BTreeNode<T> parent = this.root;
        while (parent.getLeftChild() != null) {
            parent = parent.getLeftChild();
        }
        return parent.getValue();
    }


    /**
     * 获取当前二叉树最大值
     * @return 当前二叉树最大值
     */
    public T maxValue() {
        BTreeNode<T> parent = this.root;
        while (parent.getRightChild() != null) {
            parent = parent.getRightChild();
        }
        return parent.getValue();
    }


    /**
     * 判断当前二叉树是否存在给定值
     * @param data 给定值
     * @return true：存在， false：不存在或给定值为空或根节点为空
     */
    public boolean exist(T data) {
        if (data == null) {
            return false;
        }
        BTreeNode<T> parent = this.root;
        int compareRes;
        while (parent != null) {
            compareRes = data.compareTo(parent.getValue());
            if (compareRes == 0) {
                return true;
            }
            if (compareRes == 1) {
                parent = parent.getRightChild();
            } else {
                parent = parent.getLeftChild();
            }
        }
        return false;
    }


    /**
     * 获取当前二叉树高度
     * 在AVLTree中，树节点会保存树高，这是考虑到avl树以左右子树高作为平衡判断，
     * 保存树高能减少判断时间开销。
     * 在RBTree中，平衡依靠树的定义。height()方法调用次数一般不多，所以不需要浪费
     * 空间保存树高。
     * @return 当前二叉树高度
     */
    public int height() {
        Stack<BTreeNode<T>> treeNodeStack = new ArrayStack<>(20);
        ArrayStack<Integer> heightArrayStack = new ArrayStack<Integer>(20);
        BTreeNode<T> current;
        int currentHeight;
        int treeHeight = 0;
        treeNodeStack.push(root);
        heightArrayStack.push(1);
        while (!treeNodeStack.empty()) {
            current = treeNodeStack.pop();
            currentHeight = heightArrayStack.pop();
            if (current.getLeftChild() != null) {
                treeNodeStack.push(current.getLeftChild());
                heightArrayStack.push(currentHeight + 1);
            }
            if (current.getRightChild() != null) {
                treeNodeStack.push(current.getRightChild());
                heightArrayStack.push(currentHeight + 1);
            }
        }
        while (!heightArrayStack.empty()) {
            if (treeHeight < heightArrayStack.top()) {
                treeHeight = heightArrayStack.pop();
            }
        }
        return treeHeight;
    }


    /**
     * 向指定树插入值
     * @param root 树的根节点
     * @param data 要插入的值
     * @param <T> 数据类型 需要实现comparable接口
     * @return 插入后的新节点
     */
    private static<T extends Comparable<T>> RBTreeNode<T> add(RBTreeNode<T> root, T data) {
        if (data == null) {
            throw new NullPointerException();
        }
        if (root == null) {
            root = new RBTreeNode<T>(data, NIL, NIL, RBTreeNode.RBTREE_NOTE_COLOR_BLACK);
            return root;
        }
        //正常的BST插入
        RBTreeNode<T> parent = root;
        RBTreeNode<T> child = root;
        while (child != null) {
            parent = child;
            if (data.compareTo(parent.getValue()) < 0) {
                child = (RBTreeNode<T>)parent.getLeftChild();
            } else {
                child = (RBTreeNode<T>)parent.getRightChild();
            }
        }
        if (data.compareTo(parent.getValue()) < 0) {
            parent.setLeftChild(new RBTreeNode<>(data, RBTreeNode.RBTREE_NOTE_COLOR_RED, parent));
        } else {
            parent.setRightChild(new RBTreeNode<>(data, RBTreeNode.RBTREE_NOTE_COLOR_RED ,parent));
        }
        //染色修复
        if (parent.getColor() == RBTreeNode.RBTREE_NOTE_COLOR_BLACK) {
            return parent;
        }
        return parent;
    }


    /**
     * 左旋
     * @param root 树根节点
     * @param <T> 数据类型 需要实现comparable接口
     * @return 旋转后的根节点
     */
    private static<T extends Comparable<T>> RBTreeNode<T> LSpin(RBTreeNode<T> root) {
        BTreeNode<T> rightChild = root.getRightChild();
        root.setRightChild(rightChild.getRightChild() == null ? rightChild.getLeftChild() : rightChild.getRightChild());
        rightChild.setLeftChild(root);
        return (RBTreeNode<T>) rightChild;
    }


    /**
     * 右旋
     * @param root 树根节点
     * @param <T> 数据类型 需要实现comparable接口
     * @return 旋转后的根节点
     */
    private static<T extends Comparable<T>> RBTreeNode<T> RSpin(RBTreeNode<T> root) {
        BTreeNode<T> leftChild = root.getLeftChild();
        root.setLeftChild(leftChild.getLeftChild() == null ? leftChild.getRightChild() : leftChild.getLeftChild());
        leftChild.setRightChild(root);
        return (RBTreeNode<T>) leftChild;
    }

}
