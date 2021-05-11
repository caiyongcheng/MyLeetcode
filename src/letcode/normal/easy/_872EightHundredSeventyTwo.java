package letcode.normal.easy;

import letcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * 请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是叶相似的。 
 * 如果给定的两个根结点分别为root1 和root2的树是叶相似的，则返回true；否则返回 false 。 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/leaf-similar-trees 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-05-10 09:03
 **/
public class _872EightHundredSeventyTwo {

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = getLeafNode(root1);
        List<Integer> list2 = getLeafNode(root2);
        if (list1.size() != list2.size()) {
            return false;
        }
        Iterator<Integer> itor1 = list1.iterator();
        Iterator<Integer> itro2 = list2.iterator();
        while (itor1.hasNext() && itro2.hasNext()) {
            if (!itor1.next().equals(itro2.next())) {
                return false;
            }
        }
        return true;
    }


    public List<Integer> getLeafNode(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Stack<TreeNode> stack = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();
        TreeNode temporary;
        stack.add(root);
        while (!stack.empty()) {
            temporary = stack.pop();
            if (null == temporary.right && null == temporary.left) {
                list.add(temporary.val);
            } else {
                if (temporary.left != null) {
                    stack.add(temporary.left);
                }
                if (temporary.right != null) {
                    stack.add(temporary.right);
                }
            }
        }
        return list;
    }

}
