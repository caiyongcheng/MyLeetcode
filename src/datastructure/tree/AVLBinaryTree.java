package datastructure.tree;
import datastructure.node.BinaryTreeNode;
import datastructure.utils.BinaryTreeUtil;

/**
 * @program: MyLeetcode
 * @description: AVL平衡树
 * @packagename: datastructure.node
 * @author: 6JSh5rC456iL
 * @date: 2021-03-15 17:26
 **/
public class AVLBinaryTree<T extends Comparable<T>>{

    private BinaryTreeNode<T> root;


    public BinaryTreeNode<T> getRoot() {
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
        root = root == null ? new BinaryTreeNode<>(value) : addData(root, value);
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
    private static<T extends Comparable<T>> T minValue(BinaryTreeNode<T> root) {
        return root == null ?
                null :
                root.getLeftChild() == null ?
                        root.getValue() :
                        minValue(root.getLeftChild());
    }


    /**
     * 获取给定二叉树的最大值
     * @param root 根节点
     * @param <T> 数据类型 需要实现comparable接口
     * @return 给定二叉树的最大值
     */
    private static<T extends Comparable<T>> T maxValue(BinaryTreeNode<T> root) {
        return root == null ?
                null :
                root.getRightChile() == null ?
                        root.getValue() :
                        maxValue(root.getRightChile());
    }


    /**
     * 验证给定二叉树是否存在给定值
     * @param root 根节点
     * @param data 验证值
     * @param <T> 数据类型 需要实现comparable接口
     * @return true：存在 false 不存在
     */
    private static<T extends Comparable<T>> boolean exist(BinaryTreeNode<T> root, T data) {
        if (root == null) {
            return false;
        }
        int compareRes = root.getValue().compareTo(data);
        return compareRes == 0 || (
                compareRes < 0 ?
                        exist(root.getLeftChild(), data) :
                        exist(root.getRightChile(), data)
                );
    }


    /**
     * 向指定二叉树插入给定值
     * @param root 二叉树根节点
     * @param data 插入数据
     * @param <T> 数据类型 需要实现comparable接口
     * @return 暂定
     */
    private static<T extends Comparable<T>> BinaryTreeNode<T> addData(BinaryTreeNode<T> root, T data) {
        if (root == null) {
            return new BinaryTreeNode<>(data);
        }
        if (data.compareTo(root.getValue()) >= 0) {
            root.setRightChile(addData(root.getRightChile(), data));
        } else {
            root.setLeftChild(addData(root.getLeftChild(), data));
        }
        return doBalance(root);
    }


    /**
     * 二叉树平衡操作
     * @param root 根节点
     * @param <T> 数据类型 需要实现comparable接口
     * @return 根节点
     */
    private static<T extends Comparable<T>> BinaryTreeNode<T> doBalance(BinaryTreeNode<T> root) {
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
    private static<T extends Comparable<T>> int getTreeBalanceDegree(BinaryTreeNode<T> root) {
        return root == null ? 0 : getTreeHeight(root.getLeftChild()) - getTreeHeight(root.getRightChile());
    }


    /**
     * 获取二叉树高度
     * @param root 树的根节点
     * @param <T> 数据类型 需要实现comparable接口
     * @return 树高
     */
    private static<T extends Comparable<T>> int getTreeHeight(BinaryTreeNode<T> root) {
        return root == null ? 0 : Math.max(getTreeHeight(root.getLeftChild()), getTreeHeight(root.getRightChile())) + 1;
    }


    /**
     * RR旋转
     * @param unBlanceNode 不平衡节点
     * @param <T> 数据类型 需要实现comparable接口
     * @return 根节点
     */
    private static<T extends Comparable<T>> BinaryTreeNode<T> rSpin(BinaryTreeNode<T> unBlanceNode) {
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
    private static<T extends Comparable<T>> BinaryTreeNode<T> lSpin(BinaryTreeNode<T> unBlanceNode) {
        BinaryTreeNode<T> rightChile = unBlanceNode.getRightChile();
        unBlanceNode.setRightChile(rightChile.getLeftChild());
        rightChile.setLeftChild(unBlanceNode);
        return rightChile;
    }


    /**
     * RL旋转
     * @param unBlanceNode 不平衡节点
     * @param <T> 数据类型 需要实现comparable接口
     * @return 根节点
     */
    private static<T extends Comparable<T>> BinaryTreeNode<T> rlSpin(BinaryTreeNode<T> unBlanceNode) {
        //先右旋，变成LL形式
        BinaryTreeNode<T> rightChile = unBlanceNode.getRightChile();
        rightChile.getLeftChild().setRightChile(rightChile);
        unBlanceNode.setRightChile(rightChile.getLeftChild());
        return lSpin(unBlanceNode);
    }


    /**
     * LR旋转
     * @param unBlanceNode 不平衡节点
     * @param <T> 数据类型 需要实现comparable接口
     * @return 根节点
     */
    private static<T extends Comparable<T>> BinaryTreeNode<T> lrSpin(BinaryTreeNode<T> unBlanceNode) {
        BinaryTreeNode<T> leftChild = unBlanceNode.getLeftChild();
        leftChild.getRightChile().setLeftChild(leftChild);
        unBlanceNode.setLeftChild(leftChild.getRightChile());
        return rSpin(unBlanceNode);
    }


    public static void main(String[] args) {
        AVLBinaryTree<Integer> avlBinaryTree = new AVLBinaryTree<>();
        for (int i=0; i<16; ++i) {
            avlBinaryTree.addData((int) (Math.random()*10));
        }
        System.out.println(BinaryTreeUtil.console(avlBinaryTree.root));
    }

}
