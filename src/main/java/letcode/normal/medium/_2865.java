package letcode.normal.medium;

import java.util.List;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2024/1/24 23:16
 * description 给你一个长度为 n 下标从 0 开始的整数数组 maxHeights 。
 * 你的任务是在坐标轴上建 n 座塔。第 i 座塔的下标为 i ，高度为 heights[i] 。
 * 如果以下条件满足，我们称这些塔是 美丽 的：  1 <= heights[i] <= maxHeights[i] heights 是一个 山脉 数组。 如果存在下标 i 满足以下条件，
 * 那么我们称数组 heights 是一个 山脉 数组：  对于所有 0 < j <= i ，都有 heights[j - 1] <= heights[j] 对于所有 i <= k < n - 1 ，都有 heights[k + 1] <= heights[k]
 * 请你返回满足 美丽塔 要求的方案中，高度和的最大值 。
 *
 * 1 <= n == maxHeights <= 10^3
 * 1 <= maxHeights[i] <= 10^9
 */
public class _2865 {

    public long maximumSumOfHeights(List<Integer> maxHeights) {
        /*
        从数据规模来看 直接暴力即可

         */
        return 0L;
    }

}
