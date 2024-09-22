package letcode.normal.unansweredquestions.medium;

import letcode.utils.TestUtil;

import java.util.Arrays;

/**
 * 给你一个下标从 0 开始的整数数组 nums 。  一开始，所有下标都没有被标记。你可以执行以下操作任意次：
 * 选择两个 互不相同且未标记 的下标 i 和 j ，满足 2 * nums[i] <= nums[j] ，标记下标 i 和 j 。
 * 请你执行上述操作任意次，返回 nums 中最多可以标记的下标数目。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-09-12 10:59
 */
public class N_2576 {

    public int maxNumOfMarkedIndices(int[] nums) {
        Arrays.sort(nums);
        int searchTarget = nums[nums.length - 1] / 2;
        int left = 0;
        int right = nums.length - 2;
        int mid;
        if (nums[left] > searchTarget) {
            return 0;
        }
        while (true) {
            mid = (left + right) >> 1;
            if (mid == left) {
                break;
            }
            if (nums[mid] > searchTarget) { 
                right = mid;
            } else {
                left = mid;
            }
        }
        return 0;
    }

    /**
     * Example 1:
     *
     * Input: nums = [3,5,2,4]
     * Output: 2
     * Explanation: In the first operation: pick i = 2 and j = 1, the operation is allowed because 2 * nums[2] <= nums[1]. Then mark index 2 and 1.
     * It can be shown that there's no other valid operation so the answer is 2.
     * Example 2:
     *
     * Input: nums = [9,2,5,4]
     * Output: 4
     * Explanation: In the first operation: pick i = 3 and j = 0, the operation is allowed because 2 * nums[3] <= nums[0]. Then mark index 3 and 0.
     * In the second operation: pick i = 1 and j = 2, the operation is allowed because 2 * nums[1] <= nums[2]. Then mark index 1 and 2.
     * Since there is no other operation, the answer is 4.
     * Example 3:
     *
     * Input: nums = [7,6,8]
     * Output: 0
     * Explanation: There is no valid operation to do, so the answer is 0.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(N_2576.class);
    }

}
