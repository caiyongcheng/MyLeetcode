package letcode.normal.medium;

import letcode.utils.SolutionTestMethod;
import letcode.utils.TreeNode;


/**
 * 2265. Count Nodes Equal to Average of Subtree
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/count-nodes-equal-to-average-of-subtree/
 * <p>
 * Given the root of a binary tree, return the number of nodes where the value of the node is equal to
 * the average of the values in its subtree .
 * <p>
 * Note:
 * <p>
 * - The average of n elements is the sum of the n elements divided by n and rounded down to the
 * nearest integer.
 * <p>
 * - A subtree of root is a tree consisting of root and all of its descendants.
 * <p>
 * Example 1:
 * <p>
 * Input: root = [4,8,5,0,1,null,6]
 * Output: 5
 * Explanation:
 * For the node with value 4: The average of its subtree is (4 + 8 + 5 + 0 + 1 + 6) / 6 = 24 / 6 = 4.
 * For the node with value 5: The average of its subtree is (5 + 6) / 2 = 11 / 2 = 5.
 * For the node with value 0: The average of its subtree is 0 / 1 = 0.
 * For the node with value 1: The average of its subtree is 1 / 1 = 1.
 * For the node with value 6: The average of its subtree is 6 / 1 = 6.
 * <p>
 * Example 2:
 * <p>
 * Input: root = [1]
 * Output: 1
 * Explanation: For the node with value 1: The average of its subtree is 1 / 1 = 1.
 * <p>
 * Constraints:
 * <p>
 * - The number of nodes in the tree is in the range [1, 1000] .
 * <p>
 * - 0 <= Node.val <= 1000
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {
 }
 *     TreeNode(int val) {
 this.val = val;
 }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *
 }
 *
 }
 */
public class _2265 {

    @SolutionTestMethod
    public int averageOfSubtree(TreeNode root) {
        int[] ans = new int[3];
        dfs(root, ans);
        return ans[2];
    }

    public void dfs(TreeNode root, int[] result) {

        if (root.left == null && root.right == null) {
            result[0] = root.val;
            result[1] = 1;
            result[2] = 1;
            return;
        }

        int[] leftResult = new int[] {0, 0, 0};
        if (root.left != null) {
            dfs(root.left, leftResult);
        }

        int[] rightResult = new int[] {0, 0, 0};
        if (root.right != null) {
            dfs(root.right, rightResult);
        }

        result[0] += root.val + leftResult[0] + rightResult[0];
        result[1] += 1 + leftResult[1] + rightResult[1];
        result[2] += leftResult[2] + rightResult[2] + (result[0] / result[1] == root.val ? 1 : 0);

    }

}
