package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.Arrays;

/**
 * Return the number of non-empty subsequences of nums such that the sum of the minimum and maximum element on
 * it is less or equal to target. Since the answer may be too large, return it modulo 109 + 7.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-06-29 23:12
 */
public class _1498 {

    static int[] modArr = new int[100001];
    static int mod = 1000000007;

    static {
        modArr[0] = 1;
        for (int i = 1; i < modArr.length; i++) {
            modArr[i] = (modArr[i - 1] << 1) % mod;
        }
    }

    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);

        long ans = 0;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            if (nums[left] + nums[right] <= target) {
                ans = (ans + modArr[right - left]) % mod;
                ++left;
            } else {
                --right;
            }
        }
        return (int) ans;
    }



    /**
     * Example 1:
     *
     * Input: nums = [3,5,6,7], target = 9
     * Output: 4
     * Explanation: There are 4 subsequences that satisfy the condition.
     * [3] -> Min value + max value <= target (3 + 3 <= 9)
     * [3,5] -> (3 + 5 <= 9)
     * [3,5,6] -> (3 + 6 <= 9)
     * [3,6] -> (3 + 6 <= 9)
     * Example 2:
     *
     * Input: nums = [3,3,6,8], target = 10
     * Output: 6
     * Explanation: There are 6 subsequences that satisfy the condition. (nums can have repeated numbers).
     * [3] , [3] , [3,3], [3,6] , [3,6] , [3,3,6]
     * Example 3:
     *
     * Input: nums = [2,3,3,4,6,7], target = 12
     * Output: 61
     * Explanation: There are 63 non-empty subsequences, two of them do not satisfy the condition ([6,7], [7]).
     * Number of valid subsequences (63 - 2 = 61).
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test("=[27,21,14,2,15,1,19,8,12,24,21,8,12,10,11,30,15,18,28,14,26,9,2,24,23,11,7,12,9,17,30,9,28,2,14,22,19,19,27,6,15,12,29,2,30,11,20,30,21,20,2,22,6,14,13,19,21,10,18,30,2,20,28,22],=31");
    }




}
