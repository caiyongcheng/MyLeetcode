package letcode.utils;

import datastructure.queue.LinkedQueue;
import datastructure.queue.Queue;
import datastructure.stack.LinkedStack;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Leetcode
 *
 * @author : CaiYongcheng
 * @since : 2020-06-28 20:45
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


    public static TreeNode createUseLeetCode(Integer[] data) {
        if (Objects.isNull(data) || data.length == 0) {
            return null;
        }
        int idx = 1;
        Queue<TreeNode> queue = new LinkedQueue<>();
        TreeNode root = new TreeNode(data[0]);
        queue.enQueue(root);
        while (idx < data.length && !queue.empty()) {
            TreeNode pop = queue.deQueue();
            //左节点
            if (Objects.nonNull(data[idx])) {
                pop.left = new TreeNode(data[idx]);
                queue.enQueue(pop.left);
            }
            ++idx;
            //右节点
            if (idx < data.length && Objects.nonNull(data[idx])) {
                pop.right = new TreeNode(data[idx]);
                queue.enQueue(pop.right);
            }
            ++idx;
        }
        return root;
    }
}
