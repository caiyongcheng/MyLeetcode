package letcode.normal.medium;

import letcode.utils.TreeNode;

import java.util.Objects;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/9/18 8:56
 * description 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
 * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。  给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
 */
public class _337 {


    public int rob(TreeNode root) {
        /*
       每个节点保存选择当前值与不选择当前值的最大值
         */
        int[] searchRst = search(root);
        return Integer.max(searchRst[0], searchRst[1]);
    }


    public int[] search(TreeNode root) {
        if (Objects.isNull(root)) {
            return new int[]{0, 0};
        }
        if (Objects.isNull(root.left) && Objects.isNull(root.right)) {
            return new int[]{root.val, 0};
        }
        int[] searchLeftRst = search(root.left);
        int[] searchRightRst = search(root.right);
        return new int[]{
                root.val + searchLeftRst[1] + searchRightRst[1],
                Integer.max(searchLeftRst[0], searchLeftRst[1]) + Integer.max(searchRightRst[0], searchRightRst[1])
        };
    }


}
