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
import java.util.List;

/**
 * 给定一个二叉树（具有根结点root），一个目标结点target，和一个整数值 K 。
 * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/all-nodes-distance-k-in-binary-tree 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-07-28 10:30
 **/
public class _863 {

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        /**
         * 先找到以target为根节点的，距离为k的节点。
         * 再找target的父节点下，另一棵距离子树为k-1的节点。
         * 依次向上直到根结点完成。
         */
        ArrayList<Integer> ans = new ArrayList<>();
        ArrayList<TreeNode> parent = new ArrayList<>();
        dfs(root, target, parent);
        distanceForRoot(target, ans, k);
        for (int i = 1; i < parent.size() && k > 0; i++) {
            --k;
            //父节点
            if (0 == k) {
                ans.add(parent.get(i).val);
                break;
            }
            if (parent.get(i).left == parent.get(i-1) && null != parent.get(i).right) {
                distanceForRoot(parent.get(i).right, ans, k-1);
            } else if (parent.get(i).right == parent.get(i-1) && null != parent.get(i).left) {
                distanceForRoot(parent.get(i).left, ans, k-1);
            }
        }
        return ans;
    }

    public void distanceForRoot(TreeNode root, List<Integer> list, int k) {
        if (0 == k) {
            list.add(root.val);
            return ;
        }
        if (root.left != null) {
            distanceForRoot(root.left, list, k-1);
        }
        if (root.right != null) {
            distanceForRoot(root.right, list, k-1);
        }
    }

    public boolean dfs(TreeNode root, TreeNode target, List<TreeNode> list) {
        if (root == target) {
            list.add(root);
            return true;
        }
        if (root.left != null && dfs(root.left, target, list)) {
            list.add(root);
            return true;
        }
        if (root.right != null && dfs(root.right, target, list)) {
            list.add(root);
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(5);
        treeNode.left.left = new TreeNode(6);
        treeNode.left.right = new TreeNode(2);
        treeNode.left.right.left = new TreeNode(7);
        treeNode.left.right.right = new TreeNode(4);
        treeNode.right = new TreeNode(1);
        treeNode.right.left = new TreeNode(0);
        treeNode.right.right = new TreeNode(8);
        System.out.println(FormatUtils.formatList(new _863().distanceK(treeNode, treeNode.right.left, 3)));
    }


}
