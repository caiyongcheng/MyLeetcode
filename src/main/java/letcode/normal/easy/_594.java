package letcode.normal.easy;

import letcode.utils.TestUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * We define a harmonious array as an array where the difference
 * between its maximum value and its minimum value is exactly 1.
 * Given an integer array nums, return the length of its longest
 * harmonious subsequence among all its possible subsequences.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-06-30 09:19
 */
public class _594 {

    public int findLHS(int[] nums) {
        Map<Integer, Integer> num2Cnt = new HashMap<>(nums.length);
        for (int num : nums) {
            num2Cnt.put(num, num2Cnt.getOrDefault(num, 0) + 1);
        }

        int ans = 0;
        for (int num : nums) {
            ans = Math.max(
                    ans,
                    num2Cnt.get(num) + num2Cnt.getOrDefault(num + 1, -nums.length)
            );
        }
        return ans;
    }

    /**
     * Example 1:
     *
     * Input: nums = [1,3,2,2,5,2,3,7]
     *
     * Output: 5
     *
     * Explanation:
     *
     * The longest harmonious subsequence is [3,2,2,2,3].
     *
     * Example 2:
     *
     * Input: nums = [1,2,3,4]
     *
     * Output: 2
     *
     * Explanation:
     *
     * The longest harmonious subsequences are [1,2], [2,3], and [3,4], all of which have a length of 2.
     *
     * Example 3:
     *
     * Input: nums = [1,1,1,1]
     *
     * Output: 0
     *
     * Explanation:
     *
     * No harmonic subsequence exists.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
