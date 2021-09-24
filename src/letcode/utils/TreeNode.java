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

package letcode.utils;

import datastructure.stack.LinkedStack;

import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Leetcode
 *
 * @author : CaiYongcheng
 * @date : 2020-06-28 20:45
 **/
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int x) {
        this.val = x;
    }

    public TreeNode(int[] arr) {
        createChildTreeNode(arr, 0, this);
    }

    public TreeNode(Integer[] arr) {createChildTreeNode(arr, 0, this);}

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static void display(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            display(root.left);
            display(root.right);
        }
    }

    public static List<Integer> preOrder(TreeNode node) {
        assert node != null;
        LinkedStack<TreeNode> treeNodes = new LinkedStack<>();
        LinkedList<Integer> linkedList = new LinkedList<>();
        treeNodes.push(node);
        while (!treeNodes.empty()) {
            node = treeNodes.pop();
            linkedList.add(node.val);
            if (node.right != null) {
                treeNodes.push(node.right);
            }
            if (node.left != null) {
                treeNodes.push(node.left);
            }
        }
        return linkedList;
    }


    public static List<Integer> inOrder(TreeNode node) {
        assert node != null;
        LinkedStack<TreeNode> treeNodes = new LinkedStack<>();
        LinkedList<Integer> linkedList = new LinkedList<>();
        treeNodes.push(node);
        while (!treeNodes.empty()) {
            while (treeNodes.top().left != null) {
                treeNodes.push(treeNodes.top().left);
            }
            while (!treeNodes.empty()) {
                node = treeNodes.pop();
                linkedList.add(node.val);
                if (node.right != null) {
                    treeNodes.push(node.right);
                    break;
                }
            }
        }
        return linkedList;
    }


    public static List<Integer> postOrder(TreeNode node) {
        assert node != null;
        LinkedList<Integer> linkedList = new LinkedList<>();
        postOrder(node, linkedList);
        return linkedList;
    }


    private static void postOrder(TreeNode node, List<Integer> list) {
        if (node.left != null) {
            postOrder(node.left, list);
        }
        if (node.right != null) {
            postOrder(node.right, list);
        }
        list.add(node.val);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof TreeNode)) {
            return false;
        }
        TreeNode treeNode = (TreeNode) obj;
        if (this.val != treeNode.val) {
            return false;
        }
        if (this.left != null) {
            if (treeNode.left == null) {
                return false;
            }
            if (!this.left.equals(treeNode.left)) {
                return false;
            }
        } else if (treeNode.left != null) {
            return false;
        }
        if (this.right != null) {
            if (treeNode.right == null) {
                return false;
            }
            return this.right.equals(treeNode.right);
        }
        return treeNode.right == null;
    }

    private void createChildTreeNode(int[] arr, int index, TreeNode me) {
        if (index >= arr.length) {
            return;
        }
        me.val = arr[index];
        if (index * 2 + 1 < arr.length) {
            me.left = new TreeNode(arr[index * 2 + 1]);
            createChildTreeNode(arr, index * 2 + 1, me.left);
        }
        if (index * 2 + 2 < arr.length) {
            me.right = new TreeNode(arr[index * 2 + 2]);
            createChildTreeNode(arr, index * 2 + 2, me.right);
        }
    }

    private void createChildTreeNode(Integer[] arr, int index, TreeNode me) {
        if (index >= arr.length || arr[index] == null) {
            return;
        }
        me.val = arr[index];
        if (index * 2 + 1 < arr.length && arr[index * 2 + 1] != null) {
            me.left = new TreeNode(arr[index * 2 + 1]);
            createChildTreeNode(arr, index * 2 + 1, me.left);
        }
        if (index * 2 + 2 < arr.length && arr[index * 2 + 2] != null) {
            me.right = new TreeNode(arr[index * 2 + 2]);
            createChildTreeNode(arr, index * 2 + 2, me.right);
        }
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", TreeNode.class.getSimpleName() + "[", "]")
                .add("val=" + val)
                .add("left=" + left)
                .add("right=" + right)
                .toString();
    }
}
