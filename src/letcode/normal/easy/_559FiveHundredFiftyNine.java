package letcode.normal.easy;

import letcode.utils.Node;

import java.util.Stack;

/**
 * 给定一个 N 叉树，找到其最大深度。  最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。  N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。
 *   来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-05-08 15:51
 **/
public class _559FiveHundredFiftyNine {

    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        Stack<Node> nodes = new Stack<>();
        Stack<Integer> integers = new Stack<>();
        nodes.push(root);
        integers.push(1);
        int nowHeight;
        Node currentNode;
        int ans = 0;
        while (!nodes.empty()){
            currentNode = nodes.pop();
            nowHeight = integers.pop();
            if (currentNode.children == null || currentNode.children.isEmpty() && nowHeight > ans) {
                ans = nowHeight;
            }
            for (Node child : currentNode.children) {
                nodes.add(child);
                integers.add(nowHeight+1);
            }
        }
        return ans;
    }


    public static void main(String[] args) {

    }


}
