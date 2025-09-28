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

    /**
     * Example 1:
     *
     * Input: nums = [1,5,1,1,6,4]
     * Output: [1,6,1,5,1,4]
     * Explanation: [1,4,1,5,1,6] is also accepted.
     * Example 2:
     *
     * Input: nums = [1,3,2,2,3,1]
     * Output: [2,3,1,3,1,2]
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
