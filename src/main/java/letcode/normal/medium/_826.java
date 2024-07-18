package letcode.normal.medium;

import letcode.utils.TestCaseInputUtils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 你有 n 个工作和 m 个工人。给定三个数组： difficulty, profit 和 worker ，其中:  difficulty[i] 表示第 i 个工作的难度，
 * profit[i] 表示第 i 个工作的收益。 worker[i] 是第 i 个工人的能力，即该工人只能完成难度小于等于 worker[i] 的工作。
 * 每个工人 最多 只能安排 一个 工作，但是一个工作可以 完成多次 。
 * 举个例子，如果 3 个工人都尝试完成一份报酬为 $1 的同样工作，那么总收益为 $3 。如果一个工人不能完成任何工作，他的收益为 $0 。
 * 返回 在把工人分配到工作岗位后，我们所能获得的最大利润 。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/5/17 16:59
 */
public class _826 {


    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        /*
        明显是贪心 每个工人需要完成能力范围内所能完成的收益最高的工作
        将工作按难度排序 同时维护到当前排序范围内的最高工作价值
        对于每个工人二分搜索即可
         */
        int[][] wrapArr = new int[difficulty.length][3];
        for (int i = 0; i < wrapArr.length; i++) {
            wrapArr[i][0] = difficulty[i];
            wrapArr[i][1] = profit[i];
        }

        int curMaxProfit = Integer.MIN_VALUE;
        Arrays.sort(wrapArr, Comparator.comparingInt(wrap -> wrap[0]));
        for (int[] wrap : wrapArr) {
            if (wrap[1] > curMaxProfit) {
                curMaxProfit = wrap[1];
            }
            wrap[2] = curMaxProfit;
        }

        int totalProfit = 0;
        for (int capacity : worker) {
            totalProfit += binarySearchMaxProfit(wrapArr, capacity);
        }
        return totalProfit;

    }

    public int binarySearchMaxProfit(int[][] wrapArr, int capacity) {
        if (capacity >= wrapArr[wrapArr.length - 1][0]) {
            return wrapArr[wrapArr.length - 1][2];
        }
        if (capacity < wrapArr[0][0]) {
            return 0;
        }
        int leftIdx = 0;
        int rightIdx = wrapArr.length - 1;
        int mid;
        while (leftIdx <= rightIdx) {
            mid = (leftIdx + rightIdx) >>> 1;
            if (mid == leftIdx) {
                break;
            }
            if (wrapArr[mid][0] > capacity) {
                rightIdx = mid;
            } else {
                leftIdx = mid;
            }
        }
        return wrapArr[leftIdx][2];
    }


    /**
     * 示例 1：
     *
     * 输入: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
     * 输出: 100
     * 解释: 工人被分配的工作难度是 [4,4,6,6] ，分别获得 [20,20,30,30] 的收益。
     * 示例 2:
     *
     * 输入: difficulty = [85,47,57], profit = [24,66,99], worker = [40,25,25]
     * 输出: 0
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _826().maxProfitAssignment(
                TestCaseInputUtils.getIntArr("[85,47,57]"),
                TestCaseInputUtils.getIntArr("[24,66,99]"),
                TestCaseInputUtils.getIntArr("[40,25,25]")
        ));
    }
}
