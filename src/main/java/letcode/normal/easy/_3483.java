package letcode.normal.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * 3483. Unique 3-Digit Even Numbers
 * Difficulty: Easy
 * Link: https://leetcode.cn/problems/unique-3-digit-even-numbers/
 * <p>
 * You are given an array of digits called digits . Your task is to determine the number of distinct
 * three-digit even numbers that can be formed using these digits.
 * <p>
 * Note : Each copy of a digit can only be used once per number , and there may not be leading zeros.
 * <p>
 * Example 1:
 * <p>
 * Input: digits = [1,2,3,4]
 * <p>
 * Output: 12
 * <p>
 * Explanation: The 12 distinct 3-digit even numbers that can be formed are 124, 132, 134, 142, 214,
 * 234, 312, 314, 324, 342, 412, and 432. Note that 222 cannot be formed because there is only 1 copy
 * of the digit 2.
 * <p>
 * Example 2:
 * <p>
 * Input: digits = [0,2,2]
 * <p>
 * Output: 2
 * <p>
 * Explanation: The only 3-digit even numbers that can be formed are 202 and 220. Note that the digit 2
 * can be used twice because it appears twice in the array.
 * <p>
 * Example 3:
 * <p>
 * Input: digits = [6,6,6]
 * <p>
 * Output: 1
 * <p>
 * Explanation: Only 666 can be formed.
 * <p>
 * Example 4:
 * <p>
 * Input: digits = [1,3,5]
 * <p>
 * Output: 0
 * <p>
 * Explanation: No even 3-digit numbers can be formed.
 * <p>
 * Constraints:
 * <p>
 * - 3 <= digits.length <= 10
 * <p>
 * - 0 <= digits[i] <= 9
 */
public class _3483 {

    public int totalNumbers(int[] digits) {
        int ans = 0;
        return dfs(digits, 0, 1000, new HashSet<>(), new boolean[digits.length]);
    }

    private int dfs(int[] digits, int curNum, int limit, Set<Integer> set, boolean[] visited) {
        if (curNum >= limit / 10 && curNum < limit) {
            return (curNum & 1) == 0 && set.add(curNum) ? 1 : 0;
        }

        int cnt = 0;
        for (int i = 0; i < digits.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                cnt += dfs(digits, curNum * 10 + digits[i], limit, set, visited);
                visited[i] = false;
            }
        }
        return cnt;
    }
}
