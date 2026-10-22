package letcode.normal.medium;

/**
 * 3942. Minimum Operations to Sort a Permutation
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/minimum-operations-to-sort-a-permutation/
 * <p>
 * You are given an integer array nums of length n , where nums is a permutation of the integers from 0
 * to n - 1 .
 * <p>
 * You may perform only the following operations:
 * <p>
 * - Reverse the entire array.
 * <p>
 * - Rotate Left by One : Move the first element to the end of the array, and rest elements to left by
 * one position.
 * <p>
 * Return an integer denoting the minimum number of operations required to sort the array in increasing
 * order. If it is not possible to sort the array using only the given operations, return -1.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [0,2,1]
 * <p>
 * Output: 2
 * <p>
 * Explanation:
 * <p>
 * - Rotate Left by one: [2, 1, 0]
 * <p>
 * - Reverse the array: [0, 1, 2]
 * <p>
 * The array becomes sorted in 2 operations, which is minimal
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,0,2]
 * <p>
 * Output: 2
 * <p>
 * Explanation:
 * <p>
 * - Reverse the array: [2, 0, 1]
 * <p>
 * - Rotate Left by one: [0, 1, 2]
 * <p>
 * The array becomes sorted in 2 operations, which is minimal.
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [2,0,1,3]
 * <p>
 * Output: -1
 * <p>
 * Explanation:
 * <p>
 * It is impossible to reach [2, 0, 1, 3] . Thus, the answer is -1.
 * <p>
 * Constraints:
 * <p>
 * - 1 <= n == nums.length <= 10 5
 * <p>
 * - 0 <= nums[i] <= n - 1
 * <p>
 * - nums is a permutation of integers from 0 to n - 1 .
 */
public class _3942 {

/*    public int minOperations(int[] nums) {
        *//*
        允许的操作一个是向左移动 一个是反转 那么向左移动+反转=向右移动
        那么反推回去 如果从0开始，向左或者向右移动是[0,n-1]的顺序，那么就可以通过操作获得结果
        然后从0的位置判断向左近还是向右近 注意 向右要多加1次返回的操作
         *//*

        int zereIdx = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zereIdx = i;
                break;
            }
        }

        if (zereIdx == -1) {
            return -1;
        }

        int change = nums.length + (nums[(zereIdx + 1) % nums.length] == 1 ? 1 : -1);
        int idx = zereIdx;
        int changeIdx;
        while (true) {
            changeIdx = (idx + change) % nums.length;
            if (nums[changeIdx] - nums[idx] != 1) {
                break;
            }
            idx = changeIdx;
        }
        if (nums[idx] != nums.length - 1) {
            return -1;
        }

        // ... 0 1 ... 的情况
        if (change == nums.length + 1) {
            return Math.min(zereIdx, (nums.length - zereIdx) * 3);
        } else {
            // ... 1 0 ... 的情况
            return Math.min((nums.length - 1 - zereIdx) * 3 + 1, zereIdx + 2);
        }


    }*/

    
}
