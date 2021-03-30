package letcode.normal.medium;

import letcode.utils.FormatPrintUtils;

import java.util.Stack;

/**
 * @program: MyLeetcode
 * @description: 给你一个整数数组 nums 和一个正整数 k ，返回长度为 k 且最具 竞争力 的 nums 子序列。  
 * 数组的子序列是从数组中删除一些元素（可能不删除元素）得到的序列。  
 * 在子序列a 和子序列b 第一个不相同的位置上，如果a中的数字小于 b 中对应的数字，那么我们称子序列 a 比子序列 b（相同长度下）更具 竞争力 。 
 * 例如，[1,3,4] 比 [1,3,5] 更具竞争力，在第一个不相同的位置，也就是最后一个位置上，4 小于 5 。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/find-the-most-competitive-subsequence 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @date: 2021-03-29 15:22
 **/
public class _1673OneThousandSixHundredSeventyThree {

    public int[] mostCompetitive(int[] nums, int k) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[k];
        for (int i = 0; i < nums.length; i++) {
            if (nums.length-i+stack.size() <= k) {
                stack.push(nums[i]);
            } else {
                while (!stack.empty() && nums.length-i+stack.size() > k && stack.peek() > nums[i]) {
                    stack.pop();
                }
                if (stack.size() < k) {
                    stack.push(nums[i]);
                }
            }
        }
        while (!stack.empty()) {
            ans[--k] = stack.pop();
        }
        return ans;
    }

    /**
     * 示例 1：
     * 输入：nums = [3,5,2,6], k = 2
     * 输出：[2,6]
     * 解释：在所有可能的子序列集合 {[3,5], [3,2], [3,6], [5,2], [5,6], [2,6]} 中，[2,6] 最具竞争力。
     *
     * 示例 2：
     * 输入：nums = [2,4,3,3,5,4,9,6], k = 4
     * 输出：[2,3,3,4]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-the-most-competitive-subsequence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatPrintUtils.formatArray(new _1673OneThousandSixHundredSeventyThree().mostCompetitive(
                new int[]{2,4,3,3,5,4,9,6},
                4
        )));
    }

}
