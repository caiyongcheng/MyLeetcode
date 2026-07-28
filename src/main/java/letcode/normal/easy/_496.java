package letcode.normal.easy;

import letcode.utils.TestCaseOutputUtils;

import java.util.Stack;

/**
 * 给你两个 没有重复元素 的数组nums1 和nums2，其中nums1是nums2的子集。
 * 请你找出 nums1中每个元素在nums2中的下一个比其大的值。
 * nums1中数字x的下一个更大元素是指x在nums2中对应位置的右边的第一个比x大的元素。如果不存在，对应位置输出 -1 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/next-greater-element-i 著作权归领扣网络所有。
 * <p>
 * 1 <= nums1.length <= nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 104
 * nums1和nums2中所有整数 互不相同
 * nums1 中的所有整数同样出现在 nums2 中
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-greater-element-i
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-10-26 10:12
 **/
public class _496 {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        /*
         * 需要一个数据结构 保存元素-》下一个再右侧，且比他大的元素位置。
         * 因为数据范围已经给定，比较小，所以使用数组。
         * 使用单调栈
         */
        int[] ans = new int[nums1.length];
        int[] valToIndex = new int[10001];
        Stack<Integer> stack = new Stack<>();
        stack.push(nums2[0]);
        for (int index = 1; index < nums2.length; index++) {
            while (!stack.isEmpty() && nums2[index] > stack.peek()) {
                valToIndex[stack.pop()] = nums2[index];
            }
            stack.push(nums2[index]);
        }
        while (!stack.isEmpty()) {
            valToIndex[stack.pop()] = -1;
        }
        for (int index = 0; index < nums1.length; index++) {
            ans[index] = valToIndex[nums1[index]];
        }
        return ans;
    }


}
