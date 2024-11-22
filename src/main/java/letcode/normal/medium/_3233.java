package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given 2 positive integers l and r. For any number x, all positive divisors of x except x are called the
 * proper divisors of x.  A number is called special if it has exactly 2 proper divisors. For example:
 * The number 4 is special because it has proper divisors 1 and 2. The number 6 is not special because it has proper
 * divisors 1, 2, and 3. Return the count of numbers in the range [l, r] that are not special.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-11-22 11:02
 */
public class _3233 {

    static List<Integer> primes = new ArrayList<>();

    static {
        int[] arr = new int[31623];
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] == 1) {
                continue;
            }
            for (int j = i << 1; j < arr.length; j += i) {
                arr[j] = 1;
            }
        }
        for (int i = 2; i < 31623; i++) {
            if (arr[i] == 0) {
                primes.add(i);
            }
        }
    }

    public int nonSpecialCount(int l, int r) {
        /*
        只有质数的平方才会是special number
        所以结果 等于 r - l + 1 - ([l..r]之间的质数平方)
        sqrt(l) <= 质数 <= sqrt(r)
        因为 r <= 10^9, 所以找出 [1...31622] 之内的质数即可 也就是打表
         */
        int primeSquareCount = 0;
        for (int prime : primes) {
            if (prime * prime > r) {
                break;
            }
            if (prime * prime >= l) {
                primeSquareCount++;
            }
        }
        return r - l + 1 - primeSquareCount;
    }


    /**
     * Example 1:
     *
     * Input: l = 5, r = 7
     *
     * Output: 3
     *
     * Explanation:
     *
     * There are no special numbers in the range [5, 7].
     *
     * Example 2:
     *
     * Input: l = 4, r = 16
     *
     * Output: 11
     *
     * Explanation:
     *
     * The special numbers in the range [4, 16] are 4 and 9.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(_3233.class);
    }

}
