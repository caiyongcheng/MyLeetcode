package letcode.normal.medium;

import letcode.utils.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2024/2/17 22:05
 * description 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。  树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
 */
public class _429FourHundredTwentyNine {

    public List<List<Integer>> levelOrder(Node root) {
        /*
        用栈即可
        不应该用栈的 应该用队列更合适
         */
        // 空节点直接返回
        if (Objects.isNull(root)) {
            return new ArrayList<>();
        }
        List<List<Integer>> ans = new ArrayList<>();
        Stack<Node> nodeStack = new Stack<>();
        Stack<Integer> nodeLevelStack = new Stack<>();
        int childSize;
        nodeLevelStack.push(0);
        nodeStack.push(root);
        while (!nodeStack.empty()) {
            Node curNode = nodeStack.pop();
            Integer curNodeLevel = nodeLevelStack.pop();
            if (ans.size() <= curNodeLevel) {
                ans.add(new ArrayList<>());
            }
            List<Integer> levelNodeList = ans.get(curNodeLevel);
            levelNodeList.add(curNode.val);
            if (Objects.isNull(curNode.children)) {
                continue;
            }
            childSize = curNode.children.size();
            for (int idx = childSize - 1; idx >= 0; idx--) {
                nodeStack.push(curNode.children.get(idx));
                nodeLevelStack.push(curNodeLevel + 1);
            }
        }
        return ans;
    }


    /**
     * 示例 1：
     *
     *
     *
     * 输入：root = [1,null,3,2,4,null,5,6]
     * 输出：[[1],[3,2,4],[5,6]]
     * 示例 2：
     *
     *
     *
     * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
     * 输出：[[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
     * @param args
     */
    public static void main(String[] args) {

    }

}
