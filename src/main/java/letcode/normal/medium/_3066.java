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

}
