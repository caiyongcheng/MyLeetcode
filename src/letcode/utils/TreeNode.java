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
    public TreeNode(int x){
        this.val = x;
    }
    public static void display(TreeNode root){
        if(root != null){
            display(root.left);
            display(root.right);
            System.out.print(root.val+" ");
        }
    }

}
