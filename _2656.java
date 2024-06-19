package letcode.normal.easy;

import java.util.Arrays;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/11/15 18:01
 * description 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。你需要执行以下操作 恰好 k 次，最大化你的得分：  从 nums 中选择一个元素 m 。
 * 将选中的元素 m 从数组中删除。 将新元素 m + 1 添加到数组中。 你的得分增加 m 。 请你返回执行以上操作恰好 k 次后的最大得分。
 */
public class _2656 {

    public int maximizeSum(int[] nums, int k) {
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        return (max + max + k - 1) * k >>> 1;
    }

}
