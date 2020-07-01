package letcode.easy;

import letcode.utils.TreeNode;

/**
 * Leetcode
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author : CaiYongcheng
 * @date : 2020-06-30 12:39
 **/
public class OneHundredEight {

    public static int[] tnums;

    public static void sortedArrayToBST(TreeNode node, int left, int right) {
        if(left > right) return ;
        int mid = (left+right)/2;
        node = new TreeNode(tnums[mid]);
        sortedArrayToBST(node.left,left,mid-1);
        sortedArrayToBST(node.right,mid+1,right);
    }

    /**
     * 示例:
     * 给定有序数组: [-10,-3,0,5,9],
     * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
     *       0
     *      / \
     *    -3   9
     *    /   /
     *  -10  5
     * @param nums
     * @return
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        tnums = nums;
        TreeNode root = null;
        sortedArrayToBST(root,0,tnums.length-1);
        return root;
    }

    public static void main(String[] args) {
        int[] nums = {-10,-3,0,5,9};
        TreeNode treeNode = sortedArrayToBST(nums);
        TreeNode.display(treeNode);
    }




}
