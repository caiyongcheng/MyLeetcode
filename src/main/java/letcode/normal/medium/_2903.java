package letcode.normal.medium;

import letcode.utils.TestCaseInputUtils;
import letcode.utils.TestCaseOutputUtils;

/**
 * 给你一个下标从 0 开始、长度为 n 的整数数组 nums ，以及整数 indexDifference 和整数 valueDifference 。
 * 你的任务是从范围 [0, n - 1] 内找出  2 个满足下述所有条件的下标 i 和 j ：
 * abs(i - j) >= indexDifference
 * 且 abs(nums[i] - nums[j]) >= valueDifference 返回整数数组 answer。
 * 如果存在满足题目要求的两个下标，则 answer = [i, j] ；否则，answer = [-1, -1] 。
 * 如果存在多组可供选择的下标对，只需要返回其中任意一组即可。  注意：i 和 j 可能 相等 。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-05-27 15:06
 */
public class _2903 {


    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        int currentIdx = indexDifference;
        int leftMinIdx = 0;
        int leftMaxIdx = 0;
        if (nums.length <= indexDifference) {
            return new int[]{-1, -1};
        }
        while (true) {
            if (nums[currentIdx] - nums[leftMinIdx] >= valueDifference) {
                return new int[]{leftMinIdx, currentIdx};
            }
            if (nums[leftMaxIdx] - nums[currentIdx] >= valueDifference) {
                return new int[]{leftMaxIdx, currentIdx};
            }
            ++currentIdx;
            if (currentIdx >= nums.length) {
                return new int[]{-1, -1};
            }
            if (nums[currentIdx - indexDifference] > nums[leftMaxIdx]) {
                leftMaxIdx = currentIdx - indexDifference;
            } else if (nums[currentIdx - indexDifference] < nums[leftMinIdx]) {
                leftMinIdx = currentIdx - indexDifference;
            }
        }
    }


}
