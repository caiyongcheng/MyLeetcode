package letcode.normal.difficult;

import letcode.utils.TestCaseUtils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 你有一台电脑，它可以 同时 运行无数个任务。给你一个二维整数数组 tasks ，其中 tasks[i] = [starti, endi, durationi] 表示第 i 个任务需要在
 * 闭区间 时间段 [starti, endi] 内运行 durationi 个整数时间点（但不需要连续）。
 * 当电脑需要运行任务时，你可以打开电脑，如果空闲时，你可以将电脑关闭。
 * 请你返回完成所有任务的情况下，电脑最少需要运行多少秒。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/5/15 14:06
 */
public class _2589 {

    public int findMinimumTime(int[][] tasks) {
        /*
        明显这道题的思路就是贪心
        尽量保证保证某个时间内有更多的任务在运行

        枚举时间 将tasks按结束时间升序排列 同时维护一个数组 表示正在运行的时间节点
        对于每个 task 都尽可能的利用已经运行的时间
         */

        Arrays.sort(tasks, Comparator.comparingInt(task -> task[1]));
        int[] runTime = new int[tasks[tasks.length - 1][1] + 1];
        int ans = 0;

        for (int[] task : tasks) {
            // 统计区间内有多少节点已经执行了任务
            for (int timeIdx = task[0]; timeIdx <= task[1]; ++timeIdx) {
                task[2] -= runTime[timeIdx];
                if (task[2] == 0) {
                    break;
                }
            }
            // 还需要执行多久的任务
            ans += task[2];
            // 决定区间内哪些点用于执行任务 优先选择靠后的 因为排序了 靠后的比考前的更容易重合利用
            while (task[1] >= task[0] && task[2] > 0) {
                if (runTime[task[1]] == 0) {
                    --task[2];
                    runTime[task[1]] = 1;
                }
                --task[1];
            }
        }

        return ans;

    }



    /**
     * 示例 1：
     *
     * 输入：tasks = [[2,3,1],[4,5,1],[1,5,2]]
     * 输出：2
     * 解释：
     * - 第一个任务在闭区间 [2, 2] 运行。
     * - 第二个任务在闭区间 [5, 5] 运行。
     * - 第三个任务在闭区间 [2, 2] 和 [5, 5] 运行。
     * 电脑总共运行 2 个整数时间点。
     * 示例 2：
     *
     * 输入：tasks = [[1,3,2],[2,5,3],[5,6,2]]
     * 输出：4
     * 解释：
     * - 第一个任务在闭区间 [2, 3] 运行
     * - 第二个任务在闭区间 [2, 3] 和 [5, 5] 运行。
     * - 第三个任务在闭区间 [5, 6] 运行。
     * 电脑总共运行 4 个整数时间点。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2589().findMinimumTime(
                TestCaseUtils.get2DIntArr("[[8,19,1],[3,20,1],[1,20,2],[6,13,3]]")
        ));
    }

}
