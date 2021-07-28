package letcode.normal.easy;

import letcode.utils.TreeNode;

/**
 * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为2或0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。
 * 更正式地说，root.val = min(root.left.val, root.right.val) 总成立。  给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/second-minimum-node-in-a-binary-tree 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-07-27 10:31
 **/
public class _671SixHundredSeventyOne {


    public int findSecondMinimumValue(TreeNode root) {
        /*
         * 按照题目描述
         * 可以知道在树中，根节点是最小的节点, 所以第二小的值
         * 要么是根节点的左右子节点的最大值
         * 要么是根节点的最小子节点对应字数的第二小值
         */
        if (root == null) {
            return -1;
        }
        return dfs(root, root.val);
    }

    public int dfs(TreeNode root, int limit) {
        if (root == null) {
            return -1;
        }
        int lVal = dfs(root.left, limit);
        int rVal = dfs(root.right, limit);
        int val = lVal == -1 ? rVal : (rVal == -1 ? lVal : Math.min(rVal, lVal));
        return root.val > limit ? (val == -1 ? root.val : Math.min(root.val, val)) : val;
    }



    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        System.out.println(new _671SixHundredSeventyOne().findSecondMinimumValue(root));
    }



}
