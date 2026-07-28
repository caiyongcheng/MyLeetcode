package letcode.normal.medium;

import letcode.utils.TreeNode;

import java.util.Stack;

/**
 * 给你一个二叉树的根节点root。设根节点位于二叉树的第 1 层，而根节点的子节点位于第 2 层，依此类推。
 * 请返回层内元素之和 最大 的那几层（可能只有一层）的层号，并返回其中最小 的那个。
 * 来源：力扣（LeetCode） 链接：https://leetcode.cn/problems/maximum-level-sum-of-a-binary-tree 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-08-01 11:26
 **/
public class _1161 {

    /**
     * 层序遍历 保存层内元素值最大的记录
     *
     * @param root 根节点
     * @return 层内元素之和 最大 的那几层（可能只有一层）的层号，并返回其中最小 的那个。
     */
    public int maxLevelSum(TreeNode root) {
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<TreeNode> tempStack = new Stack<>();
        TreeNode pop;
        nodeStack.add(root);
        int currentSum;
        int maxSum = Integer.MIN_VALUE;
        int maxLevel = -1;
        int currentLevel = 1;
        while (!nodeStack.empty()) {
            currentSum = 0;
            tempStack.clear();
            while (!nodeStack.empty()) {
                pop = nodeStack.pop();
                currentSum += pop.val;
                if (pop.left != null) {
                    tempStack.push(pop.left);
                }
                if (pop.right != null) {
                    tempStack.push(pop.right);
                }
            }
            if (currentSum > maxSum) {
                maxSum = currentSum;
                maxLevel = currentLevel;
            }
            while (!tempStack.empty()) {
                nodeStack.push(tempStack.pop());
            }
            ++currentLevel;
        }
        return maxLevel;
    }

}
