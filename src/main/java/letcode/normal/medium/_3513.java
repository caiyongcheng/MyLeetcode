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
         n < 3 时直接返回 n

         n >= 3 时，令 2^k 为大于 n 的最小 2 的幂：
         1. 异或上界 —— 所有元素 ≤ n < 2^k，三数异或结果 < 2^k
         2. [0, n] 全覆盖 —— x ^ x ^ x = x 得 [1, n]；1 ^ 2 ^ 3 = 0 得 0
         3. [n+1, 2^k-1] 的构造：
            设 P = 2^(k-1)，对任意 v ∈ [n+1, 2^k-1]，v 在第 k 位为 1
            取 c = P（≤ n），则需 a ^ b = v ^ P ∈ [1, P-1] ⊆ [1, n]
            [1, P-1] 内任意值均可由其中两数异或得到（x ^ 1 或 2 ^ 3 等方式）
            故三数异或 = a ^ b ^ c = v

         综上可取值为 [0, 2^k-1]，共 2^k 个
         */
        return nums.length < 3 ? nums.length : (Integer.highestOneBit(nums.length) << 1);
    }

}
