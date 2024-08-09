package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 * You are given two arrays of equal length, nums1 and nums2.
 * Each element in nums1 has been increased (or decreased in the case of negative) by an integer,
 * represented by the variable x.  As a result, nums1 becomes equal to nums2.
 * Two arrays are considered equal when they contain the same integers with the same frequencies.
 * Return the integer x.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-08-08 09:04
 */
public class _3131 {


    public int addedInteger(int[] nums1, int[] nums2) {
        int minForNums1 = Integer.MAX_VALUE;
        int minForNums2 = Integer.MAX_VALUE;
        for (int i = 0; i < nums1.length; i++) {
            minForNums1 = Math.min(minForNums1, nums1[i]);
            minForNums2 = Math.min(minForNums2, nums2[i]);
        }
        return minForNums2 - minForNums1;
    }

    /**
     * Example 1:
     *
     * Input: nums1 = [2,6,4], nums2 = [9,7,5]
     *
     * Output: 3
     *
     * Explanation:
     *
     * The integer added to each element of nums1 is 3.
     *
     * Example 2:
     *
     * Input: nums1 = [10], nums2 = [5]
     *
     * Output: -5
     *
     * Explanation:
     *
     * The integer added to each element of nums1 is -5.
     *
     * Example 3:
     *
     * Input: nums1 = [1,1,1,1], nums2 = [1,1,1,1]
     *
     * Output: 0
     *
     * Explanation:
     *
     * The integer added to each element of nums1 is 0.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.testWithTestClassFile(_3131.class);
    }

}
