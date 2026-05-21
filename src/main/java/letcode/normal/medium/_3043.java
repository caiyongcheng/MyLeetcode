package letcode.normal.medium;

import letcode.utils.SolutionTestMethod;

import java.util.HashSet;
import java.util.Set;

/**
 * You are given two arrays with positive integers arr1 and arr2.
 * A prefix of a positive integer is an integer formed by one or more of its digits,
 * starting from its leftmost digit. For example,
 * 123 is a prefix of the integer 12345, while 234 is not.
 * A common prefix of two integers a and b is an integer c,
 * such that c is a prefix of both a and b. For example,
 * 5655359 and 56554 have common prefixes 565 and 5655 while 1223 and 43456 do not have a common prefix.
 * You need to find the length of the longest common prefix between all pairs of integers (x, y) such that x belongs to
 * arr1 and y belongs to arr2.  Return the length of the longest common prefix among all pairs.
 * If no common prefix exists among them, return 0.
 *
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2026-05-21 22:55
 */
@SolutionTestMethod(method = "longestCommonPrefix")
public class _3043 {

    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Set<Integer> prefixes = new HashSet<>();
        // 收集 arr1 中每个正整数的全部数字前缀
        for (int num : arr1) {
            for (int cur = num; cur > 0; cur /= 10) {
                prefixes.add(cur);
            }
        }
        int ans = 0;
        // 枚举 arr2 的前缀，与 arr1 前缀集合求交并取最大位数
        for (int num : arr2) {
            for (int cur = num; cur > 0; cur /= 10) {
                if (prefixes.contains(cur)) {
                    ans = Math.max(ans, digitLen(cur));
                }
            }
        }
        return ans;
    }

    private static int digitLen(int num) {
        int len = 0;
        for (int cur = num; cur > 0; cur /= 10) {
            len++;
        }
        return len;
    }


}
