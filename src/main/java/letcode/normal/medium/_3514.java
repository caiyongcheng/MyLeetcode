package letcode.normal.medium;

/**
 * 3514. Number of Unique XOR Triplets II
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/number-of-unique-xor-triplets-ii/
 * <p>
 * You are given an integer array nums .
 * <p>
 * A XOR triplet is defined as the XOR of three elements nums[i] XOR nums[j] XOR nums[k] where i <= j
 * <= k .
 * <p>
 * Return the number of unique XOR triplet values from all possible triplets (i, j, k) .
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3]
 * <p>
 * Output: 2
 * <p>
 * Explanation:
 * <p>
 * The possible XOR triplet values are:
 * <p>
 * - (0, 0, 0) &rarr; 1 XOR 1 XOR 1 = 1
 * <p>
 * - (0, 0, 1) &rarr; 1 XOR 1 XOR 3 = 3
 * <p>
 * - (0, 1, 1) &rarr; 1 XOR 3 XOR 3 = 1
 * <p>
 * - (1, 1, 1) &rarr; 3 XOR 3 XOR 3 = 3
 * <p>
 * The unique XOR values are {1, 3} . Thus, the output is 2.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [6,7,8,9]
 * <p>
 * Output: 4
 * <p>
 * Explanation:
 * <p>
 * The possible XOR triplet values are {6, 7, 8, 9} . Thus, the output is 4.
 * <p>
 * Constraints:
 * <p>
 * - 1 <= nums.length <= 1500
 * <p>
 * - 1 <= nums[i] <= 1500
 */
public class _3514 {

    public int uniqueXorTriplets(int[] nums) {
        int[] twoNumsXorResultSet = new int[2048];
        for (int k : nums) {
            for (int num : nums) {
                twoNumsXorResultSet[k ^ num] = 1;
            }
        }

        int[] result = new int[2048];
        for (int i = 0; i < twoNumsXorResultSet.length; i++) {
            if (twoNumsXorResultSet[i] == 1) {
                for (int num : nums) {
                    result[i ^ num] = 1;
                }
            }
        }

        int ans = 0;
        for (int isResult : result) {
            ans += isResult;
        }
        return ans;

    }
}
