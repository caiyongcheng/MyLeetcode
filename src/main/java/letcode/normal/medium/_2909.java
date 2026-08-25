package letcode.normal.medium;

import letcode.utils.SolutionTestMethod;

/**
 * 2909. Minimum Sum of Mountain Triplets II
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/minimum-sum-of-mountain-triplets-ii/
 * <p>
 * You are given a 0-indexed array nums of integers.
 * <p>
 * A triplet of indices (i, j, k) is a mountain if:
 * <p>
 * - i < j < k
 * <p>
 * - nums[i] < nums[j] and nums[k] < nums[j]
 * <p>
 * Return the minimum possible sum of a mountain triplet of nums . If no such triplet exists, return -1
 * .
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [8,6,1,5,3]
 * Output: 9
 * Explanation: Triplet (2, 3, 4) is a mountain triplet of sum 9 since:
 * - 2 < 3 < 4
 * - nums[2] < nums[3] and nums[4] < nums[3]
 * And the sum of this triplet is nums[2] + nums[3] + nums[4] = 9. It can be shown that there are no
 * mountain triplets with a sum of less than 9.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [5,4,8,7,10,2]
 * Output: 13
 * Explanation: Triplet (1, 3, 5) is a mountain triplet of sum 13 since:
 * - 1 < 3 < 5
 * - nums[1] < nums[3] and nums[5] < nums[3]
 * And the sum of this triplet is nums[1] + nums[3] + nums[5] = 13. It can be shown that there are no
 * mountain triplets with a sum of less than 13.
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [6,5,4,3,4,5]
 * Output: -1
 * Explanation: It can be shown that there are no mountain triplets in nums.
 * <p>
 * Constraints:
 * <p>
 * - 3 <= nums.length <= 10 5
 * <p>
 * - 1 <= nums[i] <= 10 8
 */
public class _2909 {

    @SolutionTestMethod
    public int minimumSum(int[] nums) {
        int[] l2RtMinNums = new int[nums.length];
        int[] r2LMinNums = new int[nums.length];

        processMinArr(nums, l2RtMinNums, 1, 1);
        processMinArr(nums, r2LMinNums, nums.length - 2, -1);

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            if (l2RtMinNums[i] < nums[i] && r2LMinNums[i] < nums[i]) {
                ans = Math.min(ans, l2RtMinNums[i] + nums[i] + r2LMinNums[i]);
            }
        }

        return ans ==  Integer.MAX_VALUE ? -1 : ans;

    }

    public void processMinArr(int[] nums, int[] minArr, int startIdx, int step) {
        minArr[startIdx - step] = nums[startIdx - step];
        int curMinNum = minArr[startIdx - step];
        for (int i = 1; i < minArr.length; i++) {
            minArr[startIdx] = curMinNum;
            if (curMinNum > nums[startIdx]) {
                curMinNum = nums[startIdx];
            }
            startIdx += step;
        }
    }

}
