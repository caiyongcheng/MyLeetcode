package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-11-18 16:56
 */
public class _1437 {


    public boolean kLengthApart(int[] nums, int k) {
        int lastIdx = -k - 1;
        for (int idx = 0; idx < nums.length; idx++) {
            if (nums[idx] == 1) {
                if (idx - lastIdx <= k) {
                    return false;
                }
                lastIdx = idx;
            }
        }
        return true;
    }


    /**
     * Example 1:
     *
     *
     * Input: nums = [1,0,0,0,1,0,0,1], k = 2
     * Output: true
     * Explanation: Each of the 1s are at least 2 places away from each other.
     * Example 2:
     *
     *
     * Input: nums = [1,0,0,1,0,1], k = 2
     * Output: false
     * Explanation: The second 1 and third 1 are only one apart from each other.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
