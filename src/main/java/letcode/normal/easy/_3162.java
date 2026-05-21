package letcode.normal.easy;

import letcode.utils.TestUtil;

import java.util.Arrays;

/**
 * You are given 2 integer arrays nums1 and nums2 of lengths n and m respectively.
 * You are also given a positive integer k.  A pair (i, j) is called good if nums1[i] is divisible
 * by nums2[j] * k (0 <= i <= n - 1, 0 <= j <= m - 1).  Return the total number of good pairs.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-10-10 11:31
 */
public class _3162 {

    public int numberOfPairs(int[] nums1, int[] nums2, int k) {
        return (int) Arrays.stream(nums2)
                .map(i -> i * k)
                .mapToLong(i -> Arrays.stream(nums1)
                        .filter(j -> j % i == 0 && j >= i)
                        .count())
                .sum();
    }

}
