package letcode.normal.medium;

import letcode.utils.TestCaseUtils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给你一个二维整数数组 ranges ，其中 ranges[i] = [starti, endi] 表示 starti 到 endi 之间（包括二者）的所有整数都包含在第 i 个区间中。
 * 你需要将 ranges 分成 两个 组（可以为空），满足：  每个区间只属于一个组。 两个有 交集 的区间必须在 同一个 组内。 如果两个区间有至少 一个 公共整数，
 * 那么这两个区间是 有交集 的。  比方说，区间 [1, 3] 和 [2, 5] 有交集，因为 2 和 3 在两个区间中都被包含。
 * 请你返回将 ranges 划分成两个组的 总方案数 。由于答案可能很大，将它对 109 + 7 取余 后返回。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/3/27 10:18
 */
public class _2580 {

    public int countWays(int[][] ranges) {
        /**
         * 先分组 有交集的必须放在同一组 故先排序
         * 在统计 分组后有n个组 每次从n个组中取出t个组作为第一组 剩下的n-t个作为第二组 所以结果等于 n个组的集合数量
         */

        // 分组
        Arrays.sort(ranges, Comparator.comparingInt(r -> r[0]));
        int curLen = 1;
        int[] curRange = ranges[0];
        for (int i = 1; i < ranges.length; i++) {
            if (curRange[1] >= ranges[i][0]) {
                curRange[1] = Integer.max(curRange[1], ranges[i][1]);
            } else {
                curRange = ranges[i];
                ++curLen;
            }
        }

        // 统计
        int ans = 1;
        int mod = 10_0000_0000 + 7;
        for (int i = 0; i < curLen; i++) {
            ans = (ans << 1) % mod;
        }
        return ans;
    }

    /**
     * 示例 1：
     *
     * 输入：ranges = [[6,10],[5,15]]
     * 输出：2
     * 解释：
     * 两个区间有交集，所以它们必须在同一个组内。
     * 所以有两种方案：
     * - 将两个区间都放在第 1 个组中。
     * - 将两个区间都放在第 2 个组中。
     * 示例 2：
     *
     * 输入：ranges = [[1,3],[10,20],[2,5],[4,8]]
     * 输出：4
     * 解释：
     * 区间 [1,3] 和 [2,5] 有交集，所以它们必须在同一个组中。
     * 同理，区间 [2,5] 和 [4,8] 也有交集，所以它们也必须在同一个组中。
     * 所以总共有 4 种分组方案：
     * - 所有区间都在第 1 组。
     * - 所有区间都在第 2 组。
     * - 区间 [1,3] ，[2,5] 和 [4,8] 在第 1 个组中，[10,20] 在第 2 个组中。
     * - 区间 [1,3] ，[2,5] 和 [4,8] 在第 2 个组中，[10,20] 在第 1 个组中。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2580().countWays(
                TestCaseUtils.get2DIntArr("[[34,56],[28,29],[12,16],[11,48],[28,54],[22,55],[28,41],[41,44]]")
        ));
    }

}
