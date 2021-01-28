package letcode.medium;

/**
 * @program: StudyHTTP
 * @description: 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * @author: 蔡永程
 * @create: 2020-06-18 14:30
 */
public class _5Five {

    /**
     * 示例 1：
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     *
     * 示例 2：
     * 输入: "cbbd"
     * 输出: "bb"
     * @param s
     * @return
     *
     * abcdefg
     * gfedcba
     *
     * abacbd
     * dbcaba
     *
     * dabab
     *
     */


    /**
     * 动态规划法
     *
     * @param s
     * @return
     */
    public static String longestPalindromeOne(String s) {
        if (s.length() == 0) {
            return "";
        }
        char[] chars = s.toCharArray();
        int length = 0;
        int i = 0;
        int j = 0;
        int x = 0, y = 0;
        int res = Integer.MIN_VALUE;
        int n = s.length();
        boolean[][] cache = new boolean[n][n];
        for (; length < n; ++length) {
            for (i = 0; i + length < n; ++i) {
                j = i + length;
                if (length == 0) cache[i][j] = true;
                else if (length == 1) cache[i][j] = chars[i] == chars[j];
                else cache[i][j] = cache[i + 1][j - 1] && chars[i] == chars[j];
                if (cache[i][j] && length + 1 > res) {
                    res = length + 1;
                    x = i;
                    y = j;
                }
            }
        }
        return s.substring(x, y + 1);
    }

    /**
     * 中心扩展法
     *
     * @param s
     * @return
     */
    public static String longestPalindromeTwo(String s) {
        if (s.length() == 0) {
            return "";
        }
        char[] chars = s.toCharArray();
        int length = 0;
        int i = 0;
        int x = 0, y = 0;
        int res1 = Integer.MIN_VALUE;
        int res2 = Integer.MIN_VALUE;
        int res = 1;
        int left = 0, right = 0;
        int n = s.length();
        for (i = 0; i < n; ++i) {
            left = i - 1;
            right = i + 1;
            res1 = 1;
            while (left > -1 && right < n && chars[left] == chars[right]) {
                left--;
                right++;
                res1 += 2;
            }
            if (res1 > res) {
                x = left + 1;
                y = right - 1;
                res = res1;
            }
            left = i;
            right = i + 1;
            res2 = 0;
            while (left > -1 && right < n && chars[left] == chars[right]) {
                left--;
                right++;
                res2 += 2;
            }
            if (res2 > res) {
                x = left + 1;
                y = right - 1;
                res = res2;
            }
        }
        return s.substring(x, y + 1);
    }


    public static void main(String[] args) {
        System.out.println(longestPalindromeOne("d"));
        System.out.println(longestPalindromeTwo("d"));
    }


}