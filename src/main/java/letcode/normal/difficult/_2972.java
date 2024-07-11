package letcode.normal.difficult;

import letcode.normal.easy._2970;

/**
 * 给你一个下标从 0 开始的 正 整数数组 nums 。  如果 nums 的一个子数组满足：移除这个子数组后剩余元素 严格递增 ，
 * 那么我们称这个子数组为 移除递增 子数组。比方说，[5, 3, 4, 6, 7] 中的 [3, 4] 是一个移除递增子数组，
 * 因为移除该子数组后，[5, 3, 4, 6, 7] 变为 [5, 6, 7] ，是严格递增的。  请你返回 nums 中 移除递增 子数组的总数目。
 * 注意 ，剩余元素为空的数组也视为是递增的。  子数组 指的是一个数组中一段连续的元素序列。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-07-11 09:05
 */
public class _2972 {

    /**
     * 题目与{@link letcode.normal.easy._2970}相同
     * <p>区别在于<b><i>数据规模不一样而已</i></b></p>
     * @param nums 入参
     * @return 移除递增子数组的总数目
     */
    public long incremovableSubarrayCount(int[] nums) {
        return new _2970().incremovableSubarrayCount(nums);
    }

}
