package letcode.normal.easy;



import letcode.utils.Node;

import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * 给定一个 n 叉树的根节点 root ，返回 其节点值的 后序遍历 。  n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 * 进阶：递归法很简单，你可以使用迭代法完成此题吗?
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/2/19 10:13
 */
public class _590 {

    public List<Integer> postorder(Node root) {
        /*
        不使用递归那就使用栈吧 本质是一样的
         */
        List<Integer> ans = new java.util.ArrayList<>();
        if (Objects.isNull(root)) {
            return ans;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            Node parent = stack.pop();
            ans.add(0, parent.val);
            List<Node> children = parent.children;
            if (Objects.isNull(children)) {
                continue;
            }
            children.forEach(stack::push);
        }
        return ans;
    }

    /**
     * 示例 1：
     *
     *
     *
     * 输入：root = [1,null,3,2,4,null,5,6]
     * 输出：[5,6,3,2,4,1]
     * 示例 2：
     *
     *
     *
     * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
     * 输出：[2,6,14,11,7,3,12,8,4,13,9,10,5,1]
     * @param args
     */
    public static void main(String[] args) {

    }

}
