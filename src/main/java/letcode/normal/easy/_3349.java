package letcode.normal.easy;

import letcode.utils.TestUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array nums of n integers and an integer k,
 * determine whether there exist two adjacent subarrays of length k such that both subarrays are strictly increasing.
 * Specifically, check if there are two subarrays starting at indices a and b (a < b),
 * where:  Both subarrays nums[a..a + k - 1] and nums[b..b + k - 1] are strictly increasing.
 * The subarrays must be adjacent, meaning b = a + k.
 * Return true if it is possible to find two such subarrays, and false otherwise.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-10-19 08:54
 */
public class _3349 {

    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        // https://leetcode.cn/problems/adjacent-increasing-subarrays-detection-i/solutions/3801862/jian-ce-xiang-lin-di-zeng-zi-shu-zu-i-by-njbb/?envType=daily-question&envId=2025-10-19
        List<int[]> increaseRanges = calculation(nums);
        return check(increaseRanges, k);
    }

    private List<int[]> calculation(List<Integer> nums) {
        int size = nums.size();
        List<int[]> increaseRanges = new ArrayList<>();
        int left = 0;
        int right = 1;
        while (right < size) {
            if (nums.get(right) <= nums.get(right - 1)) {
                increaseRanges.add(new int[]{left, right - 1});
                left = right;
            }
            ++right;
        }
        increaseRanges.add(new int[]{left, size - 1});
        return increaseRanges;
    }


    private boolean check(List<int[]> increaseRanges, int k) {
        int rangesSize = increaseRanges.size();
        int secondRangeIdx = 0;
        int secondLeft = 0;
        for (int[] increaseRange : increaseRanges) {
            if (increaseRange[1] - increaseRange[0] + 1 < k) {
                continue;
            }
            while (increaseRange[0] + k - 1 <= increaseRange[1]) {
                secondLeft = increaseRange[0] + k;
                while (secondRangeIdx < rangesSize && secondLeft > increaseRanges.get(secondRangeIdx)[1]) {
                    ++secondRangeIdx;
                }
                if (secondRangeIdx >= rangesSize) {
                    return false;
                }
                if (increaseRanges.get(secondRangeIdx)[1] - secondLeft + 1 >= k) {
                    return true;
                }
                // like kmp
                increaseRange[0] += (increaseRanges.get(secondRangeIdx)[1] - secondLeft + 1);
            }
        }
        return false;
    }


    /**
     * Example 1:
     *
     * Input: nums = [2,5,7,8,9,2,3,4,3,1], k = 3
     *
     * Output: true
     *
     * Explanation:
     *
     * The subarray starting at index 2 is [7, 8, 9], which is strictly increasing.
     * The subarray starting at index 5 is [2, 3, 4], which is also strictly increasing.
     * These two subarrays are adjacent, so the result is true.
     * Example 2:
     *
     * Input: nums = [1,2,3,4,4,4,4,5,6,7], k = 5
     *
     * Output: false
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test("=[6,13,-17,-20,2], =2");
    }

}
