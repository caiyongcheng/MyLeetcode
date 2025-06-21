package letcode.normal.easy;

import letcode.utils.TestUtil;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.function.Function;

/**
 * You are given an integer array nums, an integer k, and an integer multiplier.  You need to perform k operations on nums.
 * In each operation:  Find the minimum value x in nums. If there are multiple occurrences of the minimum value,
 * select the one that appears first. Replace the selected minimum value x with x * multiplier.
 * Return an integer array denoting the final state of nums after performing all k operations.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-12-14 16:53
 */
public class _3264 {

    public int[] getFinalState(int[] nums, int k, int multiplier) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(
                (i, j) -> nums[i] == nums[j] ? (i - j) : (nums[i] - nums[j])
        );
        for (int i = 0; i < nums.length; i++) {
            priorityQueue.offer(i);
        }
        for (int i = 0; i < k; i++) {
            int idx = priorityQueue.poll();
            nums[idx] *= multiplier;
            priorityQueue.offer(idx);
        }
        return nums;
    }

    /**
     * Example 1:
     *
     * Input: nums = [2,1,3,5,6], k = 5, multiplier = 2
     *
     * Output: [8,4,6,5,6]
     *
     * Explanation:
     *
     * Operation	Result
     * After operation 1	[2, 2, 3, 5, 6]
     * After operation 2	[4, 2, 3, 5, 6]
     * After operation 3	[4, 4, 3, 5, 6]
     * After operation 4	[4, 4, 6, 5, 6]
     * After operation 5	[8, 4, 6, 5, 6]
     * Example 2:
     *
     * Input: nums = [1,2], k = 3, multiplier = 4
     *
     * Output: [16,8]
     *
     * Explanation:
     *
     * Operation	Result
     * After operation 1	[4, 2]
     * After operation 2	[4, 8]
     * After operation 3	[16, 8]
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(_3264.class);
    }

}
