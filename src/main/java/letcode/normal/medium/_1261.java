package letcode.normal.medium;

import letcode.utils.TestCaseInputUtils;
import letcode.utils.TestUtil;
import letcode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * root.val == 0 如果 treeNode.val == x 且 treeNode.left != null，那么 treeNode.left.val == 2 * x + 1 如果 treeNode.val == x 且 treeNode.right != null，
 * 那么 treeNode.right.val == 2 * x + 2 现在这个二叉树受到「污染」，所有的 treeNode.val 都变成了 -1。
 * 请你先还原二叉树，然后实现 FindElements 类：  FindElements(TreeNode* root) 用受污染的二叉树初始化对象，
 * 你需要先把它还原。 bool find(int target) 判断目标值 target 是否存在于还原后的二叉树中并返回结果。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/3/12 09:01
 */
public class _1261 {

    public TreeNode root;

    public Set<Integer> valSet;


    public _1261(TreeNode root) {
        this.root = root;
        Queue<TreeNode> queues = new ArrayDeque<>();
        root.val = 0;
        queues.offer(root);
        valSet = new HashSet<>();
        while (!queues.isEmpty()) {
            TreeNode parent = queues.poll();
            valSet.add(parent.val);
            if (parent.left != null) {
                parent.left.val = (parent.val << 1) + 1;
                queues.offer(parent.left);
            }
            if (parent.right != null) {
                parent.right.val = (parent.val << 1) + 2;
                queues.offer(parent.right);
            }
        }
    }

    public boolean find(int target) {
        return valSet.contains(target);
    }

    /**
     * 示例 1：
     *
     *
     *
     * 输入：
     * ["FindElements","find","find"]
     * [[[-1,null,-1]],[1],[2]]
     * 输出：
     * [null,false,true]
     * 解释：
     * FindElements findElements = new FindElements([-1,null,-1]);
     * findElements.find(1); // return False
     * findElements.find(2); // return True
     * 示例 2：
     *
     *
     *
     * 输入：
     * ["FindElements","find","find","find"]
     * [[[-1,-1,-1,-1,-1]],[1],[3],[5]]
     * 输出：
     * [null,true,true,false]
     * 解释：
     * FindElements findElements = new FindElements([-1,-1,-1,-1,-1]);
     * findElements.find(1); // return True
     * findElements.find(3); // return True
     * findElements.find(5); // return False
     * 示例 3：
     *
     *
     *
     * 输入：
     * ["FindElements","find","find","find","find"]
     * [[[-1,null,-1,-1,null,-1]],[2],[3],[4],[5]]
     * 输出：
     * [null,true,false,false,true]
     * 解释：
     * FindElements findElements = new FindElements([-1,null,-1,-1,null,-1]);
     * findElements.find(2); // return True
     * findElements.find(3); // return False
     * findElements.find(4); // return False
     * findElements.find(5); // return True
     * @param args
     */
    @SuppressWarnings("all")
    public static void main(String[] args) {
        _1261 test = new _1261(TreeNode.createUseLeetCode(TestCaseInputUtils.getIntegerArr(
                "[-1,null,-1,-1,null,-1]"
        )));
        System.out.println(TestUtil.operation(test, "[\"find\",\"find\",\"find\",\"find\"]", "[[2],[3],[4],[5]]"));
    }

}
