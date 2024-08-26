package letcode.normal.unansweredquestions.medium;

import letcode.utils.TestUtil;

/**
 * Given an integer array nums and an integer k, return true if it is possible to divide this array into k non-empty subsets whose sums are all equal.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-08-26 16:40
 */
public class N_698 {

    /**
     * dp做法
     * class Solution {
     *     public boolean canPartitionKSubsets(int[] nums, int k) {
     *         int all = Arrays.stream(nums).sum();
     *         if (all % k != 0) {
     *             return false;
     *         }
     *         int per = all / k;
     *         Arrays.sort(nums);
     *         int n = nums.length;
     *         if (nums[n - 1] > per) {
     *             return false;
     *         }
     *         boolean[] dp = new boolean[1 << n];
     *         int[] curSum = new int[1 << n];
     *         dp[0] = true;
     *         for (int i = 0; i < 1 << n; i++) {
     *             if (!dp[i]) {
     *                 continue;
     *             }
     *             for (int j = 0; j < n; j++) {
     *                 if (curSum[i] + nums[j] > per) {
     *                     break;
     *                 }
     *                 if (((i >> j) & 1) == 0) {
     *                     int next = i | (1 << j);
     *                     if (!dp[next]) {
     *                         curSum[next] = (curSum[i] + nums[j]) % per;
     *                         dp[next] = true;
     *                     }
     *                 }
     *             }
     *         }
     *         return dp[(1 << n) - 1];
     *     }
     * }
     * @param nums
     * @param k
     * @return
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }
        int subSetSum = sum / k;
        for (int num : nums) {
            if (num > subSetSum) {
                return false;
            }
        }
        return dfs(nums, 0, new int[k], subSetSum);
    }


    private boolean dfs(int[] nums, int index, int[] setSums, int subSetSum) {
        for (int i = 0; i < setSums.length; i++) {
            if (setSums[i] + nums[index] <= subSetSum) {
                setSums[i] += nums[index];
                if (index == nums.length - 1) {
                    return setSums[i] == subSetSum;
                }
                if (dfs(nums, index + 1, setSums, subSetSum)) {
                    return true;
                }
                setSums[i] -= nums[index];
            }
        }
        return false;
    }


    /**
     * Example 1:
     *
     * Input: nums = [4,3,2,3,5,2,1], k = 4
     * Output: true
     * Explanation: It is possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
     * Example 2:
     *
     * Input: nums = [1,2,3,4], k = 3
     * Output: false
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(N_698.class);
    }

}
