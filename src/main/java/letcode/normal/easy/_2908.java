package letcode.normal.easy;

import letcode.utils.TestCaseInputUtils;

/**
 * 给你一个下标从 0 开始的整数数组 nums 。  如果下标三元组 (i, j, k) 满足下述全部条件，
 * 则认为它是一个 山形三元组 ：  i < j < k nums[i] < nums[j] 且 nums[k] < nums[j] 请你找出 nums 中 元素和最小 的山形三元组，并返回其 元素和 。
 * 如果不存在满足条件的三元组，返回 -1 。
 *
 * 3 <= nums.length <= 50
 * 1 <= nums[i] <= 50
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/3/29 11:21
 */
public class _2908 {

    public int minimumSum(int[] nums) {
        int ans = 2501;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] < nums[j] && nums[j] > nums[k]) {
                        ans = Integer.min(ans, nums[i] + nums[j] + nums[k]);
                    }
                }
            }
        }
        return ans == 2501 ? -1 : ans;
    }

    public int minimumSum2(int[] nums) {
        int ans = 2501;
        int[] leftMin = new int[nums.length];
        leftMin[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            leftMin[i] = Math.min(nums[i], leftMin[i - 1]);
        }
        int rightMin = nums[nums.length - 1];
        for (int i = nums.length - 2; i > 0; i--) {
            if (nums[i] > leftMin[i - 1] && nums[i] > rightMin) {
                ans = Integer.min(ans, nums[i] + rightMin + leftMin[i - 1]);
            }
            if (nums[i] < rightMin) {
                rightMin = nums[i];
            }
        }
        return ans == 2501 ? -1 : ans;
    }


    /**
     * 示例 1：
     *
     * 输入：nums = [8,6,1,5,3]
     * 输出：9
     * 解释：三元组 (2, 3, 4) 是一个元素和等于 9 的山形三元组，因为：
     * - 2 < 3 < 4
     * - nums[2] < nums[3] 且 nums[4] < nums[3]
     * 这个三元组的元素和等于 nums[2] + nums[3] + nums[4] = 9 。可以证明不存在元素和小于 9 的山形三元组。
     * 示例 2：
     *
     * 输入：nums = [5,4,8,7,10,2]
     * 输出：13
     * 解释：三元组 (1, 3, 5) 是一个元素和等于 13 的山形三元组，因为：
     * - 1 < 3 < 5
     * - nums[1] < nums[3] 且 nums[5] < nums[3]
     * 这个三元组的元素和等于 nums[1] + nums[3] + nums[5] = 13 。可以证明不存在元素和小于 13 的山形三元组。
     * 示例 3：
     *
     * 输入：nums = [6,5,4,3,4,5]
     * 输出：-1
     * 解释：可以证明 nums 中不存在山形三元组。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2908().minimumSum(
                TestCaseInputUtils.getIntArr("[8,6,1,5,3]")
        ));
    }

}
