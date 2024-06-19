package letcode.normal.medium;

import letcode.utils.TestCaseUtils;

/**
 * 给你一个 二进制数组 nums 。
 * 如果一个 子数组 中 不存在 两个 相邻 元素的值 相同 的情况，我们称这样的子数组为 交替子数组 。
 * 返回数组 nums 中交替子数组的数量。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-05-28 14:39
 */
public class _3101 {

    public long countAlternatingSubarrays(int[] nums) {
        /*
        交替子数组中的每个子数组也都是交替子数组
        那么长度为n的交替子数组 可以构造 n个长度为1 n-1个长度为2 一直到1一个长度为n的 共计(n * n + n) / 2个
        统计数组中可以划分成多少个不同的交替子数组即可

        更好的写法
        long ans = 0, cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            cnt = i > 0 && nums[i] == nums[i - 1] ? 1 : cnt + 1;
            ans += cnt; // 有 cnt 个以 i 为右端点的交替子数组
        }
        return ans;
         */
        int startIdx = 0;
        int len;
        long ans = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i-1] == nums[i]) {
                len = i - startIdx;
                ans += (((long) len * len + len) >>> 1);
                startIdx = i;
            }
        }
        len = nums.length - startIdx;
        ans += (((long) len * len + len) >>> 1);
        return ans;
    }


    /**
     * 示例 1：
     * 输入： nums = [0,1,1,1]
     * 输出： 5
     * 解释：
     * 以下子数组是交替子数组：[0] 、[1] 、[1] 、[1] 以及 [0,1] 。
     * 示例 2：
     * 输入： nums = [1,0,1,0]
     * 输出： 10
     * 解释：
     * 数组的每个子数组都是交替子数组。可以统计在内的子数组共有 10 个。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _3101().countAlternatingSubarrays(
                TestCaseUtils.getIntArr(
                        "[1,0,1,0]"
                )
        ));
    }

}
