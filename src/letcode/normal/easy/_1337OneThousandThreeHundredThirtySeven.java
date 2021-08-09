package letcode.normal.easy;

import letcode.utils.FormatPrintUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 给你一个大小为 m * n 的矩阵 mat，矩阵由若干军人和平民组成，分别用 1 和 0 表示。
 * 请你返回矩阵中战斗力最弱的 k 行的索引，按从最弱到最强排序。
 * 如果第 i 行的军人数量少于第 j 行，或者两行军人数量相同但 i 小于 j，那么我们认为第 i 行的战斗力比第 j 行弱。
 * 军人 总是 排在一行中的靠前位置，也就是说 1 总是出现在 0 之前。
 *
 * @author CaiYongcheng
 * @date 2021-08-02 09:03
 **/
public class _1337OneThousandThreeHundredThirtySeven {

    public int[] kWeakestRows(int[][] mat, int k) {
        int[][] ans = new int[mat.length][2];
        int[] res = new int[k];
        for (int row = 0; row < mat.length; row++) {
            ans[row][1] = row;
            ans[row][0] = 0;
            for (int item : mat[row]) {
                if (item > 0) {
                    ans[row][0]++;
                } else {
                    break;
                }
            }
        }
        Arrays.sort(ans, (o1, o2) -> o1[0] > o2[0] ? 1 : o1[0] < o2[0] ? -1 : Integer.compare(o1[1], o2[1]));
        for (int i = 0; i < res.length; i++) {
            res[i] = ans[i][1];
        }
        return res;
    }


    /**
     * 示例 1：
     *
     * 输入：mat =
     * {{1,1,0,0,0},
     *  {1,1,1,1,0},
     *  {1,0,0,0,0},
     *  {1,1,0,0,0},
     *  {1,1,1,1,1}},
     * k = 3
     * 输出：{2,0,3}
     * 解释：
     * 每行中的军人数目：
     * 行 0 -> 2
     * 行 1 -> 4
     * 行 2 -> 1
     * 行 3 -> 2
     * 行 4 -> 5
     * 从最弱到最强对这些行排序后得到 {2,0,3,1,4}
     * 示例 2：
     *
     * 输入：mat =
     * {{1,0,0,0},
     * {1,1,1,1},
     * {1,0,0,0},
     * {1,0,0,0}},
     * k = 2
     * 输出：{0,2}
     * 解释：
     * 每行中的军人数目：
     * 行 0 -> 1
     * 行 1 -> 4
     * 行 2 -> 1
     * 行 3 -> 1
     * 从最弱到最强对这些行排序后得到 {0,2,3,1}
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/the-k-weakest-rows-in-a-matrix
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatPrintUtils.formatArray(new _1337OneThousandThreeHundredThirtySeven().kWeakestRows(
                new int[][] {{1,0,0,0},
                        {1,1,1,1},
                {1,0,0,0},
                {1,0,0,0}},
                2
        )));
    }

}
