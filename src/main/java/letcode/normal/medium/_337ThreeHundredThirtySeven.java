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
public class _337ThreeHundredThirtySeven {


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


    /**
     * Input: root = [3,2,3,null,3,null,1]
     * Output: 7
     * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
     * <p>
     * Input: root = [3,4,5,1,3,null,1]
     * Output: 9
     * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
     * <p>
     * [4,1,null,2,null,3]
     * 7
     * <p>
     * [2,1,3,null,4]
     * 7
     *
     * @param args
     */
    public static void main(String[] args) {
        TreeNode root = TreeNode.createUseLeetCode(new Integer[]{
                4, 1, null, 2, null, 3
        });
        System.out.println(new _337ThreeHundredThirtySeven().rob(root));
    }


}
