package letcode.normal.medium;

import java.util.Arrays;

/**
 * @program: Leetcode
 * @description: 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。  来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/next-greater-element-ii 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-12-08 16:16
 */
public class _503FiveHundredThree {

    /**
     * 输入: [1,2,1]
     * 输出: [2,-1,2]
     * 解释: 第一个 1 的下一个更大的数是 2；
     * 数字 2 找不到下一个更大的数；
     * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/next-greater-element-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        int[] nums = {1, 2, 1};
        System.out.println(Arrays.toString(
                new _503FiveHundredThree().nextGreaterElements(nums)));
    }

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

}