package letcode.normal.medium;

import java.util.Deque;
import java.util.Stack;

/**
 * @program: MyLeetcode
 * @description: 给你一个整数数组 nums ，数组中共有 n 个整数。
 * 132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
 * 如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。
 *   进阶：很容易想到时间复杂度为 O(n^2) 的解决方案，你可以设计一个时间复杂度为 O(n logn) 或 O(n) 的解决方案吗？
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/132-pattern 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @date: 2021-03-24 14:04
 **/
public class _456FourHundredFiftySix {

    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        Stack<Integer> candidateK = new Stack<>();
        candidateK.push(nums[n - 1]);
        int max = Integer.MIN_VALUE;
        for (int i = n - 2; i >= 0; --i) {
            if (nums[i] < max) {
                return true;
            }
            while (!candidateK.isEmpty() && nums[i] > candidateK.peek()) {
                max = candidateK.pop();
            }
            if (nums[i] > max) {
                candidateK.push(nums[i]);
            }
        }
        return false;
    }

    /**
     * 示例 1：
     * 输入：nums = [1,2,3,4]
     * 输出：false
     * 解释：序列中不存在 132 模式的子序列。
     *
     * 示例 2：
     * 输入：nums = [3,1,4,2]
     * 输出：true
     * 解释：序列中有 1 个 132 模式的子序列： [1, 4, 2] 。
     * 示例 3：
     * 输入：nums = [-1,3,2,0]
     * 输出：true
     * 解释：序列中有 3 个 132 模式的的子序列：[-1, 3, 2]、[-1, 3, 0] 和 [-1, 2, 0] 。
     * 1 7 3 5 3 8 7 2 1
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/132-pattern
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _456FourHundredFiftySix().find132pattern(new int[]{1,3,2,4,5,6,7,8,9,10}));
    }

}
