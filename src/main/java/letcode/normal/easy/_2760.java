package letcode.normal.easy;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/11/16 10:55
 * description 给你一个下标从 0 开始的整数数组 nums 和一个整数 threshold 。
 * 请你从 nums 的子数组中找出以下标 l 开头、下标 r 结尾 (0 <= l <= r < nums.length)
 * 且满足以下条件的 最长子数组 ：  nums[l] % 2 == 0 对于范围 [l, r - 1] 内的所有下标 i ，nums[i] % 2 != nums[i + 1] % 2
 * 对于范围 [l, r] 内的所有下标 i ，nums[i] <= threshold 以整数形式返回满足题目要求的最长子数组的长度。
 * 注意：子数组 是数组中的一个连续非空元素序列。
 */
public class _2760 {

    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int ans = 0;
        int curLen = 0;
        for (int i = 0; i < nums.length; i++) {
            if (curLen == 0 && (nums[i] & 1) == 1) {
                continue;
            }
            if (threshold < nums[i]) {
                ans = Math.max(ans, curLen);
                curLen = 0;
                continue;
            }
            ++curLen;
            if (i == nums.length - 1 || ((nums[i] + nums[i+1]) & 1) == 0) {
                ans = Math.max(ans, curLen);
                curLen = 0;
            }
        }
        return ans;
    }


}
