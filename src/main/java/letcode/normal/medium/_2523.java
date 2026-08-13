package letcode.normal.medium;

import letcode.utils.SolutionTestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * 2523. Closest Prime Numbers in Range
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/closest-prime-numbers-in-range/
 * <p>
 * Given two positive integers left and right , find the two integers num1 and num2 such that:
 * <p>
 * - left <= num1 < num2 <= right .
 * <p>
 * - Both num1 and num2 are prime numbers .
 * <p>
 * - num2 - num1 is the minimum amongst all other pairs satisfying the above conditions.
 * <p>
 * Return the positive integer array ans = [num1, num2] . If there are multiple pairs satisfying these
 * conditions, return the one with the smallest num1 value. If no such numbers exist, return [-1, -1] .
 * <p>
 * Example 1:
 * <p>
 * Input: left = 10, right = 19
 * Output: [11,13]
 * Explanation: The prime numbers between 10 and 19 are 11, 13, 17, and 19.
 * The closest gap between any pair is 2, which can be achieved by [11,13] or [17,19].
 * Since 11 is smaller than 17, we return the first pair.
 * <p>
 * Example 2:
 * <p>
 * Input: left = 4, right = 6
 * Output: [-1,-1]
 * Explanation: There exists only one prime number in the given range, so the conditions cannot be
 * satisfied.
 * <p>
 * Constraints:
 * <p>
 * - 1 <= left <= right <= 10 6
 */

public class _2523 {

    public static final List<Integer> primeList = new ArrayList<>();

    static {

        int[] screening = new int[1_000_000 + 1];

        for (int i = 4; i <= screening.length; i += 2) {
            screening[i] = 1;
        }

        primeList.add(2);
        for (int i = 3; i < screening.length; i += 2) {
            if (screening[i] == 1) {
                continue;
            }
            primeList.add(i);
            for (int j = 2; i * j < screening.length; ++j) {
                screening[i * j] = 1;
            }
        }


    }

    @SolutionTestMethod
    public int[] closestPrimes(int left, int right) {

        int[] ans = new int[]{-1, -1};

        int i = binarySearch(left);
        if (i == -1 || i >= primeList.size() - 1 || primeList.get(i + 1) > right) {
            return ans;
        }
        ans[0] = primeList.get(i);
        ans[1] = primeList.get(++i);

        int curPrime = ans[1];
        int nextPrime;
        for (++i; i < primeList.size(); ++i) {
            nextPrime = primeList.get(i);
            if (nextPrime > right) {
                break;
            }
            if (nextPrime - curPrime < (ans[1] - ans[0])) {
                ans[0] = curPrime;
                ans[1] = nextPrime;
            }
            if (ans[1] - ans[0] < 3) {
                break;
            }
            curPrime = nextPrime;
        }

        return ans;
    }


    public int binarySearch(int target) {
        int l = 0;
        if (primeList.get(l) >= target) {
            return l;
        }
        int r = primeList.size() - 1;
        if (target > primeList.get(r)) {
            return -1;
        }

        int m;
        while (true) {
            m = (l + r) >> 1;
            if (l == m) {
                break;
            }
            if (primeList.get(m) >= target) {
                r = m;
            } else {
                l = m;
            }
        }
        return r;
    }



}
