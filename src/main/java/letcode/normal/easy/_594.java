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

}
