package letcode.easy;

import java.util.Arrays;

/**
 * Leetcode
 * 给你两个有序整数数组nums1 和 nums2，请你将 nums2 合并到nums1中，使 nums1 成为一个有序数组。  
 * 说明:  初始化nums1 和 nums2 的元素数量分别为m 和 n 。
 * 你可以假设nums1有足够的空间（空间大小大于或等于m + n）来保存 nums2 中的元素。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/merge-sorted-array 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : CaiYongcheng
 * @date : 2020-06-27 14:31
 **/
public class _88EightyEight {

    /**
     * 输入:
     * nums1 = [1,2,3,0,0,0], m = 3
     * nums2 = [2,5,6],       n = 3
     * 输出:[1,2,2,3,5,6]
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] ints = new int[nums1.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < m && j < n) {
            ints[k++] = nums1[i] < nums2[j] ? nums1[i++] : nums2[j++];
        }
        while (i < m) {
            ints[k++] = nums1[i++];
        }
        while (j < n) {
            ints[k++] = nums2[j++];
        }
        for (k = 0; k < ints.length; ++k) {
            nums1[k] = ints[k];
        }
    }

    public static void main(String[] args) {
        int[] int1 = {1, 2, 3, 0, 0, 0};
        int[] int2 = {2, 5, 6};
        merge(int1, 3, int2, 3);
        System.out.println(Arrays.toString(int1));
    }
}
