package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.Arrays;

/**
 * You are given two integer arrays nums1 and nums2.  From nums1 two elements have been removed, and all other elements
 * have been increased (or decreased in the case of negative) by an integer, represented by the variable x.
 * As a result, nums1 becomes equal to nums2. Two arrays are considered equal when they contain the same integers
 * with the same frequencies.  Return the minimum possible integer x that achieves this equivalence.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-08-09 09:54
 */
public class _3132 {

    public int minimumAddedInteger(int[] nums1, int[] nums2) {
        /**
         * 根据题意 移除元素后的数组相等 那么就有 nums1[i] - nums2[i] == k
         * k 属于 (nums2[0] - nums1[0],nums2[0] - nums1[1],nums2[0] - nums1[2]) 这是因为nums1.length = nums2.length + 2
         * 所以枚举不同的k 看下哪个符合条件即可 因为题目要求k最小 那么从nums2[0] - nums1[2]开始即可
         */
        Arrays.sort(nums1);
        if (nums2.length == 1) {
            return nums2[0] - nums1[nums1.length - 1];
        }
        Arrays.sort(nums2);
        int diff = 0;
        int removableCount;
        int i1, i2;
        for (int i = 2; i >= 0; i--) {
            removableCount = 2 - i;
            diff = nums2[0] - nums1[i];
            for (i1 = i + 1, i2 = 1; i1 < nums1.length && i2 < nums2.length;) {
                if (nums2[i2] - nums1[i1] != diff) {
                    if (removableCount > 0) {
                        --removableCount;
                        ++i1;
                    } else {
                        break;
                    }
                } else {
                    ++i1;
                    ++i2;
                }
                if (i2 == nums2.length) {
                    return diff;
                }
            }
        }
        return diff;
    }


    /**
     * Example 1:
     *
     * Input: nums1 = [4,20,16,12,8], nums2 = [14,18,10]
     *
     * Output: -2
     *
     * Explanation:
     *
     * After removing elements at indices [0,4] and adding -2, nums1 becomes [18,14,10].
     *
     * Example 2:
     *
     * Input: nums1 = [3,5,5,3], nums2 = [7,7]
     *
     * Output: 2
     *
     * Explanation:
     *
     * After removing elements at indices [0,3] and adding 2, nums1 becomes [7,7].
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.testWithTestClassFile(_3132.class);
    }


}
