package letcode.normal.easy;

/**
 * 1752. Check if Array Is Sorted and Rotated
 * Difficulty: Easy
 * Link: https://leetcode.cn/problems/check-if-array-is-sorted-and-rotated/
 * Given an array nums, return true if nums can be obtained by rotating a sorted array
 * any number of positions (including zero). Otherwise, return false.
 */
public class _1752 {

    public boolean check(int[] nums) {
        int splitCnt = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                ++splitCnt;
                if (splitCnt > 1) {
                    return false;
                }
            }
        }
        return splitCnt == 0 || nums[nums.length - 1] <= nums[0];
    }

}
