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

}
