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

    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }

        int zeroIdx = 0;
        while (nums[zeroIdx] != 0) {
            zeroIdx++;
        }

        /*
         * 左移只改变起点，反转只改变方向，因此元素的环形相邻关系不会改变。
         * 能够排序的排列，必须从 0 出发沿某个方向依次得到 1、2、...、n-1。
         */
        boolean increasing = nums[(zeroIdx + 1) % n] == 1;
        int step = increasing ? 1 : -1;
        int idx = zeroIdx;

        for (int value = 1; value < n; value++) {
            idx = (idx + step + n) % n;
            if (nums[idx] != value) {
                return -1;
            }
        }

        if (increasing) {
            /*
             * 环形正序：
             * 1. 直接左移 zeroIdx 次。
             * 2. 反转、左移 n-zeroIdx 次、再反转，相当于整体右移，
             *    两次反转由全部移动共享，总共 n-zeroIdx+2 次。
             */
            return Math.min(zeroIdx, n - zeroIdx + 2);
        }

        /*
         * 环形倒序，需要奇数次反转；一次反转即可完成方向调整：
         * 1. 先反转，0 位于 n-1-zeroIdx，再左移到首位，共 n-zeroIdx 次。
         * 2. 先左移 zeroIdx+1 次，将 0 移到末尾，再反转，共 zeroIdx+2 次。
         * 更多反转可以两两合并为移动方向的切换，不会得到更短的方案。
         */
        return Math.min(n - zeroIdx, zeroIdx + 2);
    }
}
