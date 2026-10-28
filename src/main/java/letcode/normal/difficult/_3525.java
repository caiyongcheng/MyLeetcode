package letcode.normal.difficult;

/**
 * 3525. Find X Value of Array II
 * Difficulty: Hard
 * Link: https://leetcode.cn/problems/find-x-value-of-array-ii/
 * <p>
 * You are given an array of positive integers nums and a positive integer k . You are also given a 2D
 * array queries , where queries[i] = [index i , value i , start i , x i ] .
 * <p>
 * You are allowed to perform an operation once on nums , where you can remove any suffix from nums
 * such that nums remains non-empty .
 * <p>
 * The x-value of nums for a given x is defined as the number of ways to perform this operation so that
 * the product of the remaining elements leaves a remainder of x modulo k .
 * <p>
 * For each query in queries you need to determine the x-value of nums for x i after performing the
 * following actions:
 * <p>
 * - Update nums[index i ] to value i . Only this step persists for the rest of the queries.
 * <p>
 * - Remove the prefix nums[0..(start i - 1)] (where nums[0..(-1)] will be used to represent the empty
 * prefix).
 * <p>
 * Return an array result of size queries.length where result[i] is the answer for the i th query.
 * <p>
 * A prefix of an array is a subarray that starts from the beginning of the array and extends to any
 * point within it.
 * <p>
 * A suffix of an array is a subarray that starts at any point within the array and extends to the end
 * of the array.
 * <p>
 * Note that the prefix and suffix to be chosen for the operation can be empty .
 * <p>
 * Note that x-value has a different definition in this version.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,4,5], k = 3, queries = [[2,2,0,2],[3,3,3,0],[0,1,0,1]]
 * <p>
 * Output: [2,2,2]
 * <p>
 * Explanation:
 * <p>
 * - For query 0, nums becomes [1, 2, 2, 4, 5] , and the empty prefix must be removed. The possible
 * operations are:
 * <p>
 * - Remove the suffix [2, 4, 5] . nums becomes [1, 2] .
 * <p>
 * - Remove the empty suffix. nums becomes [1, 2, 2, 4, 5] with a product 80, which gives remainder 2
 * when divided by 3.
 * <p>
 * - For query 1, nums becomes [1, 2, 2, 3, 5] , and the prefix [1, 2, 2] must be removed. The possible
 * operations are:
 * <p>
 * - Remove the empty suffix. nums becomes [3, 5] .
 * <p>
 * - Remove the suffix [5] . nums becomes [3] .
 * <p>
 * - For query 2, nums becomes [1, 2, 2, 3, 5] , and the empty prefix must be removed. The possible
 * operations are:
 * <p>
 * - Remove the suffix [2, 2, 3, 5] . nums becomes [1] .
 * <p>
 * - Remove the suffix [3, 5] . nums becomes [1, 2, 2] .
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,2,4,8,16,32], k = 4, queries = [[0,2,0,2],[0,2,0,1]]
 * <p>
 * Output: [1,0]
 * <p>
 * Explanation:
 * <p>
 * - For query 0, nums becomes [2, 2, 4, 8, 16, 32] . The only possible operation is:
 * <p>
 * - Remove the suffix [2, 4, 8, 16, 32] .
 * <p>
 * - For query 1, nums becomes [2, 2, 4, 8, 16, 32] . There is no possible way to perform the
 * operation.
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [1,1,2,1,1], k = 2, queries = [[2,1,0,1]]
 * <p>
 * Output: [5]
 * <p>
 * Constraints:
 * <p>
 * - 1 <= nums[i] <= 10 9
 * <p>
 * - 1 <= nums.length <= 10 5
 * <p>
 * - 1 <= k <= 5
 * <p>
 * - 1 <= queries.length <= 2 * 10 4
 * <p>
 * - queries[i] == [index i , value i , start i , x i ]
 * <p>
 * - 0 <= index i <= nums.length - 1
 * <p>
 * - 1 <= value i <= 10 9
 * <p>
 * - 0 <= start i <= nums.length - 1
 * <p>
 * - 0 <= x i <= k - 1
 */
public class _3525 {

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        /*
        暴力解法
        对于每个 queries[i] = [index i , value i , start i , x i ]
        暴力去计算 那么时间复杂度O(queries.length * nums.length) 会超时
        如果设 count[i][j]表示 num[0,i]的乘积模k的结果
        如果忽略 index i , value 的作用进行前缀和的话
        我们就能快速求出 nums[i,j]之间的x值了
        那么考虑 index i , value的作用，会怎么去影响结果呢
        对于 index i之间的数据，count[t][j](t<i)是不会有任何影响的
        而根据模的性质 (a * b * c * d *...) % k = ((pa * k + ak) * (pb * k + bk) * .....) % k
        其中展开带k的都被消去了 就等于 (ak * bk * ck * ......) % k
        那么如果将其中一个值，例如b换成value后，会有
        原来是 (ak * bk * ck * ......) % k =
        变成 ((ak * bk * ck * ......) / bk * (value % k)) % k
        。。。
         */
        return null;
    }
}
