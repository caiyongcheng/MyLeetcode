package letcode.normal.medium;

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


}
