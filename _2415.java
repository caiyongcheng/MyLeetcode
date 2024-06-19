package letcode.normal.medium;

import letcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/12/15 10:56
 * description 给你一棵 完美 二叉树的根节点 root ，请你反转这棵树中每个 奇数 层的节点值。
 * 例如，假设第 3 层的节点值是 [2,1,3,4,7,11,29,18] ，那么反转后它应该变成 [18,29,11,7,4,3,1,2] 。 反转后，返回树的根节点。
 * 完美 二叉树需满足：二叉树的所有父节点都有两个子节点，且所有叶子节点都在同一层。
 * 节点的 层数 等于该节点到根节点之间的边数。
 */
public class _2415 {

    static Map<Integer, List<TreeNode>> groupByLevel;

    public TreeNode reverseOddLevels(TreeNode root) {
        groupByLevel = new HashMap<>(1 << 15);
        for (int i = 1; i < 15; i+=2) {
            groupByLevel.put(i, new ArrayList<>(1 << i));
        }
        /*
        遍历 保存奇数层节点 反转即可
         */
        ldr(root, 0);
        for (List<TreeNode> nodeList : groupByLevel.values()) {
            int l = 0;
            int r = nodeList.size() - 1;
            TreeNode lNode;
            TreeNode rNode;
            int tmpVal;
            while (l < r) {
                lNode = nodeList.get(l);
                rNode = nodeList.get(r);
                tmpVal = lNode.val;
                lNode.val = rNode.val;
                rNode.val = tmpVal;
                ++l;
                --r;
            }
        }
        return root;
    }

    public void ldr(TreeNode root, int level) {
        if ((level & 1) == 1) {
            groupByLevel.get(level).add(root);
        }
        if (root.left != null) {
            ldr(root.left, level + 1);
            ldr(root.right, level + 1);
        }
    }


    /**
     * 输入：root = [7,13,11]
     * 输出：[7,11,13]
     * 解释：
     * 在第 1 层的节点分别是 13、11 ，反转后为 11、13 。
     * 示例 3：
     *
     * 输入：root = [0,1,2,0,0,0,0,1,1,1,1,2,2,2,2]
     * 输出：[0,2,1,0,0,0,0,2,2,2,2,1,1,1,1]
     * 解释：奇数层由非零值组成。
     * 在第 1 层的节点分别是 1、2 ，反转后为 2、1 。
     * 在第 3 层的节点分别是 1、1、1、1、2、2、2、2 ，反转后为 2、2、2、2、1、1、1、1 。
     * @param args
     */
    public static void main(String[] args) {

    }

}
