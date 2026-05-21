package letcode.normal.easy;

/**
 * 给你一个下标从 0 开始的 正 整数数组 nums 。  如果 nums 的一个子数组满足：移除这个子数组后剩余元素 严格递增 ，那么我们称这个子数组为 移除递增 子数组。比方说，[5, 3, 4, 6, 7] 中的 [3, 4] 是一个移除递增子数组，因为移除该子数组后，[5, 3, 4, 6, 7] 变为 [5, 6, 7] ，是严格递增的。  请你返回 nums 中 移除递增 子数组的总数目。  注意 ，剩余元素为空的数组也视为是递增的。  子数组 指的是一个数组中一段连续的元素序列。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-07-11 09:04
 */
public class _2970 {

    public int incremovableSubarrayCount(int[] nums) {
        /*
         * 从数据规模上看 最简单的就是穷举 也不会超时
         * 考虑到移除的是连续子数组 原始数组可以看成 s1 s2 s3,其中s1、s3表示的连续递增的子数组
         * 情况1 只有s1,那么结果就是s1有多少可以移除的子数组
         * 情况2 s1、s2、s3的结构 那么就移除s2部分即可，s2部分可以不断扩展
         */
        int s1EndIdx = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                s1EndIdx = i;
            } else {
                break;
            }
        }
        if (s1EndIdx == nums.length - 1) {
            return ((nums.length + 1) * nums.length >> 1);
        }

        int s3StartIdx = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                s3StartIdx = i;
            } else {
                break;
            }
        }

        int ans = nums.length - s3StartIdx + 1;
        for (int s1StartIdx = 0; s1StartIdx <= s1EndIdx; s1StartIdx++) {
            ans++;
            while (s3StartIdx < nums.length && nums[s1StartIdx] >= nums[s3StartIdx]) {
                s3StartIdx++;
            }
            ans += nums.length - s3StartIdx;
        }
        return ans;
    }

}
