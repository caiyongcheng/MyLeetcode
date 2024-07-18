package letcode.normal.medium;

import letcode.utils.TestCaseInputUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个非负整数数组 nums 和一个整数 target 。
 * 向数组中的每个整数前添加 '+' 或 '-' ，
 * 然后串联起所有整数，可以构造一个 表达式 ：  例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，
 * 在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-07-01 09:10
 */
public class _494 {

    public int findTargetSumWays(int[] nums, int target) {
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(nums[nums.length - 1], 1);
        dp.put(-nums[nums.length - 1], dp.getOrDefault(-nums[nums.length - 1], 0) + 1);
        for (int i = nums.length - 2; i >= 0; i--) {
            Map<Integer, Integer> curNum2CntMap = new HashMap<>();
            for (Map.Entry<Integer, Integer> num2Cnt : dp.entrySet()) {
                curNum2CntMap.put(
                        num2Cnt.getKey() + nums[i],
                        curNum2CntMap.getOrDefault(num2Cnt.getKey() + nums[i], 0) + num2Cnt.getValue()
                );
                curNum2CntMap.put(
                        num2Cnt.getKey() - nums[i],
                        curNum2CntMap.getOrDefault(num2Cnt.getKey() - nums[i], 0) + num2Cnt.getValue()
                );
                dp = curNum2CntMap;
            }
        }
        return dp.getOrDefault(target, 0);

    }


    /**
     * 示例 1：
     * <p>
     * 输入：nums = [1,1,1,1,1], target = 3
     * 输出：5
     * 解释：一共有 5 种方法让最终目标和为 3 。
     * -1 + 1 + 1 + 1 + 1 = 3
     * +1 - 1 + 1 + 1 + 1 = 3
     * +1 + 1 - 1 + 1 + 1 = 3
     * +1 + 1 + 1 - 1 + 1 = 3
     * +1 + 1 + 1 + 1 - 1 = 3
     * 示例 2：
     * <p>
     * 输入：nums = [1], target = 1
     * 输出：1
     * <p>
     * 1, 0
     * 1
     */
    public static void main(String[] args) {
        System.out.println(new _494().findTargetSumWays(
                TestCaseInputUtils.getIntArr("[1, 0]"),
                1
        ));
    }

}
