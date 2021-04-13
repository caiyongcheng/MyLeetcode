package letcode.normal.easy;

import letcode.utils.TreeNode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @program: MyLeetcode
 * @description: 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * 注意：本题与 530：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/ 相同
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.easy
 * @author: 6JSh5rC456iL
 * @date: 2021-04-13 09:30
 **/
public class _783SevenHundredEightyThree {


    public int minDiffInBST(TreeNode root) {
        LinkedList<Integer> integers = new LinkedList<>();
        int ans = Integer.MAX_VALUE;
        fs(root, integers);
        Iterator<Integer> iterator = integers.iterator();
        int current = iterator.next();
        int next = iterator.next();
        while (iterator.hasNext()) {
            ans = Math.min(ans, Math.abs(next - current));
            current = next;
            next = iterator.next();
        }
        ans = Math.min(ans, Math.abs(next - current));
        return ans;
    }


    public void fs(TreeNode root, List<Integer> list) {
        if (root == null) {
            return ;
        }
        fs(root.left, list);
        list.add(root.val);
        fs(root.right, list);
    }


    /**
     * 输入：root = [4,2,6,1,3]
     * 输出：1
     *
     * 输入：root = [1,0,48,null,null,12,49]
     * 输出：1
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _783SevenHundredEightyThree().minDiffInBST(new TreeNode(new Integer[]{71,62,84,14,null,null,88})));
    }

}
