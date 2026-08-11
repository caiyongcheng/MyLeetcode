package letcode.normal.difficult;

/**
 * 3348. Smallest Divisible Digit Product II
 * Difficulty: Hard
 * Link: https://leetcode.cn/problems/smallest-divisible-digit-product-ii/
 * <p>
 * You are given a string num which represents a positive integer, and an integer t .
 * <p>
 * A number is called zero-free if none of its digits are 0.
 * <p>
 * Return a string representing the smallest zero-free number greater than or equal to num such that
 * the product of its digits is divisible by t . If no such number exists, return "-1" .
 * <p>
 * Example 1:
 * <p>
 * Input: num = "1234", t = 256
 * <p>
 * Output: "1488"
 * <p>
 * Explanation:
 * <p>
 * The smallest zero-free number that is greater than 1234 and has the product of its digits divisible
 * by 256 is 1488, with the product of its digits equal to 256.
 * <p>
 * Example 2:
 * <p>
 * Input: num = "12355", t = 50
 * <p>
 * Output: "12355"
 * <p>
 * Explanation:
 * <p>
 * 12355 is already zero-free and has the product of its digits divisible by 50, with the product of
 * its digits equal to 150.
 * <p>
 * Example 3:
 * <p>
 * Input: num = "11111", t = 26
 * <p>
 * Output: "-1"
 * <p>
 * Explanation:
 * <p>
 * No number greater than 11111 has the product of its digits divisible by 26.
 * <p>
 * Constraints:
 * <p>
 * - 2 <= num.length <= 2 * 10 5
 * <p>
 * - num consists only of digits in the range ['0', '9'] .
 * <p>
 * - num does not contain leading zeros.
 * <p>
 * - 1 <= t <= 10 14
 */
public class _3348 {

    public String smallestNumber(String num, long t) {
        /*
        根据题意。设满足条件的数为s, 那么s=x0x1...xi..xn, 0<=xi<=9
        因为 x0 * x1 .... xi * ... xn = t, 那么就要求t的质因素只能小于11，否则与假设矛盾。
        那么如果t的质因数大于7，可以直接返回-1。
        如果 s包含了所有t的质因素p，那么s就是答案。
        否则的话，首先要将t的质因数的长度补到s的长度，如果s是99999这样的，那么要补充到s+1长度。
        接下来要确定补充哪些字符，去差异长度的高位，找到num对应的开头放进去，如果不满足的话，就把开头的最后一位换成更大的数
         */

        // 计算t的质因数
        int[] primeFactories = new int[]{2, 3, 5, 7};
        int[] primeFactoryCnt = new int[10];
        for (int primeFactory : primeFactories) {
            while (t % primeFactory == 0) {
                t /= primeFactory;
                primeFactoryCnt[primeFactory]++;
            }
        }

        // 包含大于7的质因数
        if (t != 1) {
            return "-1";
        }

        // 计算num数字包含的质数
        int[] existPrimeFactoryCnt = new int[10];
        int[] numDigitCnt = new int[10];
        char[] charArray = num.toCharArray();
        for (char ch : charArray) {
            int n = ch - '0';
            if (ch == 2 || ch == 3 || ch == 5 || ch == 7) {
                existPrimeFactoryCnt[ch]++;
            } else if (ch == 4) {
                existPrimeFactoryCnt[2] += 2;
            } else if (ch == 6) {
                existPrimeFactoryCnt[2]++;
                existPrimeFactoryCnt[3]++;
            } else if (ch == 8) {
                existPrimeFactoryCnt[2] += 3;
            } else if (ch == 9) {
                existPrimeFactoryCnt[3] += 2;
            }
        }

        // 找到第一个不含0的，且包含t的质因数， 大于等于nums的数


        return null;
    }
}
