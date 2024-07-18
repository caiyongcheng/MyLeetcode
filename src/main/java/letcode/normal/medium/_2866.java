package letcode.normal.medium;

import letcode.utils.TestCaseInputUtils;

import java.util.List;
import java.util.Stack;

/**
 * 给你一个长度为 n 下标从 0 开始的整数数组 maxHeights 。  你的任务是在坐标轴上建 n 座塔。第 i 座塔的下标为 i ，高度为 heights[i] 。
 * 如果以下条件满足，我们称这些塔是 美丽 的：  1 <= heights[i] <= maxHeights[i] heights 是一个 山脉 数组。
 * 如果存在下标 i 满足以下条件，那么我们称数组 heights 是一个 山脉 数组：
 * 对于所有 0 < j <= i ，都有 heights[j - 1] <= heights[j] 对于所有 i <= k < n - 1 ，都有 heights[k + 1] <= heights[k]
 * 请你返回满足 美丽塔 要求的方案中，高度和的最大值 。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2023/12/26 10:16
 */
public class _2866 {

    public long maximumSumOfHeights(List<Integer> maxHeights) {
        // 要想高度和最大 也就是要每个元素都尽可能的大
        // 每个元素的大小都取决于响应maxHeights元素与相邻元素的大小关系
        // 所以选取最大值作为峰值 因为这样子其他元素的上限就会尽可能的高
        // 这样的考虑是有问题的 因为如果最大值旁边是两个小值 会导致整体的值偏小
        // 第一点 选择什么样的数可以作为峰值 明显 非递减序列、非递减序列的最大值可以作为峰值
        // 因为选取他们作为峰值，不会降低序列中的其他元素的最大值上限
        // 第二点 较小值越靠近峰值 对数据的影响也就越大 2 3 3 4 1 5 1
        // 如果这样去计算的话 最多有n/2个峰值 时间复杂度是 n^2 但是如果能够快速去计算和的话 时间复杂度就会降低
        // 所谓山脉数组，其实就是把数组划分成两部分，左侧非递减，右侧非递增 使用单调栈即可
        // 以左侧数组为例 左侧是非递减的 所以比栈顶元素小 就出栈 直到栈元素为空 或者不再出栈为止
        // 如果栈为空 那么长度*当前值就是结果 不为空的话就是之前 当前栈顶元素所对应部分的和 + 影响部分长度*当前值

        int size = maxHeights.size();
        long[] left2Right = new long[size];
        long[] right2Left = new long[size];
        Stack<Integer> stack = new Stack<>();
        left2Right[0] = maxHeights.get(0);
        stack.push(0);
        int curVal;
        for (int i = 1; i < size; i++) {
            curVal = maxHeights.get(i);
            while (!stack.empty() && curVal < maxHeights.get(stack.peek())) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                left2Right[i] = (long) (i + 1) * curVal;
            } else {
                left2Right[i] = left2Right[stack.peek()] + (long) (i - stack.peek()) * curVal;
            }
            stack.push(i);
        }
        stack.clear();
        right2Left[size - 1] = maxHeights.get(size - 1);
        stack.push(size - 1);
        for (int i = size - 2; i >= 0; i--) {
            curVal = maxHeights.get(i);
            while (!stack.empty() && curVal < maxHeights.get(stack.peek())) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                right2Left[i] = (long) (size - i) * curVal;
            } else {
                right2Left[i] = right2Left[stack.peek()] + (long) (stack.peek() - i) * curVal;
            }
            stack.push(i);
        }
        long ans = 0;
        for (int i = 0; i < size; i++) {
            ans = Math.max(ans, left2Right[i] - maxHeights.get(i) + right2Left[i]);
        }
        return ans;
    }

    /**
     * 示例 1：
     *
     * 输入：maxHeights = [5,3,4,1,1]
     * 输出：13
     * 解释：和最大的美丽塔方案为 heights = [5,3,3,1,1] ，这是一个美丽塔方案，因为：
     * - 1 <= heights[i] <= maxHeights[i]
     * - heights 是个山脉数组，峰值在 i = 0 处。
     * 13 是所有美丽塔方案中的最大高度和。
     * 示例 2：
     *
     * 输入：maxHeights = [6,5,3,9,2,7]
     * 输出：22
     * 解释： 和最大的美丽塔方案为 heights = [3,3,3,9,2,2] ，这是一个美丽塔方案，因为：
     * - 1 <= heights[i] <= maxHeights[i]
     * - heights 是个山脉数组，峰值在 i = 3 处。
     * 22 是所有美丽塔方案中的最大高度和。
     * 示例 3：
     *
     * 输入：maxHeights = [3,2,5,5,2,3]
     * 输出：18
     * 解释：和最大的美丽塔方案为 heights = [2,2,5,5,2,2] ，这是一个美丽塔方案，因为：
     * - 1 <= heights[i] <= maxHeights[i]
     * - heights 是个山脉数组，最大值在 i = 2 处。
     * 注意，在这个方案中，i = 3 也是一个峰值。
     * 18 是所有美丽塔方案中的最大高度和。
     * @param args
     */
    public static void main(String[] args) {
        // 1 3 4   2
        System.out.println(new _2866().maximumSumOfHeights(
                TestCaseInputUtils.getIntegerList("[314324228,526196638,971780775,141382951,44825730,92939243,869702460,692214717,396184442,271863091,452818943,124554145,194393992,813279621,476977123,291285997,195696382,80619001,296691434,24194433,834306546,337273583,612960339,252148987,498162770,641751698,580675254,66186200,192009966,590634046,590252844,510204257,235020771,606202644,338253570,224352005,183647397,867961176,521468453,365745792,508222499,360685429,851354307,177768509,955097078,227459453,644376561,467834249,594236609,319781404,648225233,524439197,532203513,463002246,498592686,691351312,208635346,155682966,294639403,341617283,604365123,79112831,22440031,809193898,675993946,99928197,644324211,170555722,218906830,782039120,686747235,356537885]")
        ));
    }

}
