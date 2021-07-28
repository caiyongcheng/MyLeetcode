package letcode.offer.difficult;

import letcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Stack;

/**
 * 请实现两个函数，分别用来序列化和反序列化二叉树。  你需要设计一个算法来实现二叉树的序列化与反序列化。
 * 这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * 提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/xu-lie-hua-er-cha-shu-lcof 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-06-30 09:01
 **/
public class Offer_37_ThirtySeven {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder serializeStr = new StringBuilder();
        ArrayList<int[]> treeByArr = new ArrayList<>(16);
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> parentIdStack = new Stack<>();
        TreeNode now;
        int id = 0;
        int parentId = 0;
        nodeStack.push(root);
        parentIdStack.push(id);
        treeByArr.add(new int[]{-1, id, 3, root.val});
        while (!nodeStack.empty()) {
            now = nodeStack.pop();
            parentId = parentIdStack.pop();
            if (now.left != null) {
                treeByArr.add(new int[]{parentId, ++id, 0, now.left.val});
                nodeStack.add(now.left);
                parentIdStack.add(id);
            }
            if (now.right != null) {
                treeByArr.add(new int[]{parentId, ++id, 1, now.right.val});
                nodeStack.add(now.right);
                parentIdStack.add(id);
            }
        }
        treeByArr.sort(Comparator.comparingInt(e -> e[0]));
        for (int[] ints : treeByArr) {
            serializeStr.append(ints[0]).append('s').append(ints[1]).append('s').append(ints[2]).append('s').append(ints[3]).append('s');
        }
        return serializeStr.toString();
    }




    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || "".equals(data)) {
            return null;
        }
        HashMap<String, TreeNode> nodeHashMap = new HashMap<>();
        String[] datas = data.split("[s]");
        TreeNode parent = new TreeNode(Integer.parseInt(datas[3]));
        String id;
        nodeHashMap.put(datas[1], parent);
        for (int i = 4; i < datas.length - 1; i+=4) {
            id = datas[i+1];
            TreeNode child = new TreeNode(Integer.parseInt(datas[i + 3]));
            parent = nodeHashMap.get(datas[i]);
            nodeHashMap.put(id, child);
            if (parent != null) {
                if ("0".equals(datas[i+2])) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            }
        }
        return nodeHashMap.get(datas[1]);
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        Offer_37_ThirtySeven test = new Offer_37_ThirtySeven();
        String serializeStr = test.serialize(root);
        System.out.println(serializeStr);
        System.out.println(test.serialize(test.deserialize(serializeStr)));
    }


}
