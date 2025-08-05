package letcode.normal.easy;

import letcode.utils.Node;

import java.util.List;

/**
 * 给定一个 n 叉树的根节点  root ，返回 其节点值的 前序遍历 。  n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/2/18 08:52
 */
public class _589 {

    public List<Integer> preorder(Node root) {
        List<Integer> ans = new java.util.ArrayList<>();
        preorder(root, ans);
        return ans;
    }


    public void preorder(Node root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        for (Node node : root.children) {
            preorder(node, list);
        }
    }

    /**
     * 给定一个 n 叉树的根节点  root ，返回 其节点值的 前序遍历 。
     *
     * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
     *
     *
     * 示例 1：
     *
     *
     *
     * 输入：root = [1,null,3,2,4,null,5,6]
     * 输出：[1,3,5,6,2,4]
     * 示例 2：
     *
     *
     *
     * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
     * 输出：[1,2,3,6,7,11,14,4,8,12,5,9,13,10]
     * @param args
     */
    public static void main(String[] args) {

    }

}
