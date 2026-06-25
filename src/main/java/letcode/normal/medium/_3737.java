package letcode.normal.medium;

/**
 * 3737. Count Subarrays With Majority Element I
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/count-subarrays-with-majority-element-i/
 * You are given an integer array nums and an integer target .
 * Return the number of subarrays of nums in which target is the majority element .
 * The majority element of a subarray is the element that appears strictly more than half of the times in that subarray.
 */
public class _3737 {

    public int countMajoritySubarrays(int[] nums, int target) {
        int[] preCount = new int[nums.length];

        preCount[0] = nums[0] == target ? 1 : 0;
        for (int i = 1; i < nums.length; i++) {
            preCount[i] = preCount[i - 1] + (nums[i] == target ? 1 : 0);
        }

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (preCount[j] - (i - 1 < 0 ? 0 :  preCount[i - 1]) << 1 > j - i + 1) {
                    ++count;
                }
            }
        }
        return count;

    }
}
