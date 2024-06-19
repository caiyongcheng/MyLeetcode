/*
 * 版权所有（c）<2021><蔡永程>
 *
 * 反996许可证版本1.0
 *
 * 在符合下列条件的情况下，特此免费向任何得到本授权作品的副本（包括源代码、文件和/或相关内容，以
 * 下统称为“授权作品”）的个人和法人实体授权：被授权个人或法人实体有权以任何目的处置授权作品，包括
 * 但不限于使用、复制，修改，衍生利用、散布，发布和再许可：
 *
 * 1. 个人或法人实体必须在许可作品的每个再散布或衍生副本上包含以上版权声明和本许可证，不得自行修
 * 改。
 * 2. 个人或法人实体必须严格遵守与个人实际所在地或个人出生地或归化地、或法人实体注册地或经营地（
 * 以较严格者为准）的司法管辖区所有适用的与劳动和就业相关法律、法规、规则和标准。如果该司法管辖区
 * 没有此类法律、法规、规章和标准或其法律、法规、规章和标准不可执行，则个人或法人实体必须遵守国际
 * 劳工标准的核心公约。
 * 3. 个人或法人不得以任何方式诱导、暗示或强迫其全职或兼职员工或其独立承包人以口头或书面形式同意
 * 直接或间接限制、削弱或放弃其所拥有的，受相关与劳动和就业有关的法律、法规、规则和标准保护的权利
 * 或补救措施，无论该等书面或口头协议是否被该司法管辖区的法律所承认，该等个人或法人实体也不得以任
 * 何方法限制其雇员或独立承包人向版权持有人或监督许可证合规情况的有关当局报告或投诉上述违反许可证
 * 的行为的权利。
 *
 * 该授权作品是"按原样"提供，不做任何明示或暗示的保证，包括但不限于对适销性、特定用途适用性和非侵
 * 权性的保证。在任何情况下，无论是在合同诉讼、侵权诉讼或其他诉讼中，版权持有人均不承担因本软件或
 * 本软件的使用或其他交易而产生、引起或与之相关的任何索赔、损害或其他责任。
 */

package letcode.normal.medium;

import letcode.utils.FormatUtils;
import letcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
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
 * @date 2021-09-28 14:28
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
