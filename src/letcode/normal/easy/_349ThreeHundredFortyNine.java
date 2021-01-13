package normal.easy;

import java.util.HashSet;

/**
 * @program: Leetcode
 * @description: 给定两个数组，编写一个函数来计算它们的交集。输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致
 * @author: 蔡永程
 * @create: 2021-01-12 16:55
 */
public class _349ThreeHundredFortyNine {

    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length > nums1.length) {
            return intersection(nums2, nums1);
        }
        final HashSet<Integer> uniteSet = new HashSet<>();
        for (int i : nums1) {
            uniteSet.add(i);
        }
        final HashSet<Integer> resultSet = new HashSet<>();
        for (int i : nums2) {
            if (uniteSet.contains(i)) {
                resultSet.add(i);
            }
        }
        final int[] ints = new int[resultSet.size()];
        int i = 0;
        for (Integer integer : resultSet) {
            ints[i++] = integer;
        }
        return ints;
    }
}