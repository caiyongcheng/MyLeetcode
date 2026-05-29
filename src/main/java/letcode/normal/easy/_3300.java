package letcode.normal.easy;

import letcode.utils.SolutionTestMethod;

/**
 * 3300. Minimum Element After Replacement With Digit Sum
 * Difficulty: Easy
 * Link: https://leetcode.cn/problems/minimum-element-after-replacement-with-digit-sum/
 * You are given an integer array nums . You replace each element in nums with the sum of its digits.
 * Return the minimum element in nums after all replacements.
 */
public class _3300 {

    @SolutionTestMethod
    public int minElement(int[] nums) {
        int minDigitSum = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            nums[i] = getDigitSum(nums[i]);
            minDigitSum = Math.min(minDigitSum, nums[i]);
        }
        return minDigitSum;
    }

    public int getDigitSum(int num) {
        int digitSum = 0;
        while (num > 0) {
            digitSum += num % 10;
            num /= 10;
        }
        return digitSum;
    }

}
