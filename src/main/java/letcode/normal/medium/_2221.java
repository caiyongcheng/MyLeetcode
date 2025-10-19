package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * You are given a 0-indexed integer array nums, where nums[i] is a digit between 0 and 9 (inclusive).
 * The triangular sum of nums is the value of the only element present in nums after the following process terminates:
 * Let nums comprise of n elements.
 * If n == 1, end the process. Otherwise,
 * create a new 0-indexed integer array newNums of length n - 1.
 * For each index i, where 0 <= i < n - 1,
 * assign the value of newNums[i] as (nums[i] + nums[i+1]) % 10,
 * where % denotes modulo operator.
 * Replace the array nums with newNums.
 * Repeat the entire process starting from step 1. Return the triangular sum of nums.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-09-30 14:12
 */
public class _2221 {

    static int[][] yanghuiTriangle;

    static {
        yanghuiTriangle = new int[1001][];
        yanghuiTriangle[1] = new int[]{1};
        yanghuiTriangle[2] = new int[]{1, 1};
        for (int i = 3; i < 1001; i++) {
            yanghuiTriangle[i] = new int[i];
            yanghuiTriangle[i][0] = 1;
            yanghuiTriangle[i][i - 1] = 1;
            for (int j = 1; j < i - 1; j++) {
                yanghuiTriangle[i][j] = (yanghuiTriangle[i - 1][j - 1] + yanghuiTriangle[i -1][j]) % 10;
            }
        }
    }

    public int triangularSum(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans += yanghuiTriangle[nums.length][i] * nums[i];
        }
        return ans % 10;
    }


    /**
     * Example 1:
     *
     *
     * Input: nums = [1,2,3,4,5]
     * Output: 8
     * Explanation:
     * The above diagram depicts the process from which we obtain the triangular sum of the array.
     * Example 2:
     *
     * Input: nums = [5]
     * Output: 5
     * Explanation:
     * Since there is only one element in nums, the triangular sum is the value of that element itself.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }



}
