package letcode.normal.medium;

import letcode.utils.TestCaseInputUtils;

/**
 * 给你 n 个项目，编号从 0 到 n - 1 。同时给你一个整数数组 milestones ，其中每个 milestones[i] 表示第 i 个项目中的阶段任务数量。
 * 你可以按下面两个规则参与项目中的工作：  每周，你将会完成 某一个 项目中的 恰好一个 阶段任务。你每周都 必须 工作。 在 连续的 两周中，
 * 你 不能 参与并完成同一个项目中的两个阶段任务。
 * 一旦所有项目中的全部阶段任务都完成，或者仅剩余一个阶段任务都会导致你违反上面的规则，那么你将 停止工作 。注意，由于这些条件的限制，你可能无法完成所有阶段任务。
 * 返回在不违反上面规则的情况下你 最多 能工作多少周。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/5/16 14:09
 */
public class _1953 {

    public long numberOfWeeks(int[] milestones) {
        /*
        考虑中断的可能性
        一种是全部完成了 此时结果就是数组和
        一种是剩下一个任务没有完成 此时该任务应该是最大的任务 且超过了剩余任务之和 那么结果就是剩余任务之和*2+1
         */
        int maxMilestone = -1;
        long milestoneSum = 0;
        for (int milestone : milestones) {
            if (milestone > maxMilestone) {
                maxMilestone = milestone;
            }
            milestoneSum += milestone;
        }
        if (((long) maxMilestone) << 1 > milestoneSum) {
            return ((milestoneSum - maxMilestone) << 1) + 1;
        }
        return milestoneSum;
    }


    /**
     * 示例 1：
     *
     * 输入：milestones = [1,2,3]
     * 输出：6
     * 解释：一种可能的情形是：
     * - 第 1 周，你参与并完成项目 0 中的一个阶段任务。
     * - 第 2 周，你参与并完成项目 2 中的一个阶段任务。
     * - 第 3 周，你参与并完成项目 1 中的一个阶段任务。
     * - 第 4 周，你参与并完成项目 2 中的一个阶段任务。
     * - 第 5 周，你参与并完成项目 1 中的一个阶段任务。
     * - 第 6 周，你参与并完成项目 2 中的一个阶段任务。
     * 总周数是 6 。
     * 示例 2：
     *
     * 输入：milestones = [5,2,1]
     * 输出：7
     * 解释：一种可能的情形是：
     * - 第 1 周，你参与并完成项目 0 中的一个阶段任务。
     * - 第 2 周，你参与并完成项目 1 中的一个阶段任务。
     * - 第 3 周，你参与并完成项目 0 中的一个阶段任务。
     * - 第 4 周，你参与并完成项目 1 中的一个阶段任务。
     * - 第 5 周，你参与并完成项目 0 中的一个阶段任务。
     * - 第 6 周，你参与并完成项目 2 中的一个阶段任务。
     * - 第 7 周，你参与并完成项目 0 中的一个阶段任务。
     * 总周数是 7 。
     * 注意，你不能在第 8 周参与完成项目 0 中的最后一个阶段任务，因为这会违反规则。
     * 因此，项目 0 中会有一个阶段任务维持未完成状态。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1953().numberOfWeeks(
                TestCaseInputUtils.getIntArr("[42,72,43,54,2,53,36,73,78,84,67,94,70,59,84,8,93,52,4,46]")
        ));
    }

}
