package letcode.utils;

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

    public static void display(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            display(root.left);
            display(root.right);
        }
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
