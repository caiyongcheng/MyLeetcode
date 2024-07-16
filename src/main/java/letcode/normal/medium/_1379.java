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
import java.util.ArrayList;
import java.util.Stack;


/**
 * @program: MyLeetcode
 * @description: 给你两棵二叉树，原始树 original 和克隆树 cloned，以及一个位于原始树 original中的目标节点target。
 * 其中，克隆树 cloned是原始树 original的一个 副本 。
 * 请找出在树cloned中，与target相同的节点，并返回对该节点的引用（在 C/C++ 等有指针的语言中返回 节点指针，其他语言返回节点本身）。
 *   注意：  你 不能 对两棵二叉树，以及 target节点进行更改。
 * 只能 返回对克隆树cloned中已有的节点的引用。 进阶：如果树中允许出现值相同的节点，你将如何解答？
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @since: 2021-04-13 10:49
 **/
public class _1379 {


    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == target) {
            return cloned;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> ans = new Stack<>();
        TreeNode pop1;
        TreeNode pop2;
        stack.push(original);
        ans.push(cloned);
        while (!stack.empty()) {
            if (stack.peek() == target) {
                return ans.peek();
            }
            pop1 = stack.pop();
            pop2 = ans.pop();
            if (pop1.left != null) {
                stack.push(pop1.left);
                ans.push(pop2.left);
            }
            if (pop1.right != null) {
                stack.push(pop1.right);
                ans.push(pop2.right);
            }
        }
        return null;
    }


    /**
     * 输入: tree = [7,4,3,null,null,6,19], target = 3
     * 输出: 3
     * 解释: 上图画出了树 original 和 cloned。target 节点在树 original 中，用绿色标记。答案是树 cloned 中的黄颜色的节点（其他示例类似）。
     *
     * 示例 2:
     * 输入: tree = [7], target =  7
     * 输出: 7
     *
     * 示例 3:
     * 输入: tree = [8,null,6,null,5,null,4,null,3,null,2,null,1], target = 4
     * 输出: 4
     *
     * 示例 4:
     * 输入: tree = [1,2,3,4,5,6,7,8,9,10], target = 5
     * 输出: 5
     *
     * 示例 5:
     * 输入: tree = [1,2,null,3], target = 2
     * 输出: 2
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        TreeNode r1 = new TreeNode(new Integer[]{1,2,null,3});
        TreeNode r2 = new TreeNode(new Integer[]{1,2,null,3});
        TreeNode right = r1.left;
        System.out.println(new _1379().getTargetCopy(
                r1,
                r2,
                right
        ));
    }

}
