package letcode.normal.medium;

import java.util.PriorityQueue;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/10/18 14:12
 * description 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。你的 起始分数 为 0 。
 * 在一步 操作 中：  选出一个满足 0 <= i < nums.length 的下标 i ， 将你的 分数 增加 nums[i] ，并且 将 nums[i] 替换为 ceil(nums[i] / 3) 。
 * 返回在 恰好 执行 k 次操作后，你可能获得的最大分数。  向上取整函数 ceil(val) 的结果是大于或等于 val 的最小整数。
 */
public class _2530 {

    public long maxKelements(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b) -> Integer.compare(b, a));
        for (int num : nums) {
            queue.add(num);
        }
        long ans = 0;
        int top;
        for (int i = 0; i < k; i++) {
            // 根据题目数据范围 不考虑NPE
            top = queue.poll();
            ans += top;
            // add(e) 的方法实质还是调用了offer
            queue.offer((top + 2) / 3);
        }
        return ans;
    }

}
