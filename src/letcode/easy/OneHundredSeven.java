package letcode.easy;

import letcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Leetcode
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * @author : CaiYongcheng
 * @date : 2020-06-29 21:46
 **/
public class OneHundredSeven {

    public static List<List<Integer>> lists;

    /**
     * 例如：
     * 给定二叉树 [3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回其自底向上的层次遍历为：
     * [
     *   [15,7],
     *   [9,20],
     *   [3]
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        lists = new ArrayList<List<Integer>>();
        levelOrderBottom(root,1);
        List<List<Integer>> tmp = new ArrayList<List<Integer>>();
        for(int i = lists.size()-1; i > -1; --i){
            tmp.add(lists.get(i));
        }
        return tmp;
    }

    public static  void levelOrderBottom(TreeNode root, int n) {
        if (root != null) {
            if(lists.size()<n){
                ArrayList<Integer> integers = new ArrayList<>();
                integers.add(root.val);
                lists.add(integers);
            }else{
                lists.get(n-1).add(root.val);
            }
            levelOrderBottom(root.left,n+1);
            levelOrderBottom(root.right,n+1);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        List<List<Integer>> tmp = levelOrderBottom(root);
        for (List<Integer> list : tmp) {
            System.out.print("[  ");
            for (Integer integer : list) {
                System.out.print(integer + " ");
            }
            System.out.println(" ]");
        }
    }

}
