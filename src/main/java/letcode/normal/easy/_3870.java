package letcode.normal.easy;

/**
 * 3870. Count Commas in Range
 * Difficulty: Easy
 * Link: https://leetcode.cn/problems/count-commas-in-range/
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
 * All numbers from 1 to 998 have fewer than four digits. Therefore, no commas are used.
 * <p>
 * Constraints:
 * <p>
 * - 1 <= n <= 10 5
 */
public class _3870 {

    public int countCommas(int n) {
        return n < 1000 ? 0 : n - 999;
    }
}
