package letcode.normal.medium;

import letcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树的根节点 root，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * 二叉树的节点个数的范围是 [0,1000]
 * -109 <= Node.val <= 109
 * -1000 <= targetSum <= 1000
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/path-sum-iii 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-09-28 14:28
 **/
public class _437 {

    public int pathSum(TreeNode root, int targetSum) {
        /*
         * 如果从根节点到叶节点的路径表示为 a0，a1，a2，a3...an
         * 那么长度为1的路径有n个，长度为2有n-1个，一直到长度为n的有1个
         * 共有 （1+n)*n/2 个。
         * 而从根到叶节点的路径最多有叶子节点那么多。
         * 那么在有了一颗二叉树的情况下，怎样移动叶子节点可以使得计算量最大呢？
         * 在路径长n的路径上移走叶节点，那么长度减少（n^2+n）-(n-1)^2-(n-1)/2
         * 化简得n。同理得到叶节点的路径（原长度l），如果作为叶节点那么增加长度为l+1，
         * 作为叶节点的兄弟节点则增加l。这表明了计算量最大的情况下，是只有一个叶节点的。
         * 此时的最大计算量为（设节点数为N）(N^2+N)/2,最小计算量为log2（N+1）*（N/2）
         * 整理得 最大时间复杂度 O（n2） 最小是O（nlogn）
         * 结合题目数据，完全可以穷举
         * 注意穷举的时候注意利用前缀和的关系
         */
        if (root == null) {
            return 0;
        }
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        return dps(root, targetSum, list);
    }


    public int dps(TreeNode root, int targetNum, List<Integer> preSum) {
        //计算前缀和
        preSum.add(preSum.get(preSum.size() - 1) + root.val);
        int count = calculate(preSum, targetNum);
        //如果是叶节点 那么计算当前路径
        if (root.left != null) {
            count += dps(root.left, targetNum, preSum);
        }
        if (root.right != null) {
            count += dps(root.right, targetNum, preSum);
        }
        //移除当前前缀和
        preSum.remove(preSum.size() - 1);
        return count;
    }


    public int calculate(List<Integer> preSum, int targetNum) {
        int count = 0;
        Integer sum = preSum.get(preSum.size() - 1);
        for (int i = 0; i < preSum.size() - 1; i++) {
            if (sum - preSum.get(i) == targetNum) {
                ++count;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(10);
        treeNode.left = new TreeNode(5);
        treeNode.right = new TreeNode(-3);
        treeNode.left.left = new TreeNode(3);
        treeNode.left.right = new TreeNode(2);
        treeNode.left.left.left = new TreeNode(3);
        treeNode.left.left.right = new TreeNode(-2);
        treeNode.left.right.right = new TreeNode(1);
        treeNode.right.right = new TreeNode(11);
        System.out.println(new _437().pathSum(treeNode, 8));
    }

}
