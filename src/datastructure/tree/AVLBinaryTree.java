package datastructure.tree;
import datastructure.node.AVLBTreeNode;
import datastructure.node.BTreeNode;
import datastructure.stack.LinkedStack;
import datastructure.utils.BinaryTreeUtil;

/**
 * @program: MyLeetcode
 * @description: AVL平衡树
 * @packagename: datastructure.node
 * @author: 6JSh5rC456iL
 * @date: 2021-03-15 17:26
 **/
public class AVLBinaryTree<T extends Comparable<T>>{

    private AVLBTreeNode<T> root;


    public AVLBTreeNode<T> getRoot() {
        return root;
    }


    /**
     * 向当前二叉树新增数据
     * @param value 需要新增的数据
     * @return 暂定
     */
    public boolean addData(T value) {
        if (value == null) {
            return false;
        }
        root = root == null ? new AVLBTreeNode<>(value) : addData(root, value);
        return true;
    }


    /**
     * 从树中删除给定值
     * @param value 需要删除的给定值
     * @return true 删除成功 false 树为空或删除值不存在与树中
     */
    public boolean removeData(T value) {
        if (root == null) {
            return false;
        }
        AVLBTreeNode<T> newRoot = removeData(root, value);
        if (newRoot == null) {
            return false;
        }
        root = newRoot;
        return true;
    }

    /**
     * 获取当前二叉树最小值
     * @return 当前二叉树最小值
     */
    public T minValue() {
        return minValue(root);
    }


    /**
     * 获取当前二叉树最大值
     * @return 当前二叉树最大值
     */
    public T maxValue() {
        return maxValue(root);
    }


    /**
     * 判断当前二叉树是否存在给定值
     * @param data 给定值
     * @return true：存在， false：不存在或给定值为空或根节点为空
     */
    public boolean exist(T data) {
        return data != null && exist(root, data);
    }


    /**
     * 获取当前二叉树高度
     * @return 当前二叉树高度
     */
    public int height() {
        return getTreeHeight(root);
    }


    /**
     * 获取给定二叉树的最小值
     * @param root 根节点
     * @param <T> 数据类型 需要实现comparable接口
     * @return 给定二叉树的最小值
     */
    private static<T extends Comparable<T>> T minValue(BTreeNode<T> root) {
        if (root == null) {
            return null;
        }
        while (null != root.getLeftChild()) {
            root = root.getLeftChild();
        }
        return root.getValue();
    }


    /**
     * 获取给定二叉树的最大值
     * @param root 根节点
     * @param <T> 数据类型 需要实现comparable接口
     * @return 给定二叉树的最大值
     */
    private static<T extends Comparable<T>> T maxValue(BTreeNode<T> root) {
        if (root == null) {
            return null;
        }
        while (null != root.getRightChile()) {
            root = root.getRightChile();
        }
        return root.getValue();
    }


    /**
     * 验证给定二叉树是否存在给定值
     * @param root 根节点
     * @param data 验证值
     * @param <T> 数据类型 需要实现comparable接口
     * @return true：存在 false 不存在
     */
    private static<T extends Comparable<T>> boolean exist(BTreeNode<T> root, T data) {
        if (root == null) {
            return false;
        }
        int compareRes;
        while (root != null) {
            compareRes = root.getValue().compareTo(data);
            if (compareRes == 0) {
                return true;
            } else if (compareRes < 0) {
                root = root.getLeftChild();
            } else {
                root = root.getRightChile();
            }
        }
        return false;
    }


    /**
     * 向指定二叉树插入给定值
     * @param root 二叉树根节点
     * @param data 插入数据
     * @param <T> 数据类型 需要实现comparable接口
     * @return 平衡后的根节点
     */
    private static<T extends Comparable<T>> AVLBTreeNode<T> addData(AVLBTreeNode<T> root, T data) {
        if (root == null) {
            return new AVLBTreeNode<>(data);
        }
        //原来使用递归操作，后发现容易栈溢出，改为用栈实现
        LinkedStack<AVLBTreeNode<T>> nodeLinkedStack = new LinkedStack<>();
        LinkedStack<Integer> operatorStack = new LinkedStack<>();
        AVLBTreeNode<T> tail = root;
        while (tail != null) {
            nodeLinkedStack.push(tail);
            if (data.compareTo(tail.getValue()) >= 0) {
                tail = (AVLBTreeNode<T>) tail.getRightChile();
                operatorStack.push(1);
            } else {
                tail = (AVLBTreeNode<T>) tail.getLeftChild();
                operatorStack.push(-1);
            }
        }
        tail = new AVLBTreeNode<>(data);
        while (!operatorStack.empty()) {
            AVLBTreeNode<T> parent = nodeLinkedStack.pop();
            if (operatorStack.pop() > 0) {
                parent.setRightChile(tail);
            } else {
                parent .setLeftChild(tail);
            }
            if (tail.getHeight() + 1 > parent.getHeight()) {
                parent.setHeight(tail.getHeight()+1);
                tail = doBalance(parent);
            } else {
                tail = parent;
            }
        }
        return tail;
    }


    /**
     * 从给定二叉树中删除给定值
     * @param root 二叉树根节点
     * @param data 需要删除的给定值
     * @param <T> 数据类型 需要实现comparable接口
     * @return 平衡后的根节点 null表示树是空的或找不到删除值
     */
    private static<T extends Comparable<T>> AVLBTreeNode<T> removeData(AVLBTreeNode<T> root, T data) {
        if (root == null) {
            return null;
        }
        LinkedStack<AVLBTreeNode<T>> nodeLinkedStack = new LinkedStack<>();
        LinkedStack<Integer> operatorStack = new LinkedStack<>();
        AVLBTreeNode<T> tail = root;
        while (tail != null) {
            nodeLinkedStack.push(tail);
            if (data.compareTo(tail.getValue()) > 0) {
                tail = (AVLBTreeNode<T>) tail.getRightChile();
                operatorStack.push(1);
            } else if (data.compareTo(tail.getValue()) < 0){
                tail = (AVLBTreeNode<T>) tail.getLeftChild();
                operatorStack.push(-1);
            } else {
                break;
            }
        }
        if (tail == null) {
            return null;
        }
        if (tail.getRightChile() == null && tail.getLeftChild() == null) {
            nodeLinkedStack.pop();
            if (operatorStack.top() == 1) {
                nodeLinkedStack.top().setRightChile(null);
            } else {
                nodeLinkedStack.top().setLeftChild(null);
            }
        } else if (tail.getRightChile() != null &&
                (tail.getLeftChild() == null ||
                        ((AVLBTreeNode<T>)tail.getLeftChild()).getHeight() < ((AVLBTreeNode<T>)tail.getRightChile()).getHeight()) ) {
            BTreeNode<T> rightChile = tail;
            T newVal;
            operatorStack.push(1);
            while (rightChile.getRightChile() != null) {
                nodeLinkedStack.push((AVLBTreeNode<T>) rightChile.getRightChile());
                operatorStack.push(1);
                rightChile = rightChile.getRightChile();
            }
            nodeLinkedStack.pop();
            newVal = rightChile.getValue();
            nodeLinkedStack.top().setRightChile(rightChile.getLeftChild());
            tail.setValue(newVal);
        } else {
            BTreeNode<T> leftChild = tail;
            T newVal;
            while (leftChild.getLeftChild() != null) {
                nodeLinkedStack.push((AVLBTreeNode<T>) leftChild.getLeftChild());
                operatorStack.push(-1);
                leftChild = leftChild.getLeftChild();
            }
            nodeLinkedStack.pop();
            newVal = leftChild.getValue();
            nodeLinkedStack.top().setLeftChild(leftChild.getRightChile());
            tail.setValue(newVal);
        }
        AVLBTreeNode<T> child = nodeLinkedStack.pop();
        int oldHeight = child.getHeight();
        updateTreeHeight(child);
        if (child.getHeight() == oldHeight) {
            return root;
        }
        child = doBalance(child);
        operatorStack.pop();
        while (!nodeLinkedStack.empty()) {
            AVLBTreeNode<T> parent = nodeLinkedStack.pop();
            if (operatorStack.pop() == 1) {
                parent.setRightChile(child);
            } else {
                parent.setLeftChild(child);
            }
            oldHeight = parent.getHeight();
            updateTreeHeight(parent);
            if (parent.getHeight() == oldHeight) {
                return root;
            }
            child = doBalance(parent);
        }
        return child;
    }


    /**
     * 二叉树平衡操作
     * @param root 根节点
     * @param <T> 数据类型 需要实现comparable接口
     * @return 根节点
     */
    private static<T extends Comparable<T>> AVLBTreeNode<T> doBalance(AVLBTreeNode<T> root) {
        int balanceDegree = getTreeBalanceDegree(root);
        if (balanceDegree > 1) {
            if (getTreeBalanceDegree(root.getLeftChild()) > 0) {
                return rSpin(root);
            }
            return lrSpin(root);
        }
        if (balanceDegree < -1) {
            if (getTreeBalanceDegree(root.getRightChile()) < 0) {
                return lSpin(root);
            }
            return rlSpin(root);
        }
        return root;
    }


    /**
     * 获取该二叉树树平衡值
     * @param root 树的根节点
     * @param <T> 数据类型 需要实现comparable接口
     * @return 该二叉树树平衡值
     */
    private static<T extends Comparable<T>> int getTreeBalanceDegree(BTreeNode<T> root) {
        return null == root ? 0 :
                getTreeHeight(root.getLeftChild()) - getTreeHeight(root.getRightChile());
    }


    /**
     * 获取二叉树高度
     * @param root 树的根节点
     * @param <T> 数据类型 需要实现comparable接口
     * @return 树高
     */
    private static<T extends Comparable<T>> int getTreeHeight(BTreeNode<T> root) {
        return root == null ? 0 : ((AVLBTreeNode<T>)root).getHeight();
    }


    /**
     * RR旋转
     * @param unBalanceNode 不平衡节点
     * @param <T> 数据类型 需要实现comparable接口
     * @return 根节点
     */
    private static<T extends Comparable<T>> AVLBTreeNode<T> rSpin(AVLBTreeNode<T> unBalanceNode) {
        AVLBTreeNode<T> leftChild = (AVLBTreeNode<T>) unBalanceNode.getLeftChild();
        unBalanceNode.setLeftChild(leftChild.getRightChile());
        leftChild.setRightChile(unBalanceNode);
        updateTreeHeight(unBalanceNode);
        updateTreeHeight(leftChild);
        return leftChild;
    }


    /**
     * LL旋转
     * @param unBalanceNode 不平衡节点
     * @param <T> 数据类型 需要实现comparable接口
     * @return 根节点
     */
    private static<T extends Comparable<T>> AVLBTreeNode<T> lSpin(AVLBTreeNode<T> unBalanceNode) {
        AVLBTreeNode<T> rightChile = (AVLBTreeNode<T>) unBalanceNode.getRightChile();
        unBalanceNode.setRightChile(rightChile.getLeftChild());
        rightChile.setLeftChild(unBalanceNode);
        updateTreeHeight(unBalanceNode);
        updateTreeHeight(rightChile);
        return rightChile;
    }


    /**
     * RL旋转
     * @param unBalanceNode 不平衡节点
     * @param <T> 数据类型 需要实现comparable接口
     * @return 根节点
     */
    private static<T extends Comparable<T>> AVLBTreeNode<T> rlSpin(AVLBTreeNode<T> unBalanceNode) {
        BTreeNode<T> newRoot = unBalanceNode.getRightChile().getLeftChild();
        BTreeNode<T> rightChile = unBalanceNode.getRightChile();
        if (newRoot.getLeftChild() != null) {
            unBalanceNode.setRightChile(newRoot.getLeftChild());
            rightChile.setLeftChild(null);
        } else if(newRoot.getRightChile() != null) {
            rightChile.setLeftChild(newRoot.getRightChile());
            unBalanceNode.setRightChile(null);
        } else {
            unBalanceNode.setRightChile(null);
            rightChile.setLeftChild(null);
        }
        newRoot.setLeftChild(unBalanceNode);
        newRoot.setRightChile(rightChile);
        updateTreeHeight(newRoot.getLeftChild());
        updateTreeHeight(newRoot.getRightChile());
        updateTreeHeight(newRoot);
        return (AVLBTreeNode<T>) newRoot;
    }


    /**
     * LR旋转
     * @param unBalanceNode 不平衡节点
     * @param <T> 数据类型 需要实现comparable接口
     * @return 根节点
     */
    private static<T extends Comparable<T>> AVLBTreeNode<T> lrSpin(AVLBTreeNode<T> unBalanceNode) {
        BTreeNode<T> newRoot = unBalanceNode.getLeftChild().getRightChile();
        BTreeNode<T> leftChild = unBalanceNode.getLeftChild();
        if (newRoot.getLeftChild() != null) {
            leftChild.setRightChile(newRoot.getLeftChild());
            unBalanceNode.setLeftChild(null);
        } else if(newRoot.getRightChile() != null) {
            unBalanceNode.setLeftChild(newRoot.getRightChile());
            leftChild.setRightChile(null);
        } else {
            unBalanceNode.setLeftChild(null);
            leftChild.setRightChile(null);
        }
        newRoot.setRightChile(unBalanceNode);
        newRoot.setLeftChild(leftChild);
        updateTreeHeight(unBalanceNode);
        updateTreeHeight(leftChild);
        updateTreeHeight(newRoot);
        return (AVLBTreeNode<T>) newRoot;
    }


    /**
     * 调整树节点高度值
     * @param root 树节点
     * @param <T> 数据类型 需要实现comparable接口
     */
    private static<T extends Comparable<T>>  void updateTreeHeight(BTreeNode<T> root) {
        if (root == null) {
            return;
        }
        ((AVLBTreeNode<T>)root).setHeight(1 + Math.max(getTreeHeight(root.getLeftChild()), getTreeHeight(root.getRightChile())));
    }



    public static void main(String[] args) {
        AVLBinaryTree<Integer> avlBinaryTree = new AVLBinaryTree<>();
        int[] ints = new int[18];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random()*100);
        }
        for (int i = 0; i < 18; i++) {
            avlBinaryTree.addData(ints[i]);
        }
        System.out.println(BinaryTreeUtil.console(avlBinaryTree.root));
        for (int i = 0; i < 18; i++) {
            avlBinaryTree.removeData(ints[i]);
            System.out.println("-------------------" + ints[i] + "--------------");
            System.out.println(BinaryTreeUtil.console(avlBinaryTree.root));
            System.out.println("------------------------------------------------");
        }
        //System.out.println(BinaryTreeUtil.console(avlBinaryTree.root));
    }

}
