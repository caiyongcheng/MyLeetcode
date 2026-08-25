package letcode.normal.easy;

/**
 * 3718. Smallest Missing Multiple of K
 * Difficulty: Easy
 * Link: https://leetcode.cn/problems/smallest-missing-multiple-of-k/
 * <p>
 * Given an integer array nums and an integer k , return the smallest positive multiple of k that is
 * missing from nums .
 * <p>
 * A multiple of k is any positive integer divisible by k .
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [8,2,3,4,6], k = 2
 * <p>
 * Output: 10
 * <p>
 * Explanation:
 * <p>
 * The multiples of k = 2 are 2, 4, 6, 8, 10, 12... and the smallest multiple missing from nums is 10.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,4,7,10,15], k = 5
 * <p>
 * Output: 5
 * <p>
 * Explanation:
 * <p>
 * The multiples of k = 5 are 5, 10, 15, 20... and the smallest multiple missing from nums is 5.
 * <p>
 * Constraints:
 * <p>
 * - 1 <= nums.length <= 100
 * <p>
 * - 1 <= nums[i] <= 100
 * <p>
 * - 1 <= k <= 100
 */
public class _3718 {

    public int missingMultiple(int[] nums, int k) {
        int[] countMap = new int[101];
        for (int num : nums) {
            countMap[num]++;
        }

        int multiple = k;
        while (multiple < countMap.length) {
            if (countMap[multiple] == 0) {
                return multiple;
            }
            multiple += k;
        }
        return multiple;
    }
}
