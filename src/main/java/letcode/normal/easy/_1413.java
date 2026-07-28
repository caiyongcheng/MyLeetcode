package letcode.normal.easy;

/**
 * 给你一个整数数组 nums。你可以选定任意的正数 startValue 作为初始值。
 * 你需要从左到右遍历 nums数组，并将 startValue 依次累加上nums数组中的值。
 * 请你在确保累加和始终大于等于 1 的前提下，选出一个最小的正数作为 startValue 。
 * 来源：力扣（LeetCode） 链接：https://leetcode.cn/problems/minimum-value-to-get-positive-step-by-step-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-08-09 14:33
 **/
public class _1413 {

    public int minStartValue(int[] nums) {
        int sum = 0;
        int minSum = Integer.MAX_VALUE;
        for (int num : nums) {
            sum += num;
            if (minSum > sum) {
                minSum = sum;
            }
        }
        return minSum < 0 ? -minSum + 1 : 1;
    }

}
