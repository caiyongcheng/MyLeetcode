package letcode.normal.easy;

import letcode.utils.TreeNode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: MyLeetcode
 * @description: 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。  
 *  示例：  输入：     
 * 1
 * \
 * 3     
 * /    
 * 2  
 * 输出： 1  
 * 解释： 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。 
 * 提示：  树中至少有 2 个节点。
 * 本题与 783 https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/ 相同  来源：力扣（LeetCode） 
 * 链接：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.easy
 * @author: 6JSh5rC456iL
 * @date: 2021-04-13 10:37
 **/
public class _530FiveHundredThirty {

    public int getMinimumDifference(TreeNode root) {
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

}
