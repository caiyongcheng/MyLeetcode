package letcode.normal.medium;

import letcode.utils.TestCaseInputUtils;

/**
 * You are given a 0-indexed array nums consisting of positive integers.
 * You can do the following operation on the array any number of times:
 * Choose an integer i such that 0 <= i < nums.length - 1 and nums[i] <= nums[i + 1].
 * Replace the element nums[i + 1] with nums[i] + nums[i + 1] and delete the element nums[i] from the array.
 * Return the value of the largest element that you can possibly obtain in the final array.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/3/14 09:03
 */
public class _2789 {

    public long maxArrayValue(int[] nums) {
        /*
        对于nums[0.i] 如果是非递减的 我们就可以将其合并
        依次进行合并操作即可得到 最终结果
        要注意 合并应该从后往前 例如 1 5 3 3 =》 1 5 6 =》 12
         */
        long curData = nums[nums.length - 1];
        long ans = curData;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (curData >= nums[i]) {
                curData += nums[i];
            } else {
                curData = nums[i];
            }
            if (ans < curData) {
                ans = curData;
            }
        }
        return ans;
    }


}
