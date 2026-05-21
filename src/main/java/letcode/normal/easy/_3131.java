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

}
