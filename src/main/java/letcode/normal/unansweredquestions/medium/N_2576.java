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

}
