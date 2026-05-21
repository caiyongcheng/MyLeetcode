package letcode.normal.medium;


import letcode.utils.Node;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/11/3 14:01
 * description 给定一个二叉树：  struct Node {   int val;   Node *left;   Node *right;   Node *next; } 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
 * 如果找不到下一个右侧节点，则将 next 指针设置为 NULL 。  初始状态下，所有 next 指针都被设置为 NULL 。
 *
 * 提示：
 *
 * 树中的节点数在范围 [0, 6000] 内
 * -100 <= Node.val <= 100
 * 进阶：
 *
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序的隐式栈空间不计入额外空间复杂度。
 */
public class _117 {



    public Node connect(Node root) {
        connectRight(root, null);
        return root;
    }

    public void connectRight(Node root, Node parent) {
        if (root == null) {
            return;
        }
        if (parent == null) {
            connectRight(root.right, root);
            connectRight(root.left, root);
            return;
        }
        if (parent.right != null && parent.right != root) {
            root.next = parent.right;
            connectRight(root.right, root);
            connectRight(root.left, root);
            return;
        }
        parent = parent.next;
        while (parent != null) {
            if (parent.left != null && parent.left != root) {
                root.next = parent.left;
                break;
            }
            if (parent.right != null && parent.right != root) {
                root.next = parent.right;
                break;
            }
            parent = parent.next;
        }
        connectRight(root.right, root);
        connectRight(root.left, root);
    }

}
