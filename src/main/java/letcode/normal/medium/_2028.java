package letcode.normal.medium;

import letcode.utils.TestCaseOutputUtils;
import letcode.utils.TestCaseInputUtils;

/**
 * 现有一份 n + m 次投掷单个 六面 骰子的观测数据，骰子的每个面从 1 到 6 编号。观测数据中缺失了 n 份，你手上只拿到剩余 m 次投掷的数据。
 * 幸好你有之前计算过的这 n + m 次投掷数据的 平均值 。
 * 给你一个长度为 m 的整数数组 rolls ，其中 rolls[i] 是第 i 次观测的值。同时给你两个整数 mean 和 n 。
 * 返回一个长度为 n 的数组，包含所有缺失的观测数据，且满足这 n + m 次投掷的 平均值 是 mean 。
 * 如果存在多组符合要求的答案，只需要返回其中任意一组即可。如果不存在答案，返回一个空数组。
 * k 个数字的 平均值 为这些数字求和后再除以 k 。  注意 mean 是一个整数，所以 n + m 次投掷的总和需要被 n + m 整除。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-05-27 09:50
 */
public class _2028 {

    public int[] missingRolls(int[] rolls, int mean, int n) {
        int targetSum = mean * (n + rolls.length);
        int portionSum = 0;
        int exceptSum;
        for (int roll : rolls) {
            portionSum += roll;
        }

        exceptSum = targetSum - portionSum;
        if (exceptSum < n || exceptSum > n * 6) {
            return new int[0];
        }

        int[] ans = new int[n];
        int i = 0;
        while (exceptSum - 6 > n - i - 1) {
            ans[i] = 6;
            exceptSum -= 6;
            ++i;
        }
        while (i < n - 1) {
            ans[i] = 1;
            exceptSum -= 1;
            ++i;
        }
        if (exceptSum != 0) {
            ans[i] = exceptSum;
        }
        return ans;

    }

    /**
     * 示例 1：
     *
     * 输入：rolls = [3,2,4,3], mean = 4, n = 2
     * 输出：[6,6]
     * 解释：所有 n + m 次投掷的平均值是 (3 + 2 + 4 + 3 + 6 + 6) / 6 = 4 。
     * 示例 2：
     *
     * 输入：rolls = [1,5,6], mean = 3, n = 4
     * 输出：[2,3,2,2]
     * 解释：所有 n + m 次投掷的平均值是 (1 + 5 + 6 + 2 + 3 + 2 + 2) / 7 = 3 。
     * 示例 3：
     *
     * 输入：rolls = [1,2,3,4], mean = 6, n = 4
     * 输出：[]
     * 解释：无论丢失的 4 次数据是什么，平均值都不可能是 6 。
     * 示例 4：
     *
     * 输入：rolls = [1], mean = 3, n = 1
     * 输出：[5]
     * 解释：所有 n + m 次投掷的平均值是 (1 + 5) / 2 = 3 。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(TestCaseOutputUtils.formatArray(new _2028().missingRolls(
                TestCaseInputUtils.getIntArr("[4,5,6,2,3,6,5,4,6,4,5,1,6,3,1,4,5,5,3,2,3,5,3,2,1,5,4,3,5,1,5]"),
                4,
                40
        )));
    }

}
