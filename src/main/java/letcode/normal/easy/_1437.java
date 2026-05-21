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


}
