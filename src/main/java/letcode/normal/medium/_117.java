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

    /**
     * 输入：root = [1,2,3,4,5,null,7]
     * 输出：[1,#,2,3,#,4,5,7,#]
     * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化输出按层序遍历顺序（由 next 指针连接），'#' 表示每层的末尾。
     * 示例 2：
     *
     * 输入：root = []
     * 输出：[]
     * @param args
     */
    public static void main(String[] args) {
        Node node = new Node(1);
        node.left = new Node(2);
        node.left.left = new Node(4);
        node.left.right = new Node(5);
        node.right = new Node(3);
        node.right.right = new Node(7);
        new _117().connect(node);
        System.out.println(1);
    }

}
