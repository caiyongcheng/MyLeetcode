package normal.medium;

import java.util.HashMap;

/**
 * @program: Leetcode
 * @description: 给你一个整数数组 heights ，表示建筑物的高度。
 * 另有一些砖块 bricks 和梯子 ladders 。
 * 你从建筑物 0 开始旅程，不断向后面的建筑物移动，期间可能会用到砖块或梯子。
 * 当从建筑物 i 移动到建筑物 i+1（下标 从 0 开始 ）时：
 * 如果当前建筑物的高度 大于或等于 下一建筑物的高度，则不需要梯子或砖块
 * 如果当前建筑的高度 小于 下一个建筑的高度，
 * 您可以使用 一架梯子 或 (h[i+1] - h[i]) 个砖块 如果以最佳方式使用给定的梯子和砖块，
 * 返回你可以到达的最远建筑物的下标（下标 从 0 开始 ）。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/furthest-building-you-can-reach
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-12-08 16:50
 */
public class _1642OneThousandSixHundredFortyTwo {

    private HashMap<String, Integer> cache;
    private int[] heights;

    private int dp(int nowIndex, int remainBricks, int remainLadders) {
        while (nowIndex < heights.length-1 && heights[nowIndex] >= heights[nowIndex+1]) {
            ++nowIndex;
        }
        if (nowIndex >= heights.length - 1) {
            return heights.length - 1;
        }
        String cacheKey = nowIndex+"_"+remainBricks+"_"+remainLadders;
        if (cache.containsKey(cacheKey)) {
            return cache.get(cacheKey);
        }
        int cacheValue = nowIndex;
        if (heights[nowIndex+1] - heights[nowIndex] <= remainBricks) {
            cacheValue = Math.max(cacheValue, dp(
               nowIndex+1,
               remainBricks - (heights[nowIndex+1] - heights[nowIndex]),
               remainLadders
            ));
        }
        if (remainLadders > 0) {
            cacheValue = Math.max(cacheValue, dp(
                    nowIndex+1,
                    remainBricks,
                    remainLadders-1
            ));
        }
        cache.put(cacheKey, cacheValue);
        return cacheValue;
    }

    /**
     * 输入：heights = [4,2,7,6,9,14,12], bricks = 5, ladders = 1
     * 输出：4
     * 解释：从建筑物 0 出发，你可以按此方案完成旅程：
     * - 不使用砖块或梯子到达建筑物 1 ，因为 4 >= 2
     * - 使用 5 个砖块到达建筑物 2 。你必须使用砖块或梯子，因为 2 < 7
     * - 不使用砖块或梯子到达建筑物 3 ，因为 7 >= 6
     * - 使用唯一的梯子到达建筑物 4 。你必须使用砖块或梯子，因为 6 < 9
     * 无法越过建筑物 4 ，因为没有更多砖块或梯子。
     *
     * 示例 2：
     * 输入：heights = [4,12,2,7,3,18,20,3,19], bricks = 10, ladders = 2
     * 输出：7
     *
     * 示例 3：
     * 输入：heights = [14,3,19,3], bricks = 17, ladders = 0
     * 输出：3
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/furthest-building-you-can-reach
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        cache = new HashMap<>(heights.length);
        this.heights = heights;
        return dp(0, bricks, ladders);
    }

    public static void main(String[] args) {
        System.out.println(new _1642OneThousandSixHundredFortyTwo()
                .furthestBuilding(new int[]{4,2,7,6,9,14,12}, 5, 1));
    }

}