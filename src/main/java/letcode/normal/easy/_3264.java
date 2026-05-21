package letcode.normal.easy;

import letcode.utils.TestUtil;

import java.util.PriorityQueue;

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

}
