package datastructure.utils;

import datastructure.node.BTreeNode;

import java.util.Arrays;

/**
 * @program: MyLeetcode
 * @description:
 * @packagename: datastructure.utils
 * @author: 6JSh5rC456iL
 * @date: 2021-03-18 16:07
 **/
public class BinaryTreeUtil {


    /**
     *
     * 返回给定二叉树的可视化显示
     * 如果节点数据的toString()返回字符串长度不是1
     * 那么可视化将会失败，Integer类型除外
     *         4
     *     2       5
     *   1   3   4   6
     *  * 1 2 3 * * 5 6
     * @param root 根节点
     * @param <T> 数据类型 需要实现comparable接口
     * @return 树的可视化显示
     */
    public static<T extends Comparable<T>> String console(BTreeNode<T> root) {
        int treeHeight = getTreeHeight(root);
        String[] savaStrArr = new String[(1 << treeHeight) + 1];
        Arrays.fill(savaStrArr, "*");
        inorderTraversalSave(root, 1,  savaStrArr);
        StringBuilder consoleFormat = new StringBuilder();
        for (int i = 0; i < treeHeight; i++) {
            //1 添加开头空白
            for (int j = 0; j < (int) (Math.pow(2, treeHeight-i-1)); ++ j) {
                consoleFormat.append(' ');
            }
            //2 计算间隔
            StringBuilder separator = new StringBuilder();
            for (int j = 0; j < (int) (Math.pow(2, treeHeight-i))-1; ++ j) {
                separator.append(' ');
            }
            //输出每层数据
            for (int j = (int) (Math.pow(2, i)); j < (int) (Math.pow(2, i+1)); ++j) {
                consoleFormat.append(savaStrArr[j]).append(separator);
            }
            consoleFormat.append('\n');
        }
        return consoleFormat.toString();
    }

    /**
     * 获取给定二叉树的高度
     * @param root 二叉树根节点
     * @param <T> 数据类型 需要实现comparable接口
     * @return 给定二叉树的高度
     */
    public static<T extends Comparable<T>> int getTreeHeight(BTreeNode<T> root) {
        return root == null ?
                0 :
                1 + Math.max(getTreeHeight(root.getRightChile()), getTreeHeight(root.getLeftChild()));
    }

    /**
     * 将二叉树的节点toString()以层序遍历方式保存到给定数组中
     * @param root 二叉树根节点
     * @param index 对应保存数组的下标
     * @param saveStrArr 保存数组
     * @param <T> 数据类型 需要实现comparable接口
     */
    private static<T extends Comparable<T>> void inorderTraversalSave(BTreeNode<T> root, int index, String[] saveStrArr) {
        saveStrArr[index] = root == null ? " " : root.getValue().toString();
        index = index << 1;
        if (index == 0) {
            index = 1;
        }
        if (root.getLeftChild() != null) {
            inorderTraversalSave(root.getLeftChild(), index, saveStrArr);
        }
        if (root.getRightChile() != null) {
            inorderTraversalSave(root.getRightChile(), index + 1, saveStrArr);
        }
    }

    /**
     * 格式化整数数组，将字符串数组变成等长
     * 如 【“1”，“2”，“33”，“4”】
     * 格式化后
     * 【“ 1”，“ 2”，“33”，“ 4”】
     * @param strArr
     */
    private void formatIntStrArr(String[] strArr) {
        int length = Arrays.stream(strArr).max(
                (s1, s2) -> {
                    return Integer.compareUnsigned(s1.length(), s2.length());
                }
        ).get().length();
/*        strArr = Arrays.stream(strArr).map(s -> {
            //return
        })*/
    }



}
