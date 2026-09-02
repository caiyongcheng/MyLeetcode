package letcode.normal.easy;

/**
 * 3875. Construct Uniform Parity Array I
 * Difficulty: Easy
 * Link: https://leetcode.cn/problems/construct-uniform-parity-array-i/
 * <p>
 * You are given an array nums1 of n distinct integers.
 * <p>
 * You want to construct another array nums2 of length n such that the elements in nums2 are either all
 * odd or all even .
 * <p>
 * For each index i , you must choose exactly one of the following (in any order):
 * <p>
 * - nums2[i] = nums1[i]
 * <p>
 * - nums2[i] = nums1[i] - nums1[j] , for an index j != i
 * <p>
 * Return true if it is possible to construct such an array, otherwise, return false .
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [2,3]
 * <p>
 * Output: true
 * <p>
 * Explanation:
 * <p>
 * - Choose nums2[0] = nums1[0] - nums1[1] = 2 - 3 = -1 .
 * <p>
 * - Choose nums2[1] = nums1[1] = 3 .
 * <p>
 * - nums2 = [-1, 3] , and both elements are odd. Thus, the answer is true ​​​​​​​.
 * <p>
 * Example 2:
 * <p>
 * Input: nums1 = [4,6]
 * <p>
 * Output: true
 * <p>
 * Explanation: ​​​​​​​
 * <p>
 * - Choose nums2[0] = nums1[0] = 4 .
 * <p>
 * - Choose nums2[1] = nums1[1] = 6 .
 * <p>
 * - nums2 = [4, 6] , and all elements are even. Thus, the answer is true .
 * <p>
 * Constraints:
 * <p>
 * - 1 <= n == nums1.length <= 100
 * <p>
 * - 1 <= nums1[i] <= 100
 * <p>
 * - nums1 consists of distinct integers.
 */
public class _3875 {

    public boolean uniformArray(int[] nums1) {
        return true;
    }
}
