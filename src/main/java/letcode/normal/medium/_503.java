package letcode.normal.medium;

import letcode.utils.TestCaseInputUtils;
import letcode.utils.TestCaseOutputUtils;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @program: Leetcode
 * @description: 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。  来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/next-greater-element-ii 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-12-08 16:16
 */
public class _503 {


    public int[] nextGreaterElements(int[] nums) {
        int[] result = new int[nums.length];
        boolean flag;
        for (int index = 0; index < nums.length; index++) {
            flag = false;
            for (int size = 1; size < nums.length; ++size) {
                if (nums[(index + size) % nums.length] > nums[index]) {
                    flag = true;
                    result[index] = nums[(index + size) % nums.length];
                    break;
                }
            }
            if (!flag) {
                result[index] = -1;
            }
        }
        return result;
    }


    public int[] nextGreaterElements2(int[] nums) {
        /**
         * 维护一个单调栈即可
         */
        int[] ans = new int[nums.length];
        Arrays.fill(ans, -1);
        int len = nums.length << 1;
        Deque<Integer> stack = new ArrayDeque<>(nums.length + 1);
        int idx;
        for (int i = 0; i < len; i++) {
            idx = i % nums.length;
            while (!stack.isEmpty() && nums[stack.peek()] < nums[idx]) {
                ans[stack.pop()] = nums[idx];
            }
            if (i < nums.length) {
                stack.push(i);
            }
        }
        return ans;
    }

}
