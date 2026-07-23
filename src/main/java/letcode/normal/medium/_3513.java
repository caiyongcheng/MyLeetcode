package letcode.normal.medium;

/**
 * 3513. Number of Unique XOR Triplets I
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/number-of-unique-xor-triplets-i/
 * <p>
 * You are given an integer array nums of length n , where nums is a permutation of the numbers in the
 * range [1, n] .
 * <p>
 * A XOR triplet is defined as the XOR of three elements nums[i] XOR nums[j] XOR nums[k] where i <= j
 * <= k .
 * <p>
 * Return the number of unique XOR triplet values from all possible triplets (i, j, k) .
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2]
 * <p>
 * Output: 2
 * <p>
 * Explanation:
 * <p>
 * The possible XOR triplet values are:
 * <p>
 * - (0, 0, 0) &rarr; 1 XOR 1 XOR 1 = 1
 * <p>
 * - (0, 0, 1) &rarr; 1 XOR 1 XOR 2 = 2
 * <p>
 * - (0, 1, 1) &rarr; 1 XOR 2 XOR 2 = 1
 * <p>
 * - (1, 1, 1) &rarr; 2 XOR 2 XOR 2 = 2
 * <p>
 * The unique XOR values are {1, 2} , so the output is 2.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [3,1,2]
 * <p>
 * Output: 4
 * <p>
 * Explanation:
 * <p>
 * The possible XOR triplet values include:
 * <p>
 * - (0, 0, 0) &rarr; 3 XOR 3 XOR 3 = 3
 * <p>
 * - (0, 0, 1) &rarr; 3 XOR 3 XOR 1 = 1
 * <p>
 * - (0, 0, 2) &rarr; 3 XOR 3 XOR 2 = 2
 * <p>
 * - (0, 1, 2) &rarr; 3 XOR 1 XOR 2 = 0
 * <p>
 * The unique XOR values are {0, 1, 2, 3} , so the output is 4.
 * <p>
 * Constraints:
 * <p>
 * - 1 <= n == nums.length <= 10 5
 * <p>
 * - 1 <= nums[i] <= n
 * <p>
 * - nums is a permutation of integers from 1 to n .
 */
public class _3513 {

    public int uniqueXorTriplets(int[] nums) {
        /*
        如果 nums.length < 3 那么结果是固定的
        如果 nums.length >= 3
        对于 [1, nums] 之中的数，有 x ^ x = 0, 0 ^ nums[i] = nums[i]
        对于 大于nums.length且最小的2的幂次方 2^y 来说
        如果 nums.length < 2^y, 在y位上，有 0 ^ 0 ^ 0 = 0, 那么异或结果肯定小于 2^y
        需要考虑的是 [nums.length+1, 2^y) 这部分的数怎么获得
        对于 位于[nums.length+1, 2^y)的数k, 如果他的某一个是 0，那么取 0^0^0,如果某一个是1,那么取0^0^1。
        按照这种取值方式，最大需要的数是 2^(y-1), 有 2^(y-1) <= nums.length < 2^y。
        所以结果为 2^y, 因为0也要考虑到
         */
        return nums.length < 3 ? nums.length : (1 << (32 - Integer.numberOfLeadingZeros(2)));
    }

}
