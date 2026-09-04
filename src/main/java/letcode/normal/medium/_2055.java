package letcode.normal.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 2055. Plates Between Candles
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/plates-between-candles/
 * <p>
 * There is a long table with a line of plates and candles arranged on top of it. You are given a
 * 0-indexed string s consisting of characters '*' and '|' only, where a '*' represents a plate and a
 * '|' represents a candle .
 * <p>
 * You are also given a 0-indexed 2D integer array queries where queries[i] = [left i , right i ]
 * denotes the substring s[left i ...right i ] ( inclusive ). For each query, you need to find the
 * number of plates between candles that are in the substring . A plate is considered between candles
 * if there is at least one candle to its left and at least one candle to its right in the substring .
 * <p>
 * - For example, s = "||**||**|*" , and a query [3, 8] denotes the substring "*|| ** |" . The number
 * of plates between candles in this substring is 2 , as each of the two plates has at least one candle
 * in the substring to its left and right.
 * <p>
 * Return an integer array answer where answer[i] is the answer to the i th query .
 * <p>
 * Example 1:
 * <p>
 * Input: s = "**|**|***|", queries = [[2,5],[5,9]]
 * Output: [2,3]
 * Explanation:
 * - queries[0] has two plates between candles.
 * - queries[1] has three plates between candles.
 * <p>
 * Example 2:
 * <p>
 * Input: s = "***|**|*****|**||**|*", queries = [[1,17],[4,5],[14,17],[5,11],[15,16]]
 * Output: [9,0,0,0,0]
 * Explanation:
 * - queries[0] has nine plates between candles.
 * - The other queries have zero plates between candles.
 * <p>
 * Constraints:
 * <p>
 * - 3 <= s.length <= 10 5
 * <p>
 * - s consists of '*' and '|' characters.
 * <p>
 * - 1 <= queries.length <= 10 5
 * <p>
 * - queries[i].length == 2
 * <p>
 * - 0 <= left i <= right i < s.length
 */
public class _2055 {

    public int[] platesBetweenCandles(String s, int[][] queries) {

        /*
        用1表示蜡烛 0表示盘子 那么查询子串会落在如下的范围内
        (0{0,+} 1{0,+}){0,1} queryLeft (1+0{0,}1+){0, +} queryRight (0{0,+} 1{0,+}){0,1}
        对应的结果是 queryLeft 之间 queryRight 这部分的值
        也就是第一个大于等于queryLeft到第一个小于等于queryRight区间的盘子数
        二分检索 + 前缀和即可
        二分检索可以优化 可以预先计算出每个点左右挨着的盘子是哪个
         */

        char[] charArray = s.toCharArray();
        int[][] adjacent = new int[charArray.length][2];

        int lastCandleNo = 0;
        int lastCandleIndex = -1;
        List<Integer> candlesPreSum = new ArrayList<>();
        candlesPreSum.add(0);

        for (int i = 0; i < adjacent.length; i++) {
            if (charArray[i] == '|') {
                ++lastCandleNo;
                candlesPreSum.add(i - lastCandleIndex - 1 + candlesPreSum.get(lastCandleNo - 1));
                while (lastCandleIndex + 1 < i) {
                    adjacent[lastCandleIndex + 1][1] = lastCandleNo;
                    ++lastCandleIndex;
                }
                adjacent[i][1] = lastCandleNo;
                lastCandleIndex = i;
            }
            adjacent[i][0] = lastCandleNo;
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int leftCandleNo = adjacent[queries[i][0]][1];
            int rightCandleNo = adjacent[queries[i][1]][0];

            // 查询区间内必须存在两根有效蜡烛，且左边界不能位于最后一根蜡烛之后。
            if (leftCandleNo == 0 || rightCandleNo <= leftCandleNo) {
                continue;
            }
            ans[i] = candlesPreSum.get(rightCandleNo) - candlesPreSum.get(leftCandleNo);
        }
        return ans;



    }

}
