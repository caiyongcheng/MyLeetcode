package letcode.normal.medium;

import letcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

/**
 * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/2/18 09:00
 */
public class _103 {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        /*
        层序遍历加入判别标识即可
         */
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> nodeQueue = new java.util.LinkedList<>();
        Queue<Integer> levelQueue = new java.util.LinkedList<>();
        nodeQueue.offer(root);
        levelQueue.offer(0);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int level = levelQueue.poll();
            if (Objects.isNull(node)) {
                continue;
            }
            if (ans.size() <= level) {
                ans.add(new ArrayList<>());
            }
            List<Integer> ansItem = ans.get(level);
            if (level % 2 == 0) {
                ansItem.add(node.val);
            } else {
                ansItem.add(0, node.val);
            }
            nodeQueue.offer(node.left);
            nodeQueue.offer(node.right);
            levelQueue.offer(level + 1);
            levelQueue.offer(level + 1);
        }
        return ans;
    }


    /**
     * 示例 1：
     *
     *
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：[[3],[20,9],[15,7]]
     * 示例 2：
     *
     * 输入：root = [1]
     * 输出：[[1]]
     * 示例 3：
     *
     * 输入：root = []
     * @param args
     */
    public static void main(String[] args) {

    }
    



}
