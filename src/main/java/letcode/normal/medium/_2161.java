package letcode.normal.medium;

import java.util.Arrays;

/**
 * 2161. Partition Array According to Given Pivot
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/partition-array-according-to-given-pivot/
 * You are given a 0-indexed integer array nums and an integer pivot .
 * Rearrange nums such that the following conditions are satisfied:
 * - Every element less than pivot appears before every element greater than pivot .
 * - Every element equal to pivot appears in between the elements less than and greater than pivot .
 * - The relative order of the elements less than pivot and the elements greater than pivo...
 */
public class _2161 {

    public int[] pivotArray(int[] nums, int pivot) {

        int[] less = new int[nums.length];
        int lessSize = 0;

        int[] gather = new int[nums.length];
        int gatherSize = 0;

        int equalNumSize = 0;

        int[] rst = new int[nums.length];

        for (int num : nums) {
            if (num < pivot) {
                less[lessSize++] = num;
            } else if (num > pivot) {
                gather[gatherSize++] = num;
            } else {
                equalNumSize++;
            }
        }

        System.arraycopy(less, 0, rst, 0, lessSize);
        Arrays.fill(rst, lessSize, lessSize + equalNumSize, pivot);
        System.arraycopy(gather, 0, rst, lessSize + equalNumSize, gatherSize);

        return rst;

    }
}
