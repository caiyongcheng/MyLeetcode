package letcode.normal.difficult;

import letcode.utils.FormatUtils;
import letcode.utils.TestCaseUtils;

import java.util.*;

/**
 * 给你一个下标从 0 开始大小为 m x n 的二进制矩阵 grid 。
 * 从原矩阵中选出若干行构成一个行的 非空 子集，如果子集中任何一列的和至多为子集大小的一半，那么我们称这个子集是 好子集。
 * 更正式的，如果选出来的行子集大小（即行的数量）为 k，那么每一列的和至多为 floor(k / 2) 。
 * 请你返回一个整数数组，它包含好子集的行下标，请你将子集中的元素 升序 返回。  如果有多个好子集，你可以返回任意一个。如果没有好子集，请你返回一个空数组。
 * 一个矩阵 grid 的行 子集 ，是删除 grid 中某些（也可能不删除）行后，剩余行构成的元素集合。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-06-25 11:07
 */
public class _2732 {

    public List<Integer> goodSubsetofBinaryMatrix(int[][] grid) {
        /**
         * 假设答案至少一行，那么需要这一行满足全为 0。
         * 假设答案至少两行，那么不存在一列这两行都是 1。分别用 xxx 和 yyy 表示这两行所表示的数，能推出至少存在两行 x&y==0x \& y == 0x&y==0。
         * 假设答案至少三行，那么这三行每一列加起来的和不超过 1。这个条件比两行的情况更严格，如果两行都找不到答案，那么一定没有三行的情况了。
         * 假设答案至少四行，那么这四行每一列加起来的和不超过 2。如果两行找不到答案，说明任选两行至少存在一列这两行都是1。同时这一列这两行已经都是1了，
         * 那么这一列的其他两行必须是0才满足条件。所以，当答案有四行的情况下，需要满足任选两行这一列都是1同时其他两行必须是0至少需要 C42=6
         * 但题意说矩阵的列数 n<=5n <= 5n<=5，因此这种情况不存在。
         * 作者：力扣官方题解
         * 链接：https://leetcode.cn/problems/find-a-good-subset-of-the-matrix/solutions/2817195/zhao-dao-ju-zhen-zhong-de-hao-zi-ji-by-l-qldc/
         * 来源：力扣（LeetCode）
         * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
         *
         *
         * 那么这一列的其他两行必须是0才满足条件。所以，当答案有四行的情况下，需要满足任选两行这一列都是1同时其他两行必须是0至少需要 C42=6
         * 这里是这样理解的 C42=6表示从好子集中选两行选两行的方式，因为任意两行必定存在某一列都是1，
         * 如果这一列和其他的重复了，那么和就大于2，不满足好子集的定义，所以这两行满足列的情况是唯一的。那么一共可以满足六种列，如果少了一列，
         * 那么就有一列是重复的，那么和就大于2，不满足好子集的定义，所以这六种列是唯一的。
         */

        List<Integer> ans = new ArrayList<>();
        Map<Integer, Integer> row2RowNum = new HashMap<>();
        for (int row = 0; row < grid.length; row++) {
            int rowNum = 0;
            for (int num : grid[row]) {
                rowNum = rowNum << 1 | num;
            }
            row2RowNum.put(rowNum, row);
        }

        Integer row = row2RowNum.get(0);
        if (row != null) {
            ans.add(row);
            return ans;
        }

        for (Map.Entry<Integer, Integer> e1 : row2RowNum.entrySet()) {
            for (Map.Entry<Integer, Integer> e2 : row2RowNum.entrySet()) {
                if ((e1.getKey() & e2.getKey()) == 0) {
                    if (e1.getValue() > e2.getValue()) {
                        ans.add(e2.getValue());
                        ans.add(e1.getValue());
                    } else {
                        ans.add(e1.getValue());
                        ans.add(e2.getValue());
                    }
                    return ans;
                }
            }
        }

        return ans;
    }


    /**
     * 示例 1：
     *
     * 输入：grid = [[0,1,1,0],[0,0,0,1],[1,1,1,1]]
     * 输出：[0,1]
     * 解释：我们可以选择第 0 和第 1 行构成一个好子集。
     * 选出来的子集大小为 2 。
     * - 第 0 列的和为 0 + 0 = 0 ，小于等于子集大小的一半。
     * - 第 1 列的和为 1 + 0 = 1 ，小于等于子集大小的一半。
     * - 第 2 列的和为 1 + 0 = 1 ，小于等于子集大小的一半。
     * - 第 3 列的和为 0 + 1 = 1 ，小于等于子集大小的一半。
     * 示例 2：
     *
     * 输入：grid = [[0]]
     * 输出：[0]
     * 解释：我们可以选择第 0 行构成一个好子集。
     * 选出来的子集大小为 1 。
     * - 第 0 列的和为 0 ，小于等于子集大小的一半。
     * 示例 3：
     *
     * 输入：grid = [[1,1,1],[1,1,1]]
     * 输出：[]
     * 解释：没有办法得到一个好子集。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatUtils.formatList(
                new _2732().goodSubsetofBinaryMatrix(
                        TestCaseUtils.get2DIntArr("[[1,1,1],[1,1,1]]")
                )
        ));
    }


}
