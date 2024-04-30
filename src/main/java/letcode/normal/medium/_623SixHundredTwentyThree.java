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

/**
 * 给定一个二叉树的根root和两个整数 val 和depth，在给定的深度depth处添加一个值为 val 的节点行。
 * 注意，根节点root位于深度1。  加法规则如下:  给定整数depth，对于深度为depth - 1 的每个非空树节点 cur ，
 * 创建两个值为 val 的树节点作为 cur 的左子树根和右子树根。 cur 原来的左子树应该是新的左子树根的左子树。
 * cur 原来的右子树应该是新的右子树根的右子树。 如果 depth == 1 意味着depth - 1根本没有深度，那么创建一个树节点，
 * 值 val 作为整个原始树的新根，而原始树就是新根的左子树。
 * 来源：力扣（LeetCode） 链接：https://leetcode.cn/problems/add-one-row-to-tree 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2022-08-05 08:59
 **/
public class _623SixHundredTwentyThree {

    private int targetDepth;

    private int targetVal;

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode newRoot = new TreeNode();
            newRoot.val = val;
            newRoot.left = root;
            return newRoot;
        }
        targetDepth = depth - 1;
        targetVal = val;
        recur(root, 1);
        return root;
    }


    public void recur(TreeNode curNode, int curDep) {
        if (curDep == targetDepth) {
            TreeNode newLeftSubNode = new TreeNode();
            newLeftSubNode.val = targetVal;
            newLeftSubNode.left = curNode.left;
            curNode.left = newLeftSubNode;
            TreeNode newRightSubNode = new TreeNode();
            newRightSubNode.val = targetVal;
            newRightSubNode.right = curNode.right;
            curNode.right = newRightSubNode;
            return;
        }
        if (null != curNode.left) {
            recur(curNode.left, curDep + 1);
        }
        if (null != curNode.right) {
            recur(curNode.right, curDep + 1);
        }
    }


}
