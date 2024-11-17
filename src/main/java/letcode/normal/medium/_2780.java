package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.Arrays;

/**
 * You are given a 0-indexed integer array nums representing the score of students in an exam.
 * The teacher would like to form one non-empty group of students with maximal strength,
 * where the strength of a group of students of indices i0, i1, i2, ... ,
 * ik is defined as nums[i0] * nums[i1] * nums[i2] * ... * nums[ik].
 * Return the maximum strength of a group the teacher can create.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-09-03 09:08
 */
public class _2780 {

    public long maxStrength(int[] nums) {
        long ans = 1L;
        Arrays.sort(nums);
        boolean hasStudent = false;
        boolean existZeroScore = false;

        int i = nums.length - 1;
        for (; i >= 0 && nums[i] > 0; i--) {
            hasStudent = true;
            ans *= nums[i];
        }
        for (; i >= 0 && nums[i] == 0; i--) {
            existZeroScore = true;
        }

        if (((i + 1) & 1) == 1) {
            --i;
        }
        for (int i1 = 0; i1 <= i; i1++) {
            ans *= nums[i1];
            hasStudent = true;
        }
        return hasStudent
                ? ans
                : existZeroScore
                    ? 0 : nums[0];
    }

    /**
     * Example 1:
     *
     * Input: nums = [3,-1,-5,2,5,-9]
     * Output: 1350
     * Explanation: One way to form a group of maximal strength is to group the students at
     * indices [0,2,3,4,5]. Their strength is 3 * (-5) * 2 * 5 * (-9) = 1350, which we can show is optimal.
     * Example 2:
     *
     * Input: nums = [-4,-5,-4]
     * Output: 20
     * Explanation: Group the students at indices [0, 1] . Then, we’ll have a resulting
     * strength of 20. We cannot achieve greater strength.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(_2780.class, "[0]");
    }

}
