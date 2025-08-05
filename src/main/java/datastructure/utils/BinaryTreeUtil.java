/*
 * 版权所有（c）<2021><蔡永程>
 *
 * 反996许可证版本1.0
 *
 * 在符合下列条件的情况下，特此免费向任何得到本授权作品的副本（包括源代码、文件和/或相关内容，以
 * 下统称为“授权作品”）的个人和法人实体授权：被授权个人或法人实体有权以任何目的处置授权作品，包括
 * 但不限于使用、复制，修改，衍生利用、散布，发布和再许可：
 *
 * 1. 个人或法人实体必须在许可作品的每个再散布或衍生副本上包含以上版权声明和本许可证，不得自行修
 * 改。
 * 2. 个人或法人实体必须严格遵守与个人实际所在地或个人出生地或归化地、或法人实体注册地或经营地（
 * 以较严格者为准）的司法管辖区所有适用的与劳动和就业相关法律、法规、规则和标准。如果该司法管辖区
 * 没有此类法律、法规、规章和标准或其法律、法规、规章和标准不可执行，则个人或法人实体必须遵守国际
 * 劳工标准的核心公约。
 * 3. 个人或法人不得以任何方式诱导、暗示或强迫其全职或兼职员工或其独立承包人以口头或书面形式同意
 * 直接或间接限制、削弱或放弃其所拥有的，受相关与劳动和就业有关的法律、法规、规则和标准保护的权利
 * 或补救措施，无论该等书面或口头协议是否被该司法管辖区的法律所承认，该等个人或法人实体也不得以任
 * 何方法限制其雇员或独立承包人向版权持有人或监督许可证合规情况的有关当局报告或投诉上述违反许可证
 * 的行为的权利。
 *
 * 该授权作品是"按原样"提供，不做任何明示或暗示的保证，包括但不限于对适销性、特定用途适用性和非侵
 * 权性的保证。在任何情况下，无论是在合同诉讼、侵权诉讼或其他诉讼中，版权持有人均不承担因本软件或
 * 本软件的使用或其他交易而产生、引起或与之相关的任何索赔、损害或其他责任。
 */

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
 * @since: 2021-03-18 16:07
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
                        1 + Math.max(getTreeHeight(root.getRightChild()), getTreeHeight(root.getLeftChild()));
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
                bTreeNodes[i] = bTreeNodes[i>>1] == null ? null : (i & 1) == 1 ? bTreeNodes[i>>1].getRightChild() : bTreeNodes[i>>1].getLeftChild();
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
                (s1, s2) -> Integer.compareUnsigned(s1.length(), s2.length())
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
