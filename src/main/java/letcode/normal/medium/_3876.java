package letcode.normal.medium;

/**
 * 3876. Construct Uniform Parity Array II
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/construct-uniform-parity-array-ii/
 * <p>
 * You are given an array nums1 of n distinct integers.
 * <p>
 * You want to construct another array nums2 of length n such that the elements in nums2 are either all
 * odd or all even .
 * <p>
 * For each index i , you must choose exactly one of the following (in any order):
 * <p>
 * - nums2[i] = nums1[i] ​​​​​​​
 * <p>
 * - nums2[i] = nums1[i] - nums1[j] , for an index j != i , such that nums1[i] - nums1[j] >= 1
 * <p>
 * Return true if it is possible to construct such an array, otherwise return false .
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,4,7]
 * <p>
 * Output: true
 * <p>
 * Explanation: ​​​​​​​​​​​​​​
 * <p>
 * - Set nums2[0] = nums1[0] = 1 .
 * <p>
 * - Set nums2[1] = nums1[1] - nums1[0] = 4 - 1 = 3 .
 * <p>
 * - Set nums2[2] = nums1[2] = 7 .
 * <p>
 * - nums2 = [1, 3, 7] , and all elements are odd. Thus, the answer is true .
 * <p>
 * Example 2:
 * <p>
 * Input: nums1 = [2,3]
 * <p>
 * Output: false
 * <p>
 * Explanation:
 * <p>
 * It is not possible to construct nums2 such that all elements have the same parity. Thus, the answer
 * is false .
 * <p>
 * Example 3:
 * <p>
 * Input: nums1 = [4,6]
 * <p>
 * Output: true
 * <p>
 * Explanation:
 * <p>
 * - Set nums2[0] = nums1[0] = 4 .
 * <p>
 * - Set nums2[1] = nums1[1] = 6 .
 * <p>
 * - nums2 = [4, 6] , and all elements are even. Thus, the answer is true .
 * <p>
 * Constraints:
 * <p>
 * - 1 <= n == nums1.length <= 10 5
 * <p>
 * - 1 <= nums1[i] <= 10 9
 * <p>
 * - nums1 consists of distinct integers.
 */
public class _3876 {

    public boolean uniformArray(int[] nums1) {
        // 如果全是奇数 偶数 不做处理
        // 如果存在奇数 如果期望结果是偶数的话 总有一个最小的奇数找不到更小的奇数去想减 所以 无法达成条件
        // 考虑期望结果是奇数的情况 那么需要满足每个偶数都有小于他的奇数才行 也就是最小奇数要小于最小偶数
        // 综上所述 只要满足 最小值是奇数即可 或者 全为偶数即可

        boolean hasOdd = false;
        int minValue = nums1[0];

        for (int num : nums1) {
            minValue = Math.min(minValue, num);
            hasOdd = hasOdd || (num & 1) == 1;
        }

        return (minValue & 1) == 1 || !hasOdd;

    }
}
