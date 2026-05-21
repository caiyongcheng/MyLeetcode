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

}
