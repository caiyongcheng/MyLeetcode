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
 * @date: 2021-04-13 10:49
 **/
public class _1379OneThousandThreeHundredSeventyNine {


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
        System.out.println(new _1379OneThousandThreeHundredSeventyNine().getTargetCopy(
                r1,
                r2,
                right
        ));
    }

}
