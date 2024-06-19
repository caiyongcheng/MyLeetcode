package letcode.normal.medium;

import letcode.utils.TestCaseUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个下标从 0 开始的整数数组 tasks ，其中 tasks[i] 表示任务的难度级别。在每一轮中，你可以完成 2 个或者 3 个 相同难度级别 的任务。
 * 返回完成所有任务需要的 最少 轮数，如果无法完成所有任务，返回 -1 。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/5/14 15:43
 */
public class _2244TwoThousandTwoHundredFortyFour {

    public int minimumRounds(int[] tasks) {
        Map<Integer, Integer> tasksGroupByLevel = new HashMap<>(tasks.length);
        for (int task : tasks) {
            tasksGroupByLevel.put(task, tasksGroupByLevel.getOrDefault(task, 0) + 1);
        }

        int ans = 0;
        int cnt;
        for (Map.Entry<Integer, Integer> entry : tasksGroupByLevel.entrySet()) {
            cnt = entry.getValue();
            if (cnt < 2) {
                return -1;
            }
            ans += (cnt + 2) / 3;
        }

        return ans;
    }


    public static void main(String[] args) {
        System.out.println(new _2244TwoThousandTwoHundredFortyFour().minimumRounds(
                TestCaseUtils.getIntArr("[2,3,3]")
        ));
    }

}
