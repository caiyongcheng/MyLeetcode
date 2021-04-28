package datastructure.tree;

import datastructure.node.BTreeNode;
import datastructure.node.RBTreeNode;
import datastructure.stack.ArrayStack;
import datastructure.stack.Stack;
import datastructure.utils.FormatPrintUtils;

/**
 * @program: MyLeetcode
 * @description: 红黑树实现
 * @author: 6JSh5rC456iL
 * @date 2021-04-12 14:45
 **/
public class RBTree<T extends Comparable<T>> {

    private RBTreeNode<T> root;

    private final RBTreeNode<T> NIL = new RBTreeNode<>(null, RBTreeNode.RBTREE_NOTE_COLOR_BLACK);


    public RBTree() {
        root = NIL;
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
        RBTreeNode<T> newRoot = add(root, value, NIL);
        if (!isNotNull(newRoot, NIL)) {
            return false;
        }
        root = newRoot;
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
        if (!isNotNull(root, NIL)) {
            return null;
        }
        RBTreeNode<T> parent = root;
        while (isNotNull((RBTreeNode<T>)parent.getLeftChild(), NIL)) {
            parent = (RBTreeNode<T>) parent.getLeftChild();
        }
        return parent.getValue();
    }


    /**
     * 获取当前二叉树最大值
     * @return 当前二叉树最大值
     */
    public T maxValue() {
        if (!isNotNull(root, NIL)) {
            return null;
        }
        RBTreeNode<T> parent = root;
        while (isNotNull((RBTreeNode<T>)parent.getRightChild(), NIL)) {
            parent = (RBTreeNode<T>) parent.getRightChild();
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
        RBTreeNode<T> parent = this.root;
        int compareRes;
        while (!isNotNull(parent, NIL)) {
            compareRes = data.compareTo(parent.getValue());
            if (compareRes == 0) {
                return true;
            }
            if (compareRes == 1) {
                parent = (RBTreeNode<T>) parent.getRightChild();
            } else {
                parent = (RBTreeNode<T>) parent.getLeftChild();
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
        if (!isNotNull(root, NIL)) {
            return 0;
        }
        Stack<BTreeNode<T>> treeNodeStack = new ArrayStack<>(20);
        ArrayStack<Integer> heightArrayStack = new ArrayStack<>(20);
        BTreeNode<T> current;
        int currentHeight;
        int treeHeight = 0;
        treeNodeStack.push(root);
        heightArrayStack.push(1);
        while (!treeNodeStack.empty()) {
            current = treeNodeStack.pop();
            currentHeight = heightArrayStack.pop();
            if (treeHeight < currentHeight) {
                treeHeight = currentHeight;
            }
            if (isNotNull((RBTreeNode<T>) current.getLeftChild(), NIL)) {
                treeNodeStack.push(current.getLeftChild());
                heightArrayStack.push(currentHeight + 1);
            }
            if (isNotNull((RBTreeNode<T>) current.getRightChild(), NIL)) {
                treeNodeStack.push(current.getRightChild());
                heightArrayStack.push(currentHeight + 1);
            }
        }
        return treeHeight;
    }




    /**
     * 向指定树插入值
     * @param root 树的根节点
     * @param data 要插入的值
     * @param NIL 该树使用的NIL节点
     * @param <T> 数据类型 需要实现comparable接口
     * @return 新的根节点
     */
    private static<T extends Comparable<T>> RBTreeNode<T> add(RBTreeNode<T> root, T data, final RBTreeNode<T> NIL) {
        if (data == null) {
            throw new NullPointerException();
        }
        if (!isNotNull(root, NIL)) {
            root = new RBTreeNode<>(data, NIL, NIL, RBTreeNode.RBTREE_NOTE_COLOR_BLACK);
            root.setParent(null);
            return root;
        }
        //正常的BST插入
        RBTreeNode<T> parent = root;
        RBTreeNode<T> child = root;
        while (isNotNull(child, NIL)) {
            parent = child;
            if (data.compareTo(parent.getValue()) < 0) {
                child = (RBTreeNode<T>)parent.getLeftChild();
            } else {
                child = (RBTreeNode<T>)parent.getRightChild();
            }
        }
        child = new RBTreeNode<>(data, RBTreeNode.RBTREE_NOTE_COLOR_RED, parent);
        if (data.compareTo(parent.getValue()) < 0) {
            parent.setLeftChild(child);
        } else {
            parent.setRightChild(child);
        }
        //染色修复
        if (parent.getColor() == RBTreeNode.RBTREE_NOTE_COLOR_BLACK) {
            return repairColor(parent, NIL);
        }
        return root;
    }


    /**
     * 从指定树删除指定值
     * @param root 树的根节点
     * @param data 要插入的值
     * @param NIL 该树使用的NIL节点
     * @param <T> 数据类型 需要实现comparable接口
     * @return 新的根节点
     */
    private static<T extends Comparable<T>> RBTreeNode<T> remove(RBTreeNode<T> root, T data, final RBTreeNode<T> NIL) {
        if (data == null || root == null) {
            throw new NullPointerException();

        }
        RBTreeNode<T> current = root;
        int position;
        //搜索待删除节点
        while (isNotNull(current, NIL)) {
            position = data.compareTo(current.getValue());
            if (position == 0) {
                break;
            } else if (position > 0) {
                current = (RBTreeNode<T>) current.getRightChild();
            } else {
                current = (RBTreeNode<T>) current.getLeftChild();
            }
        }
        if (!isNotNull(current, NIL)) {
            throw new NullPointerException();
        }
        RBTreeNode<T> parent = current.getParent();
        //叶子节点直接删除
        if (!isNotNull((RBTreeNode<T>)current.getLeftChild(), NIL) && !isNotNull((RBTreeNode<T>)current.getLeftChild(), NIL)) {
            if (parent.getLeftChild() == current) {
                parent.setLeftChild(NIL);
            } else {
                parent.setRightChild(NIL);
            }
            current.setParent(null);
            return root;
        }
        RBTreeNode<T> realRemoveNode;
        //待删除节点是左子节点
        position = getChildPosition(current);
        if (position == RBTreeNode.LEFT_CHILD) {
            //找到最小的大于待删除节点的节点（父节点的右子树的最小值）
            if (!isNotNull((RBTreeNode<T>)parent.getRightChild(), NIL)) {
                realRemoveNode = parent;
            } else {
                realRemoveNode = (RBTreeNode<T>) parent.getRightChild();
                while (!isNotNull((RBTreeNode<T>)realRemoveNode.getLeftChild(), NIL)) {
                    realRemoveNode = (RBTreeNode<T>) realRemoveNode.getLeftChild();
                }
            }
        } else {
            if (!isNotNull((RBTreeNode<T>)parent.getLeftChild(), NIL)) {
                realRemoveNode = parent;
            } else {
                realRemoveNode = (RBTreeNode<T>) parent.getLeftChild();
                while (!isNotNull((RBTreeNode<T>)realRemoveNode.getRightChild(), NIL)) {
                    realRemoveNode = (RBTreeNode<T>) realRemoveNode.getRightChild();
                }
            }
        }
        //实际删除节点是父节点 将子节点值赋给父节点 将实际删除节点表示为子节点
        if (realRemoveNode == current.getParent()) {
            realRemoveNode.setValue(data);
            if (position == RBTreeNode.LEFT_CHILD) {
                parent.setLeftChild(NIL);
            } else {
                parent.setRightChild(NIL);
            }
            realRemoveNode = current;
        } else {
            current.setValue(data);
        }
        // TODO: 2021/4/15 下一步完成删除后的颜色修复开发
        return null;
    }


    /**
     * 新增后的颜色修复
     * @param current 需要修复的当前节点
     * @param NIL 该树使用的NIL节点
     * @param <T> 数据类型 需要实现comparable接口
     * @return 新的根节点
     */
    private static<T extends Comparable<T>> RBTreeNode<T> repairColor(RBTreeNode<T> current, final RBTreeNode<T> NIL) {
        //父节点是黑色节点 无需修复
        RBTreeNode<T> parent = current.getParent();
        RBTreeNode<T> uncle;
        int parentPosition;
        int selfPosition;
        while (isRedNote(parent, NIL)) {
            uncle = getBroNode(parent);
            //叔叔节点存在，必定是红色节点
            if (isRedNote(uncle, NIL)) {
                parent.setColor(RBTreeNode.RBTREE_NOTE_COLOR_BLACK);
                assert uncle != null;
                uncle.setColor(RBTreeNode.RBTREE_NOTE_COLOR_BLACK);
                if (isNotNull(parent.getParent().getParent(), NIL)) {
                    parent.getParent().setColor(RBTreeNode.RBTREE_NOTE_COLOR_RED);
                }
                parent = parent.getParent();
                continue;
            }
            //需要旋转来维持平衡
            selfPosition = getChildPosition(current);
            parentPosition = getChildPosition(parent);
            parent = selfPosition == parentPosition
                        ? selfPosition == RBTreeNode.LEFT_CHILD
                            ? RSpin(parent.getParent()).getParent()
                            : LSpin(parent.getParent()).getParent()
                        : selfPosition == RBTreeNode.LEFT_CHILD
                            ? RSpin(LSpin(parent).getParent()).getParent()
                            : LSpin(RSpin(parent).getParent()).getParent();
        }
        while (isNotNull(parent, NIL) && isNotNull(parent.getParent(), NIL)) {
            parent = parent.getParent();
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
        RBTreeNode<T> rightChild = (RBTreeNode<T>) root.getRightChild();
        rightChild.setParent(root.getParent());
        if (rightChild.getLeftChild() != null) {
            root.setRightChild(rightChild.getLeftChild());
            ((RBTreeNode<T>)rightChild.getLeftChild()).setParent(root);
        }
        rightChild.setLeftChild(root);
        root.setParent(rightChild);
        rightChild.setColor(RBTreeNode.RBTREE_NOTE_COLOR_BLACK);
        root.setColor(RBTreeNode.RBTREE_NOTE_COLOR_RED);
        return rightChild;
    }


    /**
     * 右旋
     * @param root 树根节点
     * @param <T> 数据类型 需要实现comparable接口
     * @return 旋转后的根节点
     */
    private static<T extends Comparable<T>> RBTreeNode<T> RSpin(RBTreeNode<T> root) {
        RBTreeNode<T> leftChild = (RBTreeNode<T>) root.getLeftChild();
        leftChild.setParent(root.getParent());
        if (leftChild.getRightChild() != null) {
            root.setLeftChild(leftChild.getRightChild());
            ((RBTreeNode<T>)leftChild.getRightChild()).setParent(root);
        }
        leftChild.setRightChild(root);
        root.setParent(leftChild);
        leftChild.setColor(RBTreeNode.RBTREE_NOTE_COLOR_BLACK);
        root.setColor(RBTreeNode.RBTREE_NOTE_COLOR_RED);
        return leftChild;
    }



    /**
     * 检查该节点是不是红色节点
     * @param node 需要检查的节点
     * @param NIL 该树使用的NIL节点
     * @param <T> 数据类型 需要实现comparable接口
     * @return true 红色节点
     */
    private static<T extends Comparable<T>> boolean isRedNote(RBTreeNode<T> node, final RBTreeNode<T> NIL) {
        return isNotNull(node, NIL) && RBTreeNode.RBTREE_NOTE_COLOR_RED == node.getColor();
    }

    /**
     * 检查该节点是不是非空
     * @param node 需要检查的节点
     * @param NIL 该树使用的NIL节点
     * @param <T> 数据类型 需要实现comparable接口
     * @return true 非空
     */
    private static<T extends Comparable<T>> boolean isNotNull(RBTreeNode<T> node, final RBTreeNode<T> NIL) {
        return null != node && NIL != node;
    }

    /**
     * 获取目标节点的兄弟节点
     * @param node 目标节点
     * @param <T> 数据类型 需要实现comparable接口
     * @return 目标节点的兄弟节点
     */
    private static<T extends Comparable<T>> RBTreeNode<T> getBroNode(RBTreeNode<T> node) {
        RBTreeNode<T> parent = node.getParent();
        return null == parent ? null :
                (RBTreeNode<T>) (node == parent.getLeftChild() ? parent.getRightChild() : parent.getLeftChild());
    }


    /**
     * 获取目标节点是父节点的左子节点还是右子节点
     * @param child 目标节点
     * @param <T> 数据类型 需要实现comparable接口
     * @return 目标节点是父节点的左子节点还是右子节点
     */
    private static<T extends Comparable<T>> Integer getChildPosition(RBTreeNode<T> child) {
        RBTreeNode<T> parent = child.getParent();
        return null == parent ? RBTreeNode.NOTHING : child == parent.getLeftChild() ? RBTreeNode.LEFT_CHILD : RBTreeNode.RIGHT_CHILD;
    }

    public static void main(String[] args) {
        RBTree<Integer> rbTree = new RBTree<>();
        Integer[] ints = new Integer[32];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * 32);
        }
        System.out.println(FormatPrintUtils.formatArray(ints));
        for (int anInt : ints) {
            System.out.println("==================================================");
            System.out.println(rbTree.height());
            System.out.println(rbTree.addData(anInt));
            System.out.println(rbTree.exist(anInt));
            System.out.println(rbTree.maxValue() + "      " + rbTree.minValue());
        }
    }

}
