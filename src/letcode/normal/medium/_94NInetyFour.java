package letcode.medium;

import letcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Leetcode
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * @author : CaiYongcheng
 * @date : 2020-07-28 23:08
 **/
public class _94NInetyFour {

    private ArrayList<Integer> list = new ArrayList<Integer>();

    private void myInorderTraversal(TreeNode root){
        if (null != root) {
            myInorderTraversal(root.left);
            list.add(root.val);
            myInorderTraversal(root.right);
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        myInorderTraversal(root);
        return list;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = null;
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        TreeNode.display(root);
        List<Integer> integers = new _94NInetyFour().inorderTraversal(root);
        System.out.println(integers);
    }
}
