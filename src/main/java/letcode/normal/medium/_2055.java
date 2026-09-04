package letcode.normal.medium;

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
        int n = s.length();
        int[] prefixSum = new int[n + 1];
        int[] prevCandle = new int[n];
        int[] nextCandle = new int[n];

        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + (s.charAt(i) == '*' ? 1 : 0);
        }

        int lastCandle = -1;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '|') {
                lastCandle = i;
            }
            prevCandle[i] = lastCandle;
        }

        int firstCandle = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '|') {
                firstCandle = i;
            }
            nextCandle[i] = firstCandle;
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int leftCandle = nextCandle[queries[i][0]];
            int rightCandle = prevCandle[queries[i][1]];

            if (leftCandle == -1 || rightCandle == -1 || leftCandle >= rightCandle) {
                continue;
            }

            // 统计两根边界蜡烛之间的盘子，不包含蜡烛本身。
            ans[i] = prefixSum[rightCandle] - prefixSum[leftCandle + 1];
        }
        return ans;
    }
}
