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

}
