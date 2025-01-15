package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.Arrays;

/**
 * You are given a 0-indexed integer array nums, and an integer k.
 * In one operation, you will:  Take the two smallest integers x and y in nums.
 * Remove x and y from nums. Add min(x, y) * 2 + max(x, y) anywhere in the array.
 * Note that you can only apply the described operation if nums contains at least two elements.
 * Return the minimum number of operations needed so that all elements of the array are greater than or equal to k.
 *
 * The input is generated such that an answer always exists. That is,
 * there exists some sequence of operations after which all elements of the array are greater than or equal to k.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-01-15 09:19
 */
public class _3066 {

    public int minOperations(int[] nums, int k) {
        /*
        可以使用最小堆
        但是换一种思路 每次操作得到的结果是递增的，所以把每次操作的结果和原数组分开，每次寻找两边最小的更小值即可
        只不过这样要求需要先把数组排序，所以复杂度是不会降低的
         */
        long[] newNumArr = new long[nums.length];
        int newNumEndIdx = -1;
        int newNumStartIdx = 0;
        int oldNumArrStartIdx = 0;
        int ans = 0;

        Arrays.sort(nums);
        long x, y;
        while (true) {
            if (newNumEndIdx < newNumStartIdx) {
                x = nums[oldNumArrStartIdx++];
                y = nums[oldNumArrStartIdx++];
            } else {
                if (oldNumArrStartIdx >= nums.length || newNumArr[newNumStartIdx] <= nums[oldNumArrStartIdx]) {
                    x = newNumArr[newNumStartIdx];
                    ++newNumStartIdx;
                } else {
                    x = nums[oldNumArrStartIdx++];
                }
                if (oldNumArrStartIdx >= nums.length || (newNumEndIdx >= newNumStartIdx && newNumArr[newNumStartIdx] <= nums[oldNumArrStartIdx])) {
                    y = newNumArr[newNumStartIdx];
                    ++newNumStartIdx;
                } else {
                    y = nums[oldNumArrStartIdx++];
                }
            }
            if (x >= k) {
                break;
            }
            newNumArr[++newNumEndIdx] = y + (x << 1);
            ++ans;
        }
        return ans;
    }

    /**
     * Example 1:
     *
     * Input: nums = [2,11,10,1,3], k = 10
     * Output: 2
     * Explanation: In the first operation, we remove elements 1 and 2, then add 1 * 2 + 2 to nums. nums becomes equal to [4, 11, 10, 3].
     * In the second operation, we remove elements 3 and 4, then add 3 * 2 + 4 to nums. nums becomes equal to [10, 11, 10].
     * At this stage, all the elements of nums are greater than or equal to 10 so we can stop.
     * It can be shown that 2 is the minimum number of operations needed so that all elements of the array are greater than or equal to 10.
     * Example 2:
     *
     * Input: nums = [1,1,2,4,9], k = 20
     * Output: 4
     * Explanation: After one operation, nums becomes equal to [2, 4, 9, 3].
     * After two operations, nums becomes equal to [7, 4, 9].
     * After three operations, nums becomes equal to [15, 9].
     * After four operations, nums becomes equal to [33].
     * At this stage, all the elements of nums are greater than 20 so we can stop.
     * It can be shown that 4 is the minimum number of operations needed so that all elements of the array are greater than or equal to 20.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test("=[999999999,999999999,999999999], =1000000000");
    }

}
