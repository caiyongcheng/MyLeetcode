package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * Given an integer array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....  You may assume the input array always has a valid answer.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-09-28 15:54
 */
public class _324 {

    public void wiggleSort(int[] nums) {

        int[] countSortArr = new int[5001];
        for (int num : nums) {
            countSortArr[num]++;
        }

        int sortArrIdx = countSortArr.length - 1;
        for (int i = 1; i < nums.length; i+=2) {
            while (countSortArr[sortArrIdx] == 0) {
                --sortArrIdx;
            };
            nums[i] = sortArrIdx;
            countSortArr[sortArrIdx]--;
        }

        for (int i = 1; i < nums.length; i+=2) {
            while (countSortArr[sortArrIdx] == 0) {
                --sortArrIdx;
            };
            nums[i] = sortArrIdx;
            countSortArr[sortArrIdx]--;
        }
    }

}
