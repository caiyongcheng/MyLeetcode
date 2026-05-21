package letcode.normal.easy;

import letcode.utils.TestUtil;

/**
 * You are given a 0-indexed permutation of n integers nums.  A permutation is called semi-ordered if the first number
 * equals 1 and the last number equals n. You can perform the below operation as many times as you want until you
 * make nums a semi-ordered permutation:  Pick two adjacent elements in nums, then swap them. Return the minimum number
 * of operations to make nums a semi-ordered permutation.  A permutation is a sequence of integers from 1 to n of
 * length n containing each number exactly once.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-12-11 09:04
 */
public class _2717 {

    public int semiOrderedPermutation(int[] nums) {
        int ans = 0;
        boolean search = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                ans += i;
                if (search) {
                    ans--;
                    break;
                }
                search = true;
            } else if (nums[i] == nums.length) {
                ans += nums.length - 1 - i;
                if (search) {
                    break;
                }
                search = true;
            }
        }
        return ans;
    }

}
