package letcode.normal.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 3731. Find Missing Elements
 * Difficulty: Easy
 * Link: https://leetcode.cn/problems/find-missing-elements/
 * <p>
 * You are given an integer array nums consisting of unique integers.
 * <p>
 * Originally, nums contained every integer within a certain range. However, some integers might have
 * gone missing from the array.
 * <p>
 * The smallest and largest integers of the original range are still present in nums .
 * <p>
 * Return a sorted list of all the missing integers in this range. If no integers are missing, return
 * an empty list.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,4,2,5]
 * <p>
 * Output: [3]
 * <p>
 * Explanation:
 * <p>
 * The smallest integer is 1 and the largest is 5, so the full range should be [1,2,3,4,5] . Among
 * these, only 3 is missing.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [7,8,6,9]
 * <p>
 * Output: []
 * <p>
 * Explanation:
 * <p>
 * The smallest integer is 6 and the largest is 9, so the full range is [6,7,8,9] . All integers are
 * already present, so no integer is missing.
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [5,1]
 * <p>
 * Output: [2,3,4]
 * <p>
 * Explanation:
 * <p>
 * The smallest integer is 1 and the largest is 5, so the full range should be [1,2,3,4,5] . The
 * missing integers are 2, 3, and 4.
 * <p>
 * Constraints:
 * <p>
 * - 2 <= nums.length <= 100
 * <p>
 * - 1 <= nums[i] <= 100
 */
public class _3731 {

    public List<Integer> findMissingElements(int[] nums) {
        int[] hash = new int[101];
        for (int num : nums) {
            hash[num]++;
        }

        List<Integer> ans = new ArrayList<>();

        int first = -1;
        int end = 101;

        while (first < 99 && hash[++first] == 0);

        while (end > 0 && hash[--end] == 0);

        while (first <= end) {
            if (hash[first] == 0) {
                ans.add(first);
            }
            ++first;
        }
        return ans;

    }
}
