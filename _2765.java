package letcode.normal.easy;

import letcode.utils.TestCaseUtils;

/**
 * 给你一个下标从 0 开始的整数数组 nums 。如果 nums 中长度为 m 的子数组 s 满足以下条件，我们称它是一个 交替子数组 ：  m 大于 1 。 s1 = s0 + 1 。 下标从 0 开始的子数组 s 与数组 [s0, s1, s0, s1,...,s(m-1) % 2] 一样。也就是说，s1 - s0 = 1 ，s2 - s1 = -1 ，s3 - s2 = 1 ，s4 - s3 = -1 ，以此类推，直到 s[m - 1] - s[m - 2] = (-1)m 。 请你返回 nums 中所有 交替 子数组中，最长的长度，如果不存在交替子数组，请你返回 -1 。  子数组是一个数组中一段连续 非空 的元素序列。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/1/26 11:24
 */
public class _2765 {

    public int alternatingSubarray(int[] nums) {
        int ans = -1;
        int i;
        int j;
        int diff;
        for (i = 0; i < nums.length - 1; ) {
            j = i + 1;
            if (nums[j] - nums[i] != 1) {
                ++i;
                continue;
            }
            diff = 1;
            while (j < nums.length && nums[j] - nums[j - 1] == diff) {
                diff *= -1;
                ++j;
            }
            ans = Math.max(ans, j - i);
            i = j - 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new _2765().alternatingSubarray(
                TestCaseUtils.getIntArr("[14,30,29,49,3,23,44,21,26,52]")
        ));
    }

}
