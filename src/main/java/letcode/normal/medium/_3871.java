package letcode.normal.medium;

/**
 * 3871. Count Commas in Range II
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/count-commas-in-range-ii/
 *
 * You are given an integer n .
 *
 * Return the total number of commas used when writing all integers from [1, n] (inclusive) in standard
 * number formatting.
 *
 * In standard formatting:
 *
 * - A comma is inserted after every three digits from the right.
 *
 * - Numbers with fewer than 4 digits contain no commas.
 *
 * Example 1:
 *
 * Input: n = 1002
 *
 * Output: 3
 *
 * Explanation:
 *
 * The numbers "1,000" , "1,001" , and "1,002" each contain one comma, giving a total of 3.
 *
 * Example 2:
 *
 * Input: n = 998
 *
 * Output: 0
 *
 * Explanation:
 *
 * ​​​​​​​ All numbers from 1 to 998 have fewer than four digits. Therefore, no commas are used.
 *
 * Constraints:
 *
 * - 1 <= n <= 10 15
 */
public class _3871 {

    public long countCommas(long n) {
        /*
        按数字区间计算贡献
        long base = 1000;
        long baseCommasCnt = 1;
        long ans = 0;
        while (n >= base) {
            ans += (Math.min(base * 1000 - 1, n) - base + 1) * baseCommasCnt;
            base *= 1000;
            baseCommasCnt++;
        }
        return ans;
         */

        // 按每一位的逗号计算贡献
        long ans = 0;
        for (long base = 1000; n >= base; base *= 1000) {
            ans += n - base + 1;
        }
        return ans;

    }

}
