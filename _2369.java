package letcode.normal.medium;

import letcode.utils.TestCaseUtils;

/**
 * 给你一个下标从 0 开始的整数数组 nums ，你必须将数组划分为一个或多个 连续 子数组。  如果获得的这些子数组中每个都能满足下述条件 之一 ，
 * 则可以称其为数组的一种 有效 划分：  子数组 恰 由 2 个相等元素组成，例如，子数组 [2,2] 。 子数组 恰 由 3 个相等元素组成，例如，子数组 [4,4,4] 。
 * 子数组 恰 由 3 个连续递增元素组成，并且相邻元素之间的差值为 1 。例如，子数组 [3,4,5] ，但是子数组 [1,3,5] 不符合要求。
 * 如果数组 至少 存在一种有效划分，返回 true ，否则，返回 false 。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/3/13 15:07
 */
public class _2369 {

    public boolean validPartition(int[] nums) {
        /*
        can[i] 表示子数组nums[i,len-1]能否划分成功
        那么can[i]
            如果 nums[i]==nums[i+1] 那么 can[i] = can[i+2]
            如果 nums[i]==nums[i+1]==nums[i+2] 那么 can[i] = can[i+3] or can[i+2]
            如果 nums[i]==nums[i+1]-1==nums[i+1]-2的话 那么can[i]=can[i+3]
         */
        boolean[] can = new boolean[nums.length];
        can[nums.length - 1] = false;
        can[nums.length - 2] = nums[nums.length - 1] == nums[nums.length - 2];
        if (nums.length < 3) {
            return can[0];
        }
        can[nums.length - 3] = (nums[nums.length - 1] == nums[nums.length - 2] && nums[nums.length - 2] == nums[nums.length - 3])
                || (nums[nums.length - 1] == nums[nums.length - 2] + 1 && nums[nums.length - 2] == nums[nums.length - 3] + 1);
        for (int idx = nums.length - 4; idx >= 0; idx--) {
            if (nums[idx] == nums[idx + 1]) {
                can[idx] = can[idx + 2] || (nums[idx] == nums[idx + 2] && can[idx+3]);
            } else {
                can[idx] = nums[idx + 1] == nums[idx] + 1 && nums[idx + 2] == nums[idx] + 2 && can[idx + 3];
            }
        }
        return can[0];
    }


    /**
     * 示例 1：
     *
     * 输入：nums = [4,4,4,5,6]
     * 输出：true
     * 解释：数组可以划分成子数组 [4,4] 和 [4,5,6] 。
     * 这是一种有效划分，所以返回 true 。
     * 示例 2：
     *
     * 输入：nums = [1,1,1,2]
     * 输出：false
     * 解释：该数组不存在有效划分。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2369().validPartition(
                TestCaseUtils.getIntArr("[4,4,4,5,6,7,7,7,7]")
        ));
    }


}
