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
        while (null != root.getRightChild()) {
            root = root.getRightChild();
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
                root = root.getRightChild();
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
                tail = (AVLBTreeNode<T>) tail.getRightChild();
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
                parent.setRightChild(tail);
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
        //找到待删除节点
        while (tail != null) {
            nodeLinkedStack.push(tail);
            if (data.compareTo(tail.getValue()) > 0) {
                tail = (AVLBTreeNode<T>) tail.getRightChild();
                operatorStack.push(1);
            } else if (data.compareTo(tail.getValue()) < 0){
                tail = (AVLBTreeNode<T>) tail.getLeftChild();
                operatorStack.push(-1);
            } else {
                break;
            }
        }
        //不存在待删除节点
        if (tail == null) {
            return null;
        }
        if (tail.getRightChild() != null && tail.getLeftChild() != null) {
            return removeBothChild(nodeLinkedStack, operatorStack);
        }
        return removeNotBothChild(nodeLinkedStack, operatorStack);
    }


    /**
     * 当删除节点是叶子节,或只有单子节点
     * @param nodeLinkedStack 给定结点栈 栈顶是要被删除的叶子节点
     * @param operatorStack 表示节点关系的操作数栈
     * @param <T> 数据类型 需要实现comparable接口
     * @return 平衡后的根节点
     */
    private static<T extends Comparable<T>> AVLBTreeNode<T> removeNotBothChild(LinkedStack<AVLBTreeNode<T>> nodeLinkedStack,
                                                                       LinkedStack<Integer> operatorStack) {
        AVLBTreeNode<T> childNode = nodeLinkedStack.pop();
        BTreeNode<T> rightChile = childNode.getRightChild();
        if (operatorStack.pop() == 1) {
            nodeLinkedStack.top().setRightChild(rightChile == null ? childNode.getLeftChild() : rightChile);
        } else {
            nodeLinkedStack.top().setLeftChild(rightChile == null ? childNode.getLeftChild() : rightChile);
        }
        return doBalance(nodeLinkedStack, operatorStack);
    }

    /**
     * 当删除节点左右节点都有
     * @param nodeLinkedStack 给定结点栈 栈顶是要被删除的叶子节点
     * @param operatorStack 表示节点关系的操作数栈
     * @param <T> 数据类型 需要实现comparable接口
     * @return 平衡后的根节点
     */
    private static<T extends Comparable<T>> AVLBTreeNode<T> removeBothChild(LinkedStack<AVLBTreeNode<T>> nodeLinkedStack,
                                                                               LinkedStack<Integer> operatorStack) {
        AVLBTreeNode<T> childNode = nodeLinkedStack.top();
        if (((AVLBTreeNode<T>)childNode.getLeftChild()).getHeight() <
                ((AVLBTreeNode<T>)childNode.getRightChild()).getHeight()) {
            //找到替换节点
            BTreeNode<T> endChild = childNode.getRightChild();
            operatorStack.push(1);
            nodeLinkedStack.push((AVLBTreeNode<T>) endChild);
            endChild = endChild.getLeftChild();
            while (endChild != null) {
                operatorStack.push(-1);
                nodeLinkedStack.push((AVLBTreeNode<T>) endChild);
                endChild = endChild.getLeftChild();
            }
            endChild = nodeLinkedStack.pop();
            //替换节点是删除节点的右子树最小节点
            if (operatorStack.pop() == -1) {
                nodeLinkedStack.top().setLeftChild(null);
                childNode.setValue(endChild.getValue());
            } else {
                //替换节点是删除节点的右子树根节点
                endChild.setLeftChild(childNode.getLeftChild());
                if (!operatorStack.empty()) {
                    nodeLinkedStack.pop();
                    operatorStack.pop();
                    if (operatorStack.top() == 1) {
                        nodeLinkedStack.top().setRightChild(endChild);
                    } else {
                        nodeLinkedStack.top().setLeftChild(endChild);
                    }
                }
            }
        } else {
            BTreeNode<T> endChild = childNode.getLeftChild();
            operatorStack.push(-1);
            nodeLinkedStack.push((AVLBTreeNode<T>) endChild);
            endChild = endChild.getRightChild();
            while (endChild != null) {
                operatorStack.push(1);
                nodeLinkedStack.push((AVLBTreeNode<T>) endChild);
                endChild = endChild.getRightChild();
            }
            endChild = nodeLinkedStack.pop();
            if (operatorStack.pop() == 1) {
                nodeLinkedStack.top().setRightChild(null);
                childNode.setValue(endChild.getValue());
            } else {
                endChild.setRightChild(childNode.getRightChild());
                if (!operatorStack.empty()) {
                    nodeLinkedStack.pop();
                    operatorStack.pop();
                    if (operatorStack.top() == 1) {
                        nodeLinkedStack.top().setRightChild(endChild);
                    } else {
                        nodeLinkedStack.top().setLeftChild(endChild);
                    }
                }
            }
        }
        return doBalance(nodeLinkedStack, operatorStack);
    }

    /**
     * 平衡给定节点列表
     * @param nodeLinkedStack 给定结点栈
     * @param operatorStack 表示节点关系的操作数栈
     * @param <T> 数据类型 需要实现comparable接口
     * @return 平衡后的根节点
     */
    private static<T extends Comparable<T>> AVLBTreeNode<T> doBalance(LinkedStack<AVLBTreeNode<T>> nodeLinkedStack,
                                                                      LinkedStack<Integer> operatorStack) {
        AVLBTreeNode<T> childNode = nodeLinkedStack.pop();
        updateTreeHeight(childNode);
        childNode = doBalance(childNode);
        AVLBTreeNode<T> parentNode;
        while (!nodeLinkedStack.empty()) {
            parentNode = nodeLinkedStack.pop();
            if (operatorStack.pop() == 1) {
                parentNode.setRightChild(childNode);
            } else {
                parentNode.setLeftChild(childNode);
            }
            updateTreeHeight(parentNode);
            childNode = doBalance(parentNode);
        }
        return childNode;
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
            if (getTreeBalanceDegree(root.getRightChild()) < 0) {
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
                getTreeHeight(root.getLeftChild()) - getTreeHeight(root.getRightChild());
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
        unBalanceNode.setLeftChild(leftChild.getRightChild());
        leftChild.setRightChild(unBalanceNode);
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
        AVLBTreeNode<T> rightChile = (AVLBTreeNode<T>) unBalanceNode.getRightChild();
        unBalanceNode.setRightChild(rightChile.getLeftChild());
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
        BTreeNode<T> newRoot = unBalanceNode.getRightChild().getLeftChild();
        BTreeNode<T> rightChile = unBalanceNode.getRightChild();
        if (newRoot.getLeftChild() != null) {
            unBalanceNode.setRightChild(newRoot.getLeftChild());
            rightChile.setLeftChild(null);
        } else if(newRoot.getRightChild() != null) {
            rightChile.setLeftChild(newRoot.getRightChild());
            unBalanceNode.setRightChild(null);
        } else {
            unBalanceNode.setRightChild(null);
            rightChile.setLeftChild(null);
        }
        newRoot.setLeftChild(unBalanceNode);
        newRoot.setRightChild(rightChile);
        updateTreeHeight(newRoot.getLeftChild());
        updateTreeHeight(newRoot.getRightChild());
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
        BTreeNode<T> newRoot = unBalanceNode.getLeftChild().getRightChild();
        BTreeNode<T> leftChild = unBalanceNode.getLeftChild();
        if (newRoot.getLeftChild() != null) {
            leftChild.setRightChild(newRoot.getLeftChild());
            unBalanceNode.setLeftChild(null);
        } else if(newRoot.getRightChild() != null) {
            unBalanceNode.setLeftChild(newRoot.getRightChild());
            leftChild.setRightChild(null);
        } else {
            unBalanceNode.setLeftChild(null);
            leftChild.setRightChild(null);
        }
        newRoot.setRightChild(unBalanceNode);
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
        ((AVLBTreeNode<T>)root).setHeight(1 + Math.max(getTreeHeight(root.getLeftChild()), getTreeHeight(root.getRightChild())));
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
