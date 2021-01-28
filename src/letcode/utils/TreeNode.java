package letcode.utils;

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

    public TreeNode(int x) {
        this.val = x;
    }

    public TreeNode(int[] arr) {
        createChildTreeNode(arr, 0, this);
    }

    public static void display(TreeNode root) {
        if (root != null) {
            display(root.left);
            display(root.right);
            System.out.print(root.val + " ");
        }
    }

    private void createChildTreeNode(int[] arr, int index, TreeNode me) {
        if (index >= arr.length) {
            return;
        }
        me.val = arr[index];
        if (index * 2 < arr.length) {
            me.left = new TreeNode(arr[index * 2]);
            createChildTreeNode(arr, index * 2, me.left);
        }
        if (index * 2 + 1 < arr.length) {
            me.right = new TreeNode(arr[index * 2 + 1]);
            createChildTreeNode(arr, index * 2 + 1, me.right);
        }
    }

}
