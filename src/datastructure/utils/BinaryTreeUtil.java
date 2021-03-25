package datastructure.utils;

import datastructure.node.AVLBTreeNode;
import datastructure.node.BTreeNode;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        inorderTraversalSave(root, savaStrArr);
        int maxLength = formatStrArr(savaStrArr) + 2;
        StringBuilder oneSeparator = new StringBuilder();
        for (int i = 0; i < maxLength; i++) {
            oneSeparator.append(" ");
        }
        String oneSeparatorStr = oneSeparator.toString();
        StringBuilder consoleFormat = new StringBuilder();
        StringBuilder separator = new StringBuilder(oneSeparatorStr);
        StringBuilder headFill = new StringBuilder(oneSeparatorStr);
        int i, j, length;
        for (i = treeHeight - 1; i > -1; i--) {
            StringBuilder oneLine = new StringBuilder(headFill);
            headFill.append(headFill);
            if (i < treeHeight - 1) {
                length = separator.toString().length();
                int l = (length / maxLength - 1) / 2 * 2 + 2;
                for (j = 0; j < l; j++) {
                    separator.append(oneSeparatorStr);
                }
            }
            length = (1 << (i+1)) - 1;
            for (j = 1 << i; j < length; j++) {
                oneLine.append(savaStrArr[j]).append(separator);
            }
            oneLine.append(savaStrArr[length]).append('\n');
            consoleFormat = oneLine.append(consoleFormat);
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
                root instanceof AVLBTreeNode ?
                        ((AVLBTreeNode<T>)root).getHeight() :
                        1 + Math.max(getTreeHeight(root.getRightChile()), getTreeHeight(root.getLeftChild()));
    }

    /**
     * 将二叉树的节点toString()以层序遍历方式保存到给定数组中
     * @param root 二叉树根节点
     * @param saveStrArr 保存数组
     * @param <T> 数据类型 需要实现comparable接口
     */
    private static<T extends Comparable<T>> void inorderTraversalSave(BTreeNode<T> root, String[] saveStrArr) {
        BTreeNode<T>[] bTreeNodes = new BTreeNode[saveStrArr.length];
        bTreeNodes[1] = root;
        for (int i = 2, j = 4; i < saveStrArr.length; j = j << 1) {
            for (; i<j && i < saveStrArr.length; ++i) {
                bTreeNodes[i] = bTreeNodes[i>>1] == null ? null : (i & 1) == 1 ? bTreeNodes[i>>1].getRightChile() : bTreeNodes[i>>1].getLeftChild();
            }
        }
        List<String> collect = Arrays.stream(bTreeNodes).map(tbTreeNode -> tbTreeNode == null ? "" : tbTreeNode.getValue().toString()).collect(Collectors.toList());
        collect.toArray(saveStrArr);
    }

    /**
     * 格式化整数数组，将字符串数组变成等长
     * 如 【“1”，“2”，“33”，“4”】
     * 格式化后
     * 【“ 1”，“ 2”，“33”，“ 4”】
     * @param strArr
     */
    private static Integer formatStrArr(String[] strArr) {
        int length = 0;
        Optional<String> max = Arrays.stream(strArr).max(
                (s1, s2) -> {
                    return Integer.compareUnsigned(s1.length(), s2.length());
                }
        );
        if (max.isPresent()) {
            length = max.get().length();
        }
        for (int i = 0; i < strArr.length; i++) {
            int sLength = strArr[i].length();
            int leftFill = (length - sLength) >> 1;
            int rightFill = length - sLength - leftFill;
            StringBuilder stringBuilder = new StringBuilder("[");
            for (int j = 0; j < leftFill; j++) {
                stringBuilder.append(" ");
            }
            stringBuilder.append(strArr[i]);
            for (int j = 0; j < rightFill; j++) {
                stringBuilder.append(" ");
            }
            stringBuilder.append("]");
            strArr[i] = stringBuilder.toString();
        }
        return length;
    }



}
