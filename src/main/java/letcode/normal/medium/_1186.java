package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * Given an array of integers, return the maximum sum for a non-empty subarray (contiguous elements) with at most one element deletion.
 * In other words, you want to choose a subarray and optionally delete one element from it so that there is still at least one element
 * left and the sum of the remaining elements is maximum possible.
 * Note that the subarray needs to be non-empty after deleting one element.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-07-22 11:20
 */
public class _1186 {

    public int maximumSum(int[] arr) {
        int ans = arr[0];
        for (int num : arr) {
            ans = Math.max(ans, num);
        }
        if (ans <= 0) {
            return ans;
        }

        int noRemove = 0;
        int remove = 0;
        for (int num : arr) {
            remove = Integer.max(remove + num, noRemove);
            if (noRemove + num < 0) {
                noRemove = 0;
            } else {
                noRemove += num;
            }
            ans = Integer.max(ans, Integer.max(remove, noRemove));
        }
        return ans;
    }

    /**
     * Example 1:
     *
     * Input: arr = [1,-2,0,3]
     * Output: 4
     * Explanation: Because we can choose [1, -2, 0, 3] and drop -2, thus the subarray [1, 0, 3] becomes the maximum value.
     * Example 2:
     *
     * Input: arr = [1,-2,-2,3]
     * Output: 3
     * Explanation: We just choose [3] and it's the maximum sum.
     * Example 3:
     *
     * Input: arr = [-1,-1,-1,-1]
     * Output: -1
     * Explanation: The final subarray needs to be non-empty. You can't choose [-1] and delete -1 from it, then get an empty subarray to make the sum equals to 0.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(_1186.class, "Example 1: Input: arr = [1,-2,0,3] Output: 4 Explanation: Because we can choose [1, -2, 0, 3] and drop -2, thus the subarray [1, 0, 3] becomes the maximum value. Example 2: Input: arr = [1,-2,-2,3] Output: 3 Explanation: We just choose [3] and it's the maximum sum. Example 3: Input: arr = [-1,-1,-1,-1] Output: -1 Explanation: The final subarray needs to be non-empty. You can't choose [-1] and delete -1 from it, then get an empty subarray to make the sum equals to 0.");
        TestUtil.testBatch(_1186.class, "[-8,7,-12,-1,0,11,-2,-3,4,-13,2,3,-6]");
    }

}
