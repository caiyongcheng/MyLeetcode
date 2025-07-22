package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * You are given an array of positive integers nums and want to erase a subarray containing unique elements.
 * The score you get by erasing the subarray is equal to the sum of its elements.
 * Return the maximum score you can get by erasing exactly one subarray.
 * An array b is called to be a subarray of a if it forms a contiguous subsequence of a,
 * that is, if it is equal to a[l],a[l+1],...,a[r] for some (l,r).
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-07-22 09:43
 */
public class _1695 {

    public int maximumUniqueSubarray(int[] nums) {
        int[] num2PreSumIdxMap = new int[10001];
        int[] uniqueSubArrPreSumArr = new int[nums.length + 1];
        int ans = nums[0];
        int leftIdx = 0;
        uniqueSubArrPreSumArr[1] = nums[0];
        num2PreSumIdxMap[nums[0]] = 1;

        for (int i = 1; i < nums.length; i++) {
            uniqueSubArrPreSumArr[i + 1] = uniqueSubArrPreSumArr[i] + nums[i];
            leftIdx = Math.max(leftIdx, num2PreSumIdxMap[nums[i]]);
            ans = Math.max(ans, uniqueSubArrPreSumArr[i + 1] - uniqueSubArrPreSumArr[leftIdx]);
            num2PreSumIdxMap[nums[i]] = i + 1;
        }
        return ans;
    }

    /**
     * Example 1:
     *
     * Input: nums = [4,2,4,5,6]
     * Output: 17
     * Explanation: The optimal subarray here is [2,4,5,6].
     * Example 2:
     *
     * Input: nums = [5,2,1,2,5,2,1,2,5]
     * Output: 8
     * Explanation: The optimal subarray here is [5,2,1] or [1,2,5].
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }


}
