package letcode.normal.unansweredquestions.medium;

import letcode.utils.TestUtil;

import java.util.List;

/**
 * You are given an array nums consisting of n prime integers.
 * You need to construct an array ans of length n,
 * such that, for each index i, the bitwise OR of ans[i] and ans[i] + 1 is equal to nums[i],
 * i.e. ans[i] OR (ans[i] + 1) == nums[i].
 * Additionally, you must minimize each value of ans[i] in the resulting array.
 * If it is not possible to find such a value for ans[i] that satisfies the condition, then set ans[i] = -1.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2026-01-23 00:43
 */
public class _3315 {


    public int[] minBitwiseArray(List<Integer> nums) {
        /*
        s表示二进制的前缀
        t..t表示连续的t(数量大于等于0)
        1. s0 or s1 = 1 所以偶数是不符合条件的
        2.
            设 x or x + 1 = s11..1
            x =     s01..1
            x + 1 = s10..0
            怎么通过 s11..1 构造出 x
                设 s = (p0)
                => s11..1 = (p0)11..1
                => s11..1 + 1 = (p1)00..0
                => s11..1 and (s11..1 + 1) = (p0)00..0
                => s11..1 xor (s11..1 + 1) = (01)11..1
                => [s1..1 xor (s1..1 + 1)] >> 2 = (00)01..1
                => [s1..1 xor (s1..1 + 1)] + (s1..1 and (s1..1 + 1)) = (p0)01..1 = s01..1 = x
         */
        int[] ans = new int[nums.size()];
        int num;

        for (int i = 0; i < nums.size(); i++) {
            num = nums.get(i);
            if ((num & 1) == 0) {
                ans[i] = -1;
            } else {
                ans[i] = (num & (num + 1)) + ((num ^ (num + 1)) >> 2);
            }
        }
        return ans;
    }


    /**
     * Example 1:
     *
     * Input: nums = [2,3,5,7]
     *
     * Output: [-1,1,4,3]
     *
     * Explanation:
     *
     * For i = 0, as there is no value for ans[0] that satisfies ans[0] OR (ans[0] + 1) = 2, so ans[0] = -1.
     * For i = 1, the smallest ans[1] that satisfies ans[1] OR (ans[1] + 1) = 3 is 1, because 1 OR (1 + 1) = 3.
     * For i = 2, the smallest ans[2] that satisfies ans[2] OR (ans[2] + 1) = 5 is 4, because 4 OR (4 + 1) = 5.
     * For i = 3, the smallest ans[3] that satisfies ans[3] OR (ans[3] + 1) = 7 is 3, because 3 OR (3 + 1) = 7.
     * Example 2:
     *
     * Input: nums = [11,13,31]
     *
     * Output: [9,12,15]
     *
     * Explanation:
     *
     * For i = 0, the smallest ans[0] that satisfies ans[0] OR (ans[0] + 1) = 11 is 9, because 9 OR (9 + 1) = 11.
     * For i = 1, the smallest ans[1] that satisfies ans[1] OR (ans[1] + 1) = 13 is 12, because 12 OR (12 + 1) = 13.
     * For i = 2, the smallest ans[2] that satisfies ans[2] OR (ans[2] + 1) = 31 is 15, because 15 OR (15 + 1) = 31.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
