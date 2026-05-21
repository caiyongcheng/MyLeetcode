package letcode.normal.easy;

import letcode.utils.TestUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a 0-indexed integer array nums and two integers key and k.
 * A k-distant index is an index i of nums for which there exists at least one index j such that |i - j| <= k and nums[j] == key.
 * Return a list of all k-distant indices sorted in increasing order.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-06-24 10:55
 */
public class _2200 {

    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        List<Integer> ans = new ArrayList<>();
        int[] matchIdxArr = new int[nums.length];

        int startIdx;
        int endIdx;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != key) {
                continue;
            }
            startIdx = Math.max(0, i - k);
            endIdx = Math.min(nums.length - 1, i + k);
            for (int j = startIdx; j <= endIdx; j++) {
                matchIdxArr[j] = 1;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (matchIdxArr[i] == 1) {
                ans.add(i);
            }
        }
        return ans;
    }

}
