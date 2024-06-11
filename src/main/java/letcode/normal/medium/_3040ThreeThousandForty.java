package letcode.normal.medium;

import letcode.utils.TestCaseUtils;

import java.util.Arrays;

/**
 * Given an array of integers called nums, you can perform any of the following operation while nums contains at least 2 elements:
 * Choose the first two elements of nums and delete them.
 * Choose the last two elements of nums and delete them.
 * Choose the first and the last elements of nums and delete them.
 * The score of the operation is the sum of the deleted elements.
 * Your task is to find the maximum number of operations that can be performed, such that all operations have the same score.
 * Return the maximum number of operations possible that satisfy the condition mentioned above.
 *
 * Constraints:
 * 2 <= nums.length <= 2000
 * 1 <= nums[i] <= 1000
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-06-11 09:42
 */
public class _3040ThreeThousandForty {

    public int maxOperations(int[] nums) {
        int maxOperations = 0;
        maxOperations = search(nums, 0, nums.length - 1, nums[0] + nums[1], getInitCache(nums.length + 1));
        maxOperations = Math.max(maxOperations,search(nums, 0, nums.length - 1, nums[0] + nums[nums.length - 1], getInitCache(nums.length + 1)));
        maxOperations = Math.max(maxOperations,search(nums, 0, nums.length - 1, nums[nums.length - 2] + nums[nums.length - 1], getInitCache(nums.length + 1)));
        return maxOperations;
    }

    public int[][] getInitCache(int len) {
        int[][] initCache = new int[len][len];
        for (int[] arr : initCache) {
            Arrays.fill(arr, -1);
        }
        return initCache;
    }


    public int search(int[] nums, int left, int right, int score, int[][] cache) {
        if (left >= right) {
            return 0;
        }
        int key = left * 10000 + right;
        if (cache[left][right] != -1) {
            return cache[left][right];
        }
        int operations = 0;
        if (nums[left] + nums[right] == score) {
            operations = 1 + search(nums, left + 1, right - 1, score, cache);
        }
        if (left + 1 <= right && nums[left] + nums[left + 1] == score) {
            operations = Math.max(1 + search(nums, left + 2, right, score, cache), operations);
        }
        if (right - 1 >= left && nums[right] + nums[right - 1] == score) {
            operations = Math.max(1 + search(nums, left, right - 2, score, cache), operations);
        }
        cache[left][right] = operations;
        return operations;
    }

    /**
     * Example 1:
     *
     * Input: nums = [3,2,1,2,3,4]
     * Output: 3
     * Explanation: We perform the following operations:
     * - Delete the first two elements, with score 3 + 2 = 5, nums = [1,2,3,4].
     * - Delete the first and the last elements, with score 1 + 4 = 5, nums = [2,3].
     * - Delete the first and the last elements, with score 2 + 3 = 5, nums = [].
     * We are unable to perform any more operations as nums is empty.
     * Example 2:
     *
     * Input: nums = [3,2,6,1,4]
     * Output: 2
     * Explanation: We perform the following operations:
     * - Delete the first two elements, with score 3 + 2 = 5, nums = [6,1,4].
     * - Delete the last two elements, with score 1 + 4 = 5, nums = [6].
     * It can be proven that we can perform at most 2 operations.
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _3040ThreeThousandForty().maxOperations(
                TestCaseUtils.getIntArr(" [3,2,6,1,4]")
        ));
    }


}
