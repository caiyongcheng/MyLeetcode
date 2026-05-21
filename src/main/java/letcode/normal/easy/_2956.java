package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 * 给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，它们分别含有 n 和 m 个元素。
 * 请你计算以下两个数值：  统计 0 <= i < n 中的下标 i ，满足 nums1[i] 在 nums2 中 至少 出现了一次。
 * 统计 0 <= i < m 中的下标 i ，满足 nums2[i] 在 nums1 中 至少 出现了一次。
 * 请你返回一个长度为 2 的整数数组 answer ，按顺序 分别为以上两个数值。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-07-16 10:44
 */
public class _2956 {

    public int[] findIntersectionValues(int[] nums1, int[] nums2) {
        return new int[]{
                findIntersectionValue(nums1, nums2),
                findIntersectionValue(nums2, nums1)
        };
    }

    private int findIntersectionValue(int[] target, int[] data) {
        int[] record = new int[101];
        for (int num : data) {
            record[num] = 1;
        }
        int count = 0;
        for (int num : target) {
            count += record[num];
        }
        return count;
    }

}
