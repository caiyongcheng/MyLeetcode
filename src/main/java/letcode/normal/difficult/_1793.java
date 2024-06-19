package letcode.normal.difficult;

import letcode.utils.TestCaseUtils;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 一个子数组 (i, j) 的 分数 定义为 min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1) 。
 * 一个 好 子数组的两个端点下标需要满足 i <= k <= j 。  请你返回 好 子数组的最大可能 分数 。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/3/19 09:05
 */
public class _1793 {

    public int maximumScore(int[] nums, int k) {
        /*
        从数据规模上看 使用O(n^2)算法肯定会超时
        问题上 是求 区间内最小值和区间长度乘积的最大值
        所以 问题在于 某个值以及他左右两侧第一个比他小的值
        这种情况下 可以使用单调栈进行处理
         */
        // 保存每个元素左右两边第一个小于元素
        Deque<Integer> stack = new ArrayDeque<>(nums.length);
        int[] leftMinArr = getMinArr(nums, stack,  -1, -1);
        int[] rightMinArr = getMinArr(nums, stack, 1, nums.length);
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int leftMinIdx = leftMinArr[i];
            int rightMinIdx = rightMinArr[i];
            if (leftMinIdx >= k || rightMinIdx <= k) {
                continue;
            }
            ans = Integer.max(ans,  nums[i] * (rightMinIdx - leftMinIdx - 1));
        }
        return ans;
    }

    private static int[] getMinArr(int[] nums, Deque<Integer> stack, int step, int limitIdx) {
        int[] minArr = new int[nums.length];
        for (int i = step > 0 ? 0 : nums.length - 1; i < nums.length && i > -1; i += step) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                minArr[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            minArr[stack.pop()] = limitIdx;
        }
        return minArr;
    }


    /**
     * 示例 1：
     *
     * 输入：nums = [1,4,3,7,4,5], k = 3
     * 输出：15
     * 解释：最优子数组的左右端点下标是 (1, 5) ，分数为 min(4,3,7,4,5) * (5-1+1) = 3 * 5 = 15 。
     * 示例 2：
     *
     * 输入：nums = [5,5,4,5,4,1,1,1], k = 0
     * 输出：20
     * 解释：最优子数组的左右端点下标是 (0, 4) ，分数为 min(5,5,4,5,4) * (4-0+1) = 4 * 5 = 20 。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1793().maximumScore(
                TestCaseUtils.getIntArr("[5,5,4,5,4,1,1,1]"),
                0
        ));
    }

}
