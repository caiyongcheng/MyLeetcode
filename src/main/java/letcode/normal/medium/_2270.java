package letcode.normal.medium;

import letcode.utils.TestUtil; /**
 * You are given a 0-indexed integer array nums of length n.
 * nums contains a valid split at index i if the following are true:  The sum of the first i + 1 elements is greater than or equal to the sum of the last n - i - 1 elements.
 * There is at least one element to the right of i. That is, 0 <= i < n - 1. Return the number of valid splits in nums.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-01-13 16:30
 */
public class _2270 {

    public int waysToSplitArray(int[] nums) {
        long floorSum = 0L;
        for (int num : nums) {
            floorSum += num;
        }

        int ans = 0;
        long curSum = 0;
        floorSum = (floorSum >> 1) + (floorSum & 1);
        for (int i = 0; i < nums.length - 1; i++) {
            curSum += nums[i];
            if (curSum >= floorSum) {
                ans++;
            }
        }
        return ans;
    }


}
