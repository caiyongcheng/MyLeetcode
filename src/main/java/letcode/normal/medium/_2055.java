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
         */

        // 保存蜡烛坐标以及当前蜡烛到上一个蜡烛之间的盘子数量
        List<int[]> candle2Candies = new ArrayList<>();
        char[] charArray = s.toCharArray();
        int[] ans = new int[queries.length];

        // 跳过左边没有蜡烛的部分
        int startIdx = 0;
        while (startIdx < charArray.length && charArray[startIdx] != '|') {
            startIdx++;
        }

        // 没有根蜡烛 无法满足条件
        if (startIdx >= charArray.length) {
            return ans;
        }

        candle2Candies.add(new int[]{startIdx, 0});
        for (int idx = startIdx + 1; idx < charArray.length; ++idx) {
            if (charArray[idx] == '|') {
                candle2Candies.add(new int[]{idx, idx - startIdx - 1});
                startIdx = idx;
            }
        }

        // 只有一根蜡烛 无法满足条件
        if (candle2Candies.size() == 1) {
            return ans;
        }

        // 计算前缀和
        candle2Candies.add(new int[]{charArray.length, 0});
        int[] candiesPreSum = new int[candle2Candies.size()];
        for (int i = 1; i < candle2Candies.size(); i++) {
            candiesPreSum[i] = candle2Candies.get(i)[1] + candiesPreSum[i - 1];
        }

        for (int i = 0; i < queries.length; i++) {
            int left = binarySearchCeil(queries[i][0], candle2Candies);
            // 右端点包含在查询范围内，因此查找第一个大于右端点的蜡烛。
            int right = binarySearchCeil(queries[i][1] + 1, candle2Candies) - 1;
            if (right <= left) {
                ans[i] = 0;
            } else {
                ans[i] = candiesPreSum[right] - candiesPreSum[left];
            }

        }
        return ans;
    }


    private int binarySearchCeil(int target, List<int[]> candle2Candies) {
        int left = 0;
        int right = candle2Candies.size() - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (candle2Candies.get(mid)[0] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
