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


}
