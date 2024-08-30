package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s, you need to partition it into one or more balanced  substrings .
 * For example, if s == "ababcc" then ("abab", "c", "c"), ("ab", "abc", "c"), and ("ababcc") are all valid partitions,
 * but ("a", "bab", "cc"), ("aba", "bc", "c"), and ("ab", "abcc") are not. The unbalanced substrings are bolded.
 * Return the minimum number of substrings that you can partition s into.
 * Note: A balanced string is a string where each character in the string occurs the same number of times.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-08-28 09:53
 */
public class _3144 {


    public int minimumSubstringsInPartition(String s) {
        /*
        使用类似归并排序的方法做处理吧
        发现可以改写为使用递归
        dp[i][j]表示从i到j的最小分割次数 那么dp[i][j] = dp[i][k] + dp[k+1][j]

        class Solution {
    static final int INF = 0x3f3f3f3f;

    public int minimumSubstringsInPartition(String s) {
        int n = s.length();
        int[] d = new int[n + 1];
        Arrays.fill(d, INF);
        d[0] = 0;
        for (int i = 1; i <= n; i++) {
            Map<Character, Integer> occCnt = new HashMap<Character, Integer>();
            int maxCnt = 0;
            for (int j = i; j >= 1; j--) {
                occCnt.put(s.charAt(j - 1), occCnt.getOrDefault(s.charAt(j - 1), 0) + 1);
                maxCnt = Math.max(maxCnt, occCnt.get(s.charAt(j - 1)));
                if (maxCnt * occCnt.size() == (i - j + 1) && d[j - 1] != INF) {
                    d[i] = Math.min(d[i], d[j - 1] + 1);
                }
            }
        }
        return d[n];
    }
}

         */
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int[] arr : dp) {
            Arrays.fill(arr, 1001);
        }
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = 1;
        }

        int k;
        int j;
        Map<Character, Integer> map;
        int count = 0;
        for (int sub = 1; sub < len; sub++) {
            map = new HashMap<>(26);
            j = sub;
            for (int i = 0; i <= j; i++) {
                count = map.getOrDefault(s.charAt(i), 0) + 1;
                map.put(s.charAt(i), count);
            }
            for (int i = 0; j < len; i++, j++) {
                if (count * map.size() != j - i + 1) {
                    for (k = i; k < j; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                    }
                } else {
                    dp[i][j] = 1;
                }

                count = map.get(s.charAt(i)) - 1;
                if (count == 0) {
                    map.remove(s.charAt(i));
                } else {
                    map.put(s.charAt(i), count);
                }

                if (j + 1 < dp.length) {
                    count = map.getOrDefault(s.charAt(j + 1), 0) + 1;
                    map.put(s.charAt(j + 1), count);
                }
            }
        }
        return dp[0][len - 1];
    }

    /**
     * Example 1:
     *
     * Input: s = "fabccddg"
     *
     * Output: 3
     *
     * Explanation:
     *
     * We can partition the string s into 3 substrings in one of the following ways: ("fab, "ccdd", "g"), or ("fabc", "cd", "dg").
     *
     * Example 2:
     *
     * Input: s = "abababaccddb"
     *
     * Output: 2
     *
     * Explanation:
     *
     * We can partition the string s into 2 substrings like so: ("abab", "abaccddb").
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(_3144.class);
    }





}
