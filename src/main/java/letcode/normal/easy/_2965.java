package letcode.normal.easy;

import letcode.utils.FormatUtils;
import letcode.utils.TestCaseUtils;

/**
 * 给你一个下标从 0 开始的二维整数矩阵 grid，大小为 n * n ，其中的值在 [1, n2] 范围内。除了 a 出现 两次，b 缺失 之外，每个整数都 恰好出现一次 。
 * 任务是找出重复的数字a 和缺失的数字 b 。  返回一个下标从 0 开始、长度为 2 的整数数组 ans ，其中 ans[0] 等于 a ，ans[1] 等于 b 。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-05-31 09:06
 */
public class _2965 {

    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int length = grid.length;
        int[] statistics = new int[length * length + 1];
        for (int[] arr : grid) {
            for (int num : arr) {
                statistics[num]++;
            }
        }

        int noneNum = 0;
        int twiceNum = 0;
        for (int i = 0; i < statistics.length; i++) {
            if (statistics[i] == 0) {
                noneNum = i;
            } else if (statistics[i] == 2) {
                twiceNum = i;
            }
        }
        return new int[]{twiceNum, noneNum};

    }

    /**
     * 示例 1：
     *
     * 输入：grid = [[1,3],[2,2]]
     * 输出：[2,4]
     * 解释：数字 2 重复，数字 4 缺失，所以答案是 [2,4] 。
     * 示例 2：
     *
     * 输入：grid = [[9,1,7],[8,9,2],[3,4,6]]
     * 输出：[9,5]
     * 解释：数字 9 重复，数字 5 缺失，所以答案是 [9,5] 。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatUtils.formatArray(new _2965().findMissingAndRepeatedValues(
                TestCaseUtils.get2DIntArr("[[9,1,7],[8,9,2],[3,4,6]]")
        )));
    }


}
