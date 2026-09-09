package letcode.normal.medium;

/**
 * 3871. Count Commas in Range II
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/count-commas-in-range-ii/
 * <p>
 * You are given an integer n .
 * <p>
 * Return the total number of commas used when writing all integers from [1, n] (inclusive) in standard
 * number formatting.
 * <p>
 * In standard formatting:
 * <p>
 * - A comma is inserted after every three digits from the right.
 * <p>
 * - Numbers with fewer than 4 digits contain no commas.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 1002
 * <p>
 * Output: 3
 * <p>
 * Explanation:
 * <p>
 * The numbers "1,000" , "1,001" , and "1,002" each contain one comma, giving a total of 3.
 * <p>
 * Example 2:
 * <p>
 * Input: n = 998
 * <p>
 * Output: 0
 * <p>
 * Explanation:
 * <p>
 * ​​​​​​​ All numbers from 1 to 998 have fewer than four digits. Therefore, no commas are used.
 * <p>
 * Constraints:
 * <p>
 * - 1 <= n <= 10 15
 */
public class _3871 {

    public long countCommas(long n) {
        long base = 1000;
        long baseCommasCnt = 1;
        long ans = 0;
        while (n >= base) {
            ans += (Math.min(base * 1000 - 1, n) - base + 1) * baseCommasCnt;
            base *= 1000;
            baseCommasCnt++;
        }
        return ans;
    }

}
